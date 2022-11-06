package com.ruoyi.web.controller.bill;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bill.domain.FlowBill;
import com.ruoyi.bill.service.IFlowBillService;
import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.FullDate;
import com.ruoyi.config.domain.*;
import com.ruoyi.config.service.*;
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
import com.ruoyi.bill.domain.DebtBill;
import com.ruoyi.bill.service.IDebtBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 借贷账单Controller
 * 
 * @author metacoin
 * @date 2022-10-29
 */
@RestController
@RequestMapping("/bill/debt")
public class DebtBillController extends BaseController
{
    @Autowired
    private IDebtBillService debtBillService;

    @Resource
    private ISysUserService userService;

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

    @Autowired
    private IFlowBillService flowBillService;

    /**
     * 分页查询借贷账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:list')")
    @GetMapping("/list")
    public AjaxResult list(DebtBill debtBill)
    {
//        startPage();
        List<DebtBill> list = debtBillService.selectDebtBillList(debtBill);
        return AjaxResult.success(list);
    }

    /**
     * 导出借贷账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:export')")
    @Log(title = "借贷账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DebtBill debtBill)
    {
        List<DebtBill> list = debtBillService.selectDebtBillList(debtBill);
        ExcelUtil<DebtBill> util = new ExcelUtil<DebtBill>(DebtBill.class);
        util.exportExcel(response, list, "借贷账单数据");
    }

    /**
     * 获取借贷账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:query')")
    @GetMapping(value = "/{debtId}")
    public AjaxResult getInfo(@PathVariable("debtId") Long debtId)
    {
        return AjaxResult.success(debtBillService.selectDebtBillByDebtId(debtId));
    }

    /**
     * 新增借贷账单
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:add')")
    @Log(title = "借贷账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DebtBill bill) throws ParseException {
        // 基本信息
        Date debtDateTime = bill.getDebtDatetime();
        FullDate fullDate = DateStringUtils.getFullDate(debtDateTime);
        bill.setDebtName(fullDate.getDatetime() +" 借贷");
        bill.setDebtImgs("");
        bill.setIcon("");
        bill.setOrderSort(0L);
        bill.setWeight(0L);
        bill.setIsDeleted(0);
        if (bill.getDebtParentId() == null) {
            bill.setDebtParentId(0L);
        }else {
            DebtBill parent = debtBillService.selectDebtBillByDebtId(bill.getDebtParentId());
            if(parent != null){
                bill.setDebtParentName(parent.getDebtName());
            }else{
                bill.setDebtParentName("root");
            }
        }
        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        bill.setDebtBookId(books.get(0).getBookId());
        bill.setDebtBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        bill.setDebtUserId(getUserId());
        bill.setDebtUserName(getUsername());
        bill.setCreateTime(DateUtils.getNowDate());
        bill.setUpdateTime(DateUtils.getNowDate());
        // 创建者
        bill.setCreateBy(getUsername());
        bill.setUpdateBy(getUsername());

        // 关于日期
        bill.setDebtDate(fullDate.getDate());
        bill.setDebtYear(fullDate.getYear());
        bill.setDebtQuarter(fullDate.getQuarter());
        bill.setDebtMonth(fullDate.getMonth());
        bill.setDebtWeek(fullDate.getDayOfWeek());
        bill.setDebtYearWeek(fullDate.getWeekOfYear());
        bill.setDebtDay(fullDate.getDay());
        bill.setDebtPeriod(fullDate.getPeriod());



        // 关联信息
        // 天气
        if (null != bill.getDebtWeatherId()) {
             Weather weather = weatherService.selectWeatherByWeatherId(bill.getDebtWeatherId());
             bill.setDebtWeatherName(weather.getWeatherCname());
        }
        // 项目
        if(null != bill.getDebtProjectId()) {
            Project project = projectService.selectProjectByProjectId(bill.getDebtProjectId());
            bill.setDebtProjectName(project.getProjectName());
        }
        else if (null != bill.getDebtProjectName() && !"".equals(bill.getDebtProjectName()))
        {
            Project temp = new Project();
            temp.setProjectName(bill.getDebtProjectName());
            temp.setProjectDesc(bill.getDebtProjectName());
            temp.setProjectType("debt");
            temp.setProjectScope(bill.getDebtType());
            temp.setWeight(1L);
            temp.setOrderSort(1L);
            temp.setIcon("bug");
            temp.setEnableStatus(1L);
            temp.setIsDeleted(0);
            temp.setRemark("添加借贷账单创建");
            temp.setUserId(bill.getDebtUserId());
            temp.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
            temp.setBookId(bill.getDebtBookId());
            temp.setBookName(bill.getDebtBookName());
            temp.setCreateBy(getUsername());
            temp.setUpdateBy(getUsername());
            projectService.insertProject(temp);
            bill.setDebtProjectId(temp.getProjectId());
        }
        // 币种
        if (null != bill.getDebtMoneyId())
        {
            Money money = moneyService.selectMoneyByMoneyId(bill.getDebtMoneyId());
            bill.setDebtMoneyName(money.getMoneyCname());
        }
        // 成员
        if (null != bill.getDebtMemberId()) {
            Member member = memberService.selectMemberByMemberId(bill.getDebtMemberId());
            bill.setDebtMemberName(member.getMemberName());
        }
        // 标签
        if (null != bill.getDebtLabelName() && !"".equals(bill.getDebtLabelName())) {
            String[] labels = bill.getDebtLabelName().split(" ");
            String[] labelIds = new String[labels.length];
            for(int i=0; i<labelIds.length; i++) {
                Label label = new Label();
                label.setLabelCname(labels[i]);
                List<Label> entities = labelService.findLabelByLabelName(label);
                if (entities.size() > 0)
                {
                    labelIds[i] = (entities.get(0).getLabelId().toString());
                } else {
                    label.setLabelEname(label.getLabelCname());
                    label.setLabelDesc(label.getLabelCname());
                    label.setLabelType("debt");
                    label.setLabelScope(bill.getDebtType());
                    label.setWeight(1L);
                    label.setOrderSort(1L);
                    label.setIcon("component");
                    label.setEnableStatus(1L);
                    label.setIsDeleted(0);
                    label.setRemark("添加借贷账单创建");
                    label.setUserId(bill.getDebtUserId());
                    label.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
                    label.setBookId(bill.getDebtBookId());
                    label.setBookName(bill.getDebtBookName());
                    label.setCreateBy(getUsername());
                    label.setUpdateBy(getUsername());
                    labelService.insertLabel(label);
                    labelIds[i] = (label.getLabelId().toString());
                }
            }
            bill.setDebtLabelId(String.join(",", labelIds));
        }

        // 商家，实体，个人，组织，公司等
        if (null != bill.getDebtEntityId()) {
            Entity entity = entityService.selectEntityByEntityId(bill.getDebtEntityId());
            bill.setDebtEntityName(entity.getEntityName());
            String[] address = entity.getEntityAddress().split(" ");
            // 地址
            bill.setDebtAddress(entity.getEntityAddress());
            if(address.length == 1) {
                bill.setDebtCountry(address[0]);
            }else if(address.length == 2) {
                bill.setDebtCountry(address[0]);
                bill.setDebtProvince(address[1]);
            }else if(address.length == 3) {
                bill.setDebtCountry(address[0]);
                bill.setDebtProvince(address[1]);
                bill.setDebtCity(address[2]);
            }else if(address.length == 4) {
                bill.setDebtCountry(address[0]);
                bill.setDebtProvince(address[1]);
                bill.setDebtCity(address[2]);
                bill.setDebtCounty(address[3]);
            }else if(address.length == 5) {
                bill.setDebtCountry(address[0]);
                bill.setDebtProvince(address[1]);
                bill.setDebtCity(address[2]);
                bill.setDebtCounty(address[3]);
                bill.setDebtStreet(address[4]);
            }
        }
        // 表情
        if (null != bill.getDebtEmotionId()) {
            Emotion emotion = emotionService.selectEmotionByEmotionId(bill.getDebtEmotionId());
            bill.setDebtEmotionName(emotion.getEmotionCname());
        }

        // 城市
        if (null != bill.getDebtCityId()) {
            City city = cityService.selectCityByCityId(bill.getDebtCityId());
            bill.setDebtCityName(city.getCityCname());
        }
        // 分类
        if (null != bill.getDebtCategoryId()) {
            String[] ids = bill.getDebtCategoryId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Category category = categoryService.selectCategoryByCategoryId(Long.parseLong(ids[i]));
                names[i] = category.getCategoryName();
            }
            bill.setDebtCategoryName(String.join(" > ", names));
        }

        // 账户
        if (null != bill.getDebtAccountId()) {
            String[] ids = bill.getDebtAccountId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Account account = accountService.selectAccountByAccountId(Long.parseLong(ids[i]));
                names[i] = account.getAccountName();
            }
            bill.setDebtAccountName(String.join(" > ", names));
        }

        String debtDesc = "";
        if(bill.getDebtNumber() != null)
        {
            debtDesc += " 分期数："+ bill.getDebtNumber();
        }
        if(bill.getDebtRate() != null)
        {
            debtDesc += " 利率："+ bill.getDebtRate();
        }

        if(bill.getDebtInterest() != null)
        {
            debtDesc += " 利息："+ bill.getDebtInterest();
        }

        if(bill.getDebtPay() != null)
        {
            debtDesc += " 手续费："+ bill.getDebtPay();
        }

        if(bill.getDebtBadPay() != null)
        {
            debtDesc += " 违约金："+ bill.getDebtBadPay();
        }
        // 描述
        String desc =
              "时间[" + fullDate.getDatetime() + "], " +
              "金额[" + bill.getDebtAmount() + "], " +
              "单位[" + bill.getDebtMoneyName() + "], "+
              "借贷[明细" + debtDesc  + "], " +
              "类型[" + bill.getDebtType() + "], "+
              "分类[" + bill.getDebtCategoryName() + "], " +
              "账户[" + bill.getDebtAccountName() + "], " +
              "实体[" + bill.getDebtEntityName() + "], " +
              "项目[" + bill.getDebtProjectName() + "], " +
              "标签[" + bill.getDebtLabelName() + "], " +
              "城市[" + bill.getDebtCityName() + "], " +
              "成员[" + bill.getDebtMemberName() + "], " +
              "心情[" + bill.getDebtEmotionName() + "], " +
              "天气[" + bill.getDebtWeatherName() + "], " +
              "备注[" + bill.getRemark() + "] ";
        bill.setDebtDesc(desc);


        debtBillService.insertDebtBill(bill);
        FlowBill flowBill = convertDebtBillToFlowBill(bill);
        // 保存到流水中
        flowBillService.deleteFlowBillByFlowBillIdAndFlowCategory(flowBill);
        return toAjax(flowBillService.insertFlowBill(flowBill));
    }

    /**
     * 借贷账单转流水账单
     * @param bill
     * @return
     */
    public FlowBill convertDebtBillToFlowBill(DebtBill bill) {
        FlowBill flowBill = new FlowBill();
        flowBill.setFlowName(bill.getDebtName());
        flowBill.setFlowSource(bill.getDebtEntityName());
        flowBill.setFlowTarget(bill.getDebtAccountName());
        flowBill.setFlowAmount(bill.getDebtAmount());
        flowBill.setFlowSign("+");
        flowBill.setFlowType(bill.getDebtType());
        flowBill.setFlowCategory("debt");
        flowBill.setFlowDatetime(bill.getDebtDatetime());
        flowBill.setFlowParentId(bill.getDebtParentId());
        flowBill.setFlowParentName(bill.getDebtParentName());
        flowBill.setFlowBillId(bill.getDebtId());     //
        flowBill.setIsDeleted(bill.getIsDeleted());
        flowBill.setOrderSort(bill.getOrderSort()+10000000);
        flowBill.setFlowDesc(bill.getDebtDesc());
        flowBill.setFlowImgs(bill.getDebtImgs());
        flowBill.setFlowRemark(bill.getRemark());
        flowBill.setEnableStatus(bill.getEnableStatus());
        flowBill.setIcon(bill.getIcon());
        flowBill.setWeight(bill.getWeight()+10000000);
        // 关联
        flowBill.setFlowUserId(bill.getDebtUserId());
        flowBill.setFlowUserName(bill.getDebtUserName());

        flowBill.setFlowWeatherId(bill.getDebtWeatherId());
        flowBill.setFlowWeatherName(bill.getDebtWeatherName());

        flowBill.setFlowMoneyId(bill.getDebtMoneyId());
        flowBill.setFlowMoneyName(bill.getDebtMoneyName());

        flowBill.setFlowMemberId(bill.getDebtMemberId());
        flowBill.setFlowMemberName(bill.getDebtMemberName());

        flowBill.setFlowLabelId(bill.getDebtLabelId());
        flowBill.setFlowLabelName(bill.getDebtLabelName());

        flowBill.setFlowProjectId(bill.getDebtProjectId());
        flowBill.setFlowProjectName(bill.getDebtProjectName());

        flowBill.setFlowEmotionId(bill.getDebtEmotionId());
        flowBill.setFlowEmotionName(bill.getDebtEmotionName());

        flowBill.setFlowCategoryId(bill.getDebtCategoryId());
        flowBill.setFlowCategoryName(bill.getDebtCategoryName());

        flowBill.setFlowAccountId(bill.getDebtAccountId());
        flowBill.setFlowAccountName(bill.getDebtAccountName());

        flowBill.setFlowEntityId(bill.getDebtEntityId().toString());
        flowBill.setFlowEntityName(bill.getDebtEntityName());

        flowBill.setFlowCityId(bill.getDebtCityId());
        flowBill.setFlowCityName(bill.getDebtCityName());

        flowBill.setFlowBookId(bill.getDebtBookId());
        flowBill.setFlowBookName(bill.getDebtBookName());

        // 其他信息
        flowBill.setFlowYear(bill.getDebtYear());
        flowBill.setFlowMonth(bill.getDebtMonth());
        flowBill.setFlowDate(bill.getDebtDate());
        flowBill.setFlowDay(bill.getDebtDay());
        flowBill.setFlowQuarter(bill.getDebtQuarter());
        flowBill.setFlowWeek(bill.getDebtWeek());
        flowBill.setFlowYearWeek(bill.getDebtYearWeek());
        flowBill.setFlowPeriod(bill.getDebtPeriod());

        flowBill.setFlowCounty(bill.getDebtCounty());
        flowBill.setFlowProvince(bill.getDebtProvince());
        flowBill.setFlowStreet(bill.getDebtStreet());
        flowBill.setFlowCountry(bill.getDebtCountry());
        flowBill.setFlowCity(bill.getDebtCity());
        flowBill.setFlowAddress(bill.getDebtAddress());

        //
        flowBill.setCreateBy(bill.getCreateBy());
        flowBill.setUpdateBy(bill.getUpdateBy());
        flowBill.setCreateTime(bill.getCreateTime());
        flowBill.setUpdateTime(bill.getUpdateTime());
        flowBill.setRemark(bill.getRemark());

        return flowBill;
    }


