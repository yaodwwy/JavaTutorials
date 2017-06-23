import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Adam_Yao on 2017/6/22.
 */
public class JFrameTest extends JFrame {
    public static void main(String[] args) {
        JFrameTest2 jFrameTest2 = new JFrameTest2();
        //创建并添加菜单栏
        JMenuBar menuBar = new JMenuBar();
        jFrameTest2.setJMenuBar(menuBar);
        JMenu menuFile = new JMenu("文件(F)"), menuEdit = new JMenu("编辑(E)"), menuView = new JMenu("查看(V)");
        menuFile.setMnemonic('F');
        menuEdit.setMnemonic('E');
        menuView.setMnemonic('V');
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuView);

        //添加“文件”菜单的各菜单项
        JMenuItem itemOpen = new JMenuItem("打开");
        itemOpen.setMnemonic('O');
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        JMenuItem itemSave = new JMenuItem("保存");
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        JMenuItem itemExit = new JMenuItem("退出(Q)");
//        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        itemExit.setMnemonic(KeyEvent.VK_Q);
        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        menuFile.add(itemExit);

        itemExit.addActionListener(new itemExitListener());

        //添加“编辑”菜单的各菜单项
        JMenuItem itemCopy = new JMenuItem("复制");
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
        menuEdit.add(itemCopy);

        //添加“查看”菜单的各菜单项
        JMenuItem itemStop = new JMenuItem("停止"), itemRefresh = new JMenuItem("刷新");
        itemStop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        itemRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
        itemRefreshListener itemRefreshListener = new itemRefreshListener();
        itemRefreshListener.setjFrameTest2(jFrameTest2);
        itemRefresh.addActionListener(itemRefreshListener);
        menuView.add(itemStop);
        menuView.add(itemRefresh);

        jFrameTest2.setVisible(true);

    }
}

class JFrameTest2 extends JFrame{
    public JFrameTest2() throws HeadlessException {
        //设置布局管理器
        this.setLayout(new GridLayout(4, 1));
        //给窗口设置标题
        this.setTitle("给窗口设置标题");
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
}

class itemExitListener implements ActionListener{
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
class itemRefreshListener implements ActionListener{
   private JFrameTest2 jFrameTest2;
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        jFrameTest2.setVisible(false);
        System.out.println("窗口已关闭");
        jFrameTest2.setVisible(true);
        System.out.println("窗口已打开");
    }

    public JFrameTest2 getjFrameTest2() {
        return jFrameTest2;
    }

    public void setjFrameTest2(JFrameTest2 jFrameTest2) {
        this.jFrameTest2 = jFrameTest2;
    }
}