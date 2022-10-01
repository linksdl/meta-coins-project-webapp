package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.Book;
import com.ruoyi.config.service.IBookService;
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
import com.ruoyi.config.domain.Project;
import com.ruoyi.config.service.IProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目管理Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/project")
public class ProjectController extends BaseController
{
    @Autowired
    private IProjectService projectService;

    @Resource
    private IBookService bookService;

    @Resource
    private ISysUserService userService;

    /**
     * 分页查询项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(Project project)
    {
        startPage();
        project.setUserId(getUserId());
        List<Project> list = projectService.selectProjectList(project);
        return getDataTable(list);
    }

    /**
     * 导出项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:project:export')")
    @Log(title = "项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Project project)
    {
        List<Project> list = projectService.selectProjectList(project);
        ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
        util.exportExcel(response, list, "项目管理数据");
    }

    /**
     * 获取项目管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return AjaxResult.success(projectService.selectProjectByProjectId(projectId));
    }

    /**
     * 新增项目管理
     */
    @PreAuthorize("@ss.hasPermi('config:project:add')")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Project project)
    {
        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        project.setCreateBy(getUsername());
        project.setUpdateBy(getUsername());
        project.setUserId(getUserId());
        project.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        project.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        project.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改项目管理
     */
    @PreAuthorize("@ss.hasPermi('config:project:edit')")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Project project)
    {
        project.setUpdateBy(getUsername());
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除项目管理
     */
    @PreAuthorize("@ss.hasPermi('config:project:remove')")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(projectService.deleteProjectByProjectIds(projectIds));
    }

    /**
     * 获取项目管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(Project params)
    {
        List<Project> list = projectService.selectProjectAll();
        return AjaxResult.success(list);
    }

}
