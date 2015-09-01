package org.tinygroup.sdpm.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hulk on 2015/8/27.
 */
@XStreamAlias("menu")
public class Menu implements Serializable {
    /**
     * 菜单编号
     */
    @XStreamAsAttribute
    private String id;
    /**
     * 父菜单编号
     */
    @XStreamAlias("parent-id")
    @XStreamAsAttribute
    private String parentId;    // id
    /**
     * 菜单显示名称
     */
    @XStreamAsAttribute
    private String name;    // 名称
    /**
     * 菜单链接
     */
    @XStreamAsAttribute
    private String href;    // 链接
//    /**
//     * 菜单目标
//     */
//    @XStreamAsAttribute
//    private String target;  // 目标（ mainFrame、_blank、_self、_parent、_top）
    /**
     * 菜单图标
     */
    @XStreamAsAttribute
    private String icon;    // 图标
    /**
     * 菜单排序
     */
    @XStreamAsAttribute
    private Integer sort;    // 排序
    /**
     * 菜单显示名称
     */
    @XStreamAsAttribute
    private String isShow;    // 是否在菜单中显示（1：显示；0：不显示）
    /**
     * 子菜单
     */
    @XStreamAsAttribute
    private String permission; // 权限标识
    /**
     * 子菜单
     */
    @XStreamImplicit
    private List<Menu> childMenus;    // 子菜单

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }
}
