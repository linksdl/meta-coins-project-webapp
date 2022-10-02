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
import com.ruoyi.config.domain.MoneyType;
import com.ruoyi.config.service.IMoneyTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 币种类型Controller
 * 
 * @author metacoin
 * @date 2022-10-02
 */
@RestController
@RequestMapping("/config/moneyType")
public class MoneyTypeController extends BaseController
{
    @Autowired
    private IMoneyTypeService moneyTypeService;

    /**
     * 分页查询币种类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:moneyType:list')")
    @GetMapping("/list")
    public TableDataInfo list(MoneyType moneyType)
    {
        startPage();
        List<MoneyType> list = moneyTypeService.selectMoneyTypeList(moneyType);
        return getDataTable(list);
    }

    /**
     * 导出币种类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:moneyType:export')")
    @Log(title = "币种类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MoneyType moneyType)
    {
        List<MoneyType> list = moneyTypeService.selectMoneyTypeList(moneyType);
        ExcelUtil<MoneyType> util = new ExcelUtil<MoneyType>(MoneyType.class);
        util.exportExcel(response, list, "币种类型数据");
    }

    /**
     * 获取币种类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:moneyType:query')")
    @GetMapping(value = "/{moneyTypeId}")
    public AjaxResult getInfo(@PathVariable("moneyTypeId") Long moneyTypeId)
    {
        return AjaxResult.success(moneyTypeService.selectMoneyTypeByMoneyTypeId(moneyTypeId));
    }

    /**
     * 新增币种类型
     */
    @PreAuthorize("@ss.hasPermi('config:moneyType:add')")
    @Log(title = "币种类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MoneyType moneyType)
    {
        moneyType.setCreateBy(getUsername());
        moneyType.setUpdateBy(getUsername());
        return toAjax(moneyTypeService.insertMoneyType(moneyType));
    }

    /**
     * 修改币种类型
     */
    @PreAuthorize("@ss.hasPermi('config:moneyType:edit')")
    @Log(title = "币种类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MoneyType moneyType)
    {
        moneyType.setUpdateBy(getUsername());
        return toAjax(moneyTypeService.updateMoneyType(moneyType));
    }

    /**
     * 删除币种类型
     */
    @PreAuthorize("@ss.hasPermi('config:moneyType:remove')")
    @Log(title = "币种类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{moneyTypeIds}")
    public AjaxResult remove(@PathVariable Long[] moneyTypeIds)
    {
        return toAjax(moneyTypeService.deleteMoneyTypeByMoneyTypeIds(moneyTypeIds));
    }

    /**
     * 获取币种类型下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(MoneyType params)
    {
        List<MoneyType> list = moneyTypeService.selectMoneyTypeAll(params);
        for(MoneyType moneyType:list){
            moneyType.setDisabled(moneyType.getEnableStatus() == 0);
        }
        return AjaxResult.success(list);
    }

}
