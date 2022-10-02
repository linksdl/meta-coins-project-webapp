package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.WeatherMapper;
import com.ruoyi.config.domain.Weather;
import com.ruoyi.config.service.IWeatherService;

/**
 * 天气管理Service业务层处理
 * 
 * @author metacoin
 * @date 2022-09-30
 */
@Service
public class WeatherServiceImpl implements IWeatherService 
{
    @Autowired
    private WeatherMapper weatherMapper;

    /**
     * 查询天气管理
     * 
     * @param weatherId 天气管理主键
     * @return 天气管理
     */
    @Override
    public Weather selectWeatherByWeatherId(Long weatherId)
    {
        return weatherMapper.selectWeatherByWeatherId(weatherId);
    }

    /**
     * 查询天气管理列表
     * 
     * @param weather 天气管理
     * @return 天气管理
     */
    @Override
    public List<Weather> selectWeatherList(Weather weather)
    {
        return weatherMapper.selectWeatherList(weather);
    }

    /**
     * 新增天气管理
     * 
     * @param weather 天气管理
     * @return 结果
     */
    @Override
    public int insertWeather(Weather weather)
    {
        weather.setCreateTime(DateUtils.getNowDate());
        weather.setUpdateTime(DateUtils.getNowDate());
        return weatherMapper.insertWeather(weather);
    }

    /**
     * 修改天气管理
     * 
     * @param weather 天气管理
     * @return 结果
     */
    @Override
    public int updateWeather(Weather weather)
    {
        weather.setUpdateTime(DateUtils.getNowDate());
        return weatherMapper.updateWeather(weather);
    }

    /**
     * 批量删除天气管理
     * 
     * @param weatherIds 需要删除的天气管理主键
     * @return 结果
     */
    @Override
    public int deleteWeatherByWeatherIds(Long[] weatherIds)
    {
        return weatherMapper.deleteWeatherByWeatherIds(weatherIds);
    }

    /**
     * 删除天气管理信息
     * 
     * @param weatherId 天气管理主键
     * @return 结果
     */
    @Override
    public int deleteWeatherByWeatherId(Long weatherId)
    {
        return weatherMapper.deleteWeatherByWeatherId(weatherId);
    }

    @Override
    public List<Weather> selectWeatherAll() {
        return weatherMapper.selectWeatherAll();
    }
}
