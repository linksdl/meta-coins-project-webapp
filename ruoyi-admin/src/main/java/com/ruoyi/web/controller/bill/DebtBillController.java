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
import com.ruoyi.bill.domain.DebtBill;
import com.ruoyi.bill.service.IDebtBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 借贷账单Controller
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@RestController
@RequestMapping("/bill/debt")
public class DebtBillController extends BaseController
{
    @Autowired
    private IDebtBillService debtBillService;

    /**
     * 分页查询借贷账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:list')")
    @GetMapping("/list")
    public TableDataInfo list(DebtBill debtBill)
    {
        startPage();
        List<DebtBill> list = debtBillService.selectDebtBillList(debtBill);
        return getDataTable(list);
    }

    /**
     * 导出借贷账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:export')")
    @Log(title = "借贷账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DebtBill debtBill)
    {
        List<DebtBill> list = debtBillService.selectDebtBillList(debtBill);
        ExcelUtil<DebtBill> util = new ExcelUtil<DebtBill>(DebtBill.class);
        util.exportExcel(response, list, "借贷账单数据");
    }

    /**
     * 获取借贷账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:query')")
    @GetMapping(value = "/{debtId}")
    public AjaxResult getInfo(@PathVariable("debtId") Long debtId)
    {
        return AjaxResult.success(debtBillService.selectDebtBillByDebtId(debtId));
    }

    /**
     * 新增借贷账单
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:add')")
    @Log(title = "借贷账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DebtBill debtBill)
    {
        debtBill.setCreateBy(getUsername());
        debtBill.setUpdateBy(getUsername());
        return toAjax(debtBillService.insertDebtBill(debtBill));
    }

    /**
     * 修改借贷账单
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:edit')")
    @Log(title = "借贷账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DebtBill debtBill)
    {
        debtBill.setUpdateBy(getUsername());
        return toAjax(debtBillService.updateDebtBill(debtBill));
    }

    /**
     * 删除借贷账单
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:remove')")
    @Log(title = "借贷账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{debtIds}")
    public AjaxResult remove(@PathVariable Long[] debtIds)
    {
        return toAjax(debtBillService.deleteDebtBillByDebtIds(debtIds));
    }

    /**
     * 获取借贷账单下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<DebtBill> list = debtBillService.selectDebtBillAll();
        return AjaxResult.success(list);
    }

}
