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
import com.ruoyi.config.domain.EntityType;
import com.ruoyi.config.service.IEntityTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家类型Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/entityType")
public class EntityTypeController extends BaseController
{
    @Autowired
    private IEntityTypeService entityTypeService;

    /**
     * 分页查询商家类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:entityType:list')")
    @GetMapping("/list")
    public TableDataInfo list(EntityType entityType)
    {
        startPage();
        List<EntityType> list = entityTypeService.selectEntityTypeList(entityType);
        return getDataTable(list);
    }

    /**
     * 导出商家类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:entityType:export')")
    @Log(title = "商家类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntityType entityType)
    {
        List<EntityType> list = entityTypeService.selectEntityTypeList(entityType);
        ExcelUtil<EntityType> util = new ExcelUtil<EntityType>(EntityType.class);
        util.exportExcel(response, list, "商家类型数据");
    }

    /**
     * 获取商家类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:entityType:query')")
    @GetMapping(value = "/{entityTypeId}")
    public AjaxResult getInfo(@PathVariable("entityTypeId") Long entityTypeId)
    {
        return AjaxResult.success(entityTypeService.selectEntityTypeByEntityTypeId(entityTypeId));
    }

    /**
     * 新增商家类型
     */
    @PreAuthorize("@ss.hasPermi('config:entityType:add')")
    @Log(title = "商家类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EntityType entityType)
    {
        entityType.setCreateBy(getUsername());
        entityType.setUpdateBy(getUsername());
        return toAjax(entityTypeService.insertEntityType(entityType));
    }

    /**
     * 修改商家类型
     */
    @PreAuthorize("@ss.hasPermi('config:entityType:edit')")
    @Log(title = "商家类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EntityType entityType)
    {
        entityType.setUpdateBy(getUsername());
        return toAjax(entityTypeService.updateEntityType(entityType));
    }

    /**
     * 删除商家类型
     */
    @PreAuthorize("@ss.hasPermi('config:entityType:remove')")
    @Log(title = "商家类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{entityTypeIds}")
    public AjaxResult remove(@PathVariable Long[] entityTypeIds)
    {
        return toAjax(entityTypeService.deleteEntityTypeByEntityTypeIds(entityTypeIds));
    }

    /**
     * 获取商家类型下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(EntityType params)
    {
        // params

        List<EntityType> list = entityTypeService.selectEntityTypeAll(params);
        for(EntityType entityType :list){
            entityType.setDisabled(entityType.getEnableStatus() == 0);
        }
        return AjaxResult.success(list);
    }

}
