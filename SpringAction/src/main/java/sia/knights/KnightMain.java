package sia.knights;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.
        ClassPathXmlApplicationContext;
import sia.knights.config.KnightConfig;

public class KnightMain {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/knight.xml");
        //AOP方式
        ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("META-INF/spring/minstrel.xml");
        //注解获取上下文
        AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext(KnightConfig.class);
        Knight knight = ctx1.getBean(Knight.class);
        knight.embarkOnQuest();
        ctx1.close();
    }

}
