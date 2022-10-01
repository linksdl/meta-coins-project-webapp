package com.ruoyi.bill.service;

import java.util.List;
import com.ruoyi.bill.domain.TransferBill;

/**
 * 转账账单Service接口
 * 
 * @author metacoin
 * @date 2022-10-01
 */
public interface ITransferBillService 
{
    /**
     * 查询转账账单
     * 
     * @param transferId 转账账单主键
     * @return 转账账单
     */
    public TransferBill selectTransferBillByTransferId(Long transferId);

    /**
     * 查询转账账单列表
     * 
     * @param transferBill 转账账单
     * @return 转账账单集合
     */
    public List<TransferBill> selectTransferBillList(TransferBill transferBill);

    /**
     * 新增转账账单
     * 
     * @param transferBill 转账账单
     * @return 结果
     */
    public int insertTransferBill(TransferBill transferBill);

    /**
     * 修改转账账单
     * 
     * @param transferBill 转账账单
     * @return 结果
     */
    public int updateTransferBill(TransferBill transferBill);

    /**
     * 批量删除转账账单
     * 
     * @param transferIds 需要删除的转账账单主键集合
     * @return 结果
     */
    public int deleteTransferBillByTransferIds(Long[] transferIds);

    /**
     * 删除转账账单信息
     * 
     * @param transferId 转账账单主键
     * @return 结果
     */
    public int deleteTransferBillByTransferId(Long transferId);

    /**
     * 获取所有转账账单列表
     *
     * @return 结果
     */
    public List<TransferBill> selectTransferBillAll();



}
