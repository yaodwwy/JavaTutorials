package ejb.core.aop.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class UserInterceptor2 {
	@AroundInvoke
	public Object interceptorMethod(InvocationContext ic) throws Exception {
		System.out.println("UserInterceptor-----------2-------------:" + ic.getMethod().getName());
		return ic.proceed();
	}
}
