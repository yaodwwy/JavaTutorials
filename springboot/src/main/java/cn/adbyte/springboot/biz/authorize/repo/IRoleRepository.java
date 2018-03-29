package cn.adbyte.springboot.biz.authorize.repo;

import cn.adbyte.springboot.biz.authorize.entity.RoleEntity;
import cn.adbyte.springboot.common.repo.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRoleRepository extends BaseRepository<RoleEntity, Long> {
//    @EntityGraph(value = "role.resource", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM RoleEntity r where r.del=:del")
    Page<RoleEntity> findAllByDel(@Param("del") Boolean del, Pageable pageable);
}
