import org.junit.Test;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Java8LambdaTest {
    /**
     * Predicate接口
     */
    @Test
    public void predicateTest() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        boolean foo = predicate.test("foo");// true
        System.out.println(foo);
        boolean foo1 = predicate.negate().test("foo");// false
        System.out.println(foo1);

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }
    @Test
    public void functionTest(){
        Function<String, Integer> toInteger = Integer::valueOf;
//        Function<String, String> backToString = toInteger.andThen(String::valueOf);
//        backToString.apply("123");     // "123"
        Integer apply = toInteger.apply("123");
        System.out.println(apply);
    }
}
