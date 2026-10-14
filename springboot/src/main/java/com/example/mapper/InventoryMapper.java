package com.example.mapper;

import com.example.entity.Inventory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InventoryMapper {

    int insert(Inventory inventory);

    void updateById(Inventory inventory);

    void deleteById(Integer id);

    @Select("select * from `inventory` where id = #{id}")
    Inventory selectById(Integer id);

    List<Inventory> selectAll(Inventory inventory);

    @Select("select * from `inventory` where textbook_id = #{textbookId}")
    Inventory selectByTextbookId(Integer textbookId);
}
