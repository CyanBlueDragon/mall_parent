package com.yunyihenkey.auth.service.util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.yunyihenkey.basedao.malldb.basemapper.MallSysSellerUserBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yunyihenkey.auth.service.constant.JwtConstants;
import com.yunyihenkey.auth.service.enums.LoginSourceEnum;
import com.yunyihenkey.auth.service.vo.authjwt.GetTokenParam;
import com.yunyihenkey.common.utils.CertUtil;
import com.yunyihenkey.common.utils.LogUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

	/** 公钥 */
	private PublicKey publicKey;
	/** 私钥 */
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
	 * @desc 获取私钥
	 * @auth wulm
	 * @date 2018年4月28日 下午6:36:39
	 * @return
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * 
	 * @desc 获取公钥
	 * @auth wulm
	 * @date 2018年4月28日 下午6:36:53
	 * @return
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * 
	 * @desc 验证并解析jwt
	 * @param jwtStr
	 *            jwt字符串
	 * @return jwt无效、过期则返回null
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
	 * 
	 * @param getTokenParam
	 * @return
	 */
	public String cretaJwt(GetTokenParam getTokenParam) {

		// 账号验证通过后发放jwt
		String jwtId = UUID.randomUUID().toString().replace("-", "");
		// 当前时间戳：秒
		long curTimeSeconds = System.currentTimeMillis() / 1000;

		Map<String, Object> map = new HashMap<>();
		map.put(Claims.ISSUER, "yunyihenkey");// 签发人
		// map.put(Claims.SUBJECT, "");//
		// map.put(Claims.AUDIENCE, "sdji21safio234oi12i1o2j3");//
		map.put(Claims.NOT_BEFORE, curTimeSeconds);// 生效当前时间
		map.put(Claims.EXPIRATION, LoginSourceEnum.Web == getTokenParam.getLoginSourceEnum() ? curTimeSeconds + 86400
				: curTimeSeconds + 604800);// jwt过期时间(单位秒)。web端1天后过期；其他端7天后过期
		map.put(Claims.ISSUED_AT, curTimeSeconds);// 签发时间
		map.put(Claims.ID, jwtId);// jwt的唯一身份标识 存放用户ID

		map.put(JwtConstants.JWT_USER_NAME, getTokenParam.getUserName());// 用户名
		map.put(JwtConstants.JWT_SYSTEM_CODE, getTokenParam.getSystemCodeEnum().getValue());// 系统编码
		map.put(JwtConstants.JWT_LOGIN_SOURCE, getTokenParam.getLoginSourceEnum().getValue());// 登录来源

		// 查询url权限并授权
		switch (getTokenParam.getSystemCodeEnum()) {
		case SELLER:
			setPermissionSELLER(getTokenParam, map);
			break;

		default:
			throw new IllegalArgumentException("其他平台正在开发");
			// break;
		}

		// 记录jwtId到数据库，用户以后注销、或者修改密码是查询
		// ................................

		// 创建jwt
		return Jwts.builder().setClaims(map).signWith(SignatureAlgorithm.RS256, privateKey)
				.compressWith(CompressionCodecs.GZIP).compact();
	}
	@Autowired
	MallSysSellerUserBaseMapper mallSysSellerUserBaseMapper;

	private void setPermissionSELLER(GetTokenParam getTokenParam, Map<String, Object> map) {
		// 查询url权限并授权
		//		// .....................................
	}

	/**
	 * 
	 * @desc 检查token是否存在redis黑名单中
	 * @param tokenId
	 *            token的id
	 * @return true：命中黑名单。false：未命中黑名单
	 */
	public static boolean isInBlacklist(String tokenId) {
		return true;
	}

	/**
	 * 重置token 原有的token ID 不变
	 * 
	 * @param token
	 */
	public static String resetToken(String token) {
		return null;
	}
}
