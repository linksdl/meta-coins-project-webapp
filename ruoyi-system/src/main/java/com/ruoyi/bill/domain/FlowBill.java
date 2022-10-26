package com.ruoyi.bill.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 账单流水对象 bill_flow
 * 
 * @author metacoin
 * @date 2022-10-01
 */
public class FlowBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水ID */
    private Long flowId;

    /** 流水名称 */
    @Excel(name = "流水名称")
    private String flowName;

    /** 流水源头 */
    @Excel(name = "流水源头")
    private String flowSource;

    /** 流水目的 */
    @Excel(name = "流水目的")
    private String flowTarget;

    /** 流水金额 */
    @Excel(name = "流水金额")
    private Double flowAmount;

    /** 流水标志 */
    @Excel(name = "流水标志")
    private String flowSign;

    /** 流水类型 */
    @Excel(name = "流水类型")
    private String flowType;

    /** 流水分类 */
    @Excel(name = "流水分类")
    private String flowCategory;  // income, consume, debt, transfer, balance, invest

    /** 流水日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "流水日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date flowDatetime;

    /** 父类ID */
    private Long flowParentId;

    /** 父类名 */
    @Excel(name = "父类名")
    private String flowParentName;

    /** 流水账单ID */
    private Long flowBillId;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

    /** 排序 */
    private Long orderSort;

    /** 描述 */
    @Excel(name = "描述")
    private String flowDesc;

    /** 图片凭证 */
    @Excel(name = "图片凭证")
    private String flowImgs;

    /** 备注 */
    private String flowRemark;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private Long enableStatus;

    /** 图标 */
    private String icon;

    /** 权重 */
    private Long weight;

    /** 用户ID */
    private Long flowUserId;

    /** 用户名 */
    private String flowUserName;

    /** 天气ID */
    private Long flowWeatherId;

    /** 天气名 */
    @Excel(name = "天气名")
    private String flowWeatherName;

    /** 币种名 */
    @Excel(name = "币种名")
    private String flowMoneyName;

    /** 币种ID */
    private Long flowMoneyId;

    /** 成员名 */
    @Excel(name = "成员名")
    private String flowMemberName;

    /** 成员ID */
    private Long flowMemberId;

    /** 标签名 */
    @Excel(name = "标签名")
    private String flowLabelName;

    /** 标签ID */
    private String flowLabelId;

    /** 项目ID */
    private Long flowProjectId;

    /** 项目名 */
    @Excel(name = "项目名")
    private String flowProjectName;

    /** 心情ID */
    private Long flowEmotionId;

    /** 心情名 */
    @Excel(name = "心情名")
    private String flowEmotionName;

    /** 分类名 */
    @Excel(name = "分类名")
    private String flowCategoryName;

    /** 分类ID */
    private String flowCategoryId;

    /** 账户名 */
    @Excel(name = "账户名")
    private String flowAccountName;

    /** 账户ID */
    private String flowAccountId;

    /** 实体名 */
    @Excel(name = "实体名")
    private String flowEntityName;

    /** 实体ID */
    private Long flowEntityId;

    /** 城市ID */
    private Long flowCityId;

    /** 城市名 */
    @Excel(name = "城市名")
    private String flowCityName;

    /** 账本ID */
    private Long flowBookId;

    /** 账本名 */
    @Excel(name = "账本名")
    private String flowBookName;

    /** 年份 */
    private String flowYear;

    /** 月份 */
    private String flowMonth;

    /** 日期 */
    private String flowDate;

    /** 天 */
    private String flowDay;

    /** 季度 */
    private String flowQuarter;

    /** 区间 */
    private String flowPeriod;

    /** 星期 */
    private String flowWeek;

    /** 周数 */
    private String flowYearWeek;

    /** 郡市县区 */
    private String flowCounty;

    /** 省州 */
    private String flowProvince;

    /** 镇街道 */
    private String flowStreet;

    /** 国家 */
    private String flowCountry;

    /** 城市 */
    private String flowCity;

    /** 地址 */
    private String flowAddress;

    public void setFlowId(Long flowId) 
    {
        this.flowId = flowId;
    }

    public Long getFlowId() 
    {
        return flowId;
    }
    public void setFlowName(String flowName) 
    {
        this.flowName = flowName;
    }

    public String getFlowName() 
    {
        return flowName;
    }
    public void setFlowSource(String flowSource) 
    {
        this.flowSource = flowSource;
    }

    public String getFlowSource() 
    {
        return flowSource;
    }
    public void setFlowTarget(String flowTarget) 
    {
        this.flowTarget = flowTarget;
    }

    public String getFlowTarget() 
    {
        return flowTarget;
    }
    public void setFlowAmount(Double flowAmount)
    {
        this.flowAmount = flowAmount;
    }

    public Double getFlowAmount()
    {
        return flowAmount;
    }
    public void setFlowSign(String flowSign) 
    {
        this.flowSign = flowSign;
    }

    public String getFlowSign() 
    {
        return flowSign;
    }
    public void setFlowType(String flowType) 
    {
        this.flowType = flowType;
    }

    public String getFlowType() 
    {
        return flowType;
    }

    public String getFlowCategory() {
        return flowCategory;
    }

    public void setFlowCategory(String flowCategory) {
        this.flowCategory = flowCategory;
    }

    public void setFlowDatetime(Date flowDatetime)
    {
        this.flowDatetime = flowDatetime;
    }

    public Date getFlowDatetime() 
    {
        return flowDatetime;
    }
    public void setFlowParentId(Long flowParentId) 
    {
        this.flowParentId = flowParentId;
    }

    public Long getFlowParentId() 
    {
        return flowParentId;
    }
    public void setFlowParentName(String flowParentName) 
    {
        this.flowParentName = flowParentName;
    }

    public String getFlowParentName() 
    {
        return flowParentName;
    }
    public void setFlowBillId(Long flowBillId) 
    {
        this.flowBillId = flowBillId;
    }

    public Long getFlowBillId() 
    {
        return flowBillId;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }
    public void setOrderSort(Long orderSort) 
    {
        this.orderSort = orderSort;
    }

    public Long getOrderSort() 
    {
        return orderSort;
    }
    public void setFlowDesc(String flowDesc) 
    {
        this.flowDesc = flowDesc;
    }

    public String getFlowDesc() 
    {
        return flowDesc;
    }
    public void setFlowImgs(String flowImgs) 
    {
        this.flowImgs = flowImgs;
    }

    public String getFlowImgs() 
    {
        return flowImgs;
    }
    public void setFlowRemark(String flowRemark) 
    {
        this.flowRemark = flowRemark;
    }

    public String getFlowRemark() 
    {
        return flowRemark;
    }
    public void setEnableStatus(Long enableStatus) 
    {
        this.enableStatus = enableStatus;
    }

    public Long getEnableStatus() 
    {
        return enableStatus;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setFlowUserId(Long flowUserId) 
    {
        this.flowUserId = flowUserId;
    }

    public Long getFlowUserId() 
    {
        return flowUserId;
    }
    public void setFlowUserName(String flowUserName) 
    {
        this.flowUserName = flowUserName;
    }

    public String getFlowUserName() 
    {
        return flowUserName;
    }
    public void setFlowWeatherId(Long flowWeatherId) 
    {
        this.flowWeatherId = flowWeatherId;
    }

    public Long getFlowWeatherId() 
    {
        return flowWeatherId;
    }
    public void setFlowWeatherName(String flowWeatherName) 
    {
        this.flowWeatherName = flowWeatherName;
    }

    public String getFlowWeatherName() 
    {
        return flowWeatherName;
    }
    public void setFlowMoneyName(String flowMoneyName) 
    {
        this.flowMoneyName = flowMoneyName;
    }

    public String getFlowMoneyName() 
    {
        return flowMoneyName;
    }
    public void setFlowMoneyId(Long flowMoneyId) 
    {
        this.flowMoneyId = flowMoneyId;
    }

    public Long getFlowMoneyId() 
    {
        return flowMoneyId;
    }
    public void setFlowMemberName(String flowMemberName) 
    {
        this.flowMemberName = flowMemberName;
    }

    public String getFlowMemberName() 
    {
        return flowMemberName;
    }
    public void setFlowMemberId(Long flowMemberId) 
    {
        this.flowMemberId = flowMemberId;
    }

    public Long getFlowMemberId() 
    {
        return flowMemberId;
    }
    public void setFlowLabelName(String flowLabelName) 
    {
        this.flowLabelName = flowLabelName;
    }

    public String getFlowLabelName() 
    {
        return flowLabelName;
    }
    public void setFlowLabelId(String flowLabelId)
    {
        this.flowLabelId = flowLabelId;
    }

    public String getFlowLabelId()
    {
        return flowLabelId;
    }
    public void setFlowProjectId(Long flowProjectId) 
    {
        this.flowProjectId = flowProjectId;
    }

    public Long getFlowProjectId() 
    {
        return flowProjectId;
    }
    public void setFlowProjectName(String flowProjectName) 
    {
        this.flowProjectName = flowProjectName;
    }

    public String getFlowProjectName() 
    {
        return flowProjectName;
    }
    public void setFlowEmotionId(Long flowEmotionId) 
    {
        this.flowEmotionId = flowEmotionId;
    }

    public Long getFlowEmotionId() 
    {
        return flowEmotionId;
    }
    public void setFlowEmotionName(String flowEmotionName) 
    {
        this.flowEmotionName = flowEmotionName;
    }

    public String getFlowEmotionName() 
    {
        return flowEmotionName;
    }
    public void setFlowCategoryName(String flowCategoryName) 
    {
        this.flowCategoryName = flowCategoryName;
    }

    public String getFlowCategoryName() 
    {
        return flowCategoryName;
    }
    public void setFlowCategoryId(String flowCategoryId)
    {
        this.flowCategoryId = flowCategoryId;
    }

    public String getFlowCategoryId()
    {
        return flowCategoryId;
    }
    public void setFlowAccountName(String flowAccountName) 
    {
        this.flowAccountName = flowAccountName;
    }

    public String getFlowAccountName() 
    {
        return flowAccountName;
    }
    public void setFlowAccountId(String flowAccountId)
    {
        this.flowAccountId = flowAccountId;
    }

    public String getFlowAccountId()
    {
        return flowAccountId;
    }
    public void setFlowEntityName(String flowEntityName) 
    {
        this.flowEntityName = flowEntityName;
    }

    public String getFlowEntityName() 
    {
        return flowEntityName;
    }
    public void setFlowEntityId(Long flowEntityId) 
    {
        this.flowEntityId = flowEntityId;
    }

    public Long getFlowEntityId() 
    {
        return flowEntityId;
    }
    public void setFlowCityId(Long flowCityId) 
    {
        this.flowCityId = flowCityId;
    }

    public Long getFlowCityId() 
    {
        return flowCityId;
    }
    public void setFlowCityName(String flowCityName) 
    {
        this.flowCityName = flowCityName;
    }

    public String getFlowCityName() 
    {
        return flowCityName;
    }
    public void setFlowBookId(Long flowBookId) 
    {
        this.flowBookId = flowBookId;
    }

    public Long getFlowBookId() 
    {
        return flowBookId;
    }
    public void setFlowBookName(String flowBookName) 
    {
        this.flowBookName = flowBookName;
    }

    public String getFlowBookName() 
    {
        return flowBookName;
    }
    public void setFlowYear(String flowYear) 
    {
        this.flowYear = flowYear;
    }

    public String getFlowYear() 
    {
        return flowYear;
    }
    public void setFlowMonth(String flowMonth) 
    {
        this.flowMonth = flowMonth;
    }

    public String getFlowMonth() 
    {
        return flowMonth;
    }
    public void setFlowDate(String flowDate) 
    {
        this.flowDate = flowDate;
    }

    public String getFlowDate() 
    {
        return flowDate;
    }
    public void setFlowDay(String flowDay) 
    {
        this.flowDay = flowDay;
    }

    public String getFlowDay() 
    {
        return flowDay;
    }
    public void setFlowQuarter(String flowQuarter) 
    {
        this.flowQuarter = flowQuarter;
    }

    public String getFlowQuarter() 
    {
        return flowQuarter;
    }
    public void setFlowPeriod(String flowPeriod) 
    {
        this.flowPeriod = flowPeriod;
    }

    public String getFlowPeriod() 
    {
        return flowPeriod;
    }
    public void setFlowWeek(String flowWeek) 
    {
        this.flowWeek = flowWeek;
    }

    public String getFlowWeek() 
    {
        return flowWeek;
    }
    public void setFlowYearWeek(String flowYearWeek) 
    {
        this.flowYearWeek = flowYearWeek;
    }

    public String getFlowYearWeek() 
    {
        return flowYearWeek;
    }
    public void setFlowCounty(String flowCounty) 
    {
        this.flowCounty = flowCounty;
    }

    public String getFlowCounty() 
    {
        return flowCounty;
    }
    public void setFlowProvince(String flowProvince) 
    {
        this.flowProvince = flowProvince;
    }

    public String getFlowProvince() 
    {
        return flowProvince;
    }
    public void setFlowStreet(String flowStreet) 
    {
        this.flowStreet = flowStreet;
    }

    public String getFlowStreet() 
    {
        return flowStreet;
    }
    public void setFlowCountry(String flowCountry) 
    {
        this.flowCountry = flowCountry;
    }

    public String getFlowCountry() 
    {
        return flowCountry;
    }
    public void setFlowCity(String flowCity) 
    {
        this.flowCity = flowCity;
    }

    public String getFlowCity() 
    {
        return flowCity;
    }
    public void setFlowAddress(String flowAddress) 
    {
        this.flowAddress = flowAddress;
    }

    public String getFlowAddress() 
    {
        return flowAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("flowId", getFlowId())
            .append("flowName", getFlowName())
            .append("flowSource", getFlowSource())
            .append("flowTarget", getFlowTarget())
            .append("flowAmount", getFlowAmount())
            .append("flowSign", getFlowSign())
            .append("flowType", getFlowType())
            .append("flowDatetime", getFlowDatetime())
            .append("flowParentId", getFlowParentId())
            .append("flowParentName", getFlowParentName())
            .append("flowBillId", getFlowBillId())
            .append("isDeleted", getIsDeleted())
            .append("orderSort", getOrderSort())
            .append("flowDesc", getFlowDesc())
            .append("flowImgs", getFlowImgs())
            .append("flowRemark", getFlowRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("enableStatus", getEnableStatus())
            .append("icon", getIcon())
            .append("remark", getRemark())
            .append("weight", getWeight())
            .append("flowUserId", getFlowUserId())
            .append("flowUserName", getFlowUserName())
            .append("flowWeatherId", getFlowWeatherId())
            .append("flowWeatherName", getFlowWeatherName())
            .append("flowMoneyName", getFlowMoneyName())
            .append("flowMoneyId", getFlowMoneyId())
            .append("flowMemberName", getFlowMemberName())
            .append("flowMemberId", getFlowMemberId())
            .append("flowLabelName", getFlowLabelName())
            .append("flowLabelId", getFlowLabelId())
            .append("flowProjectId", getFlowProjectId())
            .append("flowProjectName", getFlowProjectName())
            .append("flowEmotionId", getFlowEmotionId())
            .append("flowEmotionName", getFlowEmotionName())
            .append("flowCategoryName", getFlowCategoryName())
            .append("flowCategoryId", getFlowCategoryId())
            .append("flowAccountName", getFlowAccountName())
            .append("flowAccountId", getFlowAccountId())
            .append("flowEntityName", getFlowEntityName())
            .append("flowEntityId", getFlowEntityId())
            .append("flowCityId", getFlowCityId())
            .append("flowCityName", getFlowCityName())
            .append("flowBookId", getFlowBookId())
            .append("flowBookName", getFlowBookName())
            .append("flowYear", getFlowYear())
            .append("flowMonth", getFlowMonth())
            .append("flowDate", getFlowDate())
            .append("flowDay", getFlowDay())
            .append("flowQuarter", getFlowQuarter())
            .append("flowPeriod", getFlowPeriod())
            .append("flowWeek", getFlowWeek())
            .append("flowYearWeek", getFlowYearWeek())
            .append("flowCounty", getFlowCounty())
            .append("flowProvince", getFlowProvince())
            .append("flowStreet", getFlowStreet())
            .append("flowCountry", getFlowCountry())
            .append("flowCity", getFlowCity())
            .append("flowAddress", getFlowAddress())
            .toString();
    }
}
