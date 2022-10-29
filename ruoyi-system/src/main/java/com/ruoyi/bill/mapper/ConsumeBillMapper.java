package com.ruoyi.bill.mapper;

import java.util.List;
import com.ruoyi.bill.domain.ConsumeBill;
import com.ruoyi.bill.domain.ConsumeGoods;
import com.ruoyi.config.domain.Goods;

/**
 * 支出账单Mapper接口
 * 
 * @author metacoin
 * @date 2022-10-01
 */
public interface ConsumeBillMapper 
{
    /**
     * 查询支出账单
     *
     * @param consumeId 支出账单主键
     * @return 支出账单
     */
    public ConsumeBill selectConsumeBillByConsumeId(Long consumeId);

    /**
     * 查询支出账单列表
     *
     * @param consumeBill 支出账单
     * @return 支出账单集合
     */
    public List<ConsumeBill> selectConsumeBillList(ConsumeBill consumeBill);

    /**
     * 新增支出账单
     *
     * @param consumeBill 支出账单
     * @return 结果
     */
    public int insertConsumeBill(ConsumeBill consumeBill);

    /**
     * 修改支出账单
     *
     * @param consumeBill 支出账单
     * @return 结果
     */
    public int updateConsumeBill(ConsumeBill consumeBill);

    /**
     * 删除支出账单
     *
     * @param consumeId 支出账单主键
     * @return 结果
     */
    public int deleteConsumeBillByConsumeId(Long consumeId);

    /**
     * 批量删除支出账单
     *
     * @param consumeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteConsumeBillByConsumeIds(Long[] consumeIds);

    /**
     * 批量删除消费商品
     *
     * @param consumeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBillConsumeGoodsByConsumeBillIds(Long[] consumeIds);

    /**
     * 批量新增消费商品
     *
     * @param billConsumeGoodsList 消费商品列表
     * @return 结果
     */
    public int batchBillConsumeGoods(List<ConsumeGoods> billConsumeGoodsList);


    /**
     * 通过支出账单主键删除消费商品信息
     *
     * @param consumeId 支出账单ID
     * @return 结果
     */
    public int deleteBillConsumeGoodsByConsumeBillId(Long consumeId);

    /**
     * 获取所有支出账单列表
     *
     * @return 支出账单集合信息
     */
    public List<ConsumeBill> selectConsumeBillAll();
}
