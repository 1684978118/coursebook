package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Inventory;
import com.example.entity.OutStock;
import com.example.entity.Textbook;
import com.example.exception.CustomException;
import com.example.mapper.InventoryMapper;
import com.example.mapper.OutStockMapper;
import com.example.utils.TokenUtils;
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
public class OutStockService {

    @Resource
    private OutStockMapper outStockMapper;
    @Resource
    InventoryMapper inventoryMapper;

    public void add(OutStock outStock) {
        outStock.setStatus("待审批");
        outStock.setOutTime(DateUtil.now());  // 申请时间
        outStockMapper.insert(outStock);
    }

    @Transactional
    public void updateById(OutStock outStock) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.ADMIN.name().equals(currentUser.getRole())) {  // 只有管理员才可以审批
            if ("待领取".equals(outStock.getStatus())) {
                // 写相应的逻辑
                // 当点击审批通过的时候，判断库存，要去扣减库存，如果库存不足，或者没有库存，那就不能审批通过，要给出相应的提示
                Integer textbookId = outStock.getTextbookId();
                Inventory inventory = inventoryMapper.selectByTextbookId(textbookId);  // 库存信息
                if (inventory == null) {  // 没有库存
                    throw new CustomException("500", "对不起，该书没有库存");
                } else if (inventory.getStore() < outStock.getNumber()){  // 库存的余量小于本次发放的教材数量
                    throw new CustomException("500", "对不起，该书库存不足");
                } else {
                    inventory.setStore(inventory.getStore() - outStock.getNumber());  // 设置库存余量
                    inventoryMapper.updateById(inventory);
                }
            }
        } else if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            if ("已领取".equals(outStock.getStatus())) {
                outStock.setGetTime(DateUtil.now());
            }
        }
        outStockMapper.updateById(outStock);
    }

    public void deleteById(Integer id) {
        outStockMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            outStockMapper.deleteById(id);
        }
    }

    public OutStock selectById(Integer id) {
        return outStockMapper.selectById(id);
    }

    public List<OutStock> selectAll(OutStock outStock) {
        return outStockMapper.selectAll(outStock);
    }

    public PageInfo<OutStock> selectPage(OutStock outStock, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OutStock> list = outStockMapper.selectAll(outStock);
        return PageInfo.of(list);
    }

}
