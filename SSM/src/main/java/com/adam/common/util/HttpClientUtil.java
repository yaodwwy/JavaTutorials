package com.adam.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.adam.common.constant.Constant;
/**
 * HttpClient接口调用
 * @author yanglong
 * 2015年12月12日14:06:30
 */
public class HttpClientUtil {
	
	static HttpClient httpclient = new DefaultHttpClient();
	static HttpPost httppost = new HttpPost(Constant.HTTP_URL);
	//调用接口
	public static String execute(Map<String, Object> params) {
		//返回内容
		String content = null;
		try {
			//请求的参数
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				nameValuePairs.add(new BasicNameValuePair(entry.getKey().toString(),entry.getValue().toString()));
			}
			httppost.addHeader("Content-type",
					"application/x-www-form-urlencoded");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);// 访问服务器的
			//返回200成功否则失败
			if (response.getStatusLine().getStatusCode() == 200) {
				content = EntityUtils.toString(response.getEntity());
				//JSONObject.parseObject(content);
			}else{
				content = "接口调用失败";
			}
		} catch (Exception e) {
			content = "接口调用异常";
		}
		return content;
	}
	
	public static void main(String[] args) {
		Map<String, Object> hehe = new HashMap<String, Object>();
		hehe.put("username", "123123");
		hehe.put("password", "123123123");
		System.out.println(execute(hehe));
	}

}
