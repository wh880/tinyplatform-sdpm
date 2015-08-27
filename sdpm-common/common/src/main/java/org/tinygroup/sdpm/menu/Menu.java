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
    @XStreamAsAttribute
    private Integer id;    // id
    @XStreamAsAttribute
    private String name;    // 名称
    @XStreamAsAttribute
    private String href;    // 链接
    @XStreamAsAttribute
    private String target;  // 目标（ mainFrame、_blank、_self、_parent、_top）
    @XStreamAsAttribute
    private String icon;    // 图标
    @XStreamAsAttribute
    private Integer sort;    // 排序
    @XStreamAsAttribute
    private String isShow;    // 是否在菜单中显示（1：显示；0：不显示）
    @XStreamAsAttribute
    private String permission; // 权限标识
    @XStreamImplicit
    private List<Menu> child;    // 子菜单

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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

    public List<Menu> getChild() {
        return child;
    }

    public void setChild(List<Menu> child) {
        this.child = child;
    }
}
