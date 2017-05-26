package com.adam.sys.sdkpublish.dao;

import com.adam.sys.sdkpublish.entity.SdkPublishEntity;


public interface SdkPublishDao {

	public SdkPublishEntity query();
	public int update(SdkPublishEntity sdk);
}
