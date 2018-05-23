package com.yunyihenkey.seller.web.test.user;

import com.yunyihenkey.Application;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.PermissionVO;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.SaveRoleParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.UpdateRoleParam;
import com.yunyihenkey.seller.service.SellerRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/21 11:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserRoleTest {
    @Autowired
    private SellerRoleService sellerRoleService;

    @Test
    public void updateRole() {
        UpdateRoleParam updateRoleParam = new UpdateRoleParam();
        updateRoleParam.setId(50918538092867584L);
        List<PermissionVO> permissionVOList = new ArrayList<>();
        //订单
        PermissionVO permissionVO1 = new PermissionVO(30l);
        PermissionVO permissionVO2 = new PermissionVO(31L);
        PermissionVO permissionVO3 = new PermissionVO(32L);
        PermissionVO permissionVO4 = new PermissionVO(33L);
        PermissionVO permissionVO5 = new PermissionVO(34L);

        PermissionVO permissionVO6 = new PermissionVO(27L);
        PermissionVO permissionVO7 = new PermissionVO(28L);
        PermissionVO permissionVO8 = new PermissionVO(29L);

        permissionVOList.add(permissionVO1);
        permissionVOList.add(permissionVO2);
        permissionVOList.add(permissionVO3);
        permissionVOList.add(permissionVO4);
        permissionVOList.add(permissionVO5);
        permissionVOList.add(permissionVO6);
        permissionVOList.add(permissionVO7);
        permissionVOList.add(permissionVO8);
        updateRoleParam.setName("管理员");
        updateRoleParam.setPermissionVOList(permissionVOList);
        try {
            sellerRoleService.updateBatch(updateRoleParam);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void saveRole() {
        SaveRoleParam saveRoleParam = new SaveRoleParam();
        List<PermissionVO> permissionVOList = new ArrayList<>();
        //订单
        PermissionVO permissionVO1 = new PermissionVO(30l);
        PermissionVO permissionVO2 = new PermissionVO(31L);
        PermissionVO permissionVO3 = new PermissionVO(32L);
        PermissionVO permissionVO4 = new PermissionVO(33L);
        PermissionVO permissionVO5 = new PermissionVO(34L);

        PermissionVO permissionVO6 = new PermissionVO(27L);
        PermissionVO permissionVO7 = new PermissionVO(28L);
        PermissionVO permissionVO8 = new PermissionVO(29L);

        permissionVOList.add(permissionVO1);
        permissionVOList.add(permissionVO2);
        permissionVOList.add(permissionVO3);
        permissionVOList.add(permissionVO4);
        permissionVOList.add(permissionVO5);
        permissionVOList.add(permissionVO6);
        permissionVOList.add(permissionVO7);
        permissionVOList.add(permissionVO8);
        saveRoleParam.setName("管理员");
        saveRoleParam.setPermissionVOList(permissionVOList);
        try {
            sellerRoleService.save(saveRoleParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
