package com.example.testspr.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.testspr.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author admin
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-09-06 20:16:34
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
