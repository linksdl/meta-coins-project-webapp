package com.ruoyi.config.service;

import java.util.List;
import com.ruoyi.config.domain.Label;

/**
 * 标签管理Service接口
 * 
 * @author metacoin
 * @date 2022-10-02
 */
public interface ILabelService 
{
    /**
     * 查询标签管理
     * 
     * @param labelId 标签管理主键
     * @return 标签管理
     */
    public Label selectLabelByLabelId(Long labelId);

    /**
     * 查询标签管理列表
     * 
     * @param label 标签管理
     * @return 标签管理集合
     */
    public List<Label> selectLabelList(Label label);

    /**
     * 新增标签管理
     * 
     * @param label 标签管理
     * @return 结果
     */
    public int insertLabel(Label label);

    /**
     * 修改标签管理
     * 
     * @param label 标签管理
     * @return 结果
     */
    public int updateLabel(Label label);

    /**
     * 批量删除标签管理
     * 
     * @param labelIds 需要删除的标签管理主键集合
     * @return 结果
     */
    public int deleteLabelByLabelIds(Long[] labelIds);

    /**
     * 删除标签管理信息
     * 
     * @param labelId 标签管理主键
     * @return 结果
     */
    public int deleteLabelByLabelId(Long labelId);

    /**
     * 获取所有标签管理列表
     *
     * @return 结果
     */
    public List<Label> selectLabelAll(Label params);



}
