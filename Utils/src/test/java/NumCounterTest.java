import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Adam_Yao on 2017/9/30.
 */
public class NumCounterTest {

    public static void main(String[] args) {
        String arr = "12%3 21sdas s34d dfsdz45   R3 jo34 sjkf8 3$1P 213ikflsd fdg55 kfd";
        Stream<Character> stream = IntStream.range(0, arr.length()).mapToObj(arr::charAt);
        System.out.println("ordered total: " + countNum(stream));


        String arr2 = "12%3 21sdas s34d dfsdz45   R3 jo34 sjkf8 3$1P 213ikflsd fdg55 kfd";
        Stream<Character> stream2 = IntStream.range(0, arr.length()).mapToObj(arr::charAt);
        // 调用parallel()变成并行流
        System.out.println("ordered total: " + countNum(stream2.parallel()));
//        System.out.println(arr.toCharArray().length);
    }

    static void myMain(String arr) {
        ArrayList<Integer> numbers = new ArrayList<>();
        char[] chars = arr.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                if (Character.isDigit(chars[i + 1])) {

                }
            }

        }
    }

    static int index = 0;
    static int getInt(String str) {
        String s = "";
        char[] chars = str.toCharArray();
//        while (Character.isDigit(chars[this.index])) {
//            s = s + String.valueOf(chars[index]);
//            index++;
//        }
        return Integer.valueOf(s);
    }

    private static int countNum(Stream<Character> stream) {
        NumCounter numCounter = stream.reduce(new NumCounter(0, 0, false), NumCounter::accumulate, NumCounter::combine);
        return numCounter.getSum();
    }

}