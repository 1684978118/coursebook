package com.example.service;

import com.example.entity.TextbookType;
import com.example.mapper.TextbookTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层方法
 */
@Service
public class TextbookTypeService {

    @Resource
    private TextbookTypeMapper textbookTypeMapper;

    public void add(TextbookType textbookType) {
        textbookTypeMapper.insert(textbookType);
    }

    public void updateById(TextbookType textbookType) {
        textbookTypeMapper.updateById(textbookType);
    }

    public void deleteById(Integer id) {
        textbookTypeMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            textbookTypeMapper.deleteById(id);
        }
    }

    public TextbookType selectById(Integer id) {
        return textbookTypeMapper.selectById(id);
    }

    public List<TextbookType> selectAll(TextbookType textbookType) {
        return textbookTypeMapper.selectAll(textbookType);
    }

    public PageInfo<TextbookType> selectPage(TextbookType textbookType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TextbookType> list = textbookTypeMapper.selectAll(textbookType);
        return PageInfo.of(list);
    }

}
