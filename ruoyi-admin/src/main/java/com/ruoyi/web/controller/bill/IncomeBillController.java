package com.ruoyi.web.controller.bill;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.common.utils.FullDate;
import com.ruoyi.config.domain.*;
import com.ruoyi.config.service.*;
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
import com.ruoyi.bill.domain.IncomeBill;
import com.ruoyi.bill.service.IIncomeBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收入账单Controller
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@RestController
@RequestMapping("/bill/income")
public class IncomeBillController extends BaseController
{
    @Autowired
    private IIncomeBillService incomeBillService;

    @Resource
    private IBookService bookService;

    @Autowired
    private IWeatherService weatherService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IMoneyService moneyService;

    @Resource
    private IMemberService memberService;

    @Autowired
    private ILabelService labelService;

    @Autowired
    private IEntityService entityService;

    @Autowired
    private IEmotionService emotionService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAccountService accountService;

    /**
     * 分页查询收入账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:income:list')")
    @GetMapping("/list")
    public TableDataInfo list(IncomeBill incomeBill)
    {
        startPage();
        List<IncomeBill> list = incomeBillService.selectIncomeBillList(incomeBill);
        return getDataTable(list);
    }

    /**
     * 导出收入账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:income:export')")
    @Log(title = "收入账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IncomeBill incomeBill)
    {
        List<IncomeBill> list = incomeBillService.selectIncomeBillList(incomeBill);
        ExcelUtil<IncomeBill> util = new ExcelUtil<IncomeBill>(IncomeBill.class);
        util.exportExcel(response, list, "收入账单数据");
    }

    /**
     * 获取收入账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:income:query')")
    @GetMapping(value = "/{incomeId}")
    public AjaxResult getInfo(@PathVariable("incomeId") Long incomeId)
    {
        return AjaxResult.success(incomeBillService.selectIncomeBillByIncomeId(incomeId));
    }

    /**
     * 新增收入账单
     */
    @PreAuthorize("@ss.hasPermi('bill:income:add')")
    @Log(title = "收入账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IncomeBill bill) throws ParseException {

        // 基本信息
        Date incomeDateTime = bill.getIncomeDatetime();
        bill.setIncomeName(bill.getIncomeDatetime() +" 收入");
        bill.setIncomeImgs("");
        bill.setIcon("");
        bill.setOrderSort(0L);
        bill.setWeight(0L);
        bill.setIsDeleted(0);
        if (bill.getIncomeParentId() == null) {
            bill.setIncomeParentId(0L);
            bill.setIncomeParentName("root");
        }

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        bill.setIncomeBookId(books.get(0).getBookId());
        bill.setIncomeName(books.size() == 1 ? books.get(0).getBookName() : null);
        bill.setIncomeUserId(getUserId());
        bill.setIncomeUserName(getUsername());

        // 关于日期
        FullDate fullDate = DateStringUtils.getFullDate(incomeDateTime);
        bill.setIncomeDate(fullDate.getDate());
        bill.setIncomeYear(fullDate.getYear());
        bill.setIncomeQuarter(fullDate.getQuarter());
        bill.setIncomeMonth(fullDate.getMonth());
        bill.setIncomeWeek(fullDate.getDayOfWeek());
        bill.setIncomeYearWeek(fullDate.getWeekOfYear());
        bill.setIncomeDay(fullDate.getDay());
        bill.setIncomePeriod(fullDate.getPeriod());

        // 创建者
        bill.setCreateBy(getUsername());
        bill.setUpdateBy(getUsername());

        // 关联信息
        // 天气
        if (null != bill.getIncomeWeatherId()) {
             Weather weather = weatherService.selectWeatherByWeatherId(bill.getIncomeWeatherId());
             bill.setIncomeWeatherName(weather.getWeatherCname());
        }
        // 项目
        if(null != bill.getIncomeProjectId()) {
            Project project = projectService.selectProjectByProjectId(bill.getIncomeProjectId());
            bill.setIncomeProjectName(project.getProjectName());
        }
        else if (null != bill.getIncomeProjectName() && !"".equals(bill.getIncomeProjectName()))
        {
            Project temp = new Project();
            temp.setProjectName(bill.getIncomeProjectName());
            temp.setProjectDesc("添加收入账单创建");
            temp.setProjectType("income");
            temp.setProjectScope("income,loan-in,reimbursement-in,borrow,return-in,pay-in,debt-in");
            temp.setWeight(1L);
            temp.setOrderSort(1L);
            temp.setIcon("bug");
            temp.setEnableStatus(1L);
            temp.setIsDeleted(0);
            temp.setRemark("添加收入账单创建");
            projectService.insertProject(temp);
            bill.setIncomeProjectId(temp.getProjectId());
        }
        // 币种
        if (null != bill.getIncomeMoneyId())
        {
            Money money = moneyService.selectMoneyByMoneyId(bill.getIncomeMoneyId());
            bill.setIncomeMoneyName(money.getMoneyCname());
        }
        // 成员
        if (null != bill.getIncomeMemberId()) {
            Member member = memberService.selectMemberByMemberId(bill.getIncomeMemberId());
            bill.setIncomeMemberName(member.getMemberName());
        }
        // 标签
        if (null != bill.getIncomeLabelName() && !"".equals(bill.getIncomeLabelName())) {
            String[] labels = bill.getIncomeLabelName().split(" ");
            String[] labelIds = new String[labels.length];
            for(int i=0; i<labelIds.length; i++) {
                Label label = new Label();
                label.setLabelCname(labels[i]);
                List<Label> entities = labelService.findLabelByLabelName(label);
                if (entities.size() > 0)
                {
                    labelIds[i] = (entities.get(0).getLabelId().toString());
                } else {
                    label.setLabelEname("");
                    label.setLabelDesc("添加收入账单创建");
                    label.setLabelType("income");
                    label.setLabelScope("income,loan-in,reimbursement-in,borrow,return-in,pay-in,debt-in");
                    label.setWeight(1L);
                    label.setOrderSort(1L);
                    label.setIcon("component");
                    label.setEnableStatus(1L);
                    label.setIsDeleted(0);
                    label.setRemark("添加收入账单创建");
                    labelService.insertLabel(label);
                    labelIds[i] = (label.getLabelId().toString());
                }
            }
            bill.setIncomeLabelId(String.join(",", labelIds));
        }

        // 商家，实体，个人，组织，公司等
        if (null != bill.getIncomeEntityId()) {
            entityService.selectEntityByEntityId(bill.getIncomeEntityId());
        }

        // 描述
        String desc =
              "时间 [" + bill.getIncomeDatetime() + "]," +
              "金额 [" + bill.getIncomeAmount() + "]" +
              "币种 [" + bill.getIncomeMemberName() + "]"+
              "类型 [" + bill.getIncomeType() + "]"+
              "分类 [" + bill.getIncomeCategoryName() + "]," +
              "账户 [" + bill.getIncomeAccountName() + "]," +
              "实体 [" + bill.getIncomeEntityName() + "]," +
              "项目 [" + bill.getIncomeProjectName() + "]," +
              "标签 [" + bill.getIncomeLabelName() + "]," +
              "城市 [" + bill.getIncomeCityName() + "]" +
              "成员 [" + bill.getIncomeMemberName() + "]," +
              "心情 [" + bill.getIncomeEmotionName() + "]," +
              "成员 [" + bill.getIncomeWeatherName() + "]," +
              "备注 [" + bill.getRemark() + "]";
        bill.setIncomeDesc(desc);

        return toAjax(incomeBillService.insertIncomeBill(bill));
    }

    /**
     * 修改收入账单
     */
    @PreAuthorize("@ss.hasPermi('bill:income:edit')")
    @Log(title = "收入账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IncomeBill incomeBill)
    {
        incomeBill.setUpdateBy(getUsername());
        return toAjax(incomeBillService.updateIncomeBill(incomeBill));
    }

    /**
     * 删除收入账单
     */
    @PreAuthorize("@ss.hasPermi('bill:income:remove')")
    @Log(title = "收入账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{incomeIds}")
    public AjaxResult remove(@PathVariable Long[] incomeIds)
    {
        return toAjax(incomeBillService.deleteIncomeBillByIncomeIds(incomeIds));
    }

    /**
     * 获取收入账单下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<IncomeBill> list = incomeBillService.selectIncomeBillAll();
        return AjaxResult.success(list);
    }

}
