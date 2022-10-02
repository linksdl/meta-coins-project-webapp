package com.ruoyi.config.service;

import java.util.List;
import com.ruoyi.config.domain.Money;

/**
 * 币种管理Service接口
 * 
 * @author metacoin
 * @date 2022-10-02
 */
public interface IMoneyService 
{
    /**
     * 查询币种管理
     * 
     * @param moneyId 币种管理主键
     * @return 币种管理
     */
    public Money selectMoneyByMoneyId(Long moneyId);

    /**
     * 查询币种管理列表
     * 
     * @param money 币种管理
     * @return 币种管理集合
     */
    public List<Money> selectMoneyList(Money money);

    /**
     * 新增币种管理
     * 
     * @param money 币种管理
     * @return 结果
     */
    public int insertMoney(Money money);

    /**
     * 修改币种管理
     * 
     * @param money 币种管理
     * @return 结果
     */
    public int updateMoney(Money money);

    /**
     * 批量删除币种管理
     * 
     * @param moneyIds 需要删除的币种管理主键集合
     * @return 结果
     */
    public int deleteMoneyByMoneyIds(Long[] moneyIds);

    /**
     * 删除币种管理信息
     * 
     * @param moneyId 币种管理主键
     * @return 结果
     */
    public int deleteMoneyByMoneyId(Long moneyId);

    /**
     * 获取所有币种管理列表
     *
     * @return 结果
     */
    public List<Money> selectMoneyAll();



}
