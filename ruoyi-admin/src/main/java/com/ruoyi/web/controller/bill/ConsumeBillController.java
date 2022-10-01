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
import com.ruoyi.bill.domain.ConsumeBill;
import com.ruoyi.bill.service.IConsumeBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 支出账单Controller
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@RestController
@RequestMapping("/bill/consume")
public class ConsumeBillController extends BaseController
{
    @Autowired
    private IConsumeBillService consumeBillService;

    /**
     * 分页查询支出账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConsumeBill consumeBill)
    {
        startPage();
        List<ConsumeBill> list = consumeBillService.selectConsumeBillList(consumeBill);
        return getDataTable(list);
    }

    /**
     * 导出支出账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:export')")
    @Log(title = "支出账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConsumeBill consumeBill)
    {
        List<ConsumeBill> list = consumeBillService.selectConsumeBillList(consumeBill);
        ExcelUtil<ConsumeBill> util = new ExcelUtil<ConsumeBill>(ConsumeBill.class);
        util.exportExcel(response, list, "支出账单数据");
    }

    /**
     * 获取支出账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:query')")
    @GetMapping(value = "/{consumeId}")
    public AjaxResult getInfo(@PathVariable("consumeId") Long consumeId)
    {
        return AjaxResult.success(consumeBillService.selectConsumeBillByConsumeId(consumeId));
    }

    /**
     * 新增支出账单
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:add')")
    @Log(title = "支出账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConsumeBill consumeBill)
    {
        consumeBill.setCreateBy(getUsername());
        consumeBill.setUpdateBy(getUsername());
        return toAjax(consumeBillService.insertConsumeBill(consumeBill));
    }

    /**
     * 修改支出账单
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:edit')")
    @Log(title = "支出账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConsumeBill consumeBill)
    {
        consumeBill.setUpdateBy(getUsername());
        return toAjax(consumeBillService.updateConsumeBill(consumeBill));
    }

    /**
     * 删除支出账单
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:remove')")
    @Log(title = "支出账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consumeIds}")
    public AjaxResult remove(@PathVariable Long[] consumeIds)
    {
        return toAjax(consumeBillService.deleteConsumeBillByConsumeIds(consumeIds));
    }

    /**
     * 获取支出账单下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<ConsumeBill> list = consumeBillService.selectConsumeBillAll();
        return AjaxResult.success(list);
    }

}
