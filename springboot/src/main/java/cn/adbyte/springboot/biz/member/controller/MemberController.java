package cn.adbyte.springboot.biz.member.controller;

import cn.adbyte.springboot.common.entity.ReturnMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Adam Yao on 2018/3/20.
 */
@RestController
@RequestMapping("member")
public class MemberController {
    @RequestMapping("get")
    public ReturnMessage get(Integer id) {
        return ReturnMessage.success(id);
    }
}
