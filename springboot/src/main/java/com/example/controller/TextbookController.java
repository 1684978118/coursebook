package com.example.controller;

import com.example.common.Result;
import com.example.entity.Textbook;
import com.example.service.TextbookService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端请求接口
 */
@RestController
@RequestMapping("/textbook")
public class TextbookController {

    @Resource
    private TextbookService textbookService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Textbook textbook) {
        textbookService.add(textbook);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody Textbook textbook) {
        textbookService.updateById(textbook);
        return Result.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        textbookService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        textbookService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Textbook textbook = textbookService.selectById(id);
        return Result.success(textbook);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Textbook textbook) {
        List<Textbook> list = textbookService.selectAll(textbook);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Textbook textbook,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Textbook> pageInfo = textbookService.selectPage(textbook, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
