package com.adam.sys.sdkpublish.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.sdkpublish.dao.SdkPublishDao;
import com.adam.sys.sdkpublish.entity.SdkPublishEntity;
import com.adam.sys.sdkpublish.service.SdkPublishService;

@Service(value="sdkPublishService")
public class SdkPublishServiceImpl implements SdkPublishService {

	@Resource
	private SdkPublishDao sdkPublishMapper;

	@Override
	public SdkPublishEntity query() {
		return sdkPublishMapper.query();
	}

	@Override
	public int update(SdkPublishEntity sdk) {
		return sdkPublishMapper.update(sdk);
	}
	


	
}
