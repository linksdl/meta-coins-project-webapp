package com.ruoyi.bill.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收入账单对象 bill_income
 * 
 * @author metacoin
 * @date 2022-10-01
 */
public class IncomeBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收入ID */
    private Long incomeId;

    /** 收入名称 */
    @Excel(name = "收入名称")
    private String incomeName;

    /** 收入类型 */
    @Excel(name = "收入类型")
    private String incomeType;

    /** 收入金额 */
    @Excel(name = "收入金额")
    private Long incomeAmount;

    /** 收入日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收入日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date incomeDatetime;

    /** 收入描述 */
    @Excel(name = "收入描述")
    private String incomeDesc;

    /** 收入凭证 */
    @Excel(name = "收入凭证")
    private String incomeImgs;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private Long enableStatus;

    /** 图标 */
    private String icon;

    /** 排序 */
    private Long orderSort;

    /** 权重 */
    private Long weight;

    /** 父类ID */
    private Long incomeParentId;

    /** 父类名称 */
    @Excel(name = "父类名称")
    private String incomeParentName;

    /** 账本ID */
    private Long incomeBookId;

    /** 账本名 */
    private String incomeBookName;

    /** 账户ID */
    private Long incomeAccountId;

    /** 账户名 */
    private String incomeAccountName;

    /** 分类ID */
    private Long incomeCategoryId;

    /** 分类名 */
    private String incomeCategoryName;

    /** 城市ID */
    private Long incomeCityId;

    /** 城市名 */
    private String incomeCityName;

    /** 心情ID */
    private Long incomeEmotionId;

    /** 心情名 */
    private String incomeEmotionName;

    /** 主体ID */
    private Long incomeEntityId;

    /** 主体名 */
    private String incomeEntityName;

    /** 地址 */
    private String incomeAddress;

    /** 标签ID */
    private Long incomeLabelId;

    /** 标签名 */
    @Excel(name = "标签名")
    private String incomeLabelName;

    /** 成员ID */
    private Long incomeMemberId;

    /** 成员名 */
    @Excel(name = "成员名")
    private String incomeMemberName;

    /** 币种ID */
    private Long incomeMoneyId;

    /** 币种名 */
    @Excel(name = "币种名")
    private String incomeMoneyName;

    /** 项目ID */
    private Long incomeProjectId;

    /** 项目名 */
    @Excel(name = "项目名")
    private String incomeProjectName;

    /** 天气ID */
    private Long incomeWeatherId;

    /** 天气名 */
    @Excel(name = "天气名")
    private String incomeWeatherName;

    /** 用户ID */
    private Long incomeUserId;

    /** 用户名 */
    private String incomeUserName;

    /** 国家 */
    private String incomeCountry;

    /** 省份州 */
    private String incomeProvince;

    /** 郡市县区 */
    private String incomeCounty;

    /** 城市 */
    private String incomeCity;

    /** 镇,街道 */
    private String incomeStreet;

    /** 日期 */
    private String incomeDate;

    /** 年份 */
    private String incomeYear;

    /** 季度 */
    private String incomeQuarter;

    /** 月份 */
    private String incomeMonth;

    /** 年周 */
    private String incomeYearWeek;

    /** 星期 */
    private String incomeWeek;

    /** 天 */
    private String incomeDay;

    /** 区间 */
    private String incomePeriod;

    /** 是否删除 */
    private Integer isDeleted;

    public void setIncomeId(Long incomeId) 
    {
        this.incomeId = incomeId;
    }

    public Long getIncomeId() 
    {
        return incomeId;
    }
    public void setIncomeName(String incomeName) 
    {
        this.incomeName = incomeName;
    }

    public String getIncomeName() 
    {
        return incomeName;
    }
    public void setIncomeType(String incomeType) 
    {
        this.incomeType = incomeType;
    }

    public String getIncomeType() 
    {
        return incomeType;
    }
    public void setIncomeAmount(Long incomeAmount) 
    {
        this.incomeAmount = incomeAmount;
    }

    public Long getIncomeAmount() 
    {
        return incomeAmount;
    }
    public void setIncomeDatetime(Date incomeDatetime) 
    {
        this.incomeDatetime = incomeDatetime;
    }

    public Date getIncomeDatetime() 
    {
        return incomeDatetime;
    }
    public void setIncomeDesc(String incomeDesc) 
    {
        this.incomeDesc = incomeDesc;
    }

    public String getIncomeDesc() 
    {
        return incomeDesc;
    }
    public void setIncomeImgs(String incomeImgs) 
    {
        this.incomeImgs = incomeImgs;
    }

    public String getIncomeImgs() 
    {
        return incomeImgs;
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
    public void setOrderSort(Long orderSort) 
    {
        this.orderSort = orderSort;
    }

    public Long getOrderSort() 
    {
        return orderSort;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setIncomeParentId(Long incomeParentId) 
    {
        this.incomeParentId = incomeParentId;
    }

    public Long getIncomeParentId() 
    {
        return incomeParentId;
    }
    public void setIncomeParentName(String incomeParentName) 
    {
        this.incomeParentName = incomeParentName;
    }

    public String getIncomeParentName() 
    {
        return incomeParentName;
    }
    public void setIncomeBookId(Long incomeBookId) 
    {
        this.incomeBookId = incomeBookId;
    }

    public Long getIncomeBookId() 
    {
        return incomeBookId;
    }
    public void setIncomeBookName(String incomeBookName) 
    {
        this.incomeBookName = incomeBookName;
    }

    public String getIncomeBookName() 
    {
        return incomeBookName;
    }
    public void setIncomeAccountId(Long incomeAccountId) 
    {
        this.incomeAccountId = incomeAccountId;
    }

    public Long getIncomeAccountId() 
    {
        return incomeAccountId;
    }
    public void setIncomeAccountName(String incomeAccountName) 
    {
        this.incomeAccountName = incomeAccountName;
    }

    public String getIncomeAccountName() 
    {
        return incomeAccountName;
    }
    public void setIncomeCategoryId(Long incomeCategoryId) 
    {
        this.incomeCategoryId = incomeCategoryId;
    }

    public Long getIncomeCategoryId() 
    {
        return incomeCategoryId;
    }
    public void setIncomeCategoryName(String incomeCategoryName) 
    {
        this.incomeCategoryName = incomeCategoryName;
    }

    public String getIncomeCategoryName() 
    {
        return incomeCategoryName;
    }
    public void setIncomeCityId(Long incomeCityId) 
    {
        this.incomeCityId = incomeCityId;
    }

    public Long getIncomeCityId() 
    {
        return incomeCityId;
    }
    public void setIncomeCityName(String incomeCityName) 
    {
        this.incomeCityName = incomeCityName;
    }

    public String getIncomeCityName() 
    {
        return incomeCityName;
    }
    public void setIncomeEmotionId(Long incomeEmotionId) 
    {
        this.incomeEmotionId = incomeEmotionId;
    }

    public Long getIncomeEmotionId() 
    {
        return incomeEmotionId;
    }
    public void setIncomeEmotionName(String incomeEmotionName) 
    {
        this.incomeEmotionName = incomeEmotionName;
    }

    public String getIncomeEmotionName() 
    {
        return incomeEmotionName;
    }
    public void setIncomeEntityId(Long incomeEntityId) 
    {
        this.incomeEntityId = incomeEntityId;
    }

    public Long getIncomeEntityId() 
    {
        return incomeEntityId;
    }
    public void setIncomeEntityName(String incomeEntityName) 
    {
        this.incomeEntityName = incomeEntityName;
    }

    public String getIncomeEntityName() 
    {
        return incomeEntityName;
    }
    public void setIncomeAddress(String incomeAddress) 
    {
        this.incomeAddress = incomeAddress;
    }

    public String getIncomeAddress() 
    {
        return incomeAddress;
    }
    public void setIncomeLabelId(Long incomeLabelId) 
    {
        this.incomeLabelId = incomeLabelId;
    }

    public Long getIncomeLabelId() 
    {
        return incomeLabelId;
    }
    public void setIncomeLabelName(String incomeLabelName) 
    {
        this.incomeLabelName = incomeLabelName;
    }

    public String getIncomeLabelName() 
    {
        return incomeLabelName;
    }
    public void setIncomeMemberId(Long incomeMemberId) 
    {
        this.incomeMemberId = incomeMemberId;
    }

    public Long getIncomeMemberId() 
    {
        return incomeMemberId;
    }
    public void setIncomeMemberName(String incomeMemberName) 
    {
        this.incomeMemberName = incomeMemberName;
    }

    public String getIncomeMemberName() 
    {
        return incomeMemberName;
    }
    public void setIncomeMoneyId(Long incomeMoneyId) 
    {
        this.incomeMoneyId = incomeMoneyId;
    }

    public Long getIncomeMoneyId() 
    {
        return incomeMoneyId;
    }
    public void setIncomeMoneyName(String incomeMoneyName) 
    {
        this.incomeMoneyName = incomeMoneyName;
    }

    public String getIncomeMoneyName() 
    {
        return incomeMoneyName;
    }
    public void setIncomeProjectId(Long incomeProjectId) 
    {
        this.incomeProjectId = incomeProjectId;
    }

    public Long getIncomeProjectId() 
    {
        return incomeProjectId;
    }
    public void setIncomeProjectName(String incomeProjectName) 
    {
        this.incomeProjectName = incomeProjectName;
    }

    public String getIncomeProjectName() 
    {
        return incomeProjectName;
    }
    public void setIncomeWeatherId(Long incomeWeatherId) 
    {
        this.incomeWeatherId = incomeWeatherId;
    }

    public Long getIncomeWeatherId() 
    {
        return incomeWeatherId;
    }
    public void setIncomeWeatherName(String incomeWeatherName) 
    {
        this.incomeWeatherName = incomeWeatherName;
    }

    public String getIncomeWeatherName() 
    {
        return incomeWeatherName;
    }
    public void setIncomeUserId(Long incomeUserId) 
    {
        this.incomeUserId = incomeUserId;
    }

    public Long getIncomeUserId() 
    {
        return incomeUserId;
    }
    public void setIncomeUserName(String incomeUserName) 
    {
        this.incomeUserName = incomeUserName;
    }

    public String getIncomeUserName() 
    {
        return incomeUserName;
    }
    public void setIncomeCountry(String incomeCountry) 
    {
        this.incomeCountry = incomeCountry;
    }

    public String getIncomeCountry() 
    {
        return incomeCountry;
    }
    public void setIncomeProvince(String incomeProvince) 
    {
        this.incomeProvince = incomeProvince;
    }

    public String getIncomeProvince() 
    {
        return incomeProvince;
    }
    public void setIncomeCounty(String incomeCounty) 
    {
        this.incomeCounty = incomeCounty;
    }

    public String getIncomeCounty() 
    {
        return incomeCounty;
    }
    public void setIncomeCity(String incomeCity) 
    {
        this.incomeCity = incomeCity;
    }

    public String getIncomeCity() 
    {
        return incomeCity;
    }
    public void setIncomeStreet(String incomeStreet) 
    {
        this.incomeStreet = incomeStreet;
    }

    public String getIncomeStreet() 
    {
        return incomeStreet;
    }
    public void setIncomeDate(String incomeDate) 
    {
        this.incomeDate = incomeDate;
    }

    public String getIncomeDate() 
    {
        return incomeDate;
    }
    public void setIncomeYear(String incomeYear) 
    {
        this.incomeYear = incomeYear;
    }

    public String getIncomeYear() 
    {
        return incomeYear;
    }
    public void setIncomeQuarter(String incomeQuarter) 
    {
        this.incomeQuarter = incomeQuarter;
    }

    public String getIncomeQuarter() 
    {
        return incomeQuarter;
    }
    public void setIncomeMonth(String incomeMonth) 
    {
        this.incomeMonth = incomeMonth;
    }

    public String getIncomeMonth() 
    {
        return incomeMonth;
    }
    public void setIncomeYearWeek(String incomeYearWeek) 
    {
        this.incomeYearWeek = incomeYearWeek;
    }

    public String getIncomeYearWeek() 
    {
        return incomeYearWeek;
    }
    public void setIncomeWeek(String incomeWeek) 
    {
        this.incomeWeek = incomeWeek;
    }

    public String getIncomeWeek() 
    {
        return incomeWeek;
    }
    public void setIncomeDay(String incomeDay) 
    {
        this.incomeDay = incomeDay;
    }

    public String getIncomeDay() 
    {
        return incomeDay;
    }
    public void setIncomePeriod(String incomePeriod) 
    {
        this.incomePeriod = incomePeriod;
    }

    public String getIncomePeriod() 
    {
        return incomePeriod;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("incomeId", getIncomeId())
            .append("incomeName", getIncomeName())
            .append("incomeType", getIncomeType())
            .append("incomeAmount", getIncomeAmount())
            .append("incomeDatetime", getIncomeDatetime())
            .append("incomeDesc", getIncomeDesc())
            .append("incomeImgs", getIncomeImgs())
            .append("enableStatus", getEnableStatus())
            .append("icon", getIcon())
            .append("remark", getRemark())
            .append("orderSort", getOrderSort())
            .append("weight", getWeight())
            .append("incomeParentId", getIncomeParentId())
            .append("incomeParentName", getIncomeParentName())
            .append("incomeBookId", getIncomeBookId())
            .append("incomeBookName", getIncomeBookName())
            .append("incomeAccountId", getIncomeAccountId())
            .append("incomeAccountName", getIncomeAccountName())
            .append("incomeCategoryId", getIncomeCategoryId())
            .append("incomeCategoryName", getIncomeCategoryName())
            .append("incomeCityId", getIncomeCityId())
            .append("incomeCityName", getIncomeCityName())
            .append("incomeEmotionId", getIncomeEmotionId())
            .append("incomeEmotionName", getIncomeEmotionName())
            .append("incomeEntityId", getIncomeEntityId())
            .append("incomeEntityName", getIncomeEntityName())
            .append("incomeAddress", getIncomeAddress())
            .append("incomeLabelId", getIncomeLabelId())
            .append("incomeLabelName", getIncomeLabelName())
            .append("incomeMemberId", getIncomeMemberId())
            .append("incomeMemberName", getIncomeMemberName())
            .append("incomeMoneyId", getIncomeMoneyId())
            .append("incomeMoneyName", getIncomeMoneyName())
            .append("incomeProjectId", getIncomeProjectId())
            .append("incomeProjectName", getIncomeProjectName())
            .append("incomeWeatherId", getIncomeWeatherId())
            .append("incomeWeatherName", getIncomeWeatherName())
            .append("incomeUserId", getIncomeUserId())
            .append("incomeUserName", getIncomeUserName())
            .append("incomeCountry", getIncomeCountry())
            .append("incomeProvince", getIncomeProvince())
            .append("incomeCounty", getIncomeCounty())
            .append("incomeCity", getIncomeCity())
            .append("incomeStreet", getIncomeStreet())
            .append("incomeDate", getIncomeDate())
            .append("incomeYear", getIncomeYear())
            .append("incomeQuarter", getIncomeQuarter())
            .append("incomeMonth", getIncomeMonth())
            .append("incomeYearWeek", getIncomeYearWeek())
            .append("incomeWeek", getIncomeWeek())
            .append("incomeDay", getIncomeDay())
            .append("incomePeriod", getIncomePeriod())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
