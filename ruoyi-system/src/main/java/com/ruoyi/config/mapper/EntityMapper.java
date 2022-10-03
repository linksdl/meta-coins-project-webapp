package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.Entity;

/**
 * 商家管理Mapper接口
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public interface EntityMapper
{
    /**
     * 查询商家管理
     * 
     * @param entityId 商家管理主键
     * @return 商家管理
     */
    public Entity selectEntityByEntityId(Long entityId);

    /**
     * 查询商家管理列表
     * 
     * @param entity 商家管理
     * @return 商家管理集合
     */
    public List<Entity> selectEntityList(Entity entity);

    /**
     * 新增商家管理
     * 
     * @param entity 商家管理
     * @return 结果
     */
    public int insertEntity(Entity entity);

    /**
     * 修改商家管理
     * 
     * @param entity 商家管理
     * @return 结果
     */
    public int updateEntity(Entity entity);

    /**
     * 删除商家管理
     * 
     * @param entityId 商家管理主键
     * @return 结果
     */
    public int deleteEntityByEntityId(Long entityId);

    /**
     * 批量删除商家管理
     * 
     * @param entityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEntityByEntityIds(Long[] entityIds);

    /**
     * 获取所有商家管理列表
     *
     * @return 商家管理集合信息
     */
    public List<Entity> selectEntityAll(Entity params);
}
