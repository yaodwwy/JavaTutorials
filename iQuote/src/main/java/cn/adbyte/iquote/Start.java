package cn.adbyte.iquote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Adam Yao on 2017/9/1.
 */
@Controller
@SpringBootApplication
public class Start {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World! ";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Start.class, args);
    }
}
