package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.BookType;
import com.ruoyi.config.domain.Member;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ruoyi.config.domain.MemberType;
import com.ruoyi.config.service.IMemberTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成员类型Controller
 * 
 * @author metacoin
 * @date 2022-09-29
 */
@RestController
@RequestMapping("/config/memberType")
public class MemberTypeController extends BaseController
{
    @Resource
    private IMemberTypeService memberTypeService;

    /**
     * 分页查询成员类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:memberType:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberType memberType)
    {
        startPage();
        List<MemberType> list = memberTypeService.selectMemberTypeList(memberType);
        return getDataTable(list);
    }

    /**
     * 导出成员类型列表
     */
    @PreAuthorize("@ss.hasPermi('config:memberType:export')")
    @Log(title = "成员类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberType memberType)
    {
        List<MemberType> list = memberTypeService.selectMemberTypeList(memberType);
        ExcelUtil<MemberType> util = new ExcelUtil<MemberType>(MemberType.class);
        util.exportExcel(response, list, "成员类型数据");
    }

    /**
     * 获取成员类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:memberType:query')")
    @GetMapping(value = "/{memberTypeId}")
    public AjaxResult getInfo(@PathVariable("memberTypeId") Long memberTypeId)
    {
        return AjaxResult.success(memberTypeService.selectMemberTypeByMemberTypeId(memberTypeId));
    }

    /**
     * 新增成员类型
     */
    @PreAuthorize("@ss.hasPermi('config:memberType:add')")
    @Log(title = "成员类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberType memberType)
    {
        memberType.setCreateBy(getUsername());
        memberType.setUpdateBy(getUsername());
        return toAjax(memberTypeService.insertMemberType(memberType));
    }

    /**
     * 修改成员类型
     */
    @PreAuthorize("@ss.hasPermi('config:memberType:edit')")
    @Log(title = "成员类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberType memberType)
    {
        memberType.setUpdateBy(getUsername());
        return toAjax(memberTypeService.updateMemberType(memberType));
    }

    /**
     * 删除成员类型
     */
    @PreAuthorize("@ss.hasPermi('config:memberType:remove')")
    @Log(title = "成员类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{memberTypeIds}")
    public AjaxResult remove(@PathVariable Long[] memberTypeIds)
    {
        return toAjax(memberTypeService.deleteMemberTypeByMemberTypeIds(memberTypeIds));
    }

    /**
     * 获取成员类型下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(MemberType params)
    {
        List<MemberType> list = memberTypeService.selectMemberTypeAll(params);
        for(MemberType memberType:list){
            memberType.setDisabled(memberType.getEnableStatus() == 0);
        }
        return AjaxResult.success(list);
    }

}
