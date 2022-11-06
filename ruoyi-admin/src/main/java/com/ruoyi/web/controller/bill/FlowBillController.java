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
import com.ruoyi.bill.domain.FlowBill;
import com.ruoyi.bill.service.IFlowBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 账单流水Controller
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@RestController
@RequestMapping("/bill/flow")
public class FlowBillController extends BaseController
{
    @Autowired
    private IFlowBillService flowBillService;

    /**
     * 分页查询账单流水列表
     */
    @PreAuthorize("@ss.hasPermi('bill:flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(FlowBill flowBill)
    {
        flowBill.setFlowUserId(getUserId());
        startPage();
        List<FlowBill> list = flowBillService.selectFlowBillList(flowBill);
        return getDataTable(list);
    }

    /**
     * 导出账单流水列表
     */
    @PreAuthorize("@ss.hasPermi('bill:flow:export')")
    @Log(title = "账单流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FlowBill flowBill)
    {
        List<FlowBill> list = flowBillService.selectFlowBillList(flowBill);
        ExcelUtil<FlowBill> util = new ExcelUtil<FlowBill>(FlowBill.class);
        util.exportExcel(response, list, "账单流水数据");
    }

    /**
     * 获取账单流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:flow:query')")
    @GetMapping(value = "/{flowId}")
    public AjaxResult getInfo(@PathVariable("flowId") Long flowId)
    {
        return AjaxResult.success(flowBillService.selectFlowBillByFlowId(flowId));
    }

    /**
     * 新增账单流水
     */
    @PreAuthorize("@ss.hasPermi('bill:flow:add')")
    @Log(title = "账单流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FlowBill flowBill)
    {
        flowBill.setCreateBy(getUsername());
        flowBill.setUpdateBy(getUsername());
        return toAjax(flowBillService.insertFlowBill(flowBill));
    }

    /**
     * 修改账单流水
     */
    @PreAuthorize("@ss.hasPermi('bill:flow:edit')")
    @Log(title = "账单流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FlowBill flowBill)
    {
        flowBill.setUpdateBy(getUsername());
        return toAjax(flowBillService.updateFlowBill(flowBill));
    }

    /**
     * 删除账单流水
     */
    @PreAuthorize("@ss.hasPermi('bill:flow:remove')")
    @Log(title = "账单流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{flowIds}")
    public AjaxResult remove(@PathVariable Long[] flowIds)
    {
        return toAjax(flowBillService.deleteFlowBillByFlowIds(flowIds));
    }

    /**
     * 获取账单流水下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<FlowBill> list = flowBillService.selectFlowBillAll();
        return AjaxResult.success(list);
    }

}
