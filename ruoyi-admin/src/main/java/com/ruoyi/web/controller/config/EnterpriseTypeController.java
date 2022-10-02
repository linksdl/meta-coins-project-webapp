package com.ruoyi.web.controller.config;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.Enterprise;
import com.ruoyi.config.domain.GoodsType;
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
import com.ruoyi.config.domain.EnterpriseType;
import com.ruoyi.config.service.IEnterpriseTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家类型Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/enterpriseType")
public class EnterpriseTypeController extends BaseController
{
    @Autowired
    private IEnterpriseTypeService enterpriseTypeService;

    /**
     * 分页查询商家类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:enterpriseType:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseType enterpriseType)
    {
        startPage();
        List<EnterpriseType> list = enterpriseTypeService.selectEnterpriseTypeList(enterpriseType);
        return getDataTable(list);
    }

    /**
     * 导出商家类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:enterpriseType:export')")
    @Log(title = "商家类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseType enterpriseType)
    {
        List<EnterpriseType> list = enterpriseTypeService.selectEnterpriseTypeList(enterpriseType);
        ExcelUtil<EnterpriseType> util = new ExcelUtil<EnterpriseType>(EnterpriseType.class);
        util.exportExcel(response, list, "商家类型数据");
    }

    /**
     * 获取商家类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:enterpriseType:query')")
    @GetMapping(value = "/{enterpriseTypeId}")
    public AjaxResult getInfo(@PathVariable("enterpriseTypeId") Long enterpriseTypeId)
    {
        return AjaxResult.success(enterpriseTypeService.selectEnterpriseTypeByEnterpriseTypeId(enterpriseTypeId));
    }

    /**
     * 新增商家类型
     */
    @PreAuthorize("@ss.hasPermi('config:enterpriseType:add')")
    @Log(title = "商家类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseType enterpriseType)
    {
        enterpriseType.setCreateBy(getUsername());
        enterpriseType.setUpdateBy(getUsername());
        return toAjax(enterpriseTypeService.insertEnterpriseType(enterpriseType));
    }

    /**
     * 修改商家类型
     */
    @PreAuthorize("@ss.hasPermi('config:enterpriseType:edit')")
    @Log(title = "商家类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseType enterpriseType)
    {
        enterpriseType.setUpdateBy(getUsername());
        return toAjax(enterpriseTypeService.updateEnterpriseType(enterpriseType));
    }

    /**
     * 删除商家类型
     */
    @PreAuthorize("@ss.hasPermi('config:enterpriseType:remove')")
    @Log(title = "商家类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{enterpriseTypeIds}")
    public AjaxResult remove(@PathVariable Long[] enterpriseTypeIds)
    {
        return toAjax(enterpriseTypeService.deleteEnterpriseTypeByEnterpriseTypeIds(enterpriseTypeIds));
    }

    /**
     * 获取商家类型下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(EnterpriseType params)
    {
        // params

        List<EnterpriseType> list = enterpriseTypeService.selectEnterpriseTypeAll(params);
        for(EnterpriseType enterpriseType:list){
            enterpriseType.setDisabled(enterpriseType.getEnableStatus() == 0);
        }
        return AjaxResult.success(list);
    }

}
