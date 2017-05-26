package com.adam.mipet.imchat.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.mipet.imchat.entity.UserMessage;
import com.adam.mipet.imchat.entity.UserMessageExample;
import com.adam.mipet.imchat.entity.UserMessageExample.Criteria;
import com.adam.mipet.imchat.service.IUserMessageService;
import com.adam.mipet.user.entity.MipetUser;
import com.adam.mipet.user.service.IMipetUserService;
import com.adam.mipet.util.Constant;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

@Controller
@RequestMapping("/msg")
public class UserMessageController {
	// 采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	private static String appId = "PzfX3pwPLU6qzG5mdV5zn3";
	private static String appKey = "MbFFr7kkMm924VBrt9SFmA";
	private static String masterSecret = "xF95U6nOV69kL4AZVgMxG1";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	
	@Resource
	IUserMessageService userMessageService;
	@Resource
	private IMipetUserService mipetUserService;
	/**
	 * 发送离线消息时,插入数据,调用个推
	 * @param userMessage
	 * @return
	 */
	@RequestMapping("insert.do")
	@ResponseBody
	public Map<String, Object> insert(String id,String text,String tId) {
		//用户消息实体
		UserMessage userMessage = new UserMessage();
		userMessage.setMessage(text);
		try {
			userMessage.setReceiverId(Integer.parseInt(tId));
			userMessage.setSenderId(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			System.out.println("连接ID转换失败！请检查！" +  e);
			return Constant.MSG.RESULT_ERROR();
		}
		//调用个推
		//获取对方CID
		MipetUser mipetUser = mipetUserService.querybyid(Integer.parseInt(tId));
		if(null == mipetUser){
			return Constant.MSG.RESULT_ERROR();
		}
		String cid = mipetUser.getClientId();

		//获取自己的用户名
		mipetUser = mipetUserService.querybyid(Integer.parseInt(id));
		String name = mipetUser.getNickname();
		if(null == name || "".equals(name)){
			name = "匿名用户";
		}
		text = name +": "+ text;
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		LinkTemplate template = linkTemplateDemo("铲屎官大人",text);
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 3600 * 1000);
		message.setData(template);
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(cid);
		// target.setAlias(Alias);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			System.out.println("服务器响应异常");
		}
		
		
		int insert = userMessageService.insert(userMessage);
		if(insert<=0){
			return Constant.MSG.RESULT_ERROR();
		}
		return Constant.MSG.RESULT_SUCCESS(userMessage);
	}
	
	/**
	 * 检查是否有离线消息
	 * @param userMessage
	 * @return
	 */
	@RequestMapping("query.do")
	@ResponseBody
	public Map<String, Object> select(Integer id){
		UserMessageExample userMessageExample = new UserMessageExample();
		Criteria criteria = userMessageExample.createCriteria();
		criteria.andReceiverIdEqualTo(id);
		List<UserMessage> userMessageList = userMessageService.selectByExample(userMessageExample);
		if(userMessageList!=null && userMessageList.size()>=1){
			//清空自己的离线消息内容
			userMessageService.deleteByExample(userMessageExample);
		}
		return Constant.MSG.RESULT_SUCCESS(userMessageList);
	}
	
	public static LinkTemplate linkTemplateDemo(String title,String text) {
		LinkTemplate template = new LinkTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 设置通知栏标题与内容
		template.setTitle(title);
		template.setText(text);
		// 配置通知栏图标
		template.setLogo("icon.png");
		// 配置通知栏网络图标，填写图标URL地址
		template.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		template.setIsRing(true);
		template.setIsVibrate(true);
		template.setIsClearable(true);
		// 设置打开的网址地址
		template.setUrl("mipet://chatList");
		return template;
	}
}
