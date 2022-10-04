package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.Book;
import com.ruoyi.config.service.IBookService;
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
import com.ruoyi.config.domain.Category;
import com.ruoyi.config.service.ICategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 分类管理Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    @Resource
    private IBookService bookService;

    /**
     * 分页查询分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:category:list')")
    @GetMapping("/list")
    public AjaxResult list(Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        return AjaxResult.success(list);
    }

    /**
     * 导出分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:category:export')")
    @Log(title = "分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "分类管理数据");
    }

    /**
     * 获取分类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return AjaxResult.success(categoryService.selectCategoryByCategoryId(categoryId));
    }

    /**
     * 新增分类管理
     */
    @PreAuthorize("@ss.hasPermi('config:category:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        Category parent = categoryService.selectCategoryByCategoryId(category.getCategoryParentId());
        if(parent != null){
            category.setCategoryParentName(parent.getCategoryName());
        }else{
            category.setCategoryParentName("root");
        }

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        category.setCreateBy(getUsername());
        category.setUpdateBy(getUsername());
        category.setUserId(getUserId());
        category.setUserName(getUsername());
        category.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        category.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改分类管理
     */
    @PreAuthorize("@ss.hasPermi('config:category:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        Category parent = categoryService.selectCategoryByCategoryId(category.getCategoryParentId());
        if(parent != null){
            category.setCategoryParentName(parent.getCategoryName());
        }else{
            category.setCategoryParentName("root");
        }
        category.setUpdateBy(getUsername());
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除分类管理
     */
    @PreAuthorize("@ss.hasPermi('config:category:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(categoryService.deleteCategoryByCategoryIds(categoryIds));
    }

    /**
     * 获取分类管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<Category> list = categoryService.selectCategoryAll();
        return AjaxResult.success(list);
    }

}
