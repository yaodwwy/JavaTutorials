package cn.adbyte.springboot.biz.authorize.entity;

import cn.adbyte.springboot.biz.org.entity.OrganizationEntity;
import cn.adbyte.springboot.common.entity.BaseEntity;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cacheable
@Table(name = "t_role")
public class RoleEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;
    private String name;
    private String description;
    @Transient//用于输出是否授权的资源
    private Boolean granted;
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "t_role_resource",
            joinColumns = {@JoinColumn(name = "role_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "resource_id", referencedColumnName = "id")})
    @OrderBy(value = "id DESC")
    private Set<RoleEntity> resource = new HashSet<>();

    public RoleEntity() {
    }

    public RoleEntity(OrganizationEntity organization, String name, String description) {
        this.organization = organization;
        this.name = name;
        this.description = description;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getGranted() {
        return granted;
    }

    public void setGranted(Boolean granted) {
        this.granted = granted;
    }

    public Set<RoleEntity> getResource() {
        return resource;
    }

    public void setResource(Set<RoleEntity> resource) {
        this.resource = resource;
    }
}
