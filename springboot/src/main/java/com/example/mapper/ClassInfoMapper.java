package com.example.mapper;

import com.example.entity.ClassInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClassInfoMapper {

    int insert(ClassInfo classInfo);

    void updateById(ClassInfo classInfo);

    void deleteById(Integer id);

    @Select("select * from `class_info` where id = #{id}")
    ClassInfo selectById(Integer id);

    List<ClassInfo> selectAll(ClassInfo classInfo);

}
