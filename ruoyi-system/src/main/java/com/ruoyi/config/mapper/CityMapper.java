package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.City;

/**
 * 城市Mapper接口
 * 
 * @author metacoin
 * @date 2022-09-29
 */
public interface CityMapper 
{
    /**
     * 查询城市
     * 
     * @param cityId 城市主键
     * @return 城市
     */
    public City selectCityByCityId(Long cityId);

    /**
     * 查询城市列表
     * 
     * @param city 城市
     * @return 城市集合
     */
    public List<City> selectCityList(City city);

    /**
     * 新增城市
     * 
     * @param city 城市
     * @return 结果
     */
    public int insertCity(City city);

    /**
     * 修改城市
     * 
     * @param city 城市
     * @return 结果
     */
    public int updateCity(City city);

    /**
     * 删除城市
     * 
     * @param cityId 城市主键
     * @return 结果
     */
    public int deleteCityByCityId(Long cityId);

    /**
     * 批量删除城市
     * 
     * @param cityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCityByCityIds(Long[] cityIds);

    /**
     * 获取所有城市列表
     *
     * @return 城市集合信息
     */
    public List<City> selectCityAll(City params);
}
