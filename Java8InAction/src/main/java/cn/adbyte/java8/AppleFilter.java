package cn.adbyte.java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Adam_Yao on 2017/9/28.
 */
public class AppleFilter {
    @FunctionalInterface
    public interface StringNotBlankFilter {
        boolean filterStringBlank(String... strings);
    }

    @FunctionalInterface
    public interface AppleFindFilter {
        boolean filter(Apple apple);

        //        boolean isOk();
        default void isTrue() {
            System.out.println("default");
        }

    }

    /*public static class redApplethen120Filter implements AppleFindFilter {
        @Override
        public boolean filter(Apple apple) {
            return (apple.getWeight() > 120 && "red".equals(apple.getColor()));
        }
    }*/

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


    public static void main(String[] args) throws InterruptedException {

        List<Apple> list = new ArrayList<Apple>(Arrays.asList(new Apple("green", 150),
                new Apple("red", 170), new Apple("yellow", 100), new Apple("green", 100)));

//        list.sort((c1,c2)->c1.getColor().compareTo(c2.getColor()));
//        System.out.println(list);

        list.sort(Comparator.comparing(Apple::getColor));
        System.out.println(list);




        List<Apple> result = findApple(list, new AppleFindFilter() {
            @Override
            public boolean filter(Apple apple) {
                return apple.getColor().equals("green");
            }

            @Override
            public void isTrue() {
                System.out.println(Thread.currentThread().getName());
            }
        });
//        assert apple == null : "is ok";
//        System.out.println(result);
        List<Apple> green = findApple(list, (Apple a) -> {
            return a.getColor().equals("green");
        });

//        System.out.println(green);

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//
//        });
//        thread.start();
//        Thread.currentThread().join();//让主线程等

//        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        Consumer<String> stringConsumer = (String s) -> s.length();

        Function<List<String>, Boolean> stringFunction = Objects::nonNull;
//            for (String str : s) {
//                if (str == null || str.trim() == ""){
//                    return false;
//                }
//            }
//            return true;
        String obj = ";;";
        List<String> foo = Arrays.asList("",obj,"ate");
        Boolean apply = stringFunction.apply(foo);
//        System.out.println(apply+"--->>");


        Runnable runnable = () -> {
        };
        runnable.run();

        String a = "a", b = "test", c = "1";

        StringNotBlankFilter stringNotBlankFilter = strings -> {
            for (String str : strings) {
                if (str == null || str.trim() == ""){
                    return false;
                }
            }
            return true;
        };

        boolean b1 = stringNotBlankFilter.filterStringBlank(a, b, c);
//        System.out.println(b1);
    }


}
