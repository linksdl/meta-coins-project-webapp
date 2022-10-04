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
import com.ruoyi.config.domain.GoodsType;
import com.ruoyi.config.service.IGoodsTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品类型Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/goodsType")
public class GoodsTypeController extends BaseController
{
    @Autowired
    private IGoodsTypeService goodsTypeService;

    /**
     * 分页查询商品类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:goodsType:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsType goodsType)
    {
        startPage();
        List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
        return getDataTable(list);
    }

    /**
     * 导出商品类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:goodsType:export')")
    @Log(title = "商品类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsType goodsType)
    {
        List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
        ExcelUtil<GoodsType> util = new ExcelUtil<GoodsType>(GoodsType.class);
        util.exportExcel(response, list, "商品类型数据");
    }

    /**
     * 获取商品类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:goodsType:query')")
    @GetMapping(value = "/{goodsTypeId}")
    public AjaxResult getInfo(@PathVariable("goodsTypeId") Long goodsTypeId)
    {
        return AjaxResult.success(goodsTypeService.selectGoodsTypeByGoodsTypeId(goodsTypeId));
    }

    /**
     * 新增商品类型
     */
    @PreAuthorize("@ss.hasPermi('config:goodsType:add')")
    @Log(title = "商品类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsType goodsType)
    {
        goodsType.setCreateBy(getUsername());
        goodsType.setUpdateBy(getUsername());
        return toAjax(goodsTypeService.insertGoodsType(goodsType));
    }

    /**
     * 修改商品类型
     */
    @PreAuthorize("@ss.hasPermi('config:goodsType:edit')")
    @Log(title = "商品类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsType goodsType)
    {
        goodsType.setUpdateBy(getUsername());
        return toAjax(goodsTypeService.updateGoodsType(goodsType));
    }

    /**
     * 删除商品类型
     */
    @PreAuthorize("@ss.hasPermi('config:goodsType:remove')")
    @Log(title = "商品类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsTypeIds}")
    public AjaxResult remove(@PathVariable Long[] goodsTypeIds)
    {
        return toAjax(goodsTypeService.deleteGoodsTypeByGoodsTypeIds(goodsTypeIds));
    }

    /**
     * 获取商品类型下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<GoodsType> list = goodsTypeService.selectGoodsTypeAll();
        return AjaxResult.success(list);
    }

}
