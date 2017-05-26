import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by adam on 2017/5/22.
 */
public interface BlogMapper {

    @Insert("insert into t_blog(id,name) values(nextval('seq_blog'),#{name})")
    Integer insert(@Param("name") String name);

    @Delete("delete from t_blog where id=#{id}")
    Integer delete(@Param("id") Integer id);

    @Delete("update t_blog set name=#{name} where id=#{id}")
    Integer update(@Param("id") Integer id, @Param("name") String name);

    @Select("SELECT * FROM t_blog WHERE id = #{id}")
    Blog getBlog(int id);

    @Select("SELECT * FROM t_blog limit #{size} offset #{start};")
    List<Blog> listBlog(@Param("size") int size, @Param("start") int start);
}