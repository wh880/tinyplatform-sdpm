package org.tinygroup.sdpm.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Hulk on 2015/8/27.
 */
@XStreamAlias("menu")
public class Menu implements Serializable, Comparable {
    public static String IS_SHOW_YES = "1";
    public static String IS_SHOW_NO = "0";

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
     * 是否在菜单中显示（1：显示；0：不显示）
     */
    @XStreamAsAttribute
    private String isShow;    // 是否在菜单中显示（1：显示；0：不显示）
    /**
     * 所属聚合分类 多个分类以 , (英文)分开
     */
    @XStreamAsAttribute
    private String scope;
    /**
     * 子菜单
     */
    @XStreamImplicit
    private List<Menu> childMenus;    // 子菜单

    @XStreamAsAttribute
    private Map<Object, Object> map;

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public int compareTo(Object o) {
        if (o != null) {
            Menu menu = (Menu) o;
            if (menu.getSort() != null) {
                return menu.getSort() - sort;
            }
        }
        return 0;
    }
}
