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
import com.ruoyi.bill.domain.IncomeBill;
import com.ruoyi.bill.service.IIncomeBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 收入账单Controller
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@RestController
@RequestMapping("/bill/income")
public class IncomeBillController extends BaseController
{
    @Autowired
    private IIncomeBillService incomeBillService;

    /**
     * 分页查询收入账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:income:list')")
    @GetMapping("/list")
    public AjaxResult list(IncomeBill incomeBill)
    {
        List<IncomeBill> list = incomeBillService.selectIncomeBillList(incomeBill);
        return AjaxResult.success(list);
    }

    /**
     * 导出收入账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:income:export')")
    @Log(title = "收入账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IncomeBill incomeBill)
    {
        List<IncomeBill> list = incomeBillService.selectIncomeBillList(incomeBill);
        ExcelUtil<IncomeBill> util = new ExcelUtil<IncomeBill>(IncomeBill.class);
        util.exportExcel(response, list, "收入账单数据");
    }

    /**
     * 获取收入账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:income:query')")
    @GetMapping(value = "/{incomeId}")
    public AjaxResult getInfo(@PathVariable("incomeId") Long incomeId)
    {
        return AjaxResult.success(incomeBillService.selectIncomeBillByIncomeId(incomeId));
    }

    /**
     * 新增收入账单
     */
    @PreAuthorize("@ss.hasPermi('bill:income:add')")
    @Log(title = "收入账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IncomeBill incomeBill)
    {
        incomeBill.setCreateBy(getUsername());
        incomeBill.setUpdateBy(getUsername());
        return toAjax(incomeBillService.insertIncomeBill(incomeBill));
    }

    /**
     * 修改收入账单
     */
    @PreAuthorize("@ss.hasPermi('bill:income:edit')")
    @Log(title = "收入账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IncomeBill incomeBill)
    {
        incomeBill.setUpdateBy(getUsername());
        return toAjax(incomeBillService.updateIncomeBill(incomeBill));
    }

    /**
     * 删除收入账单
     */
    @PreAuthorize("@ss.hasPermi('bill:income:remove')")
    @Log(title = "收入账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{incomeIds}")
    public AjaxResult remove(@PathVariable Long[] incomeIds)
    {
        return toAjax(incomeBillService.deleteIncomeBillByIncomeIds(incomeIds));
    }

    /**
     * 获取收入账单下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<IncomeBill> list = incomeBillService.selectIncomeBillAll();
        return AjaxResult.success(list);
    }

}
