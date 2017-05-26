package com.comet4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.comet4j.core.util.JSONUtil;

import com.comet4j.dto.TalkDTO;
import com.comet4j.dto.UserDTO;

public class ChatServlet extends HttpServlet {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -1311176251844328163L;
	
	private static final String CMD_FLAG = "cmd";
	
	private static final String RENAME_CMD = "rename";
	
	private static final String TALK_CMD = "talk";
	
	private static final String LIST_CMD = "list";
	
	private static final String ON_LINE = "online";//判断是否在线
	
	private static final CometContext context = CometContext.getInstance();
	
	private static final CometEngine engine = context.getEngine();
	
	private static final AppStore appStore = AppStore.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String cmd = request.getParameter(CMD_FLAG);
		// 发送信息
		if (TALK_CMD.equals(cmd)) {
			// 目标ID
			String tId = request.getParameter("tId");
			//当前连接ID
			String connId = request.getParameter("id");
			//当前用户ID
			String uId = AppStore.getInstance().getMap().get(connId);
			//当前名字
			String uName = (String) request.getSession().getAttribute("uName");
			//消息内容
			String text = request.getParameter("text");
			TalkDTO dto = new TalkDTO(connId, uName, text,uId);
			//根据tId获取连接ID
			String connTid = AppStore.getInstance().getMap().get(tId);
			System.out.println("    tId-->>"+tId+"    connTid-->>"+connTid+"    uid-->>"+uId+"    uName-->>"+uName+"    text-->>"+text);
			//判断不在线
			engine.sendTo(Constant.APP_CHANNEL, engine.getConnection(connTid), dto);
			return;
		}
		// 在线列表
		if (LIST_CMD.equals(cmd)) {
			List<UserDTO> userList = new ArrayList<UserDTO>();
			Map<String, String> map = AppStore.getInstance().getMap();
			Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = iter.next();
				String id = entry.getKey();
				String name = entry.getValue();
				userList.add(new UserDTO(id, name));
				System.out.println(userList);
			}
			String json = JSONUtil.convertToJson(userList);
			System.out.println(json);
			response.getWriter().print(json);
			return;
		}
		//检查是否在线
		if(ON_LINE.equals(cmd)){
			// 目标ID
			String tId = request.getParameter("tId");
			//根据tId获取连接ID
			String connTid = AppStore.getInstance().getMap().get(tId);
			String isOnline = "false";
			if(null != connTid){
				//在线情况
				isOnline = "true";
			}
				PrintWriter writer = response.getWriter();
				response.getWriter().print(isOnline);
				writer.flush();
				writer.close();
		}
	}
}
