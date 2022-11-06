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
import com.ruoyi.bill.domain.ConsumeBill;
import com.ruoyi.bill.service.IConsumeBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 支出账单Controller
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@RestController
@RequestMapping("/bill/consume")
public class ConsumeBillController extends BaseController
{
    @Autowired
    private IConsumeBillService consumeBillService;
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
     * 分页查询支出账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConsumeBill consumeBill)
    {
        consumeBill.setConsumeUserId(getUserId());
        startPage();
        List<ConsumeBill> list = consumeBillService.selectConsumeBillList(consumeBill);
        return getDataTable(list);
    }

    /**
     * 导出支出账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:export')")
    @Log(title = "支出账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConsumeBill consumeBill)
    {
        List<ConsumeBill> list = consumeBillService.selectConsumeBillList(consumeBill);
        ExcelUtil<ConsumeBill> util = new ExcelUtil<ConsumeBill>(ConsumeBill.class);
        util.exportExcel(response, list, "支出账单数据");
    }

    /**
     * 获取支出账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:query')")
    @GetMapping(value = "/{consumeId}")
    public AjaxResult getInfo(@PathVariable("consumeId") Long consumeId)
    {
        return AjaxResult.success(consumeBillService.selectConsumeBillByConsumeId(consumeId));
    }

    /**
     * 新增支出账单
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:add')")
    @Log(title = "支出账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConsumeBill bill) throws ParseException {
        // 基本信息
        Date incomeDateTime = bill.getConsumeDatetime();
        FullDate fullDate = DateStringUtils.getFullDate(incomeDateTime);
        bill.setConsumeName(fullDate.getDatetime() +" 支出");
        bill.setConsumeImgs("");
        bill.setIcon("");
        bill.setOrderSort(0L);
        bill.setWeight(0L);
        bill.setIsDeleted(0);
        if (bill.getConsumeParentId() == null) {
            bill.setConsumeParentId(0L);
        }
        if (bill.getConsumeParentName() == null)
        {
            bill.setConsumeParentName("root");
        }

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        bill.setConsumeBookId(books.get(0).getBookId());
        bill.setConsumeBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        bill.setConsumeUserId(getUserId());
        bill.setConsumeUserName(getUsername());

        // 关于日期
        bill.setConsumeDate(fullDate.getDate());
        bill.setConsumeYear(fullDate.getYear());
        bill.setConsumeQuarter(fullDate.getQuarter());
        bill.setConsumeMonth(fullDate.getMonth());
        bill.setConsumeWeek(fullDate.getDayOfWeek());
        bill.setConsumeYearWeek(fullDate.getWeekOfYear());
        bill.setConsumeDay(fullDate.getDay());
        bill.setConsumePeriod(fullDate.getPeriod());

        // 创建者
        bill.setCreateBy(getUsername());
        bill.setUpdateBy(getUsername());

        // 关联信息
        // 天气
        if (null != bill.getConsumeWeatherId()) {
             Weather weather = weatherService.selectWeatherByWeatherId(bill.getConsumeWeatherId());
             bill.setConsumeWeatherName(weather.getWeatherCname());
        }
        // 项目
        if(null != bill.getConsumeProjectId()) {
            Project project = projectService.selectProjectByProjectId(bill.getConsumeProjectId());
            bill.setConsumeProjectName(project.getProjectName());
        }
        else if (null != bill.getConsumeProjectName() && !"".equals(bill.getConsumeProjectName()))
        {
            Project temp = new Project();
            temp.setProjectName(bill.getConsumeProjectName());
            temp.setProjectDesc("添加支出账单创建");
            temp.setProjectType("consume");
            temp.setProjectScope("consume,loan-out,reimbursement-out,lend,return-out,pay-out,debt-out");
            temp.setWeight(1L);
            temp.setOrderSort(1L);
            temp.setIcon("bug");
            temp.setEnableStatus(1L);
            temp.setIsDeleted(0);
            temp.setRemark("添加支出账单创建");
            projectService.insertProject(temp);
            bill.setConsumeProjectId(temp.getProjectId());
        }
        // 币种
        if (null != bill.getConsumeMoneyId())
        {
            Money money = moneyService.selectMoneyByMoneyId(bill.getConsumeMoneyId());
            bill.setConsumeMoneyName(money.getMoneyCname());
        }
        // 成员
        if (null != bill.getConsumeMemberId()) {
            Member member = memberService.selectMemberByMemberId(bill.getConsumeMemberId());
            bill.setConsumeMemberName(member.getMemberName());
        }
        // 标签
        if (null != bill.getConsumeLabelName() && !"".equals(bill.getConsumeLabelName())) {
            String[] labels = bill.getConsumeLabelName().split(" ");
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
                    label.setLabelDesc("添加支出账单创建");
                    label.setLabelType("income");
                    label.setLabelScope("consume,loan-out,reimbursement-out,lend,return-out,pay-out,debt-out");
                    label.setWeight(1L);
                    label.setOrderSort(1L);
                    label.setIcon("component");
                    label.setEnableStatus(1L);
                    label.setIsDeleted(0);
                    label.setRemark("添加支出账单创建");
                    labelService.insertLabel(label);
                    labelIds[i] = (label.getLabelId().toString());
                }
            }
            bill.setConsumeLabelId(String.join(",", labelIds));
        }

        // 商家，实体，个人，组织，公司等
        if (null != bill.getConsumeEntityId()) {
            Entity entity = entityService.selectEntityByEntityId(bill.getConsumeEntityId());
            bill.setConsumeEntityName(entity.getEntityName());
            String[] address = entity.getEntityAddress().split(" ");
            // 地址
            bill.setConsumeAddress(entity.getEntityAddress());
            if(address.length == 1) {
                bill.setConsumeCountry(address[0]);
            }else if(address.length == 2) {
                bill.setConsumeCountry(address[0]);
                bill.setConsumeProvince(address[1]);
            }else if(address.length == 3) {
                bill.setConsumeCountry(address[0]);
                bill.setConsumeProvince(address[1]);
                bill.setConsumeCity(address[2]);
            }else if(address.length == 4) {
                bill.setConsumeCountry(address[0]);
                bill.setConsumeProvince(address[1]);
                bill.setConsumeCity(address[2]);
                bill.setConsumeCounty(address[3]);
            }else if(address.length == 5) {
                bill.setConsumeCountry(address[0]);
                bill.setConsumeProvince(address[1]);
                bill.setConsumeCity(address[2]);
                bill.setConsumeCounty(address[3]);
                bill.setConsumeStreet(address[4]);
            }
        }
        // 表情
        if (null != bill.getConsumeEmotionId()) {
            Emotion emotion = emotionService.selectEmotionByEmotionId(bill.getConsumeEmotionId());
            bill.setConsumeEmotionName(emotion.getEmotionCname());
        }

        // 城市
        if (null != bill.getConsumeCityId()) {
            City city = cityService.selectCityByCityId(bill.getConsumeCityId());
            bill.setConsumeCityName(city.getCityCname());
        }
        // 分类
        if (null != bill.getConsumeCategoryId()) {
            String[] ids = bill.getConsumeCategoryId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Category category = categoryService.selectCategoryByCategoryId(Long.parseLong(ids[i]));
                names[i] = category.getCategoryName();
            }
            bill.setConsumeCategoryName(String.join(" > ", names));
        }

        // 账户
        if (null != bill.getConsumeAccountId()) {
            String[] ids = bill.getConsumeAccountId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Account account = accountService.selectAccountByAccountId(Long.parseLong(ids[i]));
                names[i] = account.getAccountName();
            }
            bill.setConsumeAccountName(String.join(" > ", names));
        }

        // 描述
        String desc =
              "时间[" + fullDate.getDatetime() + "], " +
              "金额[" + bill.getConsumeAmount() + "], " +
              "单位[" + bill.getConsumeMoneyName() + "], "+
              "类型[" + bill.getConsumeType() + "], "+
              "分类[" + bill.getConsumeCategoryName() + "], " +
              "账户[" + bill.getConsumeAccountName() + "], " +
              "实体[" + bill.getConsumeEntityName() + "], " +
              "项目[" + bill.getConsumeProjectName() + "], " +
              "标签[" + bill.getConsumeLabelName() + "], " +
              "城市[" + bill.getConsumeCityName() + "], " +
              "成员[" + bill.getConsumeMemberName() + "], " +
              "心情[" + bill.getConsumeEmotionName() + "], " +
              "天气[" + bill.getConsumeWeatherName() + "], " +
              "备注[" + bill.getRemark() + "] ";
        bill.setConsumeDesc(desc);
        bill.setCreateTime(DateUtils.getNowDate());
        bill.setUpdateTime(DateUtils.getNowDate());

        consumeBillService.insertConsumeBill(bill);
        FlowBill flowBill = convertConsumeBillToFlowBill(bill);

        // 保存到流水中
        flowBillService.deleteFlowBillByFlowBillIdAndFlowCategory(flowBill);
        return toAjax(flowBillService.insertFlowBill(flowBill));
    }

    /**
     * 支出账单转流水账单
     * @param bill
     * @return
     */
    public FlowBill convertConsumeBillToFlowBill(ConsumeBill bill)
    {
        FlowBill flowBill = new FlowBill();
        flowBill.setFlowName(bill.getConsumeName());
        flowBill.setFlowSource(bill.getConsumeEntityName());
        flowBill.setFlowTarget(bill.getConsumeAccountName());
        flowBill.setFlowAmount(bill.getConsumeAmount());
        flowBill.setFlowSign("-");
        flowBill.setFlowType(bill.getConsumeType());
        flowBill.setFlowCategory("consume");
        flowBill.setFlowDatetime(bill.getConsumeDatetime());
        flowBill.setFlowParentId(bill.getConsumeParentId());
        flowBill.setFlowParentName(bill.getConsumeParentName());
        flowBill.setFlowBillId(bill.getConsumeId());     //
        flowBill.setIsDeleted(bill.getIsDeleted());
        flowBill.setOrderSort(bill.getOrderSort()+10000000);
        flowBill.setFlowDesc(bill.getConsumeDesc());
        flowBill.setFlowImgs(bill.getConsumeImgs());
        flowBill.setFlowRemark(bill.getRemark());
        flowBill.setEnableStatus(bill.getEnableStatus());
        flowBill.setIcon(bill.getIcon());
        flowBill.setWeight(bill.getWeight()+10000000);
        // 关联
        flowBill.setFlowUserId(bill.getConsumeUserId());
        flowBill.setFlowUserName(bill.getConsumeUserName());

        flowBill.setFlowWeatherId(bill.getConsumeWeatherId());
        flowBill.setFlowWeatherName(bill.getConsumeWeatherName());

        flowBill.setFlowMoneyId(bill.getConsumeMoneyId());
        flowBill.setFlowMoneyName(bill.getConsumeMoneyName());

        flowBill.setFlowMemberId(bill.getConsumeMemberId());
        flowBill.setFlowMemberName(bill.getConsumeMemberName());

        flowBill.setFlowLabelId(bill.getConsumeLabelId());
        flowBill.setFlowLabelName(bill.getConsumeLabelName());

        flowBill.setFlowProjectId(bill.getConsumeProjectId());
        flowBill.setFlowProjectName(bill.getConsumeProjectName());

        flowBill.setFlowEmotionId(bill.getConsumeEmotionId());
        flowBill.setFlowEmotionName(bill.getConsumeEmotionName());

        flowBill.setFlowCategoryId(bill.getConsumeCategoryId());
        flowBill.setFlowCategoryName(bill.getConsumeCategoryName());

        flowBill.setFlowAccountId(bill.getConsumeAccountId());
        flowBill.setFlowAccountName(bill.getConsumeAccountName());

        flowBill.setFlowEntityId(bill.getConsumeEntityId().toString());
        flowBill.setFlowEntityName(bill.getConsumeEntityName());

        flowBill.setFlowCityId(bill.getConsumeCityId());
        flowBill.setFlowCityName(bill.getConsumeCityName());

        flowBill.setFlowBookId(bill.getConsumeBookId());
        flowBill.setFlowBookName(bill.getConsumeBookName());

        // 其他信息
        flowBill.setFlowYear(bill.getConsumeYear());
        flowBill.setFlowMonth(bill.getConsumeMonth());
        flowBill.setFlowDate(bill.getConsumeDate());
        flowBill.setFlowDay(bill.getConsumeDay());
        flowBill.setFlowQuarter(bill.getConsumeQuarter());
        flowBill.setFlowWeek(bill.getConsumeWeek());
        flowBill.setFlowYearWeek(bill.getConsumeYearWeek());
        flowBill.setFlowPeriod(bill.getConsumePeriod());

        flowBill.setFlowCounty(bill.getConsumeCounty());
        flowBill.setFlowProvince(bill.getConsumeProvince());
        flowBill.setFlowStreet(bill.getConsumeStreet());
        flowBill.setFlowCountry(bill.getConsumeCountry());
        flowBill.setFlowCity(bill.getConsumeCity());
        flowBill.setFlowAddress(bill.getConsumeAddress());

        //
        flowBill.setCreateBy(bill.getCreateBy());
        flowBill.setUpdateBy(bill.getUpdateBy());
        flowBill.setCreateTime(bill.getCreateTime());
        flowBill.setUpdateTime(bill.getUpdateTime());
        flowBill.setRemark(bill.getRemark());

        return flowBill;
    }

    /**
     * 修改支出账单
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:edit')")
    @Log(title = "支出账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConsumeBill bill) throws ParseException {
        // 基本信息
        Date incomeDateTime = bill.getConsumeDatetime();
        FullDate fullDate = DateStringUtils.getFullDate(incomeDateTime);
        bill.setConsumeName(fullDate.getDatetime() +" 支出");
        bill.setConsumeImgs("");
        bill.setIcon("");
        bill.setOrderSort(0L);
        bill.setWeight(0L);
        bill.setIsDeleted(0);
        if (bill.getConsumeParentId() == null) {
            bill.setConsumeParentId(0L);
        }
        if (bill.getConsumeParentName() == null)
        {
            bill.setConsumeParentName("root");
        }

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        bill.setConsumeBookId(books.get(0).getBookId());
        bill.setConsumeBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        bill.setConsumeUserId(getUserId());
        bill.setConsumeUserName(getUsername());

        // 关于日期
        bill.setConsumeDate(fullDate.getDate());
        bill.setConsumeYear(fullDate.getYear());
        bill.setConsumeQuarter(fullDate.getQuarter());
        bill.setConsumeMonth(fullDate.getMonth());
        bill.setConsumeWeek(fullDate.getDayOfWeek());
        bill.setConsumeYearWeek(fullDate.getWeekOfYear());
        bill.setConsumeDay(fullDate.getDay());
        bill.setConsumePeriod(fullDate.getPeriod());

        // 创建者
        bill.setCreateBy(getUsername());
        bill.setUpdateBy(getUsername());

        // 关联信息
        // 天气
        if (null != bill.getConsumeWeatherId()) {
             Weather weather = weatherService.selectWeatherByWeatherId(bill.getConsumeWeatherId());
             bill.setConsumeWeatherName(weather.getWeatherCname());
        }
        // 项目
        if(null != bill.getConsumeProjectId()) {
            Project project = projectService.selectProjectByProjectId(bill.getConsumeProjectId());
            bill.setConsumeProjectName(project.getProjectName());
        }
        else if (null != bill.getConsumeProjectName() && !"".equals(bill.getConsumeProjectName()))
        {
            Project temp = new Project();
            temp.setProjectName(bill.getConsumeProjectName());
            temp.setProjectDesc("添加支出账单创建");
            temp.setProjectType("consume");
            temp.setProjectScope("consume,loan-out,reimbursement-out,lend,return-out,pay-out,debt-out");
            temp.setWeight(1L);
            temp.setOrderSort(1L);
            temp.setIcon("bug");
            temp.setEnableStatus(1L);
            temp.setIsDeleted(0);
            temp.setRemark("添加支出账单创建");
            projectService.insertProject(temp);
            bill.setConsumeProjectId(temp.getProjectId());
        }
        // 币种
        if (null != bill.getConsumeMoneyId())
        {
            Money money = moneyService.selectMoneyByMoneyId(bill.getConsumeMoneyId());
            bill.setConsumeMoneyName(money.getMoneyCname());
        }
        // 成员
        if (null != bill.getConsumeMemberId()) {
            Member member = memberService.selectMemberByMemberId(bill.getConsumeMemberId());
            bill.setConsumeMemberName(member.getMemberName());
        }
        // 标签
        if (null != bill.getConsumeLabelName() && !"".equals(bill.getConsumeLabelName())) {
            String[] labels = bill.getConsumeLabelName().split(" ");
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
                    label.setLabelDesc("添加支出账单创建");
                    label.setLabelType("income");
                    label.setLabelScope("consume,loan-out,reimbursement-out,lend,return-out,pay-out,debt-out");
                    label.setWeight(1L);
                    label.setOrderSort(1L);
                    label.setIcon("component");
                    label.setEnableStatus(1L);
                    label.setIsDeleted(0);
                    label.setRemark("添加支出账单创建");
                    labelService.insertLabel(label);
                    labelIds[i] = (label.getLabelId().toString());
                }
            }
            bill.setConsumeLabelId(String.join(",", labelIds));
        }

        // 商家，实体，个人，组织，公司等
        if (null != bill.getConsumeEntityId()) {
            Entity entity = entityService.selectEntityByEntityId(bill.getConsumeEntityId());
            bill.setConsumeEntityName(entity.getEntityName());
            String[] address = entity.getEntityAddress().split(" ");
            // 地址
            bill.setConsumeAddress(entity.getEntityAddress());
            if(address.length == 1) {
                bill.setConsumeCountry(address[0]);
            }else if(address.length == 2) {
                bill.setConsumeCountry(address[0]);
                bill.setConsumeProvince(address[1]);
            }else if(address.length == 3) {
                bill.setConsumeCountry(address[0]);
                bill.setConsumeProvince(address[1]);
                bill.setConsumeCity(address[2]);
            }else if(address.length == 4) {
                bill.setConsumeCountry(address[0]);
                bill.setConsumeProvince(address[1]);
                bill.setConsumeCity(address[2]);
                bill.setConsumeCounty(address[3]);
            }else if(address.length == 5) {
                bill.setConsumeCountry(address[0]);
                bill.setConsumeProvince(address[1]);
                bill.setConsumeCity(address[2]);
                bill.setConsumeCounty(address[3]);
                bill.setConsumeStreet(address[4]);
            }
        }
        // 表情
        if (null != bill.getConsumeEmotionId()) {
            Emotion emotion = emotionService.selectEmotionByEmotionId(bill.getConsumeEmotionId());
            bill.setConsumeEmotionName(emotion.getEmotionCname());
        }

        // 城市
        if (null != bill.getConsumeCityId()) {
            City city = cityService.selectCityByCityId(bill.getConsumeCityId());
            bill.setConsumeCityName(city.getCityCname());
        }
        // 分类
        if (null != bill.getConsumeCategoryId()) {
            String[] ids = bill.getConsumeCategoryId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Category category = categoryService.selectCategoryByCategoryId(Long.parseLong(ids[i]));
                names[i] = category.getCategoryName();
            }
            bill.setConsumeCategoryName(String.join(" > ", names));
        }

        // 账户
        if (null != bill.getConsumeAccountId()) {
            String[] ids = bill.getConsumeAccountId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Account account = accountService.selectAccountByAccountId(Long.parseLong(ids[i]));
                names[i] = account.getAccountName();
            }
            bill.setConsumeAccountName(String.join(" > ", names));
        }

        // 描述
        String desc =
              "时间[" + fullDate.getDatetime() + "], " +
              "金额[" + bill.getConsumeAmount() + "], " +
              "单位[" + bill.getConsumeMoneyName() + "], "+
              "类型[" + bill.getConsumeType() + "], "+
              "分类[" + bill.getConsumeCategoryName() + "], " +
              "账户[" + bill.getConsumeAccountName() + "], " +
              "实体[" + bill.getConsumeEntityName() + "], " +
              "项目[" + bill.getConsumeProjectName() + "], " +
              "标签[" + bill.getConsumeLabelName() + "], " +
              "城市[" + bill.getConsumeCityName() + "], " +
              "成员[" + bill.getConsumeMemberName() + "], " +
              "心情[" + bill.getConsumeEmotionName() + "], " +
              "天气[" + bill.getConsumeWeatherName() + "], " +
              "备注[" + bill.getRemark() + "] ";
        bill.setConsumeDesc(desc);
        bill.setUpdateTime(DateUtils.getNowDate());

        consumeBillService.updateConsumeBill(bill);
        FlowBill flowBill = convertConsumeBillToFlowBill(bill);

        // 保存到流水中
        flowBillService.deleteFlowBillByFlowBillIdAndFlowCategory(flowBill);
        return toAjax(flowBillService.insertFlowBill(flowBill));
    }



    /**
     * 删除支出账单
     */
    @PreAuthorize("@ss.hasPermi('bill:consume:remove')")
    @Log(title = "支出账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consumeIds}")
    public AjaxResult remove(@PathVariable Long[] consumeIds)
    {
        return toAjax(consumeBillService.deleteConsumeBillByConsumeIds(consumeIds));
    }


    /**
     * 获取支出账单下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<ConsumeBill> list = consumeBillService.selectConsumeBillAll();
        return AjaxResult.success(list);
    }

}
