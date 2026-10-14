package com.example.service;

import com.example.entity.ClassInfo;
import com.example.mapper.ClassInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层方法
 */
@Service
public class ClassInfoService {

    @Resource
    private ClassInfoMapper classInfoMapper;

    public void add(ClassInfo classInfo) {
        classInfoMapper.insert(classInfo);
    }

    public void updateById(ClassInfo classInfo) {
        classInfoMapper.updateById(classInfo);
    }

    public void deleteById(Integer id) {
        classInfoMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            classInfoMapper.deleteById(id);
        }
    }

    public ClassInfo selectById(Integer id) {
        return classInfoMapper.selectById(id);
    }

    public List<ClassInfo> selectAll(ClassInfo classInfo) {
        return classInfoMapper.selectAll(classInfo);
    }

    public PageInfo<ClassInfo> selectPage(ClassInfo classInfo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClassInfo> list = classInfoMapper.selectAll(classInfo);
        return PageInfo.of(list);
    }

}
