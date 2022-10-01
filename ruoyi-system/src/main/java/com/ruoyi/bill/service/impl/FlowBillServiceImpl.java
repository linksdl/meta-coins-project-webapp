package com.ruoyi.bill.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bill.mapper.FlowBillMapper;
import com.ruoyi.bill.domain.FlowBill;
import com.ruoyi.bill.service.IFlowBillService;

/**
 * 账单流水Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@Service
public class FlowBillServiceImpl implements IFlowBillService 
{
    @Autowired
    private FlowBillMapper flowBillMapper;

    /**
     * 查询账单流水
     * 
     * @param flowId 账单流水主键
     * @return 账单流水
     */
    @Override
    public FlowBill selectFlowBillByFlowId(Long flowId)
    {
        return flowBillMapper.selectFlowBillByFlowId(flowId);
    }

    /**
     * 查询账单流水列表
     * 
     * @param flowBill 账单流水
     * @return 账单流水
     */
    @Override
    public List<FlowBill> selectFlowBillList(FlowBill flowBill)
    {
        return flowBillMapper.selectFlowBillList(flowBill);
    }

    /**
     * 新增账单流水
     * 
     * @param flowBill 账单流水
     * @return 结果
     */
    @Override
    public int insertFlowBill(FlowBill flowBill)
    {
        flowBill.setCreateTime(DateUtils.getNowDate());
        flowBill.setUpdateTime(DateUtils.getNowDate());
        return flowBillMapper.insertFlowBill(flowBill);
    }

    /**
     * 修改账单流水
     * 
     * @param flowBill 账单流水
     * @return 结果
     */
    @Override
    public int updateFlowBill(FlowBill flowBill)
    {
        flowBill.setUpdateTime(DateUtils.getNowDate());
        return flowBillMapper.updateFlowBill(flowBill);
    }

    /**
     * 批量删除账单流水
     * 
     * @param flowIds 需要删除的账单流水主键
     * @return 结果
     */
    @Override
    public int deleteFlowBillByFlowIds(Long[] flowIds)
    {
        return flowBillMapper.deleteFlowBillByFlowIds(flowIds);
    }

    /**
     * 删除账单流水信息
     * 
     * @param flowId 账单流水主键
     * @return 结果
     */
    @Override
    public int deleteFlowBillByFlowId(Long flowId)
    {
        return flowBillMapper.deleteFlowBillByFlowId(flowId);
    }

    @Override
    public List<FlowBill> selectFlowBillAll() {
        return flowBillMapper.selectFlowBillAll();
    }
}
