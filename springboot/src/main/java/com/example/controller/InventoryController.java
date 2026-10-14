package com.example.controller;

import com.example.common.Result;
import com.example.entity.Inventory;
import com.example.service.InventoryService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端请求接口
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Resource
    private InventoryService inventoryService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Inventory inventory) {
        inventoryService.add(inventory);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody Inventory inventory) {
        inventoryService.updateById(inventory);
        return Result.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        inventoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        inventoryService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Inventory inventory = inventoryService.selectById(id);
        return Result.success(inventory);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Inventory inventory) {
        List<Inventory> list = inventoryService.selectAll(inventory);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Inventory inventory,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Inventory> pageInfo = inventoryService.selectPage(inventory, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
