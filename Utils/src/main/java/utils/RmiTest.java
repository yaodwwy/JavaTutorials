package utils;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 * Created by Adam Yao on 2017/6/11
 */
public class RmiTest {
    //    a. 定义客户端与服务器之间的通信接口
    public interface RemoteDate extends Remote {
        public Date getRemoteDate() throws RemoteException;

        public final static String LOOKUPNAME = "RemoteDate";
    }

    //        b. 编写RMI服务器
    public static class RemoteDateImpl extends UnicastRemoteObject implements RemoteDate {
        public RemoteDateImpl() throws RemoteException {
            super();
        }

        public Date getRemoteDate() throws RemoteException {
            return new Date();
        }
    }

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        //18. RMI
        //
        RemoteDateImpl im = new RemoteDateImpl();
        System.out.println("DateServer starting...");
        Naming.rebind(RemoteDate.LOOKUPNAME, im);
        System.out.println("DateServer ready.");

        //        c. 运行rmic生成stub
        //        javac RemoteDateImpl.java
        //        rmic RemoteDateImpl

        //        d. 编写客户端
        RemoteDate netConn = (RemoteDate) Naming.lookup(RemoteDate.LOOKUPNAME);
        Date today = netConn.getRemoteDate();
        System.out.println(today.toString());
        //
        //        e. 确保RMI注册表运行
        //                rmiregistry
        //
        //        f. 启动服务器
        //        java RemoteDateImpl
        //
        //        g. 运行客户端
        //        java DateClient
    }
}
