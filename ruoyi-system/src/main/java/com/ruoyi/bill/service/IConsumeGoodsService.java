package com.ruoyi.bill.service;

import java.util.List;
import com.ruoyi.bill.domain.ConsumeGoods;

/**
 * 支出商品Service接口
 * 
 * @author metacoin
 * @date 2022-10-30
 */
public interface IConsumeGoodsService 
{
    /**
     * 查询支出商品
     * 
     * @param consumeGoodsId 支出商品主键
     * @return 支出商品
     */
    public ConsumeGoods selectConsumeGoodsByConsumeGoodsId(Long consumeGoodsId);

    /**
     * 查询支出商品列表
     * 
     * @param consumeGoods 支出商品
     * @return 支出商品集合
     */
    public List<ConsumeGoods> selectConsumeGoodsList(ConsumeGoods consumeGoods);

    /**
     * 新增支出商品
     * 
     * @param consumeGoods 支出商品
     * @return 结果
     */
    public int insertConsumeGoods(ConsumeGoods consumeGoods);

    /**
     * 修改支出商品
     * 
     * @param consumeGoods 支出商品
     * @return 结果
     */
    public int updateConsumeGoods(ConsumeGoods consumeGoods);

    /**
     * 批量删除支出商品
     * 
     * @param consumeGoodsIds 需要删除的支出商品主键集合
     * @return 结果
     */
    public int deleteConsumeGoodsByConsumeGoodsIds(Long[] consumeGoodsIds);

    /**
     * 删除支出商品信息
     * 
     * @param consumeGoodsId 支出商品主键
     * @return 结果
     */
    public int deleteConsumeGoodsByConsumeGoodsId(Long consumeGoodsId);

    /**
     * 获取所有支出商品列表
     *
     * @return 结果
     */
    public List<ConsumeGoods> selectConsumeGoodsAll();



}
