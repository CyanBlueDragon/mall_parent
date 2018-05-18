package com.yunyihenkey.auth.service.util;

import com.yunyihenkey.auth.service.vo.authjwt.seller.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HeXing
 * @desc 生成树工具
 * @date 2018/5/15 10:09
 */
public class TreeUtil {
    public static <T extends TreeNode> List<T> bulid(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getPid())) {
                trees.add(treeNode);
            }
            for (T it : treeNodes) {
                if (it.getPid() == treeNode.getId()) {
                    if (treeNode.getChildList() == null) {
                        treeNode.setChildList(new ArrayList<TreeNode>());
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }
}
