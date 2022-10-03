package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.*;
import com.ruoyi.config.service.IBookService;
import com.ruoyi.config.service.IEntityTypeService;
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
import com.ruoyi.config.service.IEntityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家管理Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/entity")
public class EntityController extends BaseController
{
    @Autowired
    private IEntityService entityService;

    @Autowired
    private IEntityTypeService entityTypeService;

    @Resource
    private IBookService bookService;

    @Resource
    private ISysUserService userService;

    /**
     * 分页查询商家管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:entity:list')")
    @GetMapping("/list")
    public TableDataInfo list(Entity entity)
    {
        startPage();
        entity.setUserId(getUserId());
        List<Entity> list = entityService.selectEntityList(entity);
        for(Entity entity1 :list){
            entity1.setDisabled(entity1.getEnableStatus() == 0);
            entity1.setValue(entity1.getEntityName());
        }
        return getDataTable(list);
    }

    /**
     * 导出商家管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:entity:export')")
    @Log(title = "商家管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Entity entity)
    {
        List<Entity> list = entityService.selectEntityList(entity);
        ExcelUtil<Entity> util = new ExcelUtil<Entity>(Entity.class);
        util.exportExcel(response, list, "商家管理数据");
    }

    /**
     * 获取商家管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:entity:query')")
    @GetMapping(value = "/{entityId}")
    public AjaxResult getInfo(@PathVariable("entityId") Long entityId)
    {
        return AjaxResult.success(entityService.selectEntityByEntityId(entityId));
    }

    /**
     * 新增商家管理
     */
    @PreAuthorize("@ss.hasPermi('config:entity:add')")
    @Log(title = "商家管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Entity entity)
    {
        EntityType entityType = entityTypeService.selectEntityTypeByEntityTypeId(entity.getEntityTypeId());
        entity.setEntityTypeName(entityType.getEntityTypeName());

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        entity.setCreateBy(getUsername());
        entity.setUpdateBy(getUsername());
        entity.setUserId(getUserId());
        entity.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        entity.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        entity.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        return toAjax(entityService.insertEntity(entity));
    }

    /**
     * 修改商家管理
     */
    @PreAuthorize("@ss.hasPermi('config:entity:edit')")
    @Log(title = "商家管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Entity entity)
    {
        EntityType entityType = entityTypeService.selectEntityTypeByEntityTypeId(entity.getEntityTypeId());
        entity.setEntityTypeName(entityType.getEntityTypeName());
        entity.setUpdateBy(getUsername());
        return toAjax(entityService.updateEntity(entity));
    }

    /**
     * 删除商家管理
     */
    @PreAuthorize("@ss.hasPermi('config:entity:remove')")
    @Log(title = "商家管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{entityIds}")
    public AjaxResult remove(@PathVariable Long[] entityIds)
    {
        return toAjax(entityService.deleteEntityByEntityIds(entityIds));
    }

    /**
     * 获取商家管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(Entity params)
    {
        // params
        params.setUserId(getUserId());

        List<Entity> list = entityService.selectEntityAll(params);
        for(Entity entity :list){
            entity.setDisabled(entity.getEnableStatus() == 0);
            entity.setValue(entity.getEntityName());
        }
        return AjaxResult.success(list);
    }

}
