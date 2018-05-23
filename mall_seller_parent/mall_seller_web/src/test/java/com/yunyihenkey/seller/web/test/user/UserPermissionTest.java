package com.yunyihenkey.seller.web.test.user;

import com.yunyihenkey.Application;
import com.yunyihenkey.auth.service.vo.authjwt.seller.PermissionTree;
import com.yunyihenkey.seller.service.SellerPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/15 11:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserPermissionTest {
    @Autowired
    private SellerPermissionService  sellerPermissionService;
    @Test
    public void queryTreeList(){
       List<PermissionTree> permissionTreeList= sellerPermissionService.queryTreeList();
        permissionTreeList.forEach(permissionTree ->
                System.out.println(permissionTree.getPermissionName()));

    }
}
