package com.example.controller;

import com.example.common.Result;
import com.example.entity.OutStock;
import com.example.service.OutStockService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端请求接口
 */
@RestController
@RequestMapping("/outStock")
public class OutStockController {

    @Resource
    private OutStockService outStockService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody OutStock outStock) {
        outStockService.add(outStock);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody OutStock outStock) {
        outStockService.updateById(outStock);
        return Result.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        outStockService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        outStockService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        OutStock outStock = outStockService.selectById(id);
        return Result.success(outStock);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(OutStock outStock) {
        List<OutStock> list = outStockService.selectAll(outStock);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(OutStock outStock,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<OutStock> pageInfo = outStockService.selectPage(outStock, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
