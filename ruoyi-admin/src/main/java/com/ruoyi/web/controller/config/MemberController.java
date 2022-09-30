package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.Book;
import com.ruoyi.config.service.IBookService;
import com.ruoyi.config.domain.MemberType;
import com.ruoyi.config.service.IMemberTypeService;
import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.config.domain.Member;
import com.ruoyi.config.service.IMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成员管理Controller
 * 
 * @author metacoin
 * @date 2022-09-29
 */
@RestController
@RequestMapping("/config/member")
public class MemberController extends BaseController
{
    @Resource
    private IMemberService memberService;

    @Resource
    private IMemberTypeService memberTypeService;

    @Resource
    private IBookService bookService;

    @Resource
    private ISysUserService userService;

    /**
     * 分页查询成员管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(Member member)
    {
        startPage();
        member.setUserId(getUserId());
        List<Member> list = memberService.selectMemberList(member);
        return getDataTable(list);
    }

    /**
     * 导出成员管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:member:export')")
    @Log(title = "成员管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Member member)
    {
        List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        util.exportExcel(response, list, "成员管理数据");
    }

    /**
     * 获取成员管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:member:query')")
    @GetMapping(value = "/{memberId}")
    public AjaxResult getInfo(@PathVariable("memberId") Long memberId)
    {
        return AjaxResult.success(memberService.selectMemberByMemberId(memberId));
    }

    /**
     * 新增成员管理
     */
    @PreAuthorize("@ss.hasPermi('config:member:add')")
    @Log(title = "成员管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Member member)
    {
        MemberType memberType = memberTypeService.selectMemberTypeByMemberTypeId(member.getMemberTypeId());
        member.setMemberTypeName(memberType.getMemberTypeName());

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        member.setCreateBy(getUsername());
        member.setUpdateBy(getUsername());
        member.setUserId(getUserId());
        member.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        member.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        member.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        return toAjax(memberService.insertMember(member));
    }

    /**
     * 修改成员管理
     */
    @PreAuthorize("@ss.hasPermi('config:member:edit')")
    @Log(title = "成员管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Member member)
    {
        MemberType memberType = memberTypeService.selectMemberTypeByMemberTypeId(member.getMemberTypeId());
        member.setMemberTypeName(memberType.getMemberTypeName());
        member.setUpdateBy(getUsername());
        return toAjax(memberService.updateMember(member));
    }

    /**
     * 删除成员管理
     */
    @PreAuthorize("@ss.hasPermi('config:member:remove')")
    @Log(title = "成员管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{memberIds}")
    public AjaxResult remove(@PathVariable Long[] memberIds)
    {
        return toAjax(memberService.deleteMemberByMemberIds(memberIds));
    }

    /**
     * 获取成员管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<Member> list = memberService.selectMemberAll();
        return AjaxResult.success(list);
    }

}
