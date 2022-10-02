package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.Label;

/**
 * 标签管理Mapper接口
 * 
 * @author metacoin
 * @date 2022-10-02
 */
public interface LabelMapper 
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
     * 删除标签管理
     * 
     * @param labelId 标签管理主键
     * @return 结果
     */
    public int deleteLabelByLabelId(Long labelId);

    /**
     * 批量删除标签管理
     * 
     * @param labelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabelByLabelIds(Long[] labelIds);

    /**
     * 获取所有标签管理列表
     *
     * @return 标签管理集合信息
     */
    public List<Label> selectLabelAll(Label params);
}
