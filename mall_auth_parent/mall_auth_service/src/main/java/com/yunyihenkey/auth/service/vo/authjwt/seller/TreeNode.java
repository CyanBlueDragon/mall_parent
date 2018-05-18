package com.yunyihenkey.auth.service.vo.authjwt.seller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/15 9:24
 */
public class TreeNode {
    private Long id;
    private Long pid;
    private List<TreeNode> childList=new ArrayList<>();
    public void add(TreeNode treeNode){
        childList.add(treeNode);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<TreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeNode> childList) {
        this.childList = childList;
    }
}
