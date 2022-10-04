package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.GoodsType;

/**
 * 商品类型Mapper接口
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public interface GoodsTypeMapper 
{
    /**
     * 查询商品类型
     * 
     * @param goodsTypeId 商品类型主键
     * @return 商品类型
     */
    public GoodsType selectGoodsTypeByGoodsTypeId(Long goodsTypeId);

    /**
     * 查询商品类型列表
     * 
     * @param goodsType 商品类型
     * @return 商品类型集合
     */
    public List<GoodsType> selectGoodsTypeList(GoodsType goodsType);

    /**
     * 新增商品类型
     * 
     * @param goodsType 商品类型
     * @return 结果
     */
    public int insertGoodsType(GoodsType goodsType);

    /**
     * 修改商品类型
     * 
     * @param goodsType 商品类型
     * @return 结果
     */
    public int updateGoodsType(GoodsType goodsType);

    /**
     * 删除商品类型
     * 
     * @param goodsTypeId 商品类型主键
     * @return 结果
     */
    public int deleteGoodsTypeByGoodsTypeId(Long goodsTypeId);

    /**
     * 批量删除商品类型
     * 
     * @param goodsTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsTypeByGoodsTypeIds(Long[] goodsTypeIds);

    /**
     * 获取所有商品类型列表
     *
     * @return 商品类型集合信息
     */
    public List<GoodsType> selectGoodsTypeAll();
}
