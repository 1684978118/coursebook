package com.example.service;

import com.example.entity.Inventory;
import com.example.mapper.InventoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层方法
 */
@Service
public class InventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

    public void add(Inventory inventory) {
        inventoryMapper.insert(inventory);
    }

    public void updateById(Inventory inventory) {
        inventoryMapper.updateById(inventory);
    }

    public void deleteById(Integer id) {
        inventoryMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            inventoryMapper.deleteById(id);
        }
    }

    public Inventory selectById(Integer id) {
        return inventoryMapper.selectById(id);
    }

    public List<Inventory> selectAll(Inventory inventory) {
        return inventoryMapper.selectAll(inventory);
    }

    public PageInfo<Inventory> selectPage(Inventory inventory, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Inventory> list = inventoryMapper.selectAll(inventory);
        return PageInfo.of(list);
    }

}
