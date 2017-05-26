package mipet;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;


public class Test {
	
	public static void main(String[] args) {
	
		String address = "http://wx001:8080/mipet/favs/queryall.do";
		PostMethod postMethod = new PostMethod(address);
//		postMethod.setParameter("id", "151");
		postMethod.setParameter("userId", "18");
		postMethod.setParameter("page", "1");
		postMethod.setParameter("rows", "12");
		postMethod.getParams().setContentCharset("UTF-8");
		postMethod.getParams().setHttpElementCharset("UTF-8");
		HttpClient httpClient = new HttpClient();
		try {
			httpClient.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				String str = postMethod.getResponseBodyAsString();
				System.out.println(str + "---------------------<<<");
			} else {
				System.out.println("返回失败");
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
