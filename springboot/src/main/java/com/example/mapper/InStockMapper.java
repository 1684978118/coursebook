package com.example.mapper;

import com.example.entity.InStock;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InStockMapper {

    int insert(InStock inStock);

    void updateById(InStock inStock);

    void deleteById(Integer id);

    @Select("select * from `in_stock` where id = #{id}")
    InStock selectById(Integer id);

    List<InStock> selectAll(InStock inStock);

}
