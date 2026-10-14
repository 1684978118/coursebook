package com.example.mapper;

import com.example.entity.TextbookType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TextbookTypeMapper {

    int insert(TextbookType textbookType);

    void updateById(TextbookType textbookType);

    void deleteById(Integer id);

    @Select("select * from `textbook_type` where id = #{id}")
    TextbookType selectById(Integer id);

    List<TextbookType> selectAll(TextbookType textbookType);

}
