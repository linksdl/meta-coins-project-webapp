package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.config.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.domain.Entity;
import com.ruoyi.config.service.IEntityService;

import javax.annotation.Resource;

/**
 * 商家管理Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@Service
public class EntityServiceImpl implements IEntityService
{
    @Resource
    private EntityMapper entityMapper;

    /**
     * 查询商家管理
     * 
     * @param entityId 商家管理主键
     * @return 商家管理
     */
    @Override
    public Entity selectEntityByEntityId(Long entityId)
    {
        return entityMapper.selectEntityByEntityId(entityId);
    }

    /**
     * 查询商家管理列表
     * 
     * @param entity 商家管理
     * @return 商家管理
     */
    @Override
    public List<Entity> selectEntityList(Entity entity)
    {
        return entityMapper.selectEntityList(entity);
    }

    /**
     * 新增商家管理
     * 
     * @param entity 商家管理
     * @return 结果
     */
    @Override
    public int insertEntity(Entity entity)
    {
        entity.setCreateTime(DateUtils.getNowDate());
        entity.setUpdateTime(DateUtils.getNowDate());
        return entityMapper.insertEntity(entity);
    }

    /**
     * 修改商家管理
     * 
     * @param entity 商家管理
     * @return 结果
     */
    @Override
    public int updateEntity(Entity entity)
    {
        entity.setUpdateTime(DateUtils.getNowDate());
        return entityMapper.updateEntity(entity);
    }

    /**
     * 批量删除商家管理
     * 
     * @param entityIds 需要删除的商家管理主键
     * @return 结果
     */
    @Override
    public int deleteEntityByEntityIds(Long[] entityIds)
    {
        return entityMapper.deleteEntityByEntityIds(entityIds);
    }

    /**
     * 删除商家管理信息
     * 
     * @param entityId 商家管理主键
     * @return 结果
     */
    @Override
    public int deleteEntityByEntityId(Long entityId)
    {
        return entityMapper.deleteEntityByEntityId(entityId);
    }

    @Override
    public List<Entity> selectEntityAll(Entity params) {
        return entityMapper.selectEntityAll(params);
    }
}
