package com.ruoyi.config.service;

import java.util.List;
import com.ruoyi.config.domain.Category;

/**
 * 分类管理Service接口
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public interface ICategoryService 
{
    /**
     * 查询分类管理
     * 
     * @param categoryId 分类管理主键
     * @return 分类管理
     */
    public Category selectCategoryByCategoryId(Long categoryId);

    /**
     * 查询分类管理列表
     * 
     * @param category 分类管理
     * @return 分类管理集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增分类管理
     * 
     * @param category 分类管理
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改分类管理
     * 
     * @param category 分类管理
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除分类管理
     * 
     * @param categoryIds 需要删除的分类管理主键集合
     * @return 结果
     */
    public int deleteCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除分类管理信息
     * 
     * @param categoryId 分类管理主键
     * @return 结果
     */
    public int deleteCategoryByCategoryId(Long categoryId);

    /**
     * 获取所有分类管理列表
     *
     * @return 结果
     */
    public List<Category> selectCategoryAll();



}
