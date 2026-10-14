package com.example.controller;

import com.example.common.Result;
import com.example.entity.InStock;
import com.example.service.InStockService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端请求接口
 */
@RestController
@RequestMapping("/inStock")
public class InStockController {

    @Resource
    private InStockService inStockService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody InStock inStock) {
        inStockService.add(inStock);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody InStock inStock) {
        inStockService.updateById(inStock);
        return Result.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        inStockService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        inStockService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        InStock inStock = inStockService.selectById(id);
        return Result.success(inStock);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(InStock inStock) {
        List<InStock> list = inStockService.selectAll(inStock);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(InStock inStock,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<InStock> pageInfo = inStockService.selectPage(inStock, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
