package com.ruoyi.web.controller.bill;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bill.domain.FlowBill;
import com.ruoyi.bill.domain.TransferBill;
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
import com.ruoyi.bill.domain.TransferBill;
import com.ruoyi.bill.service.ITransferBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 转账账单Controller
 * 
 * @author metacoin
 * @date 2022-10-01
 */
@RestController
@RequestMapping("/bill/transfer")
public class TransferBillController extends BaseController
{
    @Autowired
    private ITransferBillService transferBillService;

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
     * 分页查询转账账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:list')")
    @GetMapping("/list")
    public TableDataInfo list(TransferBill transferBill)
    {
        startPage();
        List<TransferBill> list = transferBillService.selectTransferBillList(transferBill);
        return getDataTable(list);
    }

    /**
     * 导出转账账单列表
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:export')")
    @Log(title = "转账账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TransferBill transferBill)
    {
        List<TransferBill> list = transferBillService.selectTransferBillList(transferBill);
        ExcelUtil<TransferBill> util = new ExcelUtil<TransferBill>(TransferBill.class);
        util.exportExcel(response, list, "转账账单数据");
    }

    /**
     * 获取转账账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:query')")
    @GetMapping(value = "/{transferId}")
    public AjaxResult getInfo(@PathVariable("transferId") Long transferId)
    {
        return AjaxResult.success(transferBillService.selectTransferBillByTransferId(transferId));
    }

    /**
     * 新增转账账单
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:add')")
    @Log(title = "转账账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferBill bill) throws ParseException {
        // 基本信息
        Date transferDateTime = bill.getTransferDatetime();
        FullDate fullDate = DateStringUtils.getFullDate(transferDateTime);
        bill.setTransferName(fullDate.getDate() +" 转账");
        bill.setTransferImgs("");
        bill.setIcon("");
        bill.setOrderSort(0L);
        bill.setWeight(0L);
        bill.setIsDeleted(0);
        if (bill.getTransferParentId() == null) {
            bill.setTransferParentId(0L);
        }
        if (bill.getTransferParentName() == null)
        {
            bill.setTransferParentName("root");
        }

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        bill.setTransferBookId(books.get(0).getBookId());
        bill.setTransferBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        bill.setTransferUserId(getUserId());
        bill.setTransferUserName(getUsername());
        bill.setCreateTime(DateUtils.getNowDate());
        bill.setUpdateTime(DateUtils.getNowDate());
        // 创建者
        bill.setCreateBy(getUsername());
        bill.setUpdateBy(getUsername());

        // 关于日期
        bill.setTransferDate(fullDate.getDate());
        bill.setTransferYear(fullDate.getYear());
        bill.setTransferQuarter(fullDate.getQuarter());
        bill.setTransferMonth(fullDate.getMonth());
        bill.setTransferWeek(fullDate.getDayOfWeek());
        bill.setTransferYearWeek(fullDate.getWeekOfYear());
        bill.setTransferDay(fullDate.getDay());
        bill.setTransferPeriod(fullDate.getPeriod());

        // 关联信息
        // 天气
        if (null != bill.getTransferWeatherId()) {
             Weather weather = weatherService.selectWeatherByWeatherId(bill.getTransferWeatherId());
             bill.setTransferWeatherName(weather.getWeatherCname());
        }
        // 项目
        if(null != bill.getTransferProjectId()) {
            Project project = projectService.selectProjectByProjectId(bill.getTransferProjectId());
            bill.setTransferProjectName(project.getProjectName());
        }
        else if (null != bill.getTransferProjectName() && !"".equals(bill.getTransferProjectName()))
        {
            Project temp = new Project();
            temp.setProjectName(bill.getTransferProjectName());
            temp.setProjectDesc(bill.getTransferProjectName());
            temp.setProjectType("transfer");
            temp.setProjectScope(bill.getTransferType());
            temp.setWeight(1L);
            temp.setOrderSort(1L);
            temp.setIcon("bug");
            temp.setEnableStatus(1L);
            temp.setIsDeleted(0);
            temp.setRemark("添加转账账单创建");
            temp.setUserId(bill.getTransferUserId());
            temp.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
            temp.setBookId(bill.getTransferBookId());
            temp.setBookName(bill.getTransferBookName());
            temp.setCreateBy(getUsername());
            temp.setUpdateBy(getUsername());
            projectService.insertProject(temp);
            bill.setTransferProjectId(temp.getProjectId());
        }
        // 币种
        if (null != bill.getTransferMoneyId())
        {
            Money money = moneyService.selectMoneyByMoneyId(bill.getTransferMoneyId());
            bill.setTransferMoneyName(money.getMoneyCname());
        }
        // 成员
        if (null != bill.getTransferMemberId()) {
            Member member = memberService.selectMemberByMemberId(bill.getTransferMemberId());
            bill.setTransferMemberName(member.getMemberName());
        }
        // 标签
        if (null != bill.getTransferLabelName() && !"".equals(bill.getTransferLabelName())) {
            String[] labels = bill.getTransferLabelName().split(" ");
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
                    label.setLabelType("transfer");
                    label.setLabelScope(bill.getTransferType());
                    label.setWeight(1L);
                    label.setOrderSort(1L);
                    label.setIcon("component");
                    label.setEnableStatus(1L);
                    label.setIsDeleted(0);
                    label.setRemark("添加转账账单创建");
                    label.setUserId(bill.getTransferUserId());
                    label.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
                    label.setBookId(bill.getTransferBookId());
                    label.setBookName(bill.getTransferBookName());
                    label.setCreateBy(getUsername());
                    label.setUpdateBy(getUsername());
                    labelService.insertLabel(label);
                    labelIds[i] = (label.getLabelId().toString());
                }
            }
            bill.setTransferLabelId(String.join("," ,labelIds));
        }

        // 商家，实体，个人，组织，公司等
        bill.setTransferCountry("");
        bill.setTransferProvince("");
        bill.setTransferCity("");
        bill.setTransferCounty("");
        bill.setTransferStreet("");
        bill.setTransferAddress("");

        // 表情
        if (null != bill.getTransferEmotionId()) {
            Emotion emotion = emotionService.selectEmotionByEmotionId(bill.getTransferEmotionId());
            bill.setTransferEmotionName(emotion.getEmotionCname());
        }

        // 城市
        if (null != bill.getTransferCityId()) {
            City city = cityService.selectCityByCityId(bill.getTransferCityId());
            bill.setTransferCityName(city.getCityCname());
        }
        // 分类
        if (null != bill.getTransferCategoryId()) {
            String[] ids = bill.getTransferCategoryId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Category category = categoryService.selectCategoryByCategoryId(Long.parseLong(ids[i]));
                names[i] = category.getCategoryName();
            }
            bill.setTransferCategoryName(String.join(" > ", names));
        }

        // 转出账户
        if (null != bill.getTransferAccountId()) {
            String[] ids = bill.getTransferAccountId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Account account = accountService.selectAccountByAccountId(Long.parseLong(ids[i]));
                names[i] = account.getAccountName();
            }
            bill.setTransferAccountName(String.join(" > ", names));
        }

        // 转入账户
        if (null != bill.getTransferEntityId()) {
            String[] ids = bill.getTransferEntityId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Account account = accountService.selectAccountByAccountId(Long.parseLong(ids[i]));
                names[i] = account.getAccountName();
            }
            bill.setTransferEntityName(String.join(" > ", names));
        }

        // 描述
        String desc =
              "时间[" + fullDate.getDatetime() + "], " +
              "金额[" + bill.getTransferAmount() + "], " +
              "单位[" + bill.getTransferMoneyName() + "], "+
              "类型[" + bill.getTransferType() + "], "+
              "分类[" + bill.getTransferCategoryName() + "], " +
              "转出账户[" + bill.getTransferAccountName() + "], " +
              "转入账户[" + bill.getTransferEntityName() + "], " +
              "项目[" + bill.getTransferProjectName() + "], " +
              "标签[" + bill.getTransferLabelName() + "], " +
              "城市[" + bill.getTransferCityName() + "], " +
              "成员[" + bill.getTransferMemberName() + "], " +
              "心情[" + bill.getTransferEmotionName() + "], " +
              "天气[" + bill.getTransferWeatherName() + "], " +
              "备注[" + bill.getRemark() + "] ";

        bill.setTransferDesc(desc);


        transferBillService.insertTransferBill(bill);
        FlowBill flowBill = convertTransferBillToFlowBill(bill);
        // 保存到流水中
        flowBillService.deleteFlowBillByFlowBillIdAndFlowCategory(flowBill);
        return toAjax(flowBillService.insertFlowBill(flowBill));
    }

    /**
     * 转账账单转流水账单
     * @param bill
     * @return
     */
    public FlowBill convertTransferBillToFlowBill(TransferBill bill) {
        FlowBill flowBill = new FlowBill();
        flowBill.setFlowName(bill.getTransferName());
        flowBill.setFlowSource(bill.getTransferEntityName());
        flowBill.setFlowTarget(bill.getTransferAccountName());
        flowBill.setFlowAmount(bill.getTransferAmount());
        flowBill.setFlowSign("=");
        flowBill.setFlowType(bill.getTransferType());
        flowBill.setFlowCategory("transfer");
        flowBill.setFlowDatetime(bill.getTransferDatetime());
        flowBill.setFlowParentId(bill.getTransferParentId());
        flowBill.setFlowParentName(bill.getTransferParentName());
        flowBill.setFlowBillId(bill.getTransferId());     //
        flowBill.setIsDeleted(bill.getIsDeleted());
        flowBill.setOrderSort(bill.getOrderSort()+30000000);
        flowBill.setFlowDesc(bill.getTransferDesc());
        flowBill.setFlowImgs(bill.getTransferImgs());
        flowBill.setFlowRemark(bill.getRemark());
        flowBill.setEnableStatus(bill.getEnableStatus());
        flowBill.setIcon(bill.getIcon());
        flowBill.setWeight(bill.getWeight()+30000000);
        // 关联
        flowBill.setFlowUserId(bill.getTransferUserId());
        flowBill.setFlowUserName(bill.getTransferUserName());

        flowBill.setFlowWeatherId(bill.getTransferWeatherId());
        flowBill.setFlowWeatherName(bill.getTransferWeatherName());

        flowBill.setFlowMoneyId(bill.getTransferMoneyId());
        flowBill.setFlowMoneyName(bill.getTransferMoneyName());

        flowBill.setFlowMemberId(bill.getTransferMemberId());
        flowBill.setFlowMemberName(bill.getTransferMemberName());

        flowBill.setFlowLabelId(bill.getTransferLabelId());
        flowBill.setFlowLabelName(bill.getTransferLabelName());

        flowBill.setFlowProjectId(bill.getTransferProjectId());
        flowBill.setFlowProjectName(bill.getTransferProjectName());

        flowBill.setFlowEmotionId(bill.getTransferEmotionId());
        flowBill.setFlowEmotionName(bill.getTransferEmotionName());

        flowBill.setFlowCategoryId(bill.getTransferCategoryId());
        flowBill.setFlowCategoryName(bill.getTransferCategoryName());

        flowBill.setFlowAccountId(bill.getTransferAccountId());
        flowBill.setFlowAccountName(bill.getTransferAccountName());

        flowBill.setFlowEntityId(bill.getTransferEntityId());
        flowBill.setFlowEntityName(bill.getTransferEntityName());

        flowBill.setFlowCityId(bill.getTransferCityId());
        flowBill.setFlowCityName(bill.getTransferCityName());

        flowBill.setFlowBookId(bill.getTransferBookId());
        flowBill.setFlowBookName(bill.getTransferBookName());

        // 其他信息
        flowBill.setFlowYear(bill.getTransferYear());
        flowBill.setFlowMonth(bill.getTransferMonth());
        flowBill.setFlowDate(bill.getTransferDate());
        flowBill.setFlowDay(bill.getTransferDay());
        flowBill.setFlowQuarter(bill.getTransferQuarter());
        flowBill.setFlowWeek(bill.getTransferWeek());
        flowBill.setFlowYearWeek(bill.getTransferYearWeek());
        flowBill.setFlowPeriod(bill.getTransferPeriod());

        flowBill.setFlowCounty(bill.getTransferCounty());
        flowBill.setFlowProvince(bill.getTransferProvince());
        flowBill.setFlowStreet(bill.getTransferStreet());
        flowBill.setFlowCountry(bill.getTransferCountry());
        flowBill.setFlowCity(bill.getTransferCity());
        flowBill.setFlowAddress(bill.getTransferAddress());

        //
        flowBill.setCreateBy(bill.getCreateBy());
        flowBill.setUpdateBy(bill.getUpdateBy());
        flowBill.setCreateTime(bill.getCreateTime());
        flowBill.setUpdateTime(bill.getUpdateTime());
        flowBill.setRemark(bill.getRemark());

        return flowBill;
    }