    /**
     * 修改借贷账单
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:edit')")
    @Log(title = "借贷账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DebtBill bill) throws ParseException {
        // 基本信息
        Date debtDateTime = bill.getDebtDatetime();
        FullDate fullDate = DateStringUtils.getFullDate(debtDateTime);
        bill.setDebtName(fullDate.getDatetime() +" 借贷");
        bill.setDebtImgs("");
        bill.setIcon("");
        bill.setOrderSort(0L);
        bill.setWeight(0L);
        bill.setIsDeleted(0);
        if (bill.getDebtParentId() == null) {
            bill.setDebtParentId(0L);
        }else {
            DebtBill parent = debtBillService.selectDebtBillByDebtId(bill.getDebtParentId());
            if(parent != null){
                bill.setDebtParentName(parent.getDebtName());
            }else{
                bill.setDebtParentName("root");
            }
        }
        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        bill.setDebtBookId(books.get(0).getBookId());
        bill.setDebtBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        bill.setDebtUserId(getUserId());
        bill.setDebtUserName(getUsername());

        // 关于日期
        bill.setDebtDate(fullDate.getDate());
        bill.setDebtYear(fullDate.getYear());
        bill.setDebtQuarter(fullDate.getQuarter());
        bill.setDebtMonth(fullDate.getMonth());
        bill.setDebtWeek(fullDate.getDayOfWeek());
        bill.setDebtYearWeek(fullDate.getWeekOfYear());
        bill.setDebtDay(fullDate.getDay());
        bill.setDebtPeriod(fullDate.getPeriod());

        // 创建者
        bill.setUpdateBy(getUsername());

        // 关联信息
        // 天气
        if (null != bill.getDebtWeatherId()) {
             Weather weather = weatherService.selectWeatherByWeatherId(bill.getDebtWeatherId());
             bill.setDebtWeatherName(weather.getWeatherCname());
        }
        // 项目
        if(null != bill.getDebtProjectId()) {
            Project project = projectService.selectProjectByProjectId(bill.getDebtProjectId());
            bill.setDebtProjectName(project.getProjectName());
        }
        else if (null != bill.getDebtProjectName() && !"".equals(bill.getDebtProjectName()))
        {
            Project temp = new Project();
            temp.setProjectName(bill.getDebtProjectName());
            temp.setProjectDesc(bill.getDebtProjectName());
            temp.setProjectType("debt");
            temp.setProjectScope(bill.getDebtType());
            temp.setWeight(1L);
            temp.setOrderSort(1L);
            temp.setIcon("bug");
            temp.setEnableStatus(1L);
            temp.setIsDeleted(0);
            temp.setRemark("添加借贷账单创建");
            temp.setUserId(bill.getDebtUserId());
            temp.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
            temp.setBookId(bill.getDebtBookId());
            temp.setBookName(bill.getDebtBookName());
            temp.setCreateBy(getUsername());
            temp.setUpdateBy(getUsername());
            projectService.insertProject(temp);
            bill.setDebtProjectId(temp.getProjectId());
        }
        // 币种
        if (null != bill.getDebtMoneyId())
        {
            Money money = moneyService.selectMoneyByMoneyId(bill.getDebtMoneyId());
            bill.setDebtMoneyName(money.getMoneyCname());
        }
        // 成员
        if (null != bill.getDebtMemberId()) {
            Member member = memberService.selectMemberByMemberId(bill.getDebtMemberId());
            bill.setDebtMemberName(member.getMemberName());
        }
        // 标签
        if (null != bill.getDebtLabelName() && !"".equals(bill.getDebtLabelName())) {
            String[] labels = bill.getDebtLabelName().split(" ");
            String[] labelIds = new String[labels.length];
            for(int i=0; i<labelIds.length; i++) {
                Label label = new Label();
                label.setLabelCname(labels[i]);
                List<Label> entities = labelService.findLabelByLabelName(label);
                if (entities.size() > 0)
                {
                    labelIds[i] = (entities.get(0).getLabelId().toString());
                } else {
                    label.setLabelEname(label.getLabelCname());
                    label.setLabelDesc(label.getLabelCname());
                    label.setLabelType("debt");
                    label.setLabelScope(bill.getDebtType());
                    label.setWeight(1L);
                    label.setOrderSort(1L);
                    label.setIcon("component");
                    label.setEnableStatus(1L);
                    label.setIsDeleted(0);
                    label.setRemark("添加借贷账单创建");
                    label.setUserId(bill.getDebtUserId());
                    label.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
                    label.setBookId(bill.getDebtBookId());
                    label.setBookName(bill.getDebtBookName());
                    label.setCreateBy(getUsername());
                    label.setUpdateBy(getUsername());
                    labelService.insertLabel(label);
                    labelIds[i] = (label.getLabelId().toString());
                }
            }
            bill.setDebtLabelId(String.join(",", labelIds));
        }

        // 商家，实体，个人，组织，公司等
        if (null != bill.getDebtEntityId()) {
            Entity entity = entityService.selectEntityByEntityId(bill.getDebtEntityId());
            bill.setDebtEntityName(entity.getEntityName());
            String[] address = entity.getEntityAddress().split(" ");
            // 地址
            bill.setDebtAddress(entity.getEntityAddress());
            if(address.length == 1) {
                bill.setDebtCountry(address[0]);
            }else if(address.length == 2) {
                bill.setDebtCountry(address[0]);
                bill.setDebtProvince(address[1]);
            }else if(address.length == 3) {
                bill.setDebtCountry(address[0]);
                bill.setDebtProvince(address[1]);
                bill.setDebtCity(address[2]);
            }else if(address.length == 4) {
                bill.setDebtCountry(address[0]);
                bill.setDebtProvince(address[1]);
                bill.setDebtCity(address[2]);
                bill.setDebtCounty(address[3]);
            }else if(address.length == 5) {
                bill.setDebtCountry(address[0]);
                bill.setDebtProvince(address[1]);
                bill.setDebtCity(address[2]);
                bill.setDebtCounty(address[3]);
                bill.setDebtStreet(address[4]);
            }
        }
        // 表情
        if (null != bill.getDebtEmotionId()) {
            Emotion emotion = emotionService.selectEmotionByEmotionId(bill.getDebtEmotionId());
            bill.setDebtEmotionName(emotion.getEmotionCname());
        }

        // 城市
        if (null != bill.getDebtCityId()) {
            City city = cityService.selectCityByCityId(bill.getDebtCityId());
            bill.setDebtCityName(city.getCityCname());
        }
        // 分类
        if (null != bill.getDebtCategoryId()) {
            String[] ids = bill.getDebtCategoryId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Category category = categoryService.selectCategoryByCategoryId(Long.parseLong(ids[i]));
                names[i] = category.getCategoryName();
            }
            bill.setDebtCategoryName(String.join(" > ", names));
        }

        // 账户
        if (null != bill.getDebtAccountId()) {
            String[] ids = bill.getDebtAccountId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Account account = accountService.selectAccountByAccountId(Long.parseLong(ids[i]));
                names[i] = account.getAccountName();
            }
            bill.setDebtAccountName(String.join(" > ", names));
        }

        String debtDesc = "";
        if(bill.getDebtNumber() != null)
        {
            debtDesc += " 分期数："+ bill.getDebtNumber();
        }
        if(bill.getDebtRate() != null)
        {
            debtDesc += " 利率："+ bill.getDebtRate();
        }

        if(bill.getDebtInterest() != null)
        {
            debtDesc += " 利息："+ bill.getDebtInterest();
        }

        if(bill.getDebtPay() != null)
        {
            debtDesc += " 手续费："+ bill.getDebtPay();
        }

        if(bill.getDebtBadPay() != null)
        {
            debtDesc += " 违约金："+ bill.getDebtBadPay();
        }

        // 描述
        String desc =
              "时间[" + fullDate.getDatetime() + "], " +
              "金额[" + bill.getDebtAmount() + "], " +
              "单位[" + bill.getDebtMoneyName() + "], "+
              "借贷[明细" + debtDesc  + "], " +
              "类型[" + bill.getDebtType() + "], "+
              "分类[" + bill.getDebtCategoryName() + "], " +
              "账户[" + bill.getDebtAccountName() + "], " +
              "实体[" + bill.getDebtEntityName() + "], " +
              "项目[" + bill.getDebtProjectName() + "], " +
              "标签[" + bill.getDebtLabelName() + "], " +
              "城市[" + bill.getDebtCityName() + "], " +
              "成员[" + bill.getDebtMemberName() + "], " +
              "心情[" + bill.getDebtEmotionName() + "], " +
              "天气[" + bill.getDebtWeatherName() + "], " +
              "备注[" + bill.getRemark() + "] ";
        bill.setDebtDesc(desc);
        bill.setUpdateTime(DateUtils.getNowDate());

        debtBillService.updateDebtBill(bill);
        FlowBill flowBill = convertDebtBillToFlowBill(bill);
        // 更新到流水中
        flowBillService.deleteFlowBillByFlowBillIdAndFlowCategory(flowBill);
        return toAjax(flowBillService.insertFlowBill(flowBill));
    }

    /**
     * 删除借贷账单
     */
    @PreAuthorize("@ss.hasPermi('bill:debt:remove')")
    @Log(title = "借贷账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{debtIds}")
    public AjaxResult remove(@PathVariable Long[] debtIds)
    {
        return toAjax(debtBillService.deleteDebtBillByDebtIds(debtIds));
    }

    /**
     * 获取借贷账单下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<DebtBill> list = debtBillService.selectDebtBillAll();
        return AjaxResult.success(list);
    }

}
