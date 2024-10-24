package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.DemoUser;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author 28365
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Mapper
* @createDate 2024-10-23 22:15:42
* @Entity generator.domain.DemoUser
*/
public interface DemoUserMapper extends BaseMapper<DemoUser> {
    @Select({
            "<script>",
            "SELECT * FROM demo_user",
            "WHERE 1=1",
            "<if test='id != null'> AND id = #{id}</if>",
            "<if test='name != null'> AND name = #{name}</if>",
            "</script>"
    })
    List<DemoUser> search(@Param("id") Long id, @Param("name") String name);


    @Insert("INSERT INTO demo_user (name, age, email, phone_number, birth_date, plan_date, score, hobbies, agree_terms) VALUES (#{name}, #{age}, #{email}, #{phone_number}, #{a}, #{b}, #{c}, #{d}, #{e})")
    void add(@Param("name") String name,
             @Param("age") int age,
             @Param("email") String email,
             @Param("phone_number") String phone_number,
             @Param("a") LocalDate a,
             @Param("b") LocalDate b,
             @Param("c") double c,
             @Param("d") String d,
             @Param("e") boolean e);

    @Delete({
            "<script>",
            "delete FROM demo_user",
            "WHERE 1=1",
            "<if test='id != null'> AND id = #{id}</if>",
            "<if test='name != null'> AND name = #{name}</if>",
            "</script>"
    })
    void delete(@Param("id")Long id,@Param("name") String name);

    @Update("update demo_user set phone_number=#{number} where id=#{id}")
    void update(@Param("number")String number,@Param("id")Long id);
}




