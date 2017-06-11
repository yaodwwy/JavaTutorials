package utils;

import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by Adam Yao on 2017/6/11
 */
public class SocketTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //13. 网络客户端
        // 访问服务器
        Socket socket = new Socket("127.0.0.1", 8080);
        // todo something
        socket.close();

        // 查找网络地址
        InetAddress.getByName("hostName").getHostAddress(); // 根据主机名得到IP地址
        InetAddress.getByName("ipAddr").getHostName(); // 根据IP地址得到主机名

        // 连接具体异常
        //UnknownHostException
        //NoRouteToHostException
        //ConnectException

        // Socket读写文本数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String readLine = bufferedReader.readLine();
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.print("send message to client \r\n");
        out.flush();

        // Socket读写二进制数据
        DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        long remoteTime = (long) (in.readUnsignedByte() << 24);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        // Socket读写串行化数据
        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        Object o = objectInputStream.readObject();
        if (o instanceof Date) { // 验证对象类型
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }

        // UDP数据报
        final int PACKET_SIZE = 1024;

        String host = "EV001B389673DE";
        InetAddress serverAddr = InetAddress.getByName(host);
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] buffer = new byte[PACKET_SIZE]; // 分配数据缓冲空间
        DatagramPacket packet = new DatagramPacket(buffer, PACKET_SIZE, serverAddr, 8080);
        packet.setLength(PACKET_SIZE - 1); // 设置数据长度
        datagramSocket.send(packet);
        datagramSocket.receive(packet); // 接收数据


        //14. 服务器端: Socket
        // 创建ServerSocket
        ServerSocket serverSocket;
        Socket clientSocket;

        serverSocket = new ServerSocket(9999);
        while ((clientSocket = serverSocket.accept()) != null) {
            System.out.println("Accept from client " + serverSocket.getInetAddress());
            serverSocket.close();
        }

        // 监听内部网
        final short PORT = 9999;
        final String INSIDE_HOST = "acmewidgets-inside"; // 网络接口名
        final int BACKLOG = 10; // 待发数
        serverSocket = new ServerSocket(PORT, BACKLOG, InetAddress.getByName(INSIDE_HOST));

        // 返回相应对象
        serverSocket = new ServerSocket(9999);
        Socket clientsocket;
        BufferedReader bufferedReader1 = null;
        PrintWriter printWriter = null;
        for (int i = 0; i < 2; i++) {
            clientsocket = serverSocket.accept();
            bufferedReader1 = new BufferedReader(new InputStreamReader(clientsocket.getInputStream(), "8859_1"));
            printWriter = new PrintWriter(new OutputStreamWriter(clientsocket.getOutputStream(), "8859_1"), true);
            String echoLine;
            while ((echoLine = bufferedReader1.readLine()) != null) {
                System.out.println("Read " + echoLine);
                printWriter.print(echoLine + "\r\n");
            }
        }
        //以上例子返回字符串，如果返回二进制，则使用DataOutputStream；返回对象，使用ObjectOutputStream

        // 使用SSL和JSSE保护Web服务器
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket socket1 = ssf.createServerSocket(8080);

        // Log4j
        // Level级别:
        // DEBUG<INFO<WARN<ERROR<FATAL<OFF
        // Appender:
        // 输出信息
        // ConsoleAppender:
        // 输出控制台 System.printWriter

        // 找到网络接口
        Enumeration list = NetworkInterface.getNetworkInterfaces();
        while (list.hasMoreElements()) {
            NetworkInterface iface = (NetworkInterface) list.nextElement();
            System.out.println(iface.getDisplayName());
            Enumeration addrs = iface.getInetAddresses();
            while (addrs.hasMoreElements()) {
                InetAddress addr = (InetAddress) addrs.nextElement();
                System.out.println(addr);
            }
        }
    }

    // 处理多客户端
    //需要把接收数据的处理放入多线程中
    public static class EchoServerThreaded {
        public static final int ECHOPORT = 7;
        public static final int NUM_THREADS = 4;

        public static void main(String[] av) {
            new EchoServerThreaded(ECHOPORT, NUM_THREADS);
        }

        public EchoServerThreaded(int port, int numThreads) {
            ServerSocket servSock;
            Socket clientSocket;
            try {
                servSock = new ServerSocket(ECHOPORT);
            } catch (IOException e) {
                throw new RuntimeException("Could not create ServerSocket " + e);
            }
            for (int i = 0; i < numThreads; i++) {
                new Handler(servSock, i).start();
            }
        }
    }

    static class Handler extends Thread {
        ServerSocket servSock;
        int threadNumber;

        Handler(ServerSocket s, int i) {
            super();
            servSock = s;
            threadNumber = i;
            setName("Thread " + threadNumber);
        }

        public void run() {
            while (true) {
                try {
                    System.out.println(getName() + " waiting");
                    Socket clientSocket;
                    synchronized (servSock) {
                        clientSocket = servSock.accept();
                    }
                    System.out.println(getName() + " starting, IP=" + clientSocket.getInetAddress());
                    BufferedReader is = new BufferedReader(new InputStreamReader(
                            clientSocket.getInputStream()));
                    PrintStream os = new PrintStream(clientSocket.getOutputStream(), true);
                    String line;
                    while ((line = is.readLine()) != null) {
                        os.print(line + "\r\n");
                        os.flush();
                    }
                    System.out.println(getName() + " ENDED ");
                    clientSocket.close();
                } catch (IOException ex) {
                    System.out.println(getName() + ": IO Error on datagramSocket " + ex);
                    return;
                }
            }
        }
    }
}
