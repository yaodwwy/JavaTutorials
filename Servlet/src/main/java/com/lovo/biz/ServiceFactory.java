package com.lovo.biz;

import com.lovo.util.CommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * ����ҵ���߼��������Ĺ��� (�Ǽ�ʽ����ģʽ)
 * @author ���
 *
 */
public class ServiceFactory {
	private static final String DEFAULT_IMPL_PACKAGE_NAME = "impl";
	
	private static Map<Class<?>, Object> map = new HashMap<>();

	/**
	 * ��������
	 * @param type ҵ���߼����������
	 * @return ҵ���߼�����Ĵ������
	 */
	public static synchronized Object factory(Class<?> type) {
		if(map.containsKey(type)) {
			return map.get(type);
		}
		else {
			try {
				Object serviceObj = Class.forName(
						type.getPackage().getName() + "." + DEFAULT_IMPL_PACKAGE_NAME + "." 
						+ type.getSimpleName() + CommonUtil.capitalize(DEFAULT_IMPL_PACKAGE_NAME)).newInstance();
				map.put(type, ServiceProxy.getProxyInstance(serviceObj));
				return serviceObj;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
