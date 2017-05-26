package com.adam.common.util;

import java.util.UUID;

public class Uid {
	public static String getUid()
	{		
		return UUID.randomUUID().toString();
	}
}
