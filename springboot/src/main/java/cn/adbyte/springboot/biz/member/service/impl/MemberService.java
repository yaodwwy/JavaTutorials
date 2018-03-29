package cn.adbyte.springboot.biz.member.service.impl;

import cn.adbyte.springboot.biz.authorize.entity.RoleEntity;
import cn.adbyte.springboot.biz.member.entity.MemberEntity;
import cn.adbyte.springboot.biz.member.repo.IMemberRepository;
import cn.adbyte.springboot.biz.member.service.IMemberService;
import cn.adbyte.springboot.common.exception.ErrorCode;
import cn.adbyte.springboot.common.utils.RegexUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Adam Yao on 2018/3/21.
 */
@Service
public class MemberService implements IMemberService,UserDetailsService {
    @Resource
    private IMemberRepository iMemberRepository;
    @Override
    public MemberEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity member = null;
        if (RegexUtils.check(RegexUtils.Type.IS_EMAIL,username)) {
            member = iMemberRepository.findByEmail(username);
        } else if (RegexUtils.check(RegexUtils.Type.IS_MOBILE,username)) {
            member = iMemberRepository.findByMobile(username);
        }
        //非邮箱或手机号
        if (member == null) {
            member = iMemberRepository.findByUsername(username);
        }
        if (member == null) {
            throw new UsernameNotFoundException(ErrorCode.UsernameNotFoundException.des);
        }
        return member;
    }


    @Override
    public MemberEntity get(Long id) {
        return iMemberRepository.get(id);
    }
}
