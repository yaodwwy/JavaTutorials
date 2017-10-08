package cn.adbyte.iquote.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.Charset;

@Configuration
public class TomcatConfig {
    @Bean
    public EmbeddedServletContainerFactory servletContainers(){
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.setUriEncoding(Charset.forName("utf-8"));
        tomcat.setPort(361);
        return tomcat;
    }

}
