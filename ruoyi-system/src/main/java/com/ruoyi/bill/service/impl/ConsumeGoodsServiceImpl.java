package com.ruoyi.bill.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bill.mapper.ConsumeGoodsMapper;
import com.ruoyi.bill.domain.ConsumeGoods;
import com.ruoyi.bill.service.IConsumeGoodsService;

/**
 * 支出商品Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-30
 */
@Service
public class ConsumeGoodsServiceImpl implements IConsumeGoodsService 
{
    @Autowired
    private ConsumeGoodsMapper consumeGoodsMapper;

    /**
     * 查询支出商品
     * 
     * @param consumeGoodsId 支出商品主键
     * @return 支出商品
     */
    @Override
    public ConsumeGoods selectConsumeGoodsByConsumeGoodsId(Long consumeGoodsId)
    {
        return consumeGoodsMapper.selectConsumeGoodsByConsumeGoodsId(consumeGoodsId);
    }

    /**
     * 查询支出商品列表
     * 
     * @param consumeGoods 支出商品
     * @return 支出商品
     */
    @Override
    public List<ConsumeGoods> selectConsumeGoodsList(ConsumeGoods consumeGoods)
    {
        return consumeGoodsMapper.selectConsumeGoodsList(consumeGoods);
    }

    /**
     * 新增支出商品
     * 
     * @param consumeGoods 支出商品
     * @return 结果
     */
    @Override
    public int insertConsumeGoods(ConsumeGoods consumeGoods)
    {
        consumeGoods.setCreateTime(DateUtils.getNowDate());
        consumeGoods.setUpdateTime(DateUtils.getNowDate());
        return consumeGoodsMapper.insertConsumeGoods(consumeGoods);
    }

    /**
     * 修改支出商品
     * 
     * @param consumeGoods 支出商品
     * @return 结果
     */
    @Override
    public int updateConsumeGoods(ConsumeGoods consumeGoods)
    {
        consumeGoods.setUpdateTime(DateUtils.getNowDate());
        return consumeGoodsMapper.updateConsumeGoods(consumeGoods);
    }

    /**
     * 批量删除支出商品
     * 
     * @param consumeGoodsIds 需要删除的支出商品主键
     * @return 结果
     */
    @Override
    public int deleteConsumeGoodsByConsumeGoodsIds(Long[] consumeGoodsIds)
    {
        return consumeGoodsMapper.deleteConsumeGoodsByConsumeGoodsIds(consumeGoodsIds);
    }

    /**
     * 删除支出商品信息
     * 
     * @param consumeGoodsId 支出商品主键
     * @return 结果
     */
    @Override
    public int deleteConsumeGoodsByConsumeGoodsId(Long consumeGoodsId)
    {
        return consumeGoodsMapper.deleteConsumeGoodsByConsumeGoodsId(consumeGoodsId);
    }

    @Override
    public List<ConsumeGoods> selectConsumeGoodsAll() {
        return consumeGoodsMapper.selectConsumeGoodsAll();
    }
}
