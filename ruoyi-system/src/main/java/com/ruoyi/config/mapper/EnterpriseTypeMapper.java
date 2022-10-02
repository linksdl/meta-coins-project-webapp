package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.EnterpriseType;

/**
 * 商家类型Mapper接口
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public interface EnterpriseTypeMapper 
{
    /**
     * 查询商家类型
     * 
     * @param enterpriseTypeId 商家类型主键
     * @return 商家类型
     */
    public EnterpriseType selectEnterpriseTypeByEnterpriseTypeId(Long enterpriseTypeId);

    /**
     * 查询商家类型列表
     * 
     * @param enterpriseType 商家类型
     * @return 商家类型集合
     */
    public List<EnterpriseType> selectEnterpriseTypeList(EnterpriseType enterpriseType);

    /**
     * 新增商家类型
     * 
     * @param enterpriseType 商家类型
     * @return 结果
     */
    public int insertEnterpriseType(EnterpriseType enterpriseType);

    /**
     * 修改商家类型
     * 
     * @param enterpriseType 商家类型
     * @return 结果
     */
    public int updateEnterpriseType(EnterpriseType enterpriseType);

    /**
     * 删除商家类型
     * 
     * @param enterpriseTypeId 商家类型主键
     * @return 结果
     */
    public int deleteEnterpriseTypeByEnterpriseTypeId(Long enterpriseTypeId);

    /**
     * 批量删除商家类型
     * 
     * @param enterpriseTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseTypeByEnterpriseTypeIds(Long[] enterpriseTypeIds);

    /**
     * 获取所有商家类型列表
     *
     * @return 商家类型集合信息
     */
    public List<EnterpriseType> selectEnterpriseTypeAll(EnterpriseType params);
}
