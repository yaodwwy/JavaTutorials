package cn.adbyte.springboot.biz.member.service;

import cn.adbyte.springboot.biz.member.entity.MemberEntity;

public interface IMemberService{
    MemberEntity get(Long id);
}
