package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.book.domain.Book;
import com.ruoyi.config.book.service.IBookService;
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
import com.ruoyi.config.domain.Label;
import com.ruoyi.config.service.ILabelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标签管理Controller
 * 
 * @author metacoin
 * @date 2022-10-02
 */
@RestController
@RequestMapping("/config/label")
public class LabelController extends BaseController
{
    @Autowired
    private ILabelService labelService;

    @Resource
    private IBookService bookService;

    /**
     * 分页查询标签管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:label:list')")
    @GetMapping("/list")
    public TableDataInfo list(Label label)
    {
        startPage();
        List<Label> list = labelService.selectLabelList(label);
        return getDataTable(list);
    }

    /**
     * 导出标签管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:label:export')")
    @Log(title = "标签管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Label label)
    {
        List<Label> list = labelService.selectLabelList(label);
        ExcelUtil<Label> util = new ExcelUtil<Label>(Label.class);
        util.exportExcel(response, list, "标签管理数据");
    }

    /**
     * 获取标签管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:label:query')")
    @GetMapping(value = "/{labelId}")
    public AjaxResult getInfo(@PathVariable("labelId") Long labelId)
    {
        return AjaxResult.success(labelService.selectLabelByLabelId(labelId));
    }

    /**
     * 新增标签管理
     */
    @PreAuthorize("@ss.hasPermi('config:label:add')")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Label label)
    {
        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        label.setCreateBy(getUsername());
        label.setUpdateBy(getUsername());
        label.setUserId(getUserId());
        label.setUserName(getUsername());
        label.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        label.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        return toAjax(labelService.insertLabel(label));
    }

    /**
     * 修改标签管理
     */
    @PreAuthorize("@ss.hasPermi('config:label:edit')")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Label label)
    {
        label.setUpdateBy(getUsername());
        return toAjax(labelService.updateLabel(label));
    }

    /**
     * 删除标签管理
     */
    @PreAuthorize("@ss.hasPermi('config:label:remove')")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{labelIds}")
    public AjaxResult remove(@PathVariable Long[] labelIds)
    {
        return toAjax(labelService.deleteLabelByLabelIds(labelIds));
    }

    /**
     * 获取标签管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<Label> list = labelService.selectLabelAll();
        return AjaxResult.success(list);
    }

}
