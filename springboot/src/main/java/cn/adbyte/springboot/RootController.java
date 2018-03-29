package cn.adbyte.springboot;

import cn.adbyte.springboot.biz.authorize.entity.RoleEntity;
import cn.adbyte.springboot.biz.authorize.service.IRoleService;
import cn.adbyte.springboot.biz.member.entity.MemberEntity;
import cn.adbyte.springboot.biz.member.service.IMemberService;
import cn.adbyte.springboot.common.entity.ReturnMessage;
import cn.adbyte.springboot.common.impl.BasePageImpl;
import cn.adbyte.springboot.common.utils.Base64Utils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Adam Yao on 2018/3/20.
 */
@Controller
@RequestMapping("/")
public class RootController {

    @Resource
    private IMemberService iMemberService;
    @Resource
    private IRoleService iRoleService;
    @Resource
    private UserDetailsService userDetailsService;

    @ResponseBody
    @RequestMapping("/")
    public ReturnMessage get() {
        Page<RoleEntity> list = iRoleService.list(new BasePageImpl(), null);
        System.out.println("---------------->>");
        return ReturnMessage.success(list);
    }

    @ResponseBody
    @RequestMapping("/test")
    public ReturnMessage test() {
        UserDetails gomro = userDetailsService.loadUserByUsername("gomro");
        return ReturnMessage.success(gomro);
    }

    @RequestMapping("/login")
    public String login() {
        return "account/signin";
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
