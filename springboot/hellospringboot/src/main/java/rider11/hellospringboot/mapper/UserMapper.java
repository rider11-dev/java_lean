package rider11.hellospringboot.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import rider11.hellospringboot.bean.MyUser;

@Mapper
public interface UserMapper {
    // 适合复杂sql
    MyUser getByUserNameAndPassword(MyUser user);

    // 注解方式：适合简单sql
    @Delete("delete from my_user where id = #{id,jdbcType=INTEGER}")
    int deleteByPrimaryKey(Integer id);
}
