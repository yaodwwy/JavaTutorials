import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Adam_Yao on 2017/6/22.
 * 功能：学生成绩管理系统
 * 步骤1、登录界面的静态实现
 */
public class Login extends JFrame implements ActionListener {

    //定义登录界面的组件
    JButton jb1, jb2, jb3 = null;
    JRadioButton jrb1, jrb2 = null;
    JPanel jp1, jp2, jp3, jp4 = null;
    JTextField jtf = null;
    JLabel jlb1, jlb2, jlb3 = null;
    JPasswordField jpf = null;
    ButtonGroup bg = null;

    //设定用户名和密码
    static String userword = "";
    static String pwd="";

    static Connection ct = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Login ms = new Login();


        try {

            Class.forName("org.postgresql.Driver");
            ct = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "echoshen");
            Statement statement = ct.createStatement();
            statement.execute("DROP TABLE IF EXISTS t_login_info CASCADE");
            statement.execute("CREATE TABLE t_login_info (  权限  VARCHAR(50),  用户名 VARCHAR(50),  密码  VARCHAR(50),  学号  VARCHAR(50),  姓名  VARCHAR(50))");
            statement.execute("INSERT INTO t_login_info VALUES ('学生','6','1','20150101','xiaoming')");
            statement.execute("INSERT INTO t_login_info VALUES ('老师','5','1','20150102','laowang')");

            System.out.println("初始化成功");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //构造函数
    public Login() {
        //创建组件
        jb1 = new JButton("登录");
        jb2 = new JButton("重置");
        jb3 = new JButton("退出");
        //设置监听
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        //以上三个按钮的监听统一放在了actionPerformance()中
        //监听也可以是下边这种方式
//      jb3.addActionListener(new ActionListener()
//      {
//
//          @Override
//          public void actionPerformed(ActionEvent e) {
//              // TODO Auto-generated method stub
//              System.exit(0);
//          }
//
//      });

        jrb1 = new JRadioButton("教师");
        jrb2 = new JRadioButton("学生");
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        jrb2.setSelected(true);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();

        jlb1 = new JLabel("用户名：");
        jlb2 = new JLabel("密    码：");
        jlb3 = new JLabel("权    限：");

        jtf = new JTextField(10);
        jpf = new JPasswordField(10);
        //加入到JPanel中
        jp1.add(jlb1);
        jp1.add(jtf);

        jp2.add(jlb2);
        jp2.add(jpf);

        jp3.add(jlb3);
        jp3.add(jrb1);
        jp3.add(jrb2);

        jp4.add(jb1);
        jp4.add(jb2);
        jp4.add(jb3);

        //加入JFrame中
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        //设置布局管理器
        this.setLayout(new GridLayout(4, 1));
        //给窗口设置标题
        this.setTitle("学生成绩管理系统");
        //设置窗体大小
        this.setSize(300, 200);
        //设置窗体初始位置
        this.setLocation(200, 150);
        //设置当关闭窗口时，保证JVM也退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //显示窗体
        this.setVisible(true);
        this.setResizable(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "退出") {
            System.exit(0);
        } else if (e.getActionCommand() == "登录") {
            //如果选中教师登录
            if (jrb1.isSelected()) {
                //创建火箭车
                try {
                    ps = ct.prepareStatement("select * from t_login_info where 权限=? ");
                    //给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
                    ps.setString(1, "教师");
                    //ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
                    rs = ps.executeQuery();
                    //循环取出
                    while (rs.next()) {
                        //将教师的用户名和密码取出
                        userword = rs.getString(2);
                        pwd = rs.getString(3);
                        System.out.println("成功获取到密码和用户名from数据库");
                        System.out.println(userword + "\t" + pwd + "\t");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //从数据库得到用户名和密码后调用登录方法，与输入的用户名和密码作比较
                tealogin();
            } else if (jrb2.isSelected()) //学生在登录系统
            {
                //创建火箭车
                try {
                    ps = ct.prepareStatement("select * from t_login_info where 权限=? ");
                    //给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
                    ps.setString(1, "学生");
                    //ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
                    rs = ps.executeQuery();
                    //循环取出
                    while (rs.next()) {
                        //将学生的用户名和密码取出
                        userword = rs.getString(2);
                        pwd = rs.getString(3);
                        System.out.println("成功获取到密码和用户名from数据库");
                        System.out.println(userword + "\t" + pwd + "\t");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //从数据库得到用户名和密码后调用登录方法，与输入的用户名和密码作比较
                stulogin();
            }

        } else if (e.getActionCommand() == "重置") {
            clear();
        }

    }


    //清空文本框和密码框
    public void clear() {
        jtf.setText("");
        jpf.setText("");
    }

    //学生登录判断方法
    public void stulogin() {
        if (userword.equals(jtf.getText()) && pwd.equals(jpf.getText())) {
//              System.out.println("登录成功");
            JOptionPane.showMessageDialog(null, "登录成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
            clear();
            //关闭当前界面
            dispose();
            //创建一个新界面
            UI ui = new UI();
        } else if (jtf.getText().isEmpty() && jpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名和密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
        } else if (jtf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名！", "提示消息", JOptionPane.WARNING_MESSAGE);
        } else if (jpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "用户名或者密码错误！\n请重新输入", "提示消息", JOptionPane.ERROR_MESSAGE);
            //清空输入框
            clear();
        }
    }

    //教师登录判断方法
    public void tealogin() {
        if (userword.equals(jtf.getText()) && pwd.equals(jpf.getText())) {
//              System.out.println("登录成功");
            JOptionPane.showMessageDialog(null, "登录成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
            clear();
            //关闭当前界面
            dispose();
            //创建一个新界面，适用于教师来管理学生
            UI ui = new UI();
        } else if (jtf.getText().isEmpty() && jpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名和密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
        } else if (jtf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名！", "提示消息", JOptionPane.WARNING_MESSAGE);
        } else if (jpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "用户名或者密码错误！\n请重新输入", "提示消息", JOptionPane.ERROR_MESSAGE);
            //清空输入框
            clear();
        }
    }
}
