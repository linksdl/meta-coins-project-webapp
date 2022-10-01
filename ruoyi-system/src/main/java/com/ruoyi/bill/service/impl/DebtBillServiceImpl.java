package com.ruoyi.bill.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bill.mapper.DebtBillMapper;
import com.ruoyi.bill.domain.DebtBill;
import com.ruoyi.bill.service.IDebtBillService;

/**
 * 借贷账单Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@Service
public class DebtBillServiceImpl implements IDebtBillService 
{
    @Autowired
    private DebtBillMapper debtBillMapper;

    /**
     * 查询借贷账单
     * 
     * @param debtId 借贷账单主键
     * @return 借贷账单
     */
    @Override
    public DebtBill selectDebtBillByDebtId(Long debtId)
    {
        return debtBillMapper.selectDebtBillByDebtId(debtId);
    }

    /**
     * 查询借贷账单列表
     * 
     * @param debtBill 借贷账单
     * @return 借贷账单
     */
    @Override
    public List<DebtBill> selectDebtBillList(DebtBill debtBill)
    {
        return debtBillMapper.selectDebtBillList(debtBill);
    }

    /**
     * 新增借贷账单
     * 
     * @param debtBill 借贷账单
     * @return 结果
     */
    @Override
    public int insertDebtBill(DebtBill debtBill)
    {
        debtBill.setCreateTime(DateUtils.getNowDate());
        debtBill.setUpdateTime(DateUtils.getNowDate());
        return debtBillMapper.insertDebtBill(debtBill);
    }

    /**
     * 修改借贷账单
     * 
     * @param debtBill 借贷账单
     * @return 结果
     */
    @Override
    public int updateDebtBill(DebtBill debtBill)
    {
        debtBill.setUpdateTime(DateUtils.getNowDate());
        return debtBillMapper.updateDebtBill(debtBill);
    }

    /**
     * 批量删除借贷账单
     * 
     * @param debtIds 需要删除的借贷账单主键
     * @return 结果
     */
    @Override
    public int deleteDebtBillByDebtIds(Long[] debtIds)
    {
        return debtBillMapper.deleteDebtBillByDebtIds(debtIds);
    }

    /**
     * 删除借贷账单信息
     * 
     * @param debtId 借贷账单主键
     * @return 结果
     */
    @Override
    public int deleteDebtBillByDebtId(Long debtId)
    {
        return debtBillMapper.deleteDebtBillByDebtId(debtId);
    }

    @Override
    public List<DebtBill> selectDebtBillAll() {
        return debtBillMapper.selectDebtBillAll();
    }
}
