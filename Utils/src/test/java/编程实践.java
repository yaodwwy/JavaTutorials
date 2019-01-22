import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.swing.*;
import java.util.Scanner;

@RunWith(JUnit4.class)
public class 编程实践 {
    /**
     * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，
     * 小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
     * 1.程序分析：   兔子的规律为数列1,1,2,3,5,8,13,21....
     */
    @Test
    public void test1() {
        for (int i = 1; i <= 20; i++) {
            System.out.println(f(i));
        }
    }

    public static int f(int x) {
        if (x == 1 || x == 2)
            return 1;
        else
            return f(x - 1) + f(x - 2);
    }

    /**
     * 题目：判断101-200之间有多少个素数，并输出所有素数。
     * 1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，
     * 则表明此数不是素数，反之是素数。
     * 因为假设数 m=p*q ,且 p≤q 则 m=p*q ≥ p*p
     * 即p≤√m所以m必有一个小于或等于其平方根的因数,
     * 那么验证素数时就只需要验证到其平方根就可以
     */

    @Test
    public void test2() {
        int count = 0;
        long start = System.currentTimeMillis();
        for (int i = 101; i < 200; i++) {
            double sqrt = Math.sqrt(i);
//            System.out.print("{"+sqrt+"}");
            var b = false;
            for (int j = 2; j <= sqrt; j++) {
                if (i % j == 0) {
                    b = false;
                    break;
                } else {
                    b = true;
                }
            }
            if (b) {
                count++;
                System.out.print(i + ", ");
            }
        }
        System.out.println("\n素数个数为: " + count);
        System.out.println("时间耗费: " + (System.currentTimeMillis() - start) + "毫秒");
    }

    /**
     * 打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。
     * 例如：153是一个 "水仙花数 "，因为153=1的三次方＋5的三次方＋3的三次方。
     * 1.程序分析：利用for循环控制100-999个数，每个数分解出个位，十位，百位。
     */
    @Test
    public void test3() {
        int b1, b2, b3;
        for (int i = 100; i < 1000; i++) {
            b3 = i / 100;
            b2 = i % 100 / 10;
            b1 = i % 10;
            if (b1 * b1 * b1 + b2 * b2 * b2 + b3 * b3 * b3 == i) {
                System.out.println(b1 + "," + b2 + "," + b3 + "," + i);
                System.out.println(i + "是一个水仙花数.");
            }
        }
    }

    /**
     * 题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。     
     * 程序分析：对n进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成：     
     * (1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。     
     * (2)如果n <> k，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的正整数你,重复执行第一步。     
     * (3)如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
     */
    @Test
    public void test4() {
        String s = JOptionPane.showInputDialog("请输入N的值（输入exit退出）：");
        int n = Integer.parseInt(s);
        int k = 2;
        System.out.print(n + "=");
        while (k <= n) {
            if (k == n) {
                System.out.println(n);
                break;
            } else if (n % k == 0) {
                System.out.print(k + "*");
                n = n / k;
            } else {
                k++;
            }
        }
    }

    /**
     * 题目：利用条件运算符的嵌套来完成此题：学习成绩> =90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。
     * 1.程序分析：(a> b)?a:b这是条件运算符的基本例子。
     */
    @Test
    public void test5() {
        String s = JOptionPane.showInputDialog("请输入成绩的值（输入exit退出）：");
        int i = Integer.parseInt(s);
        s = i >= 90 ? "A" : i > 60 ? "B" : "C";
        JOptionPane.showMessageDialog(null, "成绩: " + s);
    }

    /**
     * 题目：输入两个正整数m和n，求其最大公约数和最小公倍数。
     * 1.程序分析：利用辗除法。
     */
    @Test
    public void test6() {
        String s1 = JOptionPane.showInputDialog("请输入第一个数：");
        String s2 = JOptionPane.showInputDialog("请输入第二个数：");
        int i1 = Integer.parseInt(s1);
        int i2 = Integer.parseInt(s2);
        if (i1 < 0 || i2 < 0) {
            System.exit(-1);
        }
        int i = commonDivisor(i1, i2);
        JOptionPane.showMessageDialog(null, "最大公约数：" + i);
        JOptionPane.showMessageDialog(null, "最小公倍数：" + i1 * i2 / i);
    }

    /**
     * 最大公约数
     */
    int commonDivisor(int m, int n) {
        if (n == 0) {
            return m;
        } else {
            return commonDivisor(n, m % n);
        }
    }

    /**
     * 利用递归方法求5!
     */
    void test22() {
        int n = 5;
        Rec fr = new Rec();
        System.out.println(n + "!=" + fr.rec(n));
    }


}

class Rec {
    public long rec(int n) {
        long value = 0;
        if (n == 1) {
            value = 1;
        } else {
            value = n * rec(n - 1);
        }
        return value;
    }
}
