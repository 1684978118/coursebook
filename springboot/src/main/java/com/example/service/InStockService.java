package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.InStock;
import com.example.entity.Inventory;
import com.example.entity.Textbook;
import com.example.mapper.InStockMapper;
import com.example.mapper.InventoryMapper;
import com.example.mapper.TextbookMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层方法
 */
@Service
public class InStockService {

    @Resource
    private InStockMapper inStockMapper;
    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private TextbookMapper textbookMapper;

    @Transactional
    public void add(InStock inStock) {
        inStock.setTime(DateUtil.now());
        Integer textbookId = inStock.getTextbookId();
        // 根据教材的ID查询库存信息  如果没有库存，就新增记录  如果存在就更新库存
        Inventory inventory = inventoryMapper.selectByTextbookId(textbookId);
        Textbook textbook = textbookMapper.selectById(textbookId);
        inStock.setPrice(textbook.getPrice());
        if (inventory == null) {  // 没有库存  初始化一个
            Inventory inv = new Inventory();
            inv.setPrice(textbook.getPrice());
            inv.setStore(inStock.getNum());
            inv.setUnit(inStock.getUnit());
            inv.setTextbookId(textbookId);
            inventoryMapper.insert(inv);
        } else {
            inventory.setStore(inventory.getStore() + inStock.getNum());  // 更新库存
            inventoryMapper.updateById(inventory);
        }
        inStockMapper.insert(inStock);
    }

    public void updateById(InStock inStock) {
        inStockMapper.updateById(inStock);
    }

    public void deleteById(Integer id) {
        inStockMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            inStockMapper.deleteById(id);
        }
    }

    public InStock selectById(Integer id) {
        return inStockMapper.selectById(id);
    }

    public List<InStock> selectAll(InStock inStock) {
        return inStockMapper.selectAll(inStock);
    }

    public PageInfo<InStock> selectPage(InStock inStock, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<InStock> list = inStockMapper.selectAll(inStock);
        return PageInfo.of(list);
    }

}
