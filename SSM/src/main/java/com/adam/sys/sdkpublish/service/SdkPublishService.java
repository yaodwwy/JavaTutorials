package com.adam.sys.sdkpublish.service;

import com.adam.sys.sdkpublish.entity.SdkPublishEntity;

public interface SdkPublishService {
	public SdkPublishEntity query();
	public int update(SdkPublishEntity sdk);
}
