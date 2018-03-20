package cn.adbyte.springboot.biz.dept.entity;

import cn.adbyte.springboot.biz.member.entity.MemberEntity;
import cn.adbyte.springboot.biz.org.entity.OrganizationEntity;
import cn.adbyte.springboot.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Cacheable
@Table(name = "t_department")
public class DepartmentEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private DepartmentEntity parent;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manager_member_id")
    private MemberEntity managerMember;
    private String number;
    private String name;
    private String fullname;
    private String contacts;
    private String telephone;
    private String description;

    public DepartmentEntity() {
    }

    public DepartmentEntity(Boolean del, Date last, Date time, Integer operator, DepartmentEntity parent, OrganizationEntity organization, MemberEntity managerMember, String number, String name, String fullname, String contacts, String telephone, String description) {
        super(del, last, time, operator);
        this.parent = parent;
        this.organization = organization;
        this.managerMember = managerMember;
        this.number = number;
        this.name = name;
        this.fullname = fullname;
        this.contacts = contacts;
        this.telephone = telephone;
        this.description = description;
    }

    public DepartmentEntity getParent() {
        return parent;
    }

    public void setParent(DepartmentEntity parent) {
        this.parent = parent;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public MemberEntity getManagerMember() {
        return managerMember;
    }

    public void setManagerMember(MemberEntity managerMember) {
        this.managerMember = managerMember;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "parent=" + parent +
                ", organization=" + organization +
                ", managerMember=" + managerMember +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", fullname='" + fullname + '\'' +
                ", contacts='" + contacts + '\'' +
                ", telephone='" + telephone + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", del=" + del +
                ", last=" + last +
                ", time=" + time +
                ", operator=" + operator +
                '}';
    }
}
