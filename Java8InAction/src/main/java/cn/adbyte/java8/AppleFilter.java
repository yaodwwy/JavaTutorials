package cn.adbyte.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Adam_Yao on 2017/9/28.
 */
public class AppleFilter {

    public interface AppleFindFilter {
        public boolean filter(Apple apple);
    }

    public static class redApplethen120Filter implements AppleFindFilter {
        @Override
        public boolean filter(Apple apple) {
            return (apple.getWeight() > 120 && "red".equals(apple.getColor()));
        }
    }

    public static List<Apple> findApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }


    public static List<Apple> findGreenlessweight120Apples(List<Apple> apples, Apple apple) {
        List<Apple> list = new ArrayList<Apple>();
        for (Apple a : apples) {
            if (apple.getColor().equals(a.getColor()) && a.getWeight() < apple.getWeight()) {
                list.add(a);
            }
        }
        return list;
    }

    public static List<Apple> findApple(List<Apple> apples, AppleFindFilter findFilter) {
        List<Apple> list = new ArrayList<Apple>();
        for (Apple a : apples) {
            if (findFilter.filter(a)) {
                list.add(a);
            }
        }
        return list;
    }


    public static void main(String[] args) {

        List<Apple> list = new ArrayList<Apple>(Arrays.asList(new Apple("green", 150),
                new Apple("red", 170), new Apple("yellow", 100), new Apple("green", 100)));

        List<Apple> apple = findApple(list, new redApplethen120Filter());
//        assert apple == null : "is ok";
        System.out.println(apple);


    }

}
