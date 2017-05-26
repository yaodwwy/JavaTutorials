package ejb.core.service;

import ejb.core.entity.UserEntity;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yaoo on 2016/9/13.
 */
public interface FirstEjb extends Serializable{

    public String sayHi();
    public List<UserEntity> getNamedQueryList();
    public String add();
    public List<UserEntity> getList();
}
