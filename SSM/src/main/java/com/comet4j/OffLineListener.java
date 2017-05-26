package com.comet4j;

import org.comet4j.core.CometConnection;
import org.comet4j.core.event.DropEvent;
import org.comet4j.core.listener.DropListener;

import com.comet4j.dto.LeftDTO;

/**
 * 用于离线的监听类
 * 
 * @author wbh
 * @date Mon Aug 04 16:43:38 CST 2014
 */
public class OffLineListener extends DropListener {

	public boolean handleEvent(DropEvent event) {
		CometConnection conn = event.getConn();
		if (conn != null) {
			//String userId = AppStore.getInstance().get(conn.getId());
//			LeftDTO dto = new LeftDTO(conn.getId(), userName);
			String id = AppStore.getInstance().getMap().get(conn.getId());
			AppStore.getInstance().getMap().remove(conn.getId());
			AppStore.getInstance().getMap().remove(id);
			//event.getTarget().sendToAll(Constant.APP_CHANNEL, dto);
		}
		return true;
	}

}
