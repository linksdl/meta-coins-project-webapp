package com.ruoyi.bill.service;

import java.util.List;
import com.ruoyi.bill.domain.IncomeBill;

/**
 * 收入账单Service接口
 * 
 * @author metacoin
 * @date 2022-10-01
 */
public interface IIncomeBillService 
{
    /**
     * 查询收入账单
     * 
     * @param incomeId 收入账单主键
     * @return 收入账单
     */
    public IncomeBill selectIncomeBillByIncomeId(Long incomeId);

    /**
     * 查询收入账单列表
     * 
     * @param incomeBill 收入账单
     * @return 收入账单集合
     */
    public List<IncomeBill> selectIncomeBillList(IncomeBill incomeBill);

    /**
     * 新增收入账单
     * 
     * @param incomeBill 收入账单
     * @return 结果
     */
    public int insertIncomeBill(IncomeBill incomeBill);

    /**
     * 修改收入账单
     * 
     * @param incomeBill 收入账单
     * @return 结果
     */
    public int updateIncomeBill(IncomeBill incomeBill);

    /**
     * 批量删除收入账单
     * 
     * @param incomeIds 需要删除的收入账单主键集合
     * @return 结果
     */
    public int deleteIncomeBillByIncomeIds(Long[] incomeIds);

    /**
     * 删除收入账单信息
     * 
     * @param incomeId 收入账单主键
     * @return 结果
     */
    public int deleteIncomeBillByIncomeId(Long incomeId);

    /**
     * 获取所有收入账单列表
     *
     * @return 结果
     */
    public List<IncomeBill> selectIncomeBillAll();



}
