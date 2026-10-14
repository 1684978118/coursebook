package com.example.mapper;

import com.example.entity.OutStock;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OutStockMapper {

    int insert(OutStock outStock);

    void updateById(OutStock outStock);

    void deleteById(Integer id);

    @Select("select * from `out_stock` where id = #{id}")
    OutStock selectById(Integer id);

    List<OutStock> selectAll(OutStock outStock);

}
