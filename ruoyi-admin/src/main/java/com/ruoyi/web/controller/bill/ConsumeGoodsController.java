package com.ruoyi.web.controller.bill;

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
import com.ruoyi.bill.domain.ConsumeGoods;
import com.ruoyi.bill.service.IConsumeGoodsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 支出商品Controller
 * 
 * @author metacoin
 * @date 2022-10-30
 */
@RestController
@RequestMapping("/bill/consumeGoods")
public class ConsumeGoodsController extends BaseController
{
    @Autowired
    private IConsumeGoodsService consumeGoodsService;

    /**
     * 分页查询支出商品列表
     */
    @PreAuthorize("@ss.hasPermi('bill:consumeGoods:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConsumeGoods consumeGoods)
    {
        startPage();
        List<ConsumeGoods> list = consumeGoodsService.selectConsumeGoodsList(consumeGoods);
        return getDataTable(list);
    }

    /**
     * 导出支出商品列表
     */
    @PreAuthorize("@ss.hasPermi('bill:consumeGoods:export')")
    @Log(title = "支出商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConsumeGoods consumeGoods)
    {
        List<ConsumeGoods> list = consumeGoodsService.selectConsumeGoodsList(consumeGoods);
        ExcelUtil<ConsumeGoods> util = new ExcelUtil<ConsumeGoods>(ConsumeGoods.class);
        util.exportExcel(response, list, "支出商品数据");
    }

    /**
     * 获取支出商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:consumeGoods:query')")
    @GetMapping(value = "/{consumeGoodsId}")
    public AjaxResult getInfo(@PathVariable("consumeGoodsId") Long consumeGoodsId)
    {
        return AjaxResult.success(consumeGoodsService.selectConsumeGoodsByConsumeGoodsId(consumeGoodsId));
    }

    /**
     * 新增支出商品
     */
    @PreAuthorize("@ss.hasPermi('bill:consumeGoods:add')")
    @Log(title = "支出商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConsumeGoods consumeGoods)
    {
        consumeGoods.setCreateBy(getUsername());
        consumeGoods.setUpdateBy(getUsername());
        return toAjax(consumeGoodsService.insertConsumeGoods(consumeGoods));
    }

    /**
     * 修改支出商品
     */
    @PreAuthorize("@ss.hasPermi('bill:consumeGoods:edit')")
    @Log(title = "支出商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConsumeGoods consumeGoods)
    {
        consumeGoods.setUpdateBy(getUsername());
        return toAjax(consumeGoodsService.updateConsumeGoods(consumeGoods));
    }

    /**
     * 删除支出商品
     */
    @PreAuthorize("@ss.hasPermi('bill:consumeGoods:remove')")
    @Log(title = "支出商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consumeGoodsIds}")
    public AjaxResult remove(@PathVariable Long[] consumeGoodsIds)
    {
        return toAjax(consumeGoodsService.deleteConsumeGoodsByConsumeGoodsIds(consumeGoodsIds));
    }

    /**
     * 获取支出商品下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<ConsumeGoods> list = consumeGoodsService.selectConsumeGoodsAll();
        return AjaxResult.success(list);
    }

}
