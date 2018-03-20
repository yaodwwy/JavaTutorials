package cn.adbyte.springboot.biz.org.entity;

import cn.adbyte.springboot.biz.org.enums.ArchitectureCateEnum;
import cn.adbyte.springboot.common.entity.BaseEntity;
import cn.adbyte.springboot.common.exception.BaseException;
import cn.adbyte.springboot.common.exception.ErrorCode;
import com.alibaba.fastjson.JSONArray;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Adam Yao on 2017/12/10.
 */
@Entity
@Table(name = "t_organization")
public class OrganizationEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private OrganizationEntity parent;
    @ManyToOne
    @JoinColumn(name = "organization_type_id")
    private OrganizationCateEntity organizationCategory;
    @Column(name = "transaction_organization_id")
    private Integer transactionOrganization;
    @Column(name = "architecture_type")
    private ArchitectureCateEnum architectureType;
    private String name;
    private String companyName;
    private String description;
    private String businessScope;
    private String licenseCode;
    private String creditIdentifier;
    private String legalRepresentative;
    private String registeredCapital;
    private String memo;
    private Boolean enabled;
    private Boolean authentication;
    private String purchaseQuota;
    private String annexAddress;
    private Boolean platform;
    @Transient
    private List<AttachmentEntity> attachments;



    public OrganizationEntity() {
    }

    public OrganizationEntity(OrganizationEntity parent, OrganizationCateEntity organizationCategory, Integer transactionOrganization, ArchitectureCateEnum architectureType, String name, String companyName, String description, String businessScope, String licenseCode, String creditIdentifier, String legalRepresentative, String registeredCapital, String memo, Boolean enabled, Boolean authentication, String purchaseQuota, String annexAddress) {
        this.parent = parent;
        this.organizationCategory = organizationCategory;
        this.transactionOrganization = transactionOrganization;
        this.architectureType = architectureType;
        this.name = name;
        this.companyName = companyName;
        this.description = description;
        this.businessScope = businessScope;
        this.licenseCode = licenseCode;
        this.creditIdentifier = creditIdentifier;
        this.legalRepresentative = legalRepresentative;
        this.registeredCapital = registeredCapital;
        this.memo = memo;
        this.enabled = enabled;
        this.authentication = authentication;
        this.purchaseQuota = purchaseQuota;
        this.annexAddress = annexAddress;
    }

    public OrganizationEntity getParent() {
        return parent;
    }

    public void setParent(OrganizationEntity parent) {
        this.parent = parent;
    }

    public OrganizationCateEntity getOrganizationCategory() {
        return organizationCategory;
    }

    public void setOrganizationCategory(OrganizationCateEntity organizationCategory) {
        this.organizationCategory = organizationCategory;
    }

    public Integer getTransactionOrganization() {
        return transactionOrganization;
    }

    public void setTransactionOrganization(Integer transactionOrganization) {
        this.transactionOrganization = transactionOrganization;
    }

    public ArchitectureCateEnum getArchitectureType() {
        return architectureType;
    }

    public void setArchitectureType(ArchitectureCateEnum architectureType) {
        this.architectureType = architectureType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getCreditIdentifier() {
        return creditIdentifier;
    }

    public void setCreditIdentifier(String creditIdentifier) {
        this.creditIdentifier = creditIdentifier;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enable) {
        this.enabled = enable;
    }

    public Boolean getAuthentication() {
        return authentication;
    }

    public String getPurchaseQuota() {
        return purchaseQuota;
    }

    public String getAnnexAddress() {
        return annexAddress;
    }

    public void setAnnexAddress(String annexAddress) {
        this.annexAddress = annexAddress;
    }

    public void setPurchaseQuota(String purchaseQuota) {
        this.purchaseQuota = purchaseQuota;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public Boolean getPlatform() { return platform; }

    public void setPlatform(Boolean platform) { this.platform = platform; }

    public List<AttachmentEntity> getAttachments() {
        String annexAddress = this.getAnnexAddress();
        try {
            if (!StringUtils.isEmpty(annexAddress)) {
                this.attachments = JSONArray.parseArray(annexAddress, AttachmentEntity.class);
            }
        } catch (Exception e) {
            throw new BaseException(ErrorCode.JSONException);
        }
        return attachments;
    }

    public void setAttachments(List<AttachmentEntity> attachments) {
        this.attachments = attachments;
    }
}
