package com.ruoyi.bill.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bill.mapper.IncomeBillMapper;
import com.ruoyi.bill.domain.IncomeBill;
import com.ruoyi.bill.service.IIncomeBillService;

/**
 * 收入账单Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@Service
public class IncomeBillServiceImpl implements IIncomeBillService 
{
    @Autowired
    private IncomeBillMapper incomeBillMapper;

    /**
     * 查询收入账单
     * 
     * @param incomeId 收入账单主键
     * @return 收入账单
     */
    @Override
    public IncomeBill selectIncomeBillByIncomeId(Long incomeId)
    {
        return incomeBillMapper.selectIncomeBillByIncomeId(incomeId);
    }

    /**
     * 查询收入账单列表
     * 
     * @param incomeBill 收入账单
     * @return 收入账单
     */
    @Override
    public List<IncomeBill> selectIncomeBillList(IncomeBill incomeBill)
    {
        return incomeBillMapper.selectIncomeBillList(incomeBill);
    }

    /**
     * 新增收入账单
     * 
     * @param incomeBill 收入账单
     * @return 结果
     */
    @Override
    public int insertIncomeBill(IncomeBill incomeBill)
    {
        incomeBill.setCreateTime(DateUtils.getNowDate());
        incomeBill.setUpdateTime(DateUtils.getNowDate());
        return incomeBillMapper.insertIncomeBill(incomeBill);
    }

    /**
     * 修改收入账单
     * 
     * @param incomeBill 收入账单
     * @return 结果
     */
    @Override
    public int updateIncomeBill(IncomeBill incomeBill)
    {
        incomeBill.setUpdateTime(DateUtils.getNowDate());
        return incomeBillMapper.updateIncomeBill(incomeBill);
    }

    /**
     * 批量删除收入账单
     * 
     * @param incomeIds 需要删除的收入账单主键
     * @return 结果
     */
    @Override
    public int deleteIncomeBillByIncomeIds(Long[] incomeIds)
    {
        return incomeBillMapper.deleteIncomeBillByIncomeIds(incomeIds);
    }

    /**
     * 删除收入账单信息
     * 
     * @param incomeId 收入账单主键
     * @return 结果
     */
    @Override
    public int deleteIncomeBillByIncomeId(Long incomeId)
    {
        return incomeBillMapper.deleteIncomeBillByIncomeId(incomeId);
    }

    @Override
    public List<IncomeBill> selectIncomeBillAll() {
        return incomeBillMapper.selectIncomeBillAll();
    }
}
