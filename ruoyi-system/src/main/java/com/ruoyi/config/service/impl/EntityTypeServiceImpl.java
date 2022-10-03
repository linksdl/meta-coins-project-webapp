package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.config.domain.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.EntityTypeMapper;
import com.ruoyi.config.service.IEntityTypeService;

import javax.annotation.Resource;

/**
 * 商家类型Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@Service
public class EntityTypeServiceImpl implements IEntityTypeService
{
    @Resource
    private EntityTypeMapper entityTypeMapper;

    /**
     * 查询商家类型
     * 
     * @param entityTypeId 商家类型主键
     * @return 商家类型
     */
    @Override
    public EntityType selectEntityTypeByEntityTypeId(Long entityTypeId)
    {
        return entityTypeMapper.selectEntityTypeByEntityTypeId(entityTypeId);
    }

    /**
     * 查询商家类型列表
     * 
     * @param entityType 商家类型
     * @return 商家类型
     */
    @Override
    public List<EntityType> selectEntityTypeList(EntityType entityType)
    {
        return entityTypeMapper.selectEntityTypeList(entityType);
    }

    /**
     * 新增商家类型
     * 
     * @param entityType 商家类型
     * @return 结果
     */
    @Override
    public int insertEntityType(EntityType entityType)
    {
        entityType.setCreateTime(DateUtils.getNowDate());
        entityType.setUpdateTime(DateUtils.getNowDate());
        return entityTypeMapper.insertEntityType(entityType);
    }

    /**
     * 修改商家类型
     * 
     * @param entityType 商家类型
     * @return 结果
     */
    @Override
    public int updateEntityType(EntityType entityType)
    {
        entityType.setUpdateTime(DateUtils.getNowDate());
        return entityTypeMapper.updateEntityType(entityType);
    }

    /**
     * 批量删除商家类型
     * 
     * @param entityTypeIds 需要删除的商家类型主键
     * @return 结果
     */
    @Override
    public int deleteEntityTypeByEntityTypeIds(Long[] entityTypeIds)
    {
        return entityTypeMapper.deleteEntityTypeByEntityTypeIds(entityTypeIds);
    }

    /**
     * 删除商家类型信息
     * 
     * @param entityTypeId 商家类型主键
     * @return 结果
     */
    @Override
    public int deleteEntityTypeByEntityTypeId(Long entityTypeId)
    {
        return entityTypeMapper.deleteEntityTypeByEntityTypeId(entityTypeId);
    }

    @Override
    public List<EntityType> selectEntityTypeAll(EntityType params) {
        return entityTypeMapper.selectEntityTypeAll(params);
    }
}
