package com.mao.community.mapper;

import com.mao.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author maoea
 */
@Repository
@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) " +
            "values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);


    @Select("select * from user where token=#{token}")
    User findUserByToken(@Param("token") String token);
}
