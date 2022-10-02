package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.MoneyTypeMapper;
import com.ruoyi.config.domain.MoneyType;
import com.ruoyi.config.service.IMoneyTypeService;

/**
 * 币种类型Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-02
 */
@Service
public class MoneyTypeServiceImpl implements IMoneyTypeService 
{
    @Autowired
    private MoneyTypeMapper moneyTypeMapper;

    /**
     * 查询币种类型
     * 
     * @param moneyTypeId 币种类型主键
     * @return 币种类型
     */
    @Override
    public MoneyType selectMoneyTypeByMoneyTypeId(Long moneyTypeId)
    {
        return moneyTypeMapper.selectMoneyTypeByMoneyTypeId(moneyTypeId);
    }

    /**
     * 查询币种类型列表
     * 
     * @param moneyType 币种类型
     * @return 币种类型
     */
    @Override
    public List<MoneyType> selectMoneyTypeList(MoneyType moneyType)
    {
        return moneyTypeMapper.selectMoneyTypeList(moneyType);
    }

    /**
     * 新增币种类型
     * 
     * @param moneyType 币种类型
     * @return 结果
     */
    @Override
    public int insertMoneyType(MoneyType moneyType)
    {
        moneyType.setCreateTime(DateUtils.getNowDate());
        moneyType.setUpdateTime(DateUtils.getNowDate());
        return moneyTypeMapper.insertMoneyType(moneyType);
    }

    /**
     * 修改币种类型
     * 
     * @param moneyType 币种类型
     * @return 结果
     */
    @Override
    public int updateMoneyType(MoneyType moneyType)
    {
        moneyType.setUpdateTime(DateUtils.getNowDate());
        return moneyTypeMapper.updateMoneyType(moneyType);
    }

    /**
     * 批量删除币种类型
     * 
     * @param moneyTypeIds 需要删除的币种类型主键
     * @return 结果
     */
    @Override
    public int deleteMoneyTypeByMoneyTypeIds(Long[] moneyTypeIds)
    {
        return moneyTypeMapper.deleteMoneyTypeByMoneyTypeIds(moneyTypeIds);
    }

    /**
     * 删除币种类型信息
     * 
     * @param moneyTypeId 币种类型主键
     * @return 结果
     */
    @Override
    public int deleteMoneyTypeByMoneyTypeId(Long moneyTypeId)
    {
        return moneyTypeMapper.deleteMoneyTypeByMoneyTypeId(moneyTypeId);
    }

    @Override
    public List<MoneyType> selectMoneyTypeAll(MoneyType params) {
        return moneyTypeMapper.selectMoneyTypeAll(params);
    }
}
