package cn.adbyte.springboot.biz.authorize.service.impl;

import cn.adbyte.springboot.biz.authorize.entity.ResourceEntity;
import cn.adbyte.springboot.biz.authorize.entity.RoleEntity;
import cn.adbyte.springboot.biz.authorize.repo.IRoleRepository;
import cn.adbyte.springboot.biz.authorize.service.IRoleService;
import cn.adbyte.springboot.common.impl.BasePageImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleService implements IRoleService {

    @Resource
    private IRoleRepository iRoleRepository;

    @Override
    public RoleEntity get(Long roleID) {
        return iRoleRepository.getOne(roleID);
    }

    @Override
    public Page<RoleEntity> list(BasePageImpl page, RoleEntity role) {
        Specification<RoleEntity> roleSpec = new Specification<>() {
            @Override
            public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Path<Object> del1 = root.get("del");
//                Path<ResourceEntity> resource = root.get("resource");
//                Predicate id = criteriaBuilder.lt(resource.get("id"), 200);
                Predicate del = criteriaBuilder.equal(del1, true);
//                predicates.add(id);
                predicates.add(del);
                Predicate[] restrictions = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(restrictions);
            }
        };
        /*Specification<ResourceEntity> resourceSpec = new Specification<>() {
            @Override
            public Predicate toPredicate(Root<ResourceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Predicate del = criteriaBuilder.equal(root.get("del"), false);
                Predicate description = criteriaBuilder.lt(root.get("id"), 100);
                predicates.add(del);
                predicates.add(description);
                Predicate[] restrictions = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(restrictions);
            }
        };*/
        PageRequest requestPage = page.getRequestPage();
        return iRoleRepository.findAllByDel(true, requestPage);
    }
}
