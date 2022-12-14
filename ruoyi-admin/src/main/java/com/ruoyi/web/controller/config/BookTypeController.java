package com.ruoyi.web.controller.config;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.Book;
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
import com.ruoyi.config.domain.BookType;
import com.ruoyi.config.service.IBookTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 账本类型Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/bookType")
public class BookTypeController extends BaseController
{
    @Autowired
    private IBookTypeService bookTypeService;

    /**
     * 分页查询账本类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:bookType:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookType bookType)
    {
        startPage();
        List<BookType> list = bookTypeService.selectBookTypeList(bookType);
        return getDataTable(list);
    }

    /**
     * 导出账本类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:bookType:export')")
    @Log(title = "账本类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookType bookType)
    {
        List<BookType> list = bookTypeService.selectBookTypeList(bookType);
        ExcelUtil<BookType> util = new ExcelUtil<BookType>(BookType.class);
        util.exportExcel(response, list, "账本类型数据");
    }

    /**
     * 获取账本类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:bookType:query')")
    @GetMapping(value = "/{bookTypeId}")
    public AjaxResult getInfo(@PathVariable("bookTypeId") Long bookTypeId)
    {
        return AjaxResult.success(bookTypeService.selectBookTypeByBookTypeId(bookTypeId));
    }

    /**
     * 新增账本类型
     */
    @PreAuthorize("@ss.hasPermi('config:bookType:add')")
    @Log(title = "账本类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookType bookType)
    {
        bookType.setCreateBy(getUsername());
        bookType.setUpdateBy(getUsername());
        return toAjax(bookTypeService.insertBookType(bookType));
    }

    /**
     * 修改账本类型
     */
    @PreAuthorize("@ss.hasPermi('config:bookType:edit')")
    @Log(title = "账本类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookType bookType)
    {
        bookType.setUpdateBy(getUsername());
        return toAjax(bookTypeService.updateBookType(bookType));
    }

    /**
     * 删除账本类型
     */
    @PreAuthorize("@ss.hasPermi('config:bookType:remove')")
    @Log(title = "账本类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookTypeIds}")
    public AjaxResult remove(@PathVariable Long[] bookTypeIds)
    {
        return toAjax(bookTypeService.deleteBookTypeByBookTypeIds(bookTypeIds));
    }

    /**
     * 获取账本类型下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(BookType params)
    {
        List<BookType> list = bookTypeService.selectBookTypeAll(params);
        for(BookType bookType:list){
            bookType.setDisabled(bookType.getEnableStatus() == 0);
        }
        return AjaxResult.success(list);
    }

}