    /**
     * 修改转账账单
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:edit')")
    @Log(title = "转账账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferBill bill) throws ParseException {
        // 基本信息
        Date transferDateTime = bill.getTransferDatetime();
        FullDate fullDate = DateStringUtils.getFullDate(transferDateTime);
        bill.setTransferName(fullDate.getDate() +" 转账");
        bill.setTransferImgs("");
        bill.setIcon("");
        bill.setOrderSort(0L);
        bill.setWeight(0L);
        bill.setIsDeleted(0);
        if (bill.getTransferParentId() == null) {
            bill.setTransferParentId(0L);
        }
        if (bill.getTransferParentName() == null)
        {
            bill.setTransferParentName("root");
        }

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        bill.setTransferBookId(books.get(0).getBookId());
        bill.setTransferBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        bill.setTransferUserId(getUserId());
        bill.setTransferUserName(getUsername());
        bill.setUpdateTime(DateUtils.getNowDate());
        // 创建者
        bill.setUpdateBy(getUsername());

        // 关于日期
        bill.setTransferDate(fullDate.getDate());
        bill.setTransferYear(fullDate.getYear());
        bill.setTransferQuarter(fullDate.getQuarter());
        bill.setTransferMonth(fullDate.getMonth());
        bill.setTransferWeek(fullDate.getDayOfWeek());
        bill.setTransferYearWeek(fullDate.getWeekOfYear());
        bill.setTransferDay(fullDate.getDay());
        bill.setTransferPeriod(fullDate.getPeriod());



        // 关联信息
        // 天气
        if (null != bill.getTransferWeatherId()) {
             Weather weather = weatherService.selectWeatherByWeatherId(bill.getTransferWeatherId());
             bill.setTransferWeatherName(weather.getWeatherCname());
        }
        // 项目
        if(null != bill.getTransferProjectId()) {
            Project project = projectService.selectProjectByProjectId(bill.getTransferProjectId());
            bill.setTransferProjectName(project.getProjectName());
        }
        else if (null != bill.getTransferProjectName() && !"".equals(bill.getTransferProjectName()))
        {
            Project temp = new Project();
            temp.setProjectName(bill.getTransferProjectName());
            temp.setProjectDesc(bill.getTransferProjectName());
            temp.setProjectType("transfer");
            temp.setProjectScope(bill.getTransferType());
            temp.setWeight(1L);
            temp.setOrderSort(1L);
            temp.setIcon("bug");
            temp.setEnableStatus(1L);
            temp.setIsDeleted(0);
            temp.setRemark("添加转账账单创建");
            temp.setUserId(bill.getTransferUserId());
            temp.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
            temp.setBookId(bill.getTransferBookId());
            temp.setBookName(bill.getTransferBookName());
            temp.setCreateBy(getUsername());
            temp.setUpdateBy(getUsername());
            projectService.insertProject(temp);
            bill.setTransferProjectId(temp.getProjectId());
        }
        // 币种
        if (null != bill.getTransferMoneyId())
        {
            Money money = moneyService.selectMoneyByMoneyId(bill.getTransferMoneyId());
            bill.setTransferMoneyName(money.getMoneyCname());
        }
        // 成员
        if (null != bill.getTransferMemberId()) {
            Member member = memberService.selectMemberByMemberId(bill.getTransferMemberId());
            bill.setTransferMemberName(member.getMemberName());
        }
        // 标签
        if (null != bill.getTransferLabelName() && !"".equals(bill.getTransferLabelName())) {
            String[] labels = bill.getTransferLabelName().split(" ");
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
                    label.setLabelType("transfer");
                    label.setLabelScope(bill.getTransferType());
                    label.setWeight(1L);
                    label.setOrderSort(1L);
                    label.setIcon("component");
                    label.setEnableStatus(1L);
                    label.setIsDeleted(0);
                    label.setRemark("添加转账账单创建");
                    label.setUserId(bill.getTransferUserId());
                    label.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
                    label.setBookId(bill.getTransferBookId());
                    label.setBookName(bill.getTransferBookName());
                    label.setCreateBy(getUsername());
                    label.setUpdateBy(getUsername());
                    labelService.insertLabel(label);
                    labelIds[i] = (label.getLabelId().toString());
                }
            }
            bill.setTransferLabelId(String.join(",", labelIds));
        }

        // 商家，实体，个人，组织，公司等
        bill.setTransferCountry("");
        bill.setTransferProvince("");
        bill.setTransferCity("");
        bill.setTransferCounty("");
        bill.setTransferStreet("");
        bill.setTransferAddress("");

        // 表情
        if (null != bill.getTransferEmotionId()) {
            Emotion emotion = emotionService.selectEmotionByEmotionId(bill.getTransferEmotionId());
            bill.setTransferEmotionName(emotion.getEmotionCname());
        }

        // 城市
        if (null != bill.getTransferCityId()) {
            City city = cityService.selectCityByCityId(bill.getTransferCityId());
            bill.setTransferCityName(city.getCityCname());
        }

        // 分类
        if (null != bill.getTransferCategoryId()) {
            String[] ids = bill.getTransferCategoryId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Category category = categoryService.selectCategoryByCategoryId(Long.parseLong(ids[i]));
                names[i] = category.getCategoryName();
            }
            bill.setTransferCategoryName(String.join(" > ", names));
        }

        // 转出账户
        if (null != bill.getTransferAccountId()) {
            String[] ids = bill.getTransferAccountId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Account account = accountService.selectAccountByAccountId(Long.parseLong(ids[i]));
                names[i] = account.getAccountName();
            }
            bill.setTransferAccountName(String.join(" > ", names));
        }

        // 转入账户
        if (null != bill.getTransferEntityId()) {
            String[] ids = bill.getTransferEntityId().split(",");
            String[] names = new String[ids.length];
            for (int i=0; i < ids.length; i++) {
                Account account = accountService.selectAccountByAccountId(Long.parseLong(ids[i]));
                names[i] = account.getAccountName();
            }
            bill.setTransferEntityName(String.join(" > ", names));
        }

        // 描述
        String desc =
              "时间[" + fullDate.getDatetime() + "], " +
              "金额[" + bill.getTransferAmount() + "], " +
              "单位[" + bill.getTransferMoneyName() + "], "+
              "类型[" + bill.getTransferType() + "], "+
              "分类[" + bill.getTransferCategoryName() + "], " +
              "转出账户[" + bill.getTransferAccountName() + "], " +
              "转入账户[" + bill.getTransferEntityName() + "], " +
              "项目[" + bill.getTransferProjectName() + "], " +
              "标签[" + bill.getTransferLabelName() + "], " +
              "城市[" + bill.getTransferCityName() + "], " +
              "成员[" + bill.getTransferMemberName() + "], " +
              "心情[" + bill.getTransferEmotionName() + "], " +
              "天气[" + bill.getTransferWeatherName() + "], " +
              "备注[" + bill.getRemark() + "] ";
        bill.setTransferDesc(desc);


        transferBillService.updateTransferBill(bill);
        FlowBill flowBill = convertTransferBillToFlowBill(bill);
        // 更新到流水中 可以逻辑删除
        flowBillService.deleteFlowBillByFlowBillIdAndFlowCategory(flowBill);
        return toAjax(flowBillService.insertFlowBill(flowBill));
    }

    /**
     * 删除转账账单
     */
    @PreAuthorize("@ss.hasPermi('bill:transfer:remove')")
    @Log(title = "转账账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{transferIds}")
    public AjaxResult remove(@PathVariable Long[] transferIds)
    {
        return toAjax(transferBillService.deleteTransferBillByTransferIds(transferIds));
    }

    /**
     * 获取转账账单下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<TransferBill> list = transferBillService.selectTransferBillAll();
        return AjaxResult.success(list);
    }

}
