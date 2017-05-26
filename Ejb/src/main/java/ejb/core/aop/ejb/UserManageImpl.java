package ejb.core.aop.ejb;

import ejb.core.aop.interceptor.UserInterceptor1;
import ejb.core.aop.interceptor.UserInterceptor2;
import ejb.core.aop.model.User;

import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

@Stateless
@Interceptors({UserInterceptor1.class, UserInterceptor2.class})   //多个拦截器
//@Interceptors(UserInterceptor1.class)   // 一个拦截器
public class UserManageImpl implements UserManage {
    
	@AroundInvoke 
    public Object myInterceptor1(InvocationContext ic) throws Exception  
    {  
        System.out.println("myInterceptor-----------1-------------:" + ic.getMethod().getName());  
        return  ic.proceed();      
    }  
    @AroundInvoke 
    public Object myInterceptor2(InvocationContext ic) throws Exception  
    {  
        System.out.println("myInterceptor-----------2-------------:" + ic.getMethod().getName());  
        return  ic.proceed(); 
    }  

    @ExcludeClassInterceptors    //只能阻止拦截器类中的拦截器方法对Bean方法的拦截
	public String sayHello(String userName) {
		// TODO Auto-generated method stub
		return "hello!@"+userName;
	}

	public String greet(User user) {
		// TODO Auto-generated method stub
		return "greet!@"+user.getName();
	}

	public User getUser() {
		// TODO Auto-generated method stub
		User user=new User();
		user.setName("贾琳");
		return user;
	}
	
}
