package com.ruoyi.bill.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.bill.domain.ConsumeGoods;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.config.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bill.mapper.ConsumeBillMapper;
import com.ruoyi.bill.domain.ConsumeBill;
import com.ruoyi.bill.service.IConsumeBillService;

import javax.annotation.Resource;

/**
 * 支出账单Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@Service
public class ConsumeBillServiceImpl implements IConsumeBillService 
{
    @Resource
    private ConsumeBillMapper consumeBillMapper;

    /**
     * 查询支出账单
     * 
     * @param consumeId 支出账单主键
     * @return 支出账单
     */
    @Override
    public ConsumeBill selectConsumeBillByConsumeId(Long consumeId)
    {
        return consumeBillMapper.selectConsumeBillByConsumeId(consumeId);
    }

    /**
     * 查询支出账单列表
     * 
     * @param consumeBill 支出账单
     * @return 支出账单
     */
    @Override
    public List<ConsumeBill> selectConsumeBillList(ConsumeBill consumeBill)
    {
        return consumeBillMapper.selectConsumeBillList(consumeBill);
    }

    /**
     * 新增支出账单
     * 
     * @param consumeBill 支出账单
     * @return 结果
     */
    @Override
    public int insertConsumeBill(ConsumeBill consumeBill)
    {
        consumeBill.setCreateTime(DateUtils.getNowDate());
        consumeBill.setUpdateTime(DateUtils.getNowDate());
        return consumeBillMapper.insertConsumeBill(consumeBill);
    }

    /**
     * 修改支出账单
     * 
     * @param consumeBill 支出账单
     * @return 结果
     */
    @Override
    public int updateConsumeBill(ConsumeBill consumeBill)
    {
        consumeBill.setUpdateTime(DateUtils.getNowDate());
        consumeBillMapper.deleteBillConsumeGoodsByConsumeBillId(consumeBill.getConsumeId());
        insertGoods(consumeBill);
        return consumeBillMapper.updateConsumeBill(consumeBill);
    }

    /**
     * 批量删除支出账单
     * 
     * @param consumeIds 需要删除的支出账单主键
     * @return 结果
     */
    @Override
    public int deleteConsumeBillByConsumeIds(Long[] consumeIds)
    {
        consumeBillMapper.deleteBillConsumeGoodsByConsumeBillIds(consumeIds);
        return consumeBillMapper.deleteConsumeBillByConsumeIds(consumeIds);
    }

    /**
     * 删除支出账单信息
     * 
     * @param consumeId 支出账单主键
     * @return 结果
     */
    @Override
    public int deleteConsumeBillByConsumeId(Long consumeId)
    {
        consumeBillMapper.deleteBillConsumeGoodsByConsumeBillId(consumeId);
        return consumeBillMapper.deleteConsumeBillByConsumeId(consumeId);
    }

    /**
     * 新增商品管理信息
     *
     * @param consumeBill 支出账单对象
     */
    public void insertGoods(ConsumeBill consumeBill)
    {
        List<ConsumeGoods> billConsumeGoodsList = consumeBill.getBillConsumeGoodsList();
        Long consumeId = consumeBill.getConsumeId();
        if (StringUtils.isNotNull(billConsumeGoodsList))
        {
            List<ConsumeGoods> list = new ArrayList<ConsumeGoods>();
            for (ConsumeGoods billConsumeGoods : billConsumeGoodsList)
            {
                billConsumeGoods.setConsumeBillId(consumeId);
                list.add(billConsumeGoods);
            }
            if (list.size() > 0)
            {
                consumeBillMapper.batchBillConsumeGoods(list);
            }
        }
    }

    @Override
    public List<ConsumeBill> selectConsumeBillAll() {
        return consumeBillMapper.selectConsumeBillAll();
    }
}
