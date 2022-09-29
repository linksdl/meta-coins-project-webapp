package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.CityMapper;
import com.ruoyi.config.domain.City;
import com.ruoyi.config.service.ICityService;

import javax.annotation.Resource;

/**
 * 城市Service业务层处理
 * 
 * @author metacoin
 * @date 2022-09-29
 */
@Service
public class CityServiceImpl implements ICityService 
{
    @Resource
    private CityMapper cityMapper;

    /**
     * 查询城市
     * 
     * @param cityId 城市主键
     * @return 城市
     */
    @Override
    public City selectCityByCityId(Long cityId)
    {
        return cityMapper.selectCityByCityId(cityId);
    }

    /**
     * 查询城市列表
     * 
     * @param city 城市
     * @return 城市
     */
    @Override
    public List<City> selectCityList(City city)
    {
        return cityMapper.selectCityList(city);
    }

    /**
     * 新增城市
     * 
     * @param city 城市
     * @return 结果
     */
    @Override
    public int insertCity(City city)
    {
        city.setCreateTime(DateUtils.getNowDate());
        return cityMapper.insertCity(city);
    }

    /**
     * 修改城市
     * 
     * @param city 城市
     * @return 结果
     */
    @Override
    public int updateCity(City city)
    {
        city.setUpdateTime(DateUtils.getNowDate());
        return cityMapper.updateCity(city);
    }

    /**
     * 批量删除城市
     * 
     * @param cityIds 需要删除的城市主键
     * @return 结果
     */
    @Override
    public int deleteCityByCityIds(Long[] cityIds)
    {
        return cityMapper.deleteCityByCityIds(cityIds);
    }

    /**
     * 删除城市信息
     * 
     * @param cityId 城市主键
     * @return 结果
     */
    @Override
    public int deleteCityByCityId(Long cityId)
    {
        return cityMapper.deleteCityByCityId(cityId);
    }

    @Override
    public List<City> selectCityAll() {
        return cityMapper.selectCityAll();
    }
}
