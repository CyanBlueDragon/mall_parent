package com.yunyihenkey.auth.service.util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.yunyihenkey.basedao.malldb.basemapper.SellerUserBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yunyihenkey.auth.service.enums.ReqSourceEnum;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import com.yunyihenkey.basedao.malldb.basemapper.SellerUserTokenBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import com.yunyihenkey.basedao.malldb.basevo.SellerUserToken;
import com.yunyihenkey.basedao.malldb.basevoEnum.SellerUser.UserTypeEnum;
import com.yunyihenkey.common.constant.JwtConstants;
import com.yunyihenkey.common.constant.RedisConstant;
import com.yunyihenkey.common.utils.BeanMapUtils;
import com.yunyihenkey.common.utils.CertUtil;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.utils.MallUtils;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerPermMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerUserMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	@Autowired(required = false)
	private AuthSellerPermMapper authSellerPermMapper;
	@Autowired(required = false)
	private AuthSellerUserMapper authSellerUserMapper;
	@Autowired
	private SellerUserTokenBaseMapper sellerUserTokenBaseMapper;

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SellerUserBaseMapper sellerUserBaseMapper;
	/**
	 * 公钥
	 */
	private PublicKey publicKey;
	/**
	 * 私钥
	 */
	private PrivateKey privateKey;
	private @Value("${jwt.rsa.privateKey}") String privateKeyUrl;
	private @Value("${jwt.rsa.publicKey}") String publicKeyUrl;

	@PostConstruct
	private void init() {

		try {
			privateKey = (PrivateKey) CertUtil.getKey(privateKeyUrl, CertUtil.CERTYPE_PFX, "yunyihenkey", true);
		} catch (Exception e) {
			LogUtils.getLogger().error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!私钥加载异常!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", e);
		}
		try {
			publicKey = CertUtil.getPublicKey(publicKeyUrl);
		} catch (Exception e) {
			LogUtils.getLogger().error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!公钥加载异常!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", e);
		}
	}

	/**
	 * 
	 * @desc 从请求中获取用户信息，用于测试
	 * @auth wulm
	 * @date 2018年5月8日 下午6:45:23
	 * @param request
	 * @return
	 */
	public AuthSellerUser getSellerUser(HttpServletRequest request) {
		AuthSellerUser authSellerUser = (AuthSellerUser) request.getAttribute(JwtConstants.JWT_USER_INFO);
		return authSellerUser;
	}

	public AuthSellerUser getSellerUser(String jwtToken) {
		Jws<Claims> j = parseJwtStr(jwtToken);
		Claims body = j.getBody();
		if (!Integer.toString(SystemCodeEnum.SELLER.getValue())
				.equals(MallUtils.defaultString(body.get(JwtConstants.JWT_SYSTEM_CODE), null))) {
			throw new IllegalArgumentException("错误的系统编码！！！！");
		}

		// 将map值复制到对象中
		AuthSellerUser authSellerUser = BeanMapUtils
				.mapToBean((Map<String, Object>) body.get(JwtConstants.JWT_USER_INFO), new AuthSellerUser());

		return authSellerUser;
	}

	/**
	 * @param tokenId
	 *            token的id
	 * @return true：命中黑名单。false：未命中黑名单
	 * @desc 检查token是否存在redis黑名单中
	 */
	public boolean isInBlacklist(String systemcode, String tokenId) {
		return redisUtil.hasKey(RedisConstant.getMallJwtBlackKey(systemcode, tokenId));
	}

	/**
	 * @param jwtStr
	 *            jwt字符串
	 * @return jwt无效、过期则返回null
	 * @desc 验证并解析jwt
	 */
	public Jws<Claims> parseJwtStr(String jwtStr) {
		Jws<Claims> claims;
		try {
			claims = Jwts.parser().setSigningKey(privateKey).parseClaimsJws(jwtStr);
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * 生成JWT token
	 * @param userName
	 * @param systemCodeEnum
	 * @param loginSourceEnum
	 * @return
	 */
	public String cretaJwt(String userName, SystemCodeEnum systemCodeEnum, ReqSourceEnum loginSourceEnum) {

		// 账号验证通过后发放jwt
		String jwtId = UUID.randomUUID().toString().replace("-", "");
		// 当前时间戳：秒
		long curTimeSeconds = System.currentTimeMillis() / 1000;

		Map<String, Object> map = new HashMap<>();
		map.put(Claims.ISSUER, "yunyihenkey");// 签发人
		// map.put(Claims.SUBJECT, "");//
		// map.put(Claims.AUDIENCE, "sdji21safio234oi12i1o2j3");//
		map.put(Claims.NOT_BEFORE, curTimeSeconds - JwtConstants.MAX_DIFFER);// 生效当前时间，减5分钟，防止集群环境各个机器时间不同步
		map.put(Claims.EXPIRATION, ReqSourceEnum.WEB == loginSourceEnum ? curTimeSeconds + JwtConstants.EXPIRATION_WEB
				: curTimeSeconds + JwtConstants.EXPIRATION_OTHERS);// jwt过期时间(单位秒)。web端1天后过期；其他端7天后过期
		map.put(Claims.ISSUED_AT, curTimeSeconds);// 签发时间
		map.put(Claims.ID, jwtId);// jwt的唯一身份标识 存放用户ID

		map.put(JwtConstants.JWT_SYSTEM_CODE, systemCodeEnum.getValue());// 系统编码
		map.put(JwtConstants.JWT_LOGIN_SOURCE, loginSourceEnum.getValue());// 登录来源

		Date curTime = new Date();
		// 查询url权限并授权
		switch (systemCodeEnum) {
		case SELLER:
			SellerUser sellerUser = authSellerUserMapper.selectByUserName(userName);
			if (sellerUser == null || sellerUser.getId() == null) {
				throw new IllegalArgumentException(LogUtils.getString("SELLER 分销系统，用户未查询到！！！userName=", userName));
			}

			List<SellerPerm> sellerPerms = null;
			// 查询url权限，只有员工才需要存放权限
			if (UserTypeEnum.STAFF == UserTypeEnum.getByValue(sellerUser.getUserType())) {
				sellerPerms = authSellerPermMapper.getPermByUserId(sellerUser.getId());
			}
			// 存放权限
			map.put(JwtConstants.JWT_USER_PERMISSION, sellerPerms);

			// 存放用户信息到jwt
			map.put(JwtConstants.JWT_USER_INFO,
					new AuthSellerUser(MallUtils.defaultString(sellerUser.getId(), null),
							MallUtils.defaultString(sellerUser.getShopId(), null),
							MallUtils.defaultString(sellerUser.getUserName(), null),
							MallUtils.defaultString(sellerUser.getUserType(), null),
							MallUtils.defaultString(sellerUser.getParentUserId(), null),
							MallUtils.defaultString(sellerUser.getSellerGrade(), null)));

			// 记录jwtId到数据库，作用于查询用户以后注销、或者修改密码
			SellerUserToken SellerUserToken = new SellerUserToken();
			SellerUserToken.setUserId(sellerUser.getId());
			SellerUserToken.setTokenId(jwtId);
			SellerUserToken.setLoginSource(loginSourceEnum.getValue());
			SellerUserToken.setCreateTime(curTime);
			SellerUserToken.setUpdateTime(curTime);
			sellerUserTokenBaseMapper.insert(SellerUserToken);
			//记录登录时间
			SellerUser sellerUserUp= new SellerUser();
			sellerUserUp.setLoginTime(new Date());
			sellerUserUp.setId(sellerUser.getId());
			sellerUserBaseMapper.updateByPrimaryKeySelective(sellerUserUp);

			break;
		default:
			throw new IllegalArgumentException("其他平台正在开发");
			// break;
		}

		// 创建jwt
		return Jwts.builder().setClaims(map).signWith(SignatureAlgorithm.RS256, privateKey)
				.compressWith(CompressionCodecs.GZIP).compact();
	}

	/**
	 * @return
	 * @desc 获取私钥
	 * @auth wulm
	 * @date 2018年4月28日 下午6:36:39
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * @return
	 * @desc 获取公钥
	 * @auth wulm
	 * @date 2018年4月28日 下午6:36:53
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	public String refreshToken(Claims bodyClaims) {
		// 当前时间戳
		long curTime = System.currentTimeMillis();

		if (ReqSourceEnum.WEB.getValue().equals(bodyClaims.get(JwtConstants.JWT_LOGIN_SOURCE).toString())) {
			// web端1天后过期
			bodyClaims.setExpiration(new Date(curTime + (JwtConstants.EXPIRATION_WEB * 1000)));
		} else {
			// 其他端7天后过期
			bodyClaims.setExpiration(new Date(curTime + (JwtConstants.EXPIRATION_OTHERS * 1000)));
		}
		// 更新签发时间
		bodyClaims.setIssuedAt(new Date());

		return Jwts.builder().setClaims(bodyClaims).signWith(SignatureAlgorithm.RS256, privateKey)
				.compressWith(CompressionCodecs.GZIP).compact();

	}

}
