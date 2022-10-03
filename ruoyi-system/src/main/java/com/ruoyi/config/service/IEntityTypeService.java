package com.ruoyi.config.service;

import java.util.List;
import com.ruoyi.config.domain.EntityType;

/**
 * 商家类型Service接口
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public interface IEntityTypeService
{
    /**
     * 查询商家类型
     * 
     * @param entityTypeId 商家类型主键
     * @return 商家类型
     */
    public EntityType selectEntityTypeByEntityTypeId(Long entityTypeId);

    /**
     * 查询商家类型列表
     * 
     * @param entityType 商家类型
     * @return 商家类型集合
     */
    public List<EntityType> selectEntityTypeList(EntityType entityType);

    /**
     * 新增商家类型
     * 
     * @param entityType 商家类型
     * @return 结果
     */
    public int insertEntityType(EntityType entityType);

    /**
     * 修改商家类型
     * 
     * @param entityType 商家类型
     * @return 结果
     */
    public int updateEntityType(EntityType entityType);

    /**
     * 批量删除商家类型
     * 
     * @param entityTypeIds 需要删除的商家类型主键集合
     * @return 结果
     */
    public int deleteEntityTypeByEntityTypeIds(Long[] entityTypeIds);

    /**
     * 删除商家类型信息
     * 
     * @param entityTypeId 商家类型主键
     * @return 结果
     */
    public int deleteEntityTypeByEntityTypeId(Long entityTypeId);

    /**
     * 获取所有商家类型列表
     *
     * @return 结果
     */
    public List<EntityType> selectEntityTypeAll(EntityType params);



}
