package ejb.core.aop.client;


import ejb.core.aop.ejb.UserManage;
import ejb.core.aop.ejbtool.EjbFactory;
import ejb.core.aop.model.User;

import javax.naming.NamingException;

public class MyClient {
    public static void main(String[] args) throws NamingException {
        // 获取sessionbean
        UserManage userManage = (UserManage) EjbFactory.getSessionBean(
                "UserManageImpl/remote", "localhost");

        // 测试拦截器拦截顺序
        User user = userManage.getUser();
        System.out.println(user.getName());
        System.out.println(userManage.getUser());
        System.out.println(userManage.greet(user));

        // 测试@ExcludeClassInterceptors，只能阻止拦截器类中的拦截器方法对Bean方法的拦截
        System.out.println(userManage.sayHello("jialin"));

    }
}
