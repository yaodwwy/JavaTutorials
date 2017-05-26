package test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Adam on 2017/3/21.
 */
public class UrlTest {
    static String sURL="https://passport.yhd.com/m/login.do";
    static String responseCookie;//标示Session必须
    public static void main(String[] args) {
        //访问URL，并把信息存入sb中
        //如果服务端登录成功后，服务端的代码调用下面的代码
        //response.sendRedirect("welcome.jsp");
        //则会不成功，原因(Step2，没有上传jsessionid值，导致没session)如下
        //Step1[login.jsp登录成功]->转到->
        //Step2[welcome.jsp不能得到session，判断没有登录成功]->转到->Step3[login.jsp要求用户登录]

        try {
            StringBuilder sbR = new StringBuilder();
            URL url = new URL(sURL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);//允许连接提交信息
            connection.setRequestMethod("POST");//网页默认“GET”提交方式

            StringBuffer sb = new StringBuffer();
            sb.append("captchaToken="+"VNdrVaANjK3eosVQdf3PVoFHGIwaPOXoU2%2FPUjU0iRQqL5G7uExoCAGLJNiqb9GNyERHQX299AqRwT8Cqm0PmyVk7xhS0YTffc8JQ2iDID1FbbICjmUkSrbjhQTh4vVEBohwvCrtV7aaawzmU1MuJHrJLbRIh4JH6BXOHMF7dF6Hiau11Dnpya14TVvHj2Ykv9ivDr1veT1OhAceNaqAQByPXSEI6cDe3giaqZmEnPMXGIl9aQuFQQ2f2R8rZHAl6tQDZV0EhQ7wO%2FzXlu2%2FaPpgkJA%2BkVVqSKroZaWkNJM7oN0FrGAXB22k2fKujlJ5");
            sb.append("credentials.username="+"zhukdmS/FObdRTgh9mFnGNX+PaMAGKTZxFOxaCNm3UkNE4cqlpMpqcRap/9FGJvgFJA6RJitTX5UC0X2tqZrL4xhjLoVwh456w2EJr3ckiSztFsWfPnzqPuIq0xFE0Ohd5VPZOzhOCl9By1VbOOYiSjrWH377iAFWWomkaEE35g=");
            sb.append("credentials.password="+"hYWlpM243vdjoTB/dmaDOW9LOXHkq/+nWa/9haT21dIEmkZZCtDELDp03FXN9/y0ENdqagLf4l5REWMGHTkS6p001Hm2DJlYkNt7LU45VvpLIo3fqS2dC1ypvfBmY41CA9Oz/q2SZbJSA3qhoCzPqiVVtroiz4ipJCtgFtfn9Gw=");
            sb.append("loginSource="+"1");
            sb.append("isAutoLogin="+"1");
            connection.setRequestProperty("Content-Length",
                    String.valueOf(sb.toString().length()));

            OutputStream os = connection.getOutputStream();
            os.write(sb.toString().getBytes());
            os.close();

            //取Cookie
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            responseCookie = connection.getHeaderField("Set-Cookie");//取到所用的Cookie
            System.out.println("cookie:" + responseCookie);

//读取返回的页面信息到br1
            BufferedReader br1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //取返回的页面,br1转sbR
            String line1= br1.readLine();
            while (line1 != null) {
                sbR.append(line1);
                line1 = br1.readLine();
            }

            System.out.println(sbR.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
