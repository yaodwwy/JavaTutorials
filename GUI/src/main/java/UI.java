import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adam_Yao on 2017/6/22.
 */
public class UI extends JFrame implements ActionListener
{

    //定义组件
    JButton jb1,jb2=null;
    JPanel jp1,jp2,jp3=null;
    JLabel jlb1,jlb2,jlb3,jlb4=null;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
          UI  ui=new UI();
    }

    //构造函数
    public  UI()    //不能申明为void!!!!!否则弹不出新界面
    {
        //创建组件
        jb1=new JButton("课程管理");
        jb2=new JButton("成绩查询");

        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();

        jlb1=new JLabel("姓名");
        jlb2=new JLabel("学号");
        jlb3=new JLabel("最新公告：");
        jlb4=new JLabel("我校举行六十周年校庆的通知");

        jp1.add(jlb1);
        jp1.add(jlb2);

        jp2.add(jb1);
        jp2.add(jlb3);

        jp3.add(jb2);
        jp3.add(jlb4);


        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        //设置布局管理器
        this.setLayout(new GridLayout(3,3,50,50));
        this.setTitle("学生成绩管理系统");
        this.setSize(400,300);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}