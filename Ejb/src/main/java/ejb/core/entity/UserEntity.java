package ejb.core.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yaoo on 2016/9/12.
 */
@Cacheable
@Entity
@Table(name = "t_user_info")
@NamedQueries({
        @NamedQuery(name = "UserEntity.getUserInfoById", query = "select us from UserEntity us where us.userId=:userId"),
        @NamedQuery(name = "UserEntity.findUserInfoList", query = "select us from UserEntity us where us.del=false")
})
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String username;
    private String password;

    public UserEntity() {
    }

    public UserEntity(String userId, String username, String password, Boolean del, Date last, Date time) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.del=del;
        this.last=last;
        this.time=time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
