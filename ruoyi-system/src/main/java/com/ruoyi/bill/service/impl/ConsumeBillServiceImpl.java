package com.ruoyi.bill.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.bill.domain.ConsumeGoods;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.config.domain.Goods;
import com.ruoyi.config.mapper.GoodsMapper;
import com.ruoyi.config.mapper.GoodsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bill.mapper.ConsumeBillMapper;
import com.ruoyi.bill.domain.ConsumeBill;
import com.ruoyi.bill.service.IConsumeBillService;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Resource
    private GoodsMapper goodsMapper;

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
        int rows = consumeBillMapper.insertConsumeBill(consumeBill);
        insertBillConsumeGoods(consumeBill);
        return rows;
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
        insertBillConsumeGoods(consumeBill);
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
    public void insertBillConsumeGoods(ConsumeBill consumeBill)
    {
        List<ConsumeGoods> billConsumeGoodsList = consumeBill.getBillConsumeGoodsList();
        Long consumeId = consumeBill.getConsumeId();
        if (StringUtils.isNotNull(billConsumeGoodsList))
        {
            List<ConsumeGoods> list = new ArrayList<ConsumeGoods>();
            for (ConsumeGoods billConsumeGoods : billConsumeGoodsList)
            {
                billConsumeGoods.setConsumeBillId(consumeId);
                Goods goods = goodsMapper.selectGoodsByGoodsId(billConsumeGoods.getGoodsId());
                //billConsumeGoods.setGoodsId();
                billConsumeGoods.setGoodsCname(goods.getGoodsCname());
                billConsumeGoods.setGoodsEname(goods.getGoodsEname());
                //billConsumeGoods.setGoodsPrice();
                //billConsumeGoods.setGoodsTotalPrice();
                //billConsumeGoods.setGoodsTotal();
                billConsumeGoods.setEnableStatus(1);
                billConsumeGoods.setWeight(1);
                billConsumeGoods.setIcon("");
                billConsumeGoods.setOrderSort(1);
                billConsumeGoods.setGoodsDesc(consumeBill.getRemark());
                billConsumeGoods.setUpdateBy(consumeBill.getUpdateBy());
                billConsumeGoods.setCreateBy(consumeBill.getCreateBy());
                billConsumeGoods.setCreateTime(consumeBill.getCreateTime());
                billConsumeGoods.setUpdateTime(consumeBill.getUpdateTime());
                billConsumeGoods.setRemark("消费购买的商品");
                billConsumeGoods.setIsDeleted(0);
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
