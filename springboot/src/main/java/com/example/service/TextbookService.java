package com.example.service;

import com.example.entity.Inventory;
import com.example.entity.Textbook;
import com.example.mapper.InventoryMapper;
import com.example.mapper.TextbookMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层方法
 */
@Service
public class TextbookService {

    @Resource
    private TextbookMapper textbookMapper;
    @Resource
    InventoryMapper inventoryMapper;

    public void add(Textbook textbook) {
        textbookMapper.insert(textbook);
    }

    public void updateById(Textbook textbook) {
        textbookMapper.updateById(textbook);
    }

    public void deleteById(Integer id) {
        textbookMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            textbookMapper.deleteById(id);
        }
    }

    public Textbook selectById(Integer id) {
        return textbookMapper.selectById(id);
    }

    public List<Textbook> selectAll(Textbook textbook) {
        return textbookMapper.selectAll(textbook);
    }

    public PageInfo<Textbook> selectPage(Textbook textbook, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Textbook> list = textbookMapper.selectAll(textbook);
        for (Textbook book : list) {
            Inventory inventory = inventoryMapper.selectByTextbookId(book.getId());
            book.setStore(inventory == null ? 0 : inventory.getStore());
        }
        return PageInfo.of(list);
    }

}
