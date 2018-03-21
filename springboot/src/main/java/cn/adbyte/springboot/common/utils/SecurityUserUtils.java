package cn.adbyte.springboot.common.utils;

import cn.adbyte.springboot.biz.member.entity.MemberEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Adam.yao on 2017/11/10.
 */
public class SecurityUserUtils {
    public static MemberEntity getSecurityMember() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if(principal instanceof MemberEntity) {
                    return (MemberEntity) principal;
                }
            }
        }
        return null;
    }
}
