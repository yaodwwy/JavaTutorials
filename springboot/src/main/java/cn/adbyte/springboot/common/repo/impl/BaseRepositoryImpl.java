package cn.adbyte.springboot.common.repo.impl;

import cn.adbyte.springboot.biz.member.entity.MemberEntity;
import cn.adbyte.springboot.common.constant.SysConstant;
import cn.adbyte.springboot.common.entity.BaseEntity;
import cn.adbyte.springboot.common.exception.BaseException;
import cn.adbyte.springboot.common.exception.ErrorCode;
import cn.adbyte.springboot.common.repo.BaseRepository;
import cn.adbyte.springboot.common.utils.SecurityUserUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Adam.yao on 2017/11/2.
 */
public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {

    private final Class<T> domainClass;

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection"})
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
    }

    @Override
    public boolean support(String modelType) {
        return domainClass.getName().equals(modelType);
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        if (null == entity.getDel()) {
            entity.setDel(false);
        }
        if (null == entity.getOperator()) {
            Long operatorId = -1L;
            MemberEntity securityMember = SecurityUserUtils.getSecurityMember();
            if (securityMember != null) {
                operatorId = securityMember.getId();
            }
            entity.setOperator(operatorId);
        }
        if (null == entity.getTime()) {
            entity.setTime(Date.from(Instant.now()));
        }
        return super.save(entity);
    }

    @Override
    public List<T> saveAll(List<T> entitys) {
        return super.save(entitys);
    }

    @Transactional
    public T update(T entity) {
        entity.setLast(new Date());
        return super.saveAndFlush(entity);
    }

    @Override
    public void delete(T entity) {
        throw new BaseException(ErrorCode.RealDelException);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        throw new BaseException(ErrorCode.RealDelException);
    }

    @Override
    public void deleteAll() {
        throw new BaseException(ErrorCode.RealDelException);
    }

    @Override
    public void deleteAllInBatch() {
        throw new BaseException(ErrorCode.RealDelException);
    }

    @Transactional
    public boolean remove(ID id) {
        T one = findOne(id);
        if (null != one) {
            one.setDel(true);
            this.update(one);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean removeAll(List<ID> id) {
        for (ID index : id) {
            if (!remove(index)) {
                throw new BaseException(ErrorCode.NO_DATA);
            }
        }
        return true;
    }

    public boolean exists(ID id) {
        return super.exists(id);
    }

    public T findOne(ID id) {
        Assert.notNull(id, "Id's must not be null!");
        Optional<T> byId = super.findById(id);
        return byId.orElse(null);
    }

    public long count(Example example) {
        return super.count(example);
    }

    public long count(Specification spec) {
        return super.count(spec);
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) {
        List<T> all = super.findAllById(ids);
        List<T> result = new ArrayList<>();
        for (T t : all) {
            if (!t.getDel()) {
                result.add(t);
            }
        }
        return result;
    }

    public List<T> findAll(Example example) throws BaseException {
        List<T> all = super.findAll(example);
        if (all.size() > SysConstant.MAX_SIZE) {
            all.clear();
            throw new BaseException(ErrorCode.ReturnSizeTooLargeException);
        }
        return all;
    }

    public List<T> findAll(Specification spec) {
        List<T> all = super.findAll(spec);
        if (all.size() > SysConstant.MAX_SIZE) {
            all.clear();
            throw new BaseException(ErrorCode.ReturnSizeTooLargeException);
        }
        return all;
    }

    public List<T> findAll(Specification spec, Sort sort) {
        List<T> all = super.findAll(spec, sort);
        if (all.size() > SysConstant.MAX_SIZE) {
            all.clear();
            throw new BaseException(ErrorCode.ReturnSizeTooLargeException);
        }
        return all;
    }

    public Page<T> findAll(Example example, Pageable pageable) {
        if (pageable.getPageSize() > SysConstant.MAX_SIZE) {
            throw new BaseException(ErrorCode.ReturnSizeTooLargeException);
        }
        return super.findAll(example, pageable);
    }


    public Page<T> findAll(Specification spec, Pageable pageable) {
        if (pageable.getPageSize() > SysConstant.MAX_SIZE) {
            throw new BaseException(ErrorCode.ReturnSizeTooLargeException);
        }
        return super.findAll(spec, pageable);
    }



}
