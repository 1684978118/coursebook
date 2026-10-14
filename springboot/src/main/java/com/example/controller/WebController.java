package com.example.controller;

import com.example.common.Result;
import com.example.common.enums.RoleEnum;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.service.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class WebController {

    @Resource
    private AdminService adminService;
    @Resource
    StudentService studentService;
    @Resource
    TeacherService teacherService;

    @Resource
    InStockService inStockService;
    @Resource
    OutStockService outStockService;
    @Resource
    InventoryService inventoryService;

    /**
     * 默认请求接口
     */
    @GetMapping("/")
    public Result hello () {
        return Result.success();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account loginAccount = null;
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            loginAccount = adminService.login(account);
        } else if (RoleEnum.STUDENT.name().equals(account.getRole())) {
            loginAccount = studentService.login(account);
        } else if (RoleEnum.TEACHER.name().equals(account.getRole())) {
            loginAccount = teacherService.login(account);
        } else {
            throw new CustomException("500", "非法登录");
        }
        return Result.success(loginAccount);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody Student student) {
        studentService.add(student);
        return Result.success();
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        } else if (RoleEnum.TEACHER.name().equals(account.getRole())) {
            teacherService.updatePassword(account);
        } else if (RoleEnum.STUDENT.name().equals(account.getRole())) {
            studentService.updatePassword(account);
        }
        return Result.success();
    }

    @GetMapping("/selectCount")
    public Result selectCount() {
        Map<String, Object> map = new HashMap<>();

        List<InStock> inStockList = inStockService.selectAll(null);
        Integer inStock = inStockList.stream().map(InStock::getNum).filter(Objects::nonNull).reduce(Integer::sum).orElse(0);
        map.put("inStock", inStock);

        List<OutStock> outStockList = outStockService.selectAll(null);
        Integer outStock = outStockList.stream().filter(o -> !"拒绝".equals(o.getStatus()) && !"待审批".equals(o.getStatus())).map(OutStock::getNumber).filter(Objects::nonNull).reduce(Integer::sum).orElse(0);
        map.put("outStock", outStock);

        List<Inventory> inventoryList = inventoryService.selectAll(null);
        Integer inventory = inventoryList.stream().map(Inventory::getStore).filter(Objects::nonNull).reduce(Integer::sum).orElse(0);
        map.put("inventory", inventory);

        return Result.success(map);
    }

    @GetMapping("/pie")
    public Result selectPie() {
        List<Inventory> inventoryList = inventoryService.selectAll(null);
        List<Map<String, Object>> list = new ArrayList<>();
        inventoryList.stream()
                .filter(inventory -> inventory.getTextbookName() != null)
                .collect(Collectors.groupingBy(Inventory::getTextbookName,
                        Collectors.summingInt(inventory -> inventory.getStore() == null ? 0 : inventory.getStore())))
                .forEach((name, count) -> {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("value", count);
                    list.add(map);
                });
        return Result.success(list);
    }

}
