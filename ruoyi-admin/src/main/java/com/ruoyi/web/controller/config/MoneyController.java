package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.Book;
import com.ruoyi.config.service.IBookService;
import com.ruoyi.config.domain.MoneyType;
import com.ruoyi.config.service.IMoneyTypeService;
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
import com.ruoyi.config.domain.Money;
import com.ruoyi.config.service.IMoneyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 币种管理Controller
 * 
 * @author metacoin
 * @date 2022-10-02
 */
@RestController
@RequestMapping("/config/money")
public class MoneyController extends BaseController
{
    @Autowired
    private IMoneyService moneyService;

    @Autowired
    private IMoneyTypeService moneyTypeService;

    @Resource
    private IBookService bookService;

    @Resource
    private ISysUserService userService;
    /**
     * 分页查询币种管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:money:list')")
    @GetMapping("/list")
    public TableDataInfo list(Money money)
    {
        startPage();
        money.setUserId(getUserId());
        List<Money> list = moneyService.selectMoneyList(money);
        return getDataTable(list);
    }

    /**
     * 导出币种管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:money:export')")
    @Log(title = "币种管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Money money)
    {
        List<Money> list = moneyService.selectMoneyList(money);
        ExcelUtil<Money> util = new ExcelUtil<Money>(Money.class);
        util.exportExcel(response, list, "币种管理数据");
    }

    /**
     * 获取币种管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:money:query')")
    @GetMapping(value = "/{moneyId}")
    public AjaxResult getInfo(@PathVariable("moneyId") Long moneyId)
    {
        return AjaxResult.success(moneyService.selectMoneyByMoneyId(moneyId));
    }

    /**
     * 新增币种管理
     */
    @PreAuthorize("@ss.hasPermi('config:money:add')")
    @Log(title = "币种管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Money money)
    {

        MoneyType moneyType = moneyTypeService.selectMoneyTypeByMoneyTypeId(money.getMoneyTypeId());
        money.setMoneyTypeName(moneyType.getMoneyTypeCname());
        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        money.setUserId(getUserId());
        money.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
        money.setCreateBy(getUsername());
        money.setUpdateBy(getUsername());
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        money.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        money.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);

        money.setMoneyCname(money.getMoneyCname()+"("+money.getMoneyRate().toString()+")");
        return toAjax(moneyService.insertMoney(money));
    }

    /**
     * 修改币种管理
     */
    @PreAuthorize("@ss.hasPermi('config:money:edit')")
    @Log(title = "币种管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Money money)
    {
        money.setUpdateBy(getUsername());
        money.setMoneyCname(money.getMoneyCname()+"("+money.getMoneyRate().toString()+")");
        return toAjax(moneyService.updateMoney(money));
    }

    /**
     * 删除币种管理
     */
    @PreAuthorize("@ss.hasPermi('config:money:remove')")
    @Log(title = "币种管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{moneyIds}")
    public AjaxResult remove(@PathVariable Long[] moneyIds)
    {
        return toAjax(moneyService.deleteMoneyByMoneyIds(moneyIds));
    }

    /**
     * 获取币种管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(Money params)
    {
        //params
        params.setUserId(getUserId());

        List<Money> list = moneyService.selectMoneyAll(params);
        for(Money money:list){
            money.setDisabled(money.getEnableStatus() == 0);
        }
        return AjaxResult.success(list);
    }

}
