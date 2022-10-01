package com.ruoyi.bill.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bill.mapper.TransferBillMapper;
import com.ruoyi.bill.domain.TransferBill;
import com.ruoyi.bill.service.ITransferBillService;

/**
 * 转账账单Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@Service
public class TransferBillServiceImpl implements ITransferBillService 
{
    @Autowired
    private TransferBillMapper transferBillMapper;

    /**
     * 查询转账账单
     * 
     * @param transferId 转账账单主键
     * @return 转账账单
     */
    @Override
    public TransferBill selectTransferBillByTransferId(Long transferId)
    {
        return transferBillMapper.selectTransferBillByTransferId(transferId);
    }

    /**
     * 查询转账账单列表
     * 
     * @param transferBill 转账账单
     * @return 转账账单
     */
    @Override
    public List<TransferBill> selectTransferBillList(TransferBill transferBill)
    {
        return transferBillMapper.selectTransferBillList(transferBill);
    }

    /**
     * 新增转账账单
     * 
     * @param transferBill 转账账单
     * @return 结果
     */
    @Override
    public int insertTransferBill(TransferBill transferBill)
    {
        transferBill.setCreateTime(DateUtils.getNowDate());
        transferBill.setUpdateTime(DateUtils.getNowDate());
        return transferBillMapper.insertTransferBill(transferBill);
    }

    /**
     * 修改转账账单
     * 
     * @param transferBill 转账账单
     * @return 结果
     */
    @Override
    public int updateTransferBill(TransferBill transferBill)
    {
        transferBill.setUpdateTime(DateUtils.getNowDate());
        return transferBillMapper.updateTransferBill(transferBill);
    }

    /**
     * 批量删除转账账单
     * 
     * @param transferIds 需要删除的转账账单主键
     * @return 结果
     */
    @Override
    public int deleteTransferBillByTransferIds(Long[] transferIds)
    {
        return transferBillMapper.deleteTransferBillByTransferIds(transferIds);
    }

    /**
     * 删除转账账单信息
     * 
     * @param transferId 转账账单主键
     * @return 结果
     */
    @Override
    public int deleteTransferBillByTransferId(Long transferId)
    {
        return transferBillMapper.deleteTransferBillByTransferId(transferId);
    }

    @Override
    public List<TransferBill> selectTransferBillAll() {
        return transferBillMapper.selectTransferBillAll();
    }
}
