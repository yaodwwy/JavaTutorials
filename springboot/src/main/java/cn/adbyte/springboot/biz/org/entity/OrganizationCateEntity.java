package cn.adbyte.springboot.biz.org.entity;

import cn.adbyte.springboot.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Adam.yao on 2017/11/8.
 */
@Entity
@Table(name = "t_organization_type")
public class OrganizationCateEntity extends BaseEntity {

    private String name;
    private String description;

    public OrganizationCateEntity() {
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

    @Override
    public String toString() {
        return "OrganizationCategoryEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", del=" + del +
                ", last=" + last +
                ", time=" + time +
                ", operator=" + operator +
                '}';
    }
}
