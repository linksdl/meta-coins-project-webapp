package com.ruoyi.bill.service;

import java.util.List;
import com.ruoyi.bill.domain.FlowBill;

/**
 * 账单流水Service接口
 * 
 * @author metacoin
 * @date 2022-10-01
 */
public interface IFlowBillService 
{
    /**
     * 查询账单流水
     * 
     * @param flowId 账单流水主键
     * @return 账单流水
     */
    public FlowBill selectFlowBillByFlowId(Long flowId);

    /**
     * 查询账单流水列表
     * 
     * @param flowBill 账单流水
     * @return 账单流水集合
     */
    public List<FlowBill> selectFlowBillList(FlowBill flowBill);

    /**
     * 新增账单流水
     * 
     * @param flowBill 账单流水
     * @return 结果
     */
    public int insertFlowBill(FlowBill flowBill);

    /**
     * 修改账单流水
     * 
     * @param flowBill 账单流水
     * @return 结果
     */
    public int updateFlowBill(FlowBill flowBill);

    /**
     * 批量删除账单流水
     * 
     * @param flowIds 需要删除的账单流水主键集合
     * @return 结果
     */
    public int deleteFlowBillByFlowIds(Long[] flowIds);

    /**
     * 删除账单流水信息
     * 
     * @param flowId 账单流水主键
     * @return 结果
     */
    public int deleteFlowBillByFlowId(Long flowId);

    /**
     * 获取所有账单流水列表
     *
     * @return 结果
     */
    public List<FlowBill> selectFlowBillAll();



}