package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.GoodsTypeMapper;
import com.ruoyi.config.domain.GoodsType;
import com.ruoyi.config.service.IGoodsTypeService;

/**
 * 商品类型Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService 
{
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    /**
     * 查询商品类型
     * 
     * @param goodsTypeId 商品类型主键
     * @return 商品类型
     */
    @Override
    public GoodsType selectGoodsTypeByGoodsTypeId(Long goodsTypeId)
    {
        return goodsTypeMapper.selectGoodsTypeByGoodsTypeId(goodsTypeId);
    }

    /**
     * 查询商品类型列表
     * 
     * @param goodsType 商品类型
     * @return 商品类型
     */
    @Override
    public List<GoodsType> selectGoodsTypeList(GoodsType goodsType)
    {
        return goodsTypeMapper.selectGoodsTypeList(goodsType);
    }

    /**
     * 新增商品类型
     * 
     * @param goodsType 商品类型
     * @return 结果
     */
    @Override
    public int insertGoodsType(GoodsType goodsType)
    {
        goodsType.setCreateTime(DateUtils.getNowDate());
        goodsType.setUpdateTime(DateUtils.getNowDate());
        return goodsTypeMapper.insertGoodsType(goodsType);
    }

    /**
     * 修改商品类型
     * 
     * @param goodsType 商品类型
     * @return 结果
     */
    @Override
    public int updateGoodsType(GoodsType goodsType)
    {
        goodsType.setUpdateTime(DateUtils.getNowDate());
        return goodsTypeMapper.updateGoodsType(goodsType);
    }

    /**
     * 批量删除商品类型
     * 
     * @param goodsTypeIds 需要删除的商品类型主键
     * @return 结果
     */
    @Override
    public int deleteGoodsTypeByGoodsTypeIds(Long[] goodsTypeIds)
    {
        return goodsTypeMapper.deleteGoodsTypeByGoodsTypeIds(goodsTypeIds);
    }

    /**
     * 删除商品类型信息
     * 
     * @param goodsTypeId 商品类型主键
     * @return 结果
     */
    @Override
    public int deleteGoodsTypeByGoodsTypeId(Long goodsTypeId)
    {
        return goodsTypeMapper.deleteGoodsTypeByGoodsTypeId(goodsTypeId);
    }

    @Override
    public List<GoodsType> selectGoodsTypeAll() {
        return goodsTypeMapper.selectGoodsTypeAll();
    }
}
