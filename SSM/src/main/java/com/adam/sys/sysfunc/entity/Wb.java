package com.adam.sys.sysfunc.entity;

import com.adam.common.entity.BaseEntity;

public class Wb extends BaseEntity {
	
	private static final long serialVersionUID = 2729172284303832819L;

	private Integer wbId;
	
	private String wbName;
    
	private String dataType;

    private String webUrl;

    public String getWbName() {
		return wbName;
	}

	public void setWbName(String wbName) {
		this.wbName = wbName;
	}

	public Integer getWbId() {
        return wbId;
    }

    public void setWbId(Integer wbId) {
        this.wbId = wbId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

}