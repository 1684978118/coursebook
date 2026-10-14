package com.example.mapper;

import com.example.entity.Textbook;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TextbookMapper {

    int insert(Textbook textbook);

    void updateById(Textbook textbook);

    void deleteById(Integer id);

    @Select("select * from `textbook` where id = #{id}")
    Textbook selectById(Integer id);

    List<Textbook> selectAll(Textbook textbook);

}
