package cn.adbyte.springboot;

import cn.adbyte.springboot.common.entity.ReturnMessage;
import cn.adbyte.springboot.common.utils.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Adam Yao on 2018/3/20.
 */
@RestController
@RequestMapping("/")
public class RootController {
    @RequestMapping("/")
    public ReturnMessage get(HttpServletRequest request, Integer id) {
        this.setStaticUrl(request);
        return ReturnMessage.success(id);
    }

    private void setStaticUrl(HttpServletRequest request) {
        String serverName = request.getServerName();
        if (serverName.startsWith("localhost")) {
            request.getSession().setAttribute("staticUrl", "");
        } else {
            String url = Base64Utils.encodeStr("aHR0cDovL3N0YXRpYy5nb21yby5jbi9jbG91ZC9zb3VyY2U=");
            request.getSession().setAttribute("src", url);
        }
    }

}
