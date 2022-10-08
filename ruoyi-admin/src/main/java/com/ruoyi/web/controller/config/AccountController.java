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
import com.ruoyi.config.domain.Account;
import com.ruoyi.config.service.IAccountService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 账户管理Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/account")
public class AccountController extends BaseController
{
    @Autowired
    private IAccountService accountService;

    @Resource
    private IBookService bookService;

    /**
     * 分页查询账户管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:account:list')")
    @GetMapping("/list")
    public AjaxResult list(Account account)
    {
        account.setUserId(getUserId());
        List<Account> list = accountService.selectAccountList(account);
        return AjaxResult.success(list);
    }

    /**
     * 导出账户管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:account:export')")
    @Log(title = "账户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Account account)
    {
        List<Account> list = accountService.selectAccountList(account);
        ExcelUtil<Account> util = new ExcelUtil<Account>(Account.class);
        util.exportExcel(response, list, "账户管理数据");
    }

    /**
     * 获取账户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:account:query')")
    @GetMapping(value = "/{accountId}")
    public AjaxResult getInfo(@PathVariable("accountId") Long accountId)
    {
        return AjaxResult.success(accountService.selectAccountByAccountId(accountId));
    }

    /**
     * 新增账户管理
     */
    @PreAuthorize("@ss.hasPermi('config:account:add')")
    @Log(title = "账户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Account account)
    {
        Account parent = accountService.selectAccountByAccountId(account.getAccountParentId());
        if(parent != null){
            account.setAccountParentName(parent.getAccountName());
        }else{
            account.setAccountParentName("root");
        }

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        account.setCreateBy(getUsername());
        account.setUpdateBy(getUsername());
        account.setUserId(getUserId());
        account.setUserName(getUsername());
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        account.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        account.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        return toAjax(accountService.insertAccount(account));
    }

    /**
     * 修改账户管理
     */
    @PreAuthorize("@ss.hasPermi('config:account:edit')")
    @Log(title = "账户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Account account)
    {
        Account parent = accountService.selectAccountByAccountId(account.getAccountParentId());
        if(parent != null){
            account.setAccountParentName(parent.getAccountName());
        }else{
            account.setAccountParentName("root");
        }
        account.setUpdateBy(getUsername());
        return toAjax(accountService.updateAccount(account));
    }

    /**
     * 删除账户管理
     */
    @PreAuthorize("@ss.hasPermi('config:account:remove')")
    @Log(title = "账户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{accountIds}")
    public AjaxResult remove(@PathVariable Long[] accountIds)
    {
        return toAjax(accountService.deleteAccountByAccountIds(accountIds));
    }

    /**
     * 获取账户管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<Account> list = accountService.selectAccountAll();
        return AjaxResult.success(list);
    }

}
