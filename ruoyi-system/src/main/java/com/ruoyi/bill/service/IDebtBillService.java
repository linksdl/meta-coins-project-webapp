package com.ruoyi.bill.service;

import java.util.List;
import com.ruoyi.bill.domain.DebtBill;

/**
 * 借贷账单Service接口
 * 
 * @author metacoin
 * @date 2022-10-29
 */
public interface IDebtBillService 
{
    /**
     * 查询借贷账单
     * 
     * @param debtId 借贷账单主键
     * @return 借贷账单
     */
    public DebtBill selectDebtBillByDebtId(Long debtId);

    /**
     * 查询借贷账单列表
     * 
     * @param debtBill 借贷账单
     * @return 借贷账单集合
     */
    public List<DebtBill> selectDebtBillList(DebtBill debtBill);

    /**
     * 新增借贷账单
     * 
     * @param debtBill 借贷账单
     * @return 结果
     */
    public int insertDebtBill(DebtBill debtBill);

    /**
     * 修改借贷账单
     * 
     * @param debtBill 借贷账单
     * @return 结果
     */
    public int updateDebtBill(DebtBill debtBill);

    /**
     * 批量删除借贷账单
     * 
     * @param debtIds 需要删除的借贷账单主键集合
     * @return 结果
     */
    public int deleteDebtBillByDebtIds(Long[] debtIds);

    /**
     * 删除借贷账单信息
     * 
     * @param debtId 借贷账单主键
     * @return 结果
     */
    public int deleteDebtBillByDebtId(Long debtId);

    /**
     * 获取所有借贷账单列表
     *
     * @return 结果
     */
    public List<DebtBill> selectDebtBillAll();



}
