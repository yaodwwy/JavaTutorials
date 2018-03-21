package cn.adbyte.springboot.biz.authorize.entity;

import cn.adbyte.springboot.biz.authorize.enums.ResourceTypeEnum;
import cn.adbyte.springboot.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "t_resource")
public class ResourceEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ResourceEntity parent;
    private ResourceTypeEnum type;
    private String name;
    private String url;
    private String iconCls;
    private Integer moduleSort;
    private Integer menuSort;
    private Integer tabSort;
    private Integer funcSort;
    private Boolean enabled;
    @Transient//用于输出是否授权的资源
    private Boolean granted;
    @SuppressWarnings("JpaModelReferenceInspection")
    @JsonIgnore
    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private Set<ResourceEntity> children = new HashSet<>();

    public ResourceEntity() {
    }

    public ResourceEntity(ResourceEntity parent, ResourceTypeEnum type, String name, String url, String iconCls, Integer moduleSort, Integer menuSort, Integer tabSort, Integer funcSort, Boolean enabled) {
        this.parent = parent;
        this.type = type;
        this.name = name;
        this.url = url;
        this.iconCls = iconCls;
        this.moduleSort = moduleSort;
        this.menuSort = menuSort;
        this.tabSort = tabSort;
        this.funcSort = funcSort;
        this.enabled = enabled;
    }
    public ResourceEntity getParent() {
        return parent;
    }

    public void setParent(ResourceEntity parent) {
        this.parent = parent;
    }

    public ResourceTypeEnum getType() {
        return type;
    }

    public void setType(ResourceTypeEnum type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Integer getTabSort() {
        return tabSort;
    }

    public void setTabSort(Integer tabSort) {
        this.tabSort = tabSort;
    }

    public Integer getFuncSort() {
        return funcSort;
    }

    public void setFuncSort(Integer funcSort) {
        this.funcSort = funcSort;
    }

    public Integer getModuleSort() {
        return moduleSort;
    }

    public void setModuleSort(Integer moduleSort) {
        this.moduleSort = moduleSort;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getGranted() {
        return granted;
    }

    public void setGranted(Boolean granted) {
        this.granted = granted;
    }

    public Set<ResourceEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<ResourceEntity> children) {
        this.children = children;
    }
}
