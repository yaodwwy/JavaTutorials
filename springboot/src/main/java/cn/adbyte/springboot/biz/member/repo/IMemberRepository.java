package cn.adbyte.springboot.biz.member.repo;

import cn.adbyte.springboot.biz.member.entity.MemberEntity;
import cn.adbyte.springboot.common.repo.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;

/**
 * Created by Adam Yao on 2018/3/21.
 */
public interface IMemberRepository extends BaseRepository<MemberEntity, Long> {
    //通过@EntityGraph来指定MemberEntity类中定义的NamedEntityGraph
    @EntityGraph(value="member.all",type= EntityGraph.EntityGraphType.FETCH)
    MemberEntity findByUsername(String username);
    @EntityGraph(value="member.all",type= EntityGraph.EntityGraphType.FETCH)
    MemberEntity findByEmail(String email);
    @EntityGraph(value="member.all",type= EntityGraph.EntityGraphType.FETCH)
    MemberEntity findByMobile(String mobile);
}
