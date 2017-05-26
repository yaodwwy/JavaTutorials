package ejb.core.service;

import ejb.core.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by yaoo on 2016/9/13.
 */
@Stateless
public class FirstEjbImpl implements FirstEjbRemote, FirstEjbLocal {

    @PersistenceContext(unitName = "postgres")
    private EntityManager entityManager;

    public FirstEjbImpl() {
    }

    @Override
    public String sayHi() {
        return "sayHi";
    }

    @Override
    public List<UserEntity> getNamedQueryList() {
        long timeStart = System.currentTimeMillis();

        Query namedQuery = entityManager.createNamedQuery("UserEntity.findUserInfoList");
        namedQuery.setHint("org.hibernate.cacheable", true);
        List resultList1 = namedQuery.getResultList();

        long timeEnd = System.currentTimeMillis();
        String x = "------->>> " + (timeEnd - timeStart) + "毫秒 <<<---------";
        System.out.println(x);
        return resultList1;
    }

    @Override
    public String add() {
        for (int i = 0; i < 1200; i++) {
            UserEntity userEntity = new UserEntity("userId" + i, "username" + i, "password" + i, false, new Date(), new Date());
            entityManager.persist(userEntity);
            entityManager.flush();
        }
        return "ok";
    }

    @Override
    public List<UserEntity> getList() {
        long timeStart = System.currentTimeMillis();

        Query query = entityManager.createQuery("FROM UserEntity where password='www'");
        query.setHint("org.hibernate.cacheable", true);
        List<UserEntity> resultList = query.getResultList();

        long timeEnd = System.currentTimeMillis();
        String x = "------->>> " + (timeEnd - timeStart) + "毫秒 <<<---------";
        System.out.println(x);

        return resultList;
    }
}
