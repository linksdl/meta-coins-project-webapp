package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.Emotion;

/**
 * 心情管理Mapper接口
 * 
 * @author metacoin
 * @date 2022-10-02
 */
public interface EmotionMapper 
{
    /**
     * 查询心情管理
     * 
     * @param emotionId 心情管理主键
     * @return 心情管理
     */
    public Emotion selectEmotionByEmotionId(Long emotionId);

    /**
     * 查询心情管理列表
     * 
     * @param emotion 心情管理
     * @return 心情管理集合
     */
    public List<Emotion> selectEmotionList(Emotion emotion);

    /**
     * 新增心情管理
     * 
     * @param emotion 心情管理
     * @return 结果
     */
    public int insertEmotion(Emotion emotion);

    /**
     * 修改心情管理
     * 
     * @param emotion 心情管理
     * @return 结果
     */
    public int updateEmotion(Emotion emotion);

    /**
     * 删除心情管理
     * 
     * @param emotionId 心情管理主键
     * @return 结果
     */
    public int deleteEmotionByEmotionId(Long emotionId);

    /**
     * 批量删除心情管理
     * 
     * @param emotionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmotionByEmotionIds(Long[] emotionIds);

    /**
     * 获取所有心情管理列表
     *
     * @return 心情管理集合信息
     */
    public List<Emotion> selectEmotionAll(Emotion params);
}
