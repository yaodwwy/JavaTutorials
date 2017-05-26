package com.adam.sys.sysfunc.entity;

import com.adam.common.entity.BaseEntity;

public class DictDetail extends BaseEntity {
    
	private static final long serialVersionUID = -5859787143516809171L;

	private Integer id;

    private String code;

    private String codeName;
    
    private Integer type;

    private String typeName;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj == null){
			return false;
		}
		if(obj instanceof DictDetail){
			DictDetail temp = (DictDetail)obj;
			return temp.getId().equals(this.getId());
		}
		return false;
    }
    
    @Override
    public int hashCode() {
    	return this.getId()*12367;
    }
}