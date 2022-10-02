package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.*;
import com.ruoyi.config.service.IBookService;
import com.ruoyi.config.service.IEnterpriseTypeService;
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
import com.ruoyi.config.service.IEnterpriseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家管理Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/enterprise")
public class EnterpriseController extends BaseController
{
    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private IEnterpriseTypeService enterpriseTypeService;

    @Resource
    private IBookService bookService;

    @Resource
    private ISysUserService userService;

    /**
     * 分页查询商家管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(Enterprise enterprise)
    {
        startPage();
        enterprise.setUserId(getUserId());
        List<Enterprise> list = enterpriseService.selectEnterpriseList(enterprise);
        for(Enterprise enterprise1:list){
            enterprise1.setDisabled(enterprise1.getEnableStatus() == 0);
            enterprise1.setValue(enterprise1.getEnterpriseName());
        }
        return getDataTable(list);
    }

    /**
     * 导出商家管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:enterprise:export')")
    @Log(title = "商家管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Enterprise enterprise)
    {
        List<Enterprise> list = enterpriseService.selectEnterpriseList(enterprise);
        ExcelUtil<Enterprise> util = new ExcelUtil<Enterprise>(Enterprise.class);
        util.exportExcel(response, list, "商家管理数据");
    }

    /**
     * 获取商家管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:enterprise:query')")
    @GetMapping(value = "/{enterpriseId}")
    public AjaxResult getInfo(@PathVariable("enterpriseId") Long enterpriseId)
    {
        return AjaxResult.success(enterpriseService.selectEnterpriseByEnterpriseId(enterpriseId));
    }

    /**
     * 新增商家管理
     */
    @PreAuthorize("@ss.hasPermi('config:enterprise:add')")
    @Log(title = "商家管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Enterprise enterprise)
    {
        EnterpriseType enterpriseType = enterpriseTypeService.selectEnterpriseTypeByEnterpriseTypeId(enterprise.getEnterpriseTypeId());
        enterprise.setEnterpriseTypeName(enterpriseType.getEnterpriseTypeName());

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        enterprise.setCreateBy(getUsername());
        enterprise.setUpdateBy(getUsername());
        enterprise.setUserId(getUserId());
        enterprise.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        enterprise.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        enterprise.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        return toAjax(enterpriseService.insertEnterprise(enterprise));
    }

    /**
     * 修改商家管理
     */
    @PreAuthorize("@ss.hasPermi('config:enterprise:edit')")
    @Log(title = "商家管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Enterprise enterprise)
    {
        EnterpriseType enterpriseType = enterpriseTypeService.selectEnterpriseTypeByEnterpriseTypeId(enterprise.getEnterpriseTypeId());
        enterprise.setEnterpriseTypeName(enterpriseType.getEnterpriseTypeName());
        enterprise.setUpdateBy(getUsername());
        return toAjax(enterpriseService.updateEnterprise(enterprise));
    }

    /**
     * 删除商家管理
     */
    @PreAuthorize("@ss.hasPermi('config:enterprise:remove')")
    @Log(title = "商家管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{enterpriseIds}")
    public AjaxResult remove(@PathVariable Long[] enterpriseIds)
    {
        return toAjax(enterpriseService.deleteEnterpriseByEnterpriseIds(enterpriseIds));
    }

    /**
     * 获取商家管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(Enterprise params)
    {
        // params
        params.setUserId(getUserId());

        List<Enterprise> list = enterpriseService.selectEnterpriseAll(params);
        for(Enterprise enterprise:list){
            enterprise.setDisabled(enterprise.getEnableStatus() == 0);
            enterprise.setValue(enterprise.getEnterpriseName());
        }
        return AjaxResult.success(list);
    }

}
