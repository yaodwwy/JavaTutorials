package tools;

/**
 * Created by Adam_Yao on 2017/6/19.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketObject {
    private Socket socket;
    private ServerSocket serverSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String ip;
    private int port;

    public SocketObject(int port) {
        this.port = port;

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public SocketObject(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void open() {
        if(this.serverSocket != null) {
            try {
                this.socket = this.serverSocket.accept();
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        } else {
            try {
                this.socket = new Socket(this.ip, this.port);
            } catch (UnknownHostException var2) {
                var2.printStackTrace();
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

    }

    public String readString() {
        try {
            this.inputStream = this.socket.getInputStream();
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
            String var4 = this.bufferedReader.readLine();
            return var4;
        } catch (IOException var12) {
            var12.printStackTrace();
        } finally {
            try {
                this.socket.shutdownInput();
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

        return null;
    }

    public void writeString(String str) {
        try {
            this.outputStream = this.socket.getOutputStream();
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.outputStream));
            this.bufferedWriter.write(str);
            this.bufferedWriter.flush();
        } catch (IOException var11) {
            var11.printStackTrace();
        } finally {
            try {
                this.socket.shutdownOutput();
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        }

    }

    public Object readObject() {
        try {
            this.inputStream = this.socket.getInputStream();
            this.objectInputStream = new ObjectInputStream(this.inputStream);
            Object var4 = this.objectInputStream.readObject();
            return var4;
        } catch (ClassNotFoundException var13) {
            var13.printStackTrace();
        } catch (IOException var14) {
            var14.printStackTrace();
        } finally {
            try {
                this.socket.shutdownInput();
            } catch (IOException var12) {
                var12.printStackTrace();
            }

        }

        return null;
    }

    public void writeObject(Object obj) {
        try {
            this.outputStream = this.socket.getOutputStream();
            this.objectOutputStream = new ObjectOutputStream(this.outputStream);
            this.objectOutputStream.writeObject(obj);
            this.objectOutputStream.flush();
        } catch (IOException var11) {
            var11.printStackTrace();
        } finally {
            try {
                this.socket.shutdownOutput();
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        }

    }

    public int readDataInputStream() {
        try {
            this.inputStream = this.socket.getInputStream();
            this.dataInputStream = new DataInputStream(this.inputStream);
            int var4 = this.dataInputStream.read();
            return var4;
        } catch (IOException var12) {
            var12.printStackTrace();
        } finally {
            try {
                this.socket.shutdownInput();
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

        return -1;
    }

    public void writeDataInputStream(byte[] b) {
        try {
            this.outputStream = this.socket.getOutputStream();
            this.dataOutputStream = new DataOutputStream(this.outputStream);
            this.dataOutputStream.write(b, 0, b.length);
            this.dataOutputStream.flush();
        } catch (IOException var11) {
            var11.printStackTrace();
        } finally {
            try {
                this.socket.shutdownOutput();
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        }

    }

    public void close() {
        if(this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException var10) {
                var10.printStackTrace();
            }
        }

        if(this.outputStream != null) {
            try {
                this.outputStream.close();
            } catch (IOException var9) {
                var9.printStackTrace();
            }
        }

        if(this.bufferedReader != null) {
            try {
                this.bufferedReader.close();
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

        if(this.bufferedWriter != null) {
            try {
                this.bufferedWriter.close();
            } catch (IOException var7) {
                var7.printStackTrace();
            }
        }

        if(this.objectInputStream != null) {
            try {
                this.objectInputStream.close();
            } catch (IOException var6) {
                var6.printStackTrace();
            }
        }

        if(this.objectOutputStream != null) {
            try {
                this.objectOutputStream.close();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

        if(this.dataOutputStream != null) {
            try {
                this.dataOutputStream.close();
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

        if(this.dataInputStream != null) {
            try {
                this.dataInputStream.close();
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

        if(this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }
}
