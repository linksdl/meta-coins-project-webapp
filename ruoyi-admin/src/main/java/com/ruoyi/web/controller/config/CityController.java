package com.ruoyi.web.controller.config;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.BookType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.config.domain.City;
import com.ruoyi.config.service.ICityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 城市Controller
 * 
 * @author metacoin
 * @date 2022-09-29
 */
@RestController
@RequestMapping("/config/city")
public class CityController extends BaseController
{
    @Autowired
    private ICityService cityService;

    /**
     * 分页查询城市列表
     */
    @PreAuthorize("@ss.hasPermi('config:city:list')")
    @GetMapping("/list")
    public TableDataInfo list(City city)
    {
        startPage();
        List<City> list = cityService.selectCityList(city);
        return getDataTable(list);
    }

    /**
     * 导出城市列表
     */
    @PreAuthorize("@ss.hasPermi('config:city:export')")
    @Log(title = "城市", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, City city)
    {
        List<City> list = cityService.selectCityList(city);
        ExcelUtil<City> util = new ExcelUtil<City>(City.class);
        util.exportExcel(response, list, "城市数据");
    }

    /**
     * 获取城市详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:city:query')")
    @GetMapping(value = "/{cityId}")
    public AjaxResult getInfo(@PathVariable("cityId") Long cityId)
    {
        return AjaxResult.success(cityService.selectCityByCityId(cityId));
    }

    /**
     * 新增城市
     */
    @PreAuthorize("@ss.hasPermi('config:city:add')")
    @Log(title = "城市", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody City city)
    {
        city.setCreateBy(getUsername());
        city.setUpdateBy(getUsername());
        return toAjax(cityService.insertCity(city));
    }

    /**
     * 修改城市
     */
    @PreAuthorize("@ss.hasPermi('config:city:edit')")
    @Log(title = "城市", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody City city)
    {
        city.setUpdateBy(getUsername());
        return toAjax(cityService.updateCity(city));
    }

    /**
     * 删除城市
     */
    @PreAuthorize("@ss.hasPermi('config:city:remove')")
    @Log(title = "城市", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cityIds}")
    public AjaxResult remove(@PathVariable Long[] cityIds)
    {
        return toAjax(cityService.deleteCityByCityIds(cityIds));
    }

    /**
     * 获取城市下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(City params)
    {
        List<City> list = cityService.selectCityAll();
        return AjaxResult.success(list);
    }

}
