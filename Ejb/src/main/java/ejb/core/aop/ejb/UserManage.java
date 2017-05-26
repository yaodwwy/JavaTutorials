package ejb.core.aop.ejb;

import ejb.core.aop.model.User;

public interface UserManage{
	String sayHello(String userName);
	String greet(User user);
	User  getUser();
}
