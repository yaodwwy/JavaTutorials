import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation {

}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Test {

}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface UseCase {
    public int id();
    public String description() default "no description";
}

class testable {

    void execute() {
        System.out.println("Execute...");
    }

    @Test
    void testExecute() {
        execute();
    }

    @UseCase(id = 47, description = "Password must contain at least on numeric")
    public boolean validatePassword(String password) {
        return password.matches("\\w*\\d\\w*");
    }

    public static void main(String[] args) {
        Method[] declaredMethods = testable.class.getDeclaredMethods();
        for (Method method:
             declaredMethods) {
            UseCase annotation = method.getAnnotation(UseCase.class);
            if(annotation!=null)
            System.out.println(annotation.id()+"-->>"+annotation.description());
        }
    }
}