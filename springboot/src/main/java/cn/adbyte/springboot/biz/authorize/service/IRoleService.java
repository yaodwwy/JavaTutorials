package cn.adbyte.springboot.biz.authorize.service;

import cn.adbyte.springboot.biz.authorize.entity.RoleEntity;
import cn.adbyte.springboot.common.impl.BasePageImpl;
import org.springframework.data.domain.Page;

public interface IRoleService {

    RoleEntity get(Long roleID);

    Page<RoleEntity> list(BasePageImpl page, RoleEntity role);

}
