package com.comet4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.comet4j.core.CometConnection;
import org.comet4j.core.event.ConnectEvent;
import org.comet4j.core.listener.ConnectListener;
import org.comet4j.core.util.JSONUtil;

import com.comet4j.dto.JoinDTO;
import com.comet4j.dto.UserDTO;

/**
 * 用于上线的监听类
 * 
 * @author wbh
 * @date Mon Aug 04 16:43:38 CST 2014
 */
public class OnLineListener extends ConnectListener {

	@Override
	public boolean handleEvent(ConnectEvent anEvent) {
		CometConnection conn = anEvent.getConn();
		HttpServletRequest request = conn.getRequest();
		String uName = getCookieValue(request.getCookies(), "uName", "");
		String uId = getCookieValue(request.getCookies(), "uId", "");
		try {
			uName = URLDecoder.decode(uName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		}
		JoinDTO dto = new JoinDTO(conn.getId(), uName,uId);
		AppStore.getInstance().put(conn.getId(),uId);
		AppStore.getInstance().put(uId,conn.getId());
		
		anEvent.getTarget().sendToAll(Constant.APP_CHANNEL, dto);
		List<UserDTO> userList = new ArrayList<UserDTO>();
		Map<String, String> map = AppStore.getInstance().getMap();
		Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			String id = entry.getKey();
			String name = entry.getValue();
			userList.add(new UserDTO(id, name));
		}
		String json = JSONUtil.convertToJson(userList);
		System.out.println("上线通知————————"+json);
		return true;
	}

	public String getCookieValue(Cookie[] cookies, String cookieName,
			String defaultValue) {
		String result = defaultValue;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return result;
	}
}
