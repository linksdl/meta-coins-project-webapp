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
import com.ruoyi.bill.domain.TransferBill;
import com.ruoyi.bill.service.ITransferBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 转账账单Controller
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@RestController
@RequestMapping("/bill/transfer")
public class TransferBillController extends BaseController
{
    @Autowired
    private ITransferBillService transferBillService;

    /**
     * 分页查询转账账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:list')")
    @GetMapping("/list")
    public TableDataInfo list(TransferBill transferBill)
    {
        startPage();
        List<TransferBill> list = transferBillService.selectTransferBillList(transferBill);
        return getDataTable(list);
    }

    /**
     * 导出转账账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:export')")
    @Log(title = "转账账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TransferBill transferBill)
    {
        List<TransferBill> list = transferBillService.selectTransferBillList(transferBill);
        ExcelUtil<TransferBill> util = new ExcelUtil<TransferBill>(TransferBill.class);
        util.exportExcel(response, list, "转账账单数据");
    }

    /**
     * 获取转账账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:query')")
    @GetMapping(value = "/{transferId}")
    public AjaxResult getInfo(@PathVariable("transferId") Long transferId)
    {
        return AjaxResult.success(transferBillService.selectTransferBillByTransferId(transferId));
    }

    /**
     * 新增转账账单
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:add')")
    @Log(title = "转账账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferBill transferBill)
    {
        transferBill.setCreateBy(getUsername());
        transferBill.setUpdateBy(getUsername());
        return toAjax(transferBillService.insertTransferBill(transferBill));
    }

    /**
     * 修改转账账单
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:edit')")
    @Log(title = "转账账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferBill transferBill)
    {
        transferBill.setUpdateBy(getUsername());
        return toAjax(transferBillService.updateTransferBill(transferBill));
    }

    /**
     * 删除转账账单
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:remove')")
    @Log(title = "转账账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{transferIds}")
    public AjaxResult remove(@PathVariable Long[] transferIds)
    {
        return toAjax(transferBillService.deleteTransferBillByTransferIds(transferIds));
    }

    /**
     * 获取转账账单下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<TransferBill> list = transferBillService.selectTransferBillAll();
        return AjaxResult.success(list);
    }

}
