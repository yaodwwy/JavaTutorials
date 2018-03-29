package cn.adbyte.springboot.temp;

/**
 * Created by Adam Yao on 2018/3/22.
 */
public class ThreadTest {
    public void function(String a) {

        new Thread(){
            @Override
            public void run() {
                System.out.println(a);
            }
        }.start();
    }


    public static void main(String[] args) {
        new ThreadTest().function("a");

    }
}
