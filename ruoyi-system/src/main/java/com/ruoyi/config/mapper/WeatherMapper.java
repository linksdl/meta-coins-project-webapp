package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.Weather;

/**
 * 天气管理Mapper接口
 * 
 * @author metacoin
 * @date 2022-09-30
 */
public interface WeatherMapper 
{
    /**
     * 查询天气管理
     * 
     * @param weatherId 天气管理主键
     * @return 天气管理
     */
    public Weather selectWeatherByWeatherId(Long weatherId);

    /**
     * 查询天气管理列表
     * 
     * @param weather 天气管理
     * @return 天气管理集合
     */
    public List<Weather> selectWeatherList(Weather weather);

    /**
     * 新增天气管理
     * 
     * @param weather 天气管理
     * @return 结果
     */
    public int insertWeather(Weather weather);

    /**
     * 修改天气管理
     * 
     * @param weather 天气管理
     * @return 结果
     */
    public int updateWeather(Weather weather);

    /**
     * 删除天气管理
     * 
     * @param weatherId 天气管理主键
     * @return 结果
     */
    public int deleteWeatherByWeatherId(Long weatherId);

    /**
     * 批量删除天气管理
     * 
     * @param weatherIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWeatherByWeatherIds(Long[] weatherIds);

    /**
     * 获取所有天气管理列表
     *
     * @return 天气管理集合信息
     */
    public List<Weather> selectWeatherAll(Weather params);
}
