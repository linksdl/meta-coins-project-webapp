package com.ruoyi.web.controller.config;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.config.domain.Weather;
import com.ruoyi.config.service.IWeatherService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 天气管理Controller
 * 
 * @author metacoin
 * @date 2022-09-30
 */
@RestController
@RequestMapping("/config/weather")
public class WeatherController extends BaseController
{
    @Autowired
    private IWeatherService weatherService;

    /**
     * 分页查询天气管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:weather:list')")
    @GetMapping("/list")
    public TableDataInfo list(Weather weather)
    {
        startPage();
        List<Weather> list = weatherService.selectWeatherList(weather);
        return getDataTable(list);
    }

    /**
     * 导出天气管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:weather:export')")
    @Log(title = "天气管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Weather weather)
    {
        List<Weather> list = weatherService.selectWeatherList(weather);
        ExcelUtil<Weather> util = new ExcelUtil<Weather>(Weather.class);
        util.exportExcel(response, list, "天气管理数据");
    }

    /**
     * 获取天气管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:weather:query')")
    @GetMapping(value = "/{weatherId}")
    public AjaxResult getInfo(@PathVariable("weatherId") Long weatherId)
    {
        return AjaxResult.success(weatherService.selectWeatherByWeatherId(weatherId));
    }

    /**
     * 新增天气管理
     */
    @PreAuthorize("@ss.hasPermi('config:weather:add')")
    @Log(title = "天气管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Weather weather)
    {
        weather.setCreateBy(getUsername());
        weather.setUpdateBy(getUsername());
        return toAjax(weatherService.insertWeather(weather));
    }

    /**
     * 修改天气管理
     */
    @PreAuthorize("@ss.hasPermi('config:weather:edit')")
    @Log(title = "天气管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Weather weather)
    {
        weather.setUpdateBy(getUsername());
        return toAjax(weatherService.updateWeather(weather));
    }

    /**
     * 删除天气管理
     */
    @PreAuthorize("@ss.hasPermi('config:weather:remove')")
    @Log(title = "天气管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{weatherIds}")
    public AjaxResult remove(@PathVariable Long[] weatherIds)
    {
        return toAjax(weatherService.deleteWeatherByWeatherIds(weatherIds));
    }

    /**
     * 获取天气管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(Weather params)
    {

        List<Weather> list = weatherService.selectWeatherAll(params);
        return AjaxResult.success(list);
    }

}
