package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.MoneyType;

/**
 * 币种类型Mapper接口
 * 
 * @author metacoin
 * @date 2022-10-02
 */
public interface MoneyTypeMapper 
{
    /**
     * 查询币种类型
     * 
     * @param moneyTypeId 币种类型主键
     * @return 币种类型
     */
    public MoneyType selectMoneyTypeByMoneyTypeId(Long moneyTypeId);

    /**
     * 查询币种类型列表
     * 
     * @param moneyType 币种类型
     * @return 币种类型集合
     */
    public List<MoneyType> selectMoneyTypeList(MoneyType moneyType);

    /**
     * 新增币种类型
     * 
     * @param moneyType 币种类型
     * @return 结果
     */
    public int insertMoneyType(MoneyType moneyType);

    /**
     * 修改币种类型
     * 
     * @param moneyType 币种类型
     * @return 结果
     */
    public int updateMoneyType(MoneyType moneyType);

    /**
     * 删除币种类型
     * 
     * @param moneyTypeId 币种类型主键
     * @return 结果
     */
    public int deleteMoneyTypeByMoneyTypeId(Long moneyTypeId);

    /**
     * 批量删除币种类型
     * 
     * @param moneyTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMoneyTypeByMoneyTypeIds(Long[] moneyTypeIds);

    /**
     * 获取所有币种类型列表
     *
     * @return 币种类型集合信息
     */
    public List<MoneyType> selectMoneyTypeAll();
}
