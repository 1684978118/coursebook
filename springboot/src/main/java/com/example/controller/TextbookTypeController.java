package com.example.controller;

import com.example.common.Result;
import com.example.entity.TextbookType;
import com.example.service.TextbookTypeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端请求接口
 */
@RestController
@RequestMapping("/textbookType")
public class TextbookTypeController {

    @Resource
    private TextbookTypeService textbookTypeService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody TextbookType textbookType) {
        textbookTypeService.add(textbookType);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody TextbookType textbookType) {
        textbookTypeService.updateById(textbookType);
        return Result.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        textbookTypeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        textbookTypeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        TextbookType textbookType = textbookTypeService.selectById(id);
        return Result.success(textbookType);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(TextbookType textbookType) {
        List<TextbookType> list = textbookTypeService.selectAll(textbookType);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(TextbookType textbookType,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<TextbookType> pageInfo = textbookTypeService.selectPage(textbookType, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
