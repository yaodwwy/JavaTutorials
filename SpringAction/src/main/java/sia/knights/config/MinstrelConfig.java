package sia.knights.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sia.knights.*;

/**
 * Created by Adam on 2017/5/30.
 */
@Configuration
public class MinstrelConfig {

    @Bean
    public Knight knight(){
        return new BraveKnight(quest());
    }

    @Bean
    public Quest quest(){
        return new SlayDragonQuest(System.out);
    }

    @Bean
    public Minstrel minstrel(){
        return new Minstrel(System.out);
    }

      /*<aop:config>
    <aop:aspect ref="minstrel">
      <aop:pointcut id="embark"
    expression="execution(* *.embarkOnQuest(..))"/>

      <aop:before pointcut-ref="embark"
    method="singBeforeQuest"/>

      <aop:after pointcut-ref="embark"
    method="singAfterQuest"/>
    </aop:aspect>
  </aop:config>*/
}
