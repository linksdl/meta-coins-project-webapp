package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.EmotionMapper;
import com.ruoyi.config.domain.Emotion;
import com.ruoyi.config.service.IEmotionService;

/**
 * 心情管理Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-02
 */
@Service
public class EmotionServiceImpl implements IEmotionService 
{
    @Autowired
    private EmotionMapper emotionMapper;

    /**
     * 查询心情管理
     * 
     * @param emotionId 心情管理主键
     * @return 心情管理
     */
    @Override
    public Emotion selectEmotionByEmotionId(Long emotionId)
    {
        return emotionMapper.selectEmotionByEmotionId(emotionId);
    }

    /**
     * 查询心情管理列表
     * 
     * @param emotion 心情管理
     * @return 心情管理
     */
    @Override
    public List<Emotion> selectEmotionList(Emotion emotion)
    {
        return emotionMapper.selectEmotionList(emotion);
    }

    /**
     * 新增心情管理
     * 
     * @param emotion 心情管理
     * @return 结果
     */
    @Override
    public int insertEmotion(Emotion emotion)
    {
        emotion.setCreateTime(DateUtils.getNowDate());
        emotion.setUpdateTime(DateUtils.getNowDate());
        return emotionMapper.insertEmotion(emotion);
    }

    /**
     * 修改心情管理
     * 
     * @param emotion 心情管理
     * @return 结果
     */
    @Override
    public int updateEmotion(Emotion emotion)
    {
        emotion.setUpdateTime(DateUtils.getNowDate());
        return emotionMapper.updateEmotion(emotion);
    }

    /**
     * 批量删除心情管理
     * 
     * @param emotionIds 需要删除的心情管理主键
     * @return 结果
     */
    @Override
    public int deleteEmotionByEmotionIds(Long[] emotionIds)
    {
        return emotionMapper.deleteEmotionByEmotionIds(emotionIds);
    }

    /**
     * 删除心情管理信息
     * 
     * @param emotionId 心情管理主键
     * @return 结果
     */
    @Override
    public int deleteEmotionByEmotionId(Long emotionId)
    {
        return emotionMapper.deleteEmotionByEmotionId(emotionId);
    }

    @Override
    public List<Emotion> selectEmotionAll(Emotion params) {
        return emotionMapper.selectEmotionAll(params);
    }
}
