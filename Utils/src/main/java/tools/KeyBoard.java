package tools;

import java.util.Scanner;

/**
 * Created by Adam_Yao on 2017/6/19.
 */
public class KeyBoard {
    private static Scanner input;

    static {
        input = new Scanner(System.in);
    }

    private KeyBoard() {
    }

    public static String input(String tip) {
        System.out.println(tip + ":");
        return input.next();
    }
}
