package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.LabelMapper;
import com.ruoyi.config.domain.Label;
import com.ruoyi.config.service.ILabelService;

/**
 * 标签管理Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-02
 */
@Service
public class LabelServiceImpl implements ILabelService 
{
    @Autowired
    private LabelMapper labelMapper;

    /**
     * 查询标签管理
     * 
     * @param labelId 标签管理主键
     * @return 标签管理
     */
    @Override
    public Label selectLabelByLabelId(Long labelId)
    {
        return labelMapper.selectLabelByLabelId(labelId);
    }

    /**
     * 查询标签管理列表
     * 
     * @param label 标签管理
     * @return 标签管理
     */
    @Override
    public List<Label> selectLabelList(Label label)
    {
        return labelMapper.selectLabelList(label);
    }

    /**
     * 新增标签管理
     * 
     * @param label 标签管理
     * @return 结果
     */
    @Override
    public int insertLabel(Label label)
    {
        label.setCreateTime(DateUtils.getNowDate());
        label.setUpdateTime(DateUtils.getNowDate());
        return labelMapper.insertLabel(label);
    }

    /**
     * 修改标签管理
     * 
     * @param label 标签管理
     * @return 结果
     */
    @Override
    public int updateLabel(Label label)
    {
        label.setUpdateTime(DateUtils.getNowDate());
        return labelMapper.updateLabel(label);
    }

    /**
     * 批量删除标签管理
     * 
     * @param labelIds 需要删除的标签管理主键
     * @return 结果
     */
    @Override
    public int deleteLabelByLabelIds(Long[] labelIds)
    {
        return labelMapper.deleteLabelByLabelIds(labelIds);
    }

    /**
     * 删除标签管理信息
     * 
     * @param labelId 标签管理主键
     * @return 结果
     */
    @Override
    public int deleteLabelByLabelId(Long labelId)
    {
        return labelMapper.deleteLabelByLabelId(labelId);
    }

    @Override
    public List<Label> selectLabelAll(Label params) {
        return labelMapper.selectLabelAll(params);
    }
}
