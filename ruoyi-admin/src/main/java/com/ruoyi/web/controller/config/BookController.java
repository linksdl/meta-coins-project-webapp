package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.BookType;
import com.ruoyi.config.service.IBookTypeService;
import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.config.domain.Book;
import com.ruoyi.config.service.IBookService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个人账本Controller
 * 
 * @author metacoin
 * @date 2022-09-24
 */
@RestController
@RequestMapping("/config/book")
public class BookController extends BaseController
{
    @Autowired
    private IBookService bookService;

    @Resource
    private IBookTypeService bookTypeService;

    @Resource
    private ISysUserService userService;

    /**
     * 查询个人账本列表
     */
    @PreAuthorize("@ss.hasPermi('config:book:list')")
    @GetMapping("/list")
    public TableDataInfo list(Book book)
    {
        startPage();
        book.setUserId(getUserId());
        List<Book> list = bookService.selectBookList(book);
        return getDataTable(list);
    }

    /**
     * 导出个人账本列表
     */
    @PreAuthorize("@ss.hasPermi('config:book:export')")
    @Log(title = "个人账本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Book book)
    {
        List<Book> list = bookService.selectBookList(book);
        ExcelUtil<Book> util = new ExcelUtil<Book>(Book.class);
        util.exportExcel(response, list, "个人账本数据");
    }

    /**
     * 获取个人账本详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:book:query')")
    @GetMapping(value = "/{bookId}")
    public AjaxResult getInfo(@PathVariable("bookId") Long bookId)
    {
        return AjaxResult.success(bookService.selectBookByBookId(bookId));
    }

    /**
     * 新增个人账本
     */
    @PreAuthorize("@ss.hasPermi('config:book:add')")
    @Log(title = "个人账本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Book book)
    {
        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() >= 1 && book.getBookDefault() == 1)
        {
            return AjaxResult.error("只能创建一个设置为默认的账本！！！");
        }
        book.setCreateBy(getUsername());
        book.setUpdateBy(getUsername());
        book.setUserId(getUserId());
        book.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
        BookType bookType = bookTypeService.selectBookTypeByBookTypeId(book.getBookTypeId());
        book.setBookTypeName(bookType.getBookTypeName());
        return toAjax(bookService.insertBook(book));
    }

    /**
     * 修改个人账本
     */
    @PreAuthorize("@ss.hasPermi('config:book:edit')")
    @Log(title = "个人账本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Book book)
    {
        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() >= 1 && book.getBookDefault() == 1)
        {
            return AjaxResult.error("只能创建一个设置为默认的账本！！！");
        }
        book.setUpdateBy(getUsername());
        return toAjax(bookService.updateBook(book));
    }

    /**
     * 删除个人账本
     */
    @PreAuthorize("@ss.hasPermi('config:book:remove')")
    @Log(title = "个人账本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookIds}")
    public AjaxResult remove(@PathVariable Long[] bookIds)
    {
        return toAjax(bookService.deleteBookByBookIds(bookIds));
    }
}
