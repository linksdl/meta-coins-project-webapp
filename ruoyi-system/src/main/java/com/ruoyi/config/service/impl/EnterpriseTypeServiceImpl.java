package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.EnterpriseTypeMapper;
import com.ruoyi.config.domain.EnterpriseType;
import com.ruoyi.config.service.IEnterpriseTypeService;

/**
 * 商家类型Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@Service
public class EnterpriseTypeServiceImpl implements IEnterpriseTypeService 
{
    @Autowired
    private EnterpriseTypeMapper enterpriseTypeMapper;

    /**
     * 查询商家类型
     * 
     * @param enterpriseTypeId 商家类型主键
     * @return 商家类型
     */
    @Override
    public EnterpriseType selectEnterpriseTypeByEnterpriseTypeId(Long enterpriseTypeId)
    {
        return enterpriseTypeMapper.selectEnterpriseTypeByEnterpriseTypeId(enterpriseTypeId);
    }

    /**
     * 查询商家类型列表
     * 
     * @param enterpriseType 商家类型
     * @return 商家类型
     */
    @Override
    public List<EnterpriseType> selectEnterpriseTypeList(EnterpriseType enterpriseType)
    {
        return enterpriseTypeMapper.selectEnterpriseTypeList(enterpriseType);
    }

    /**
     * 新增商家类型
     * 
     * @param enterpriseType 商家类型
     * @return 结果
     */
    @Override
    public int insertEnterpriseType(EnterpriseType enterpriseType)
    {
        enterpriseType.setCreateTime(DateUtils.getNowDate());
        enterpriseType.setUpdateTime(DateUtils.getNowDate());
        return enterpriseTypeMapper.insertEnterpriseType(enterpriseType);
    }

    /**
     * 修改商家类型
     * 
     * @param enterpriseType 商家类型
     * @return 结果
     */
    @Override
    public int updateEnterpriseType(EnterpriseType enterpriseType)
    {
        enterpriseType.setUpdateTime(DateUtils.getNowDate());
        return enterpriseTypeMapper.updateEnterpriseType(enterpriseType);
    }

    /**
     * 批量删除商家类型
     * 
     * @param enterpriseTypeIds 需要删除的商家类型主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseTypeByEnterpriseTypeIds(Long[] enterpriseTypeIds)
    {
        return enterpriseTypeMapper.deleteEnterpriseTypeByEnterpriseTypeIds(enterpriseTypeIds);
    }

    /**
     * 删除商家类型信息
     * 
     * @param enterpriseTypeId 商家类型主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseTypeByEnterpriseTypeId(Long enterpriseTypeId)
    {
        return enterpriseTypeMapper.deleteEnterpriseTypeByEnterpriseTypeId(enterpriseTypeId);
    }

    @Override
    public List<EnterpriseType> selectEnterpriseTypeAll(EnterpriseType params) {
        return enterpriseTypeMapper.selectEnterpriseTypeAll(params);
    }
}
