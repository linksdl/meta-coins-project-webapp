package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.MoneyMapper;
import com.ruoyi.config.domain.Money;
import com.ruoyi.config.service.IMoneyService;

/**
 * 币种管理Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-02
 */
@Service
public class MoneyServiceImpl implements IMoneyService 
{
    @Autowired
    private MoneyMapper moneyMapper;

    /**
     * 查询币种管理
     * 
     * @param moneyId 币种管理主键
     * @return 币种管理
     */
    @Override
    public Money selectMoneyByMoneyId(Long moneyId)
    {
        return moneyMapper.selectMoneyByMoneyId(moneyId);
    }

    /**
     * 查询币种管理列表
     * 
     * @param money 币种管理
     * @return 币种管理
     */
    @Override
    public List<Money> selectMoneyList(Money money)
    {
        return moneyMapper.selectMoneyList(money);
    }

    /**
     * 新增币种管理
     * 
     * @param money 币种管理
     * @return 结果
     */
    @Override
    public int insertMoney(Money money)
    {
        money.setCreateTime(DateUtils.getNowDate());
        money.setUpdateTime(DateUtils.getNowDate());
        return moneyMapper.insertMoney(money);
    }

    /**
     * 修改币种管理
     * 
     * @param money 币种管理
     * @return 结果
     */
    @Override
    public int updateMoney(Money money)
    {
        money.setUpdateTime(DateUtils.getNowDate());
        return moneyMapper.updateMoney(money);
    }

    /**
     * 批量删除币种管理
     * 
     * @param moneyIds 需要删除的币种管理主键
     * @return 结果
     */
    @Override
    public int deleteMoneyByMoneyIds(Long[] moneyIds)
    {
        return moneyMapper.deleteMoneyByMoneyIds(moneyIds);
    }

    /**
     * 删除币种管理信息
     * 
     * @param moneyId 币种管理主键
     * @return 结果
     */
    @Override
    public int deleteMoneyByMoneyId(Long moneyId)
    {
        return moneyMapper.deleteMoneyByMoneyId(moneyId);
    }

    @Override
    public List<Money> selectMoneyAll(Money params) {
        return moneyMapper.selectMoneyAll(params);
    }
}
