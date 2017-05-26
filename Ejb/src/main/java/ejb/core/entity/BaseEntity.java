package ejb.core.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaoo on 2016/9/12.
 */

@MappedSuperclass
public class BaseEntity implements Serializable {

    protected Boolean del;
    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date last;
    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date time;

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public Date getLast() {
        return this.last;
    }

    public void setLast(Date last) {
        this.last = last;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @SuppressWarnings("unchecked")
    public Map getAllField() {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<? extends BaseEntity> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String fieldName = "";
        String methodName = "";
        Method method = null;
        Object val = null;
        for (Field field : fields) {
            fieldName = field.getName();
            methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                method = clazz.getMethod(methodName);
                if (method != null) {
                    val = method.invoke(this, new Object());
                    map.put(fieldName, val);
                }
            } catch (Exception e) {
            }
        }
        return map;
    }
}
