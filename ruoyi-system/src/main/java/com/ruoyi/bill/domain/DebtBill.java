package com.ruoyi.bill.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 借贷账单对象 bill_debt
 * 
 * @author metacoin
 * @date 2022-10-01
 */
public class DebtBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借贷ID */
    private Long debtId;

    /** 借贷名称 */
    @Excel(name = "借贷名称")
    private String debtName;

    /** 借贷类型 */
    @Excel(name = "借贷类型")
    private String debtType;

    /** 凭证 */
    @Excel(name = "凭证")
    private String debtImgs;

    /** 描述 */
    @Excel(name = "描述")
    private String debtDesc;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date debtDatetime;

    /** 金额 */
    @Excel(name = "金额")
    private Long debtAmount;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private Long enableStatus;

    /** 父类ID */
    private Long debtParentId;

    /** 父类名称 */
    @Excel(name = "父类名称")
    private String debtParentName;

    /** 图标 */
    private String icon;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderSort;

    /** 权重 */
    @Excel(name = "权重")
    private Long weight;

    /** 用户ID */
    private Long debtUserId;

    /** 用户名 */
    private String debtUserName;

    /** 账户ID */
    private Long debtAccountId;

    /** 账户名 */
    @Excel(name = "账户名")
    private String debtAccountName;

    /** 账本ID */
    private Long debtBookId;

    /** 账本名 */
    @Excel(name = "账本名")
    private String debtBookName;

    /** 分类ID */
    private Long debtCategoryId;

    /** 分类名 */
    @Excel(name = "分类名")
    private String debtCategoryName;

    /** 城市ID */
    private Long debtCityId;

    /** 城市名 */
    @Excel(name = "城市名")
    private String debtCityName;

    /** 心情ID */
    private Long debtEmotionId;

    /** 心情名 */
    @Excel(name = "心情名")
    private String debtEmotionName;

    /** 实体ID */
    private Long debtEntityId;

    /** 实体名 */
    @Excel(name = "实体名")
    private String debtEntityName;

    /** 标签ID */
    private Long debtLabelId;

    /** 标签名 */
    @Excel(name = "标签名")
    private String debtLabelName;

    /** 成员ID */
    private Long debtMemberId;

    /** 成员名 */
    private String debtMemberName;

    /** 币种ID */
    private Long debtMoneyId;

    /** 币种名 */
    @Excel(name = "币种名")
    private String debtMoneyName;

    /** 项目ID */
    private Long debtProjectId;

    /** 项目名 */
    @Excel(name = "项目名")
    private String debtProjectName;

    /** 天气ID */
    private Long debtWeatherId;

    /** 天气名 */
    @Excel(name = "天气名")
    private String debtWeatherName;

    /** 地址 */
    private String debtAddress;

    /** 城市 */
    private String debtCity;

    /** 国家 */
    private String debtCountry;

    /** 省州 */
    private String debtProvince;

    /** 郡县市 */
    private String debtCounty;

    /** 镇街道 */
    private String debtStreet;

    /** 年份 */
    private String debtYear;

    /** 日期 */
    private String debtDate;

    /** 天 */
    private String debtDay;

    /** 月份 */
    private String debtMonth;

    /** 区间 */
    private String debtPeriod;

    /** 季度 */
    private String debtQuarter;

    /** 星期 */
    private String debtWeek;

    /** 周 */
    private String debtYearWeek;

    public void setDebtId(Long debtId) 
    {
        this.debtId = debtId;
    }

    public Long getDebtId() 
    {
        return debtId;
    }
    public void setDebtName(String debtName) 
    {
        this.debtName = debtName;
    }

    public String getDebtName() 
    {
        return debtName;
    }
    public void setDebtType(String debtType) 
    {
        this.debtType = debtType;
    }

    public String getDebtType() 
    {
        return debtType;
    }
    public void setDebtImgs(String debtImgs) 
    {
        this.debtImgs = debtImgs;
    }

    public String getDebtImgs() 
    {
        return debtImgs;
    }
    public void setDebtDesc(String debtDesc) 
    {
        this.debtDesc = debtDesc;
    }

    public String getDebtDesc() 
    {
        return debtDesc;
    }
    public void setDebtDatetime(Date debtDatetime) 
    {
        this.debtDatetime = debtDatetime;
    }

    public Date getDebtDatetime() 
    {
        return debtDatetime;
    }
    public void setDebtAmount(Long debtAmount) 
    {
        this.debtAmount = debtAmount;
    }

    public Long getDebtAmount() 
    {
        return debtAmount;
    }
    public void setEnableStatus(Long enableStatus) 
    {
        this.enableStatus = enableStatus;
    }

    public Long getEnableStatus() 
    {
        return enableStatus;
    }
    public void setDebtParentId(Long debtParentId) 
    {
        this.debtParentId = debtParentId;
    }

    public Long getDebtParentId() 
    {
        return debtParentId;
    }
    public void setDebtParentName(String debtParentName) 
    {
        this.debtParentName = debtParentName;
    }

    public String getDebtParentName() 
    {
        return debtParentName;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
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
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setDebtUserId(Long debtUserId) 
    {
        this.debtUserId = debtUserId;
    }

    public Long getDebtUserId() 
    {
        return debtUserId;
    }
    public void setDebtUserName(String debtUserName) 
    {
        this.debtUserName = debtUserName;
    }

    public String getDebtUserName() 
    {
        return debtUserName;
    }
    public void setDebtAccountId(Long debtAccountId) 
    {
        this.debtAccountId = debtAccountId;
    }

    public Long getDebtAccountId() 
    {
        return debtAccountId;
    }
    public void setDebtAccountName(String debtAccountName) 
    {
        this.debtAccountName = debtAccountName;
    }

    public String getDebtAccountName() 
    {
        return debtAccountName;
    }
    public void setDebtBookId(Long debtBookId) 
    {
        this.debtBookId = debtBookId;
    }

    public Long getDebtBookId() 
    {
        return debtBookId;
    }
    public void setDebtBookName(String debtBookName) 
    {
        this.debtBookName = debtBookName;
    }

    public String getDebtBookName() 
    {
        return debtBookName;
    }
    public void setDebtCategoryId(Long debtCategoryId) 
    {
        this.debtCategoryId = debtCategoryId;
    }

    public Long getDebtCategoryId() 
    {
        return debtCategoryId;
    }
    public void setDebtCategoryName(String debtCategoryName) 
    {
        this.debtCategoryName = debtCategoryName;
    }

    public String getDebtCategoryName() 
    {
        return debtCategoryName;
    }
    public void setDebtCityId(Long debtCityId) 
    {
        this.debtCityId = debtCityId;
    }

    public Long getDebtCityId() 
    {
        return debtCityId;
    }
    public void setDebtCityName(String debtCityName) 
    {
        this.debtCityName = debtCityName;
    }

    public String getDebtCityName() 
    {
        return debtCityName;
    }
    public void setDebtEmotionId(Long debtEmotionId) 
    {
        this.debtEmotionId = debtEmotionId;
    }

    public Long getDebtEmotionId() 
    {
        return debtEmotionId;
    }
    public void setDebtEmotionName(String debtEmotionName) 
    {
        this.debtEmotionName = debtEmotionName;
    }

    public String getDebtEmotionName() 
    {
        return debtEmotionName;
    }
    public void setDebtEntityId(Long debtEntityId) 
    {
        this.debtEntityId = debtEntityId;
    }

    public Long getDebtEntityId() 
    {
        return debtEntityId;
    }
    public void setDebtEntityName(String debtEntityName) 
    {
        this.debtEntityName = debtEntityName;
    }

    public String getDebtEntityName() 
    {
        return debtEntityName;
    }
    public void setDebtLabelId(Long debtLabelId) 
    {
        this.debtLabelId = debtLabelId;
    }

    public Long getDebtLabelId() 
    {
        return debtLabelId;
    }
    public void setDebtLabelName(String debtLabelName) 
    {
        this.debtLabelName = debtLabelName;
    }

    public String getDebtLabelName() 
    {
        return debtLabelName;
    }
    public void setDebtMemberId(Long debtMemberId) 
    {
        this.debtMemberId = debtMemberId;
    }

    public Long getDebtMemberId() 
    {
        return debtMemberId;
    }
    public void setDebtMemberName(String debtMemberName) 
    {
        this.debtMemberName = debtMemberName;
    }

    public String getDebtMemberName() 
    {
        return debtMemberName;
    }
    public void setDebtMoneyId(Long debtMoneyId) 
    {
        this.debtMoneyId = debtMoneyId;
    }

    public Long getDebtMoneyId() 
    {
        return debtMoneyId;
    }
    public void setDebtMoneyName(String debtMoneyName) 
    {
        this.debtMoneyName = debtMoneyName;
    }

    public String getDebtMoneyName() 
    {
        return debtMoneyName;
    }
    public void setDebtProjectId(Long debtProjectId) 
    {
        this.debtProjectId = debtProjectId;
    }

    public Long getDebtProjectId() 
    {
        return debtProjectId;
    }
    public void setDebtProjectName(String debtProjectName) 
    {
        this.debtProjectName = debtProjectName;
    }

    public String getDebtProjectName() 
    {
        return debtProjectName;
    }
    public void setDebtWeatherId(Long debtWeatherId) 
    {
        this.debtWeatherId = debtWeatherId;
    }

    public Long getDebtWeatherId() 
    {
        return debtWeatherId;
    }
    public void setDebtWeatherName(String debtWeatherName) 
    {
        this.debtWeatherName = debtWeatherName;
    }

    public String getDebtWeatherName() 
    {
        return debtWeatherName;
    }
    public void setDebtAddress(String debtAddress) 
    {
        this.debtAddress = debtAddress;
    }

    public String getDebtAddress() 
    {
        return debtAddress;
    }
    public void setDebtCity(String debtCity) 
    {
        this.debtCity = debtCity;
    }

    public String getDebtCity() 
    {
        return debtCity;
    }
    public void setDebtCountry(String debtCountry) 
    {
        this.debtCountry = debtCountry;
    }

    public String getDebtCountry() 
    {
        return debtCountry;
    }
    public void setDebtProvince(String debtProvince) 
    {
        this.debtProvince = debtProvince;
    }

    public String getDebtProvince() 
    {
        return debtProvince;
    }
    public void setDebtCounty(String debtCounty) 
    {
        this.debtCounty = debtCounty;
    }

    public String getDebtCounty() 
    {
        return debtCounty;
    }
    public void setDebtStreet(String debtStreet) 
    {
        this.debtStreet = debtStreet;
    }

    public String getDebtStreet() 
    {
        return debtStreet;
    }
    public void setDebtYear(String debtYear) 
    {
        this.debtYear = debtYear;
    }

    public String getDebtYear() 
    {
        return debtYear;
    }
    public void setDebtDate(String debtDate) 
    {
        this.debtDate = debtDate;
    }

    public String getDebtDate() 
    {
        return debtDate;
    }
    public void setDebtDay(String debtDay) 
    {
        this.debtDay = debtDay;
    }

    public String getDebtDay() 
    {
        return debtDay;
    }
    public void setDebtMonth(String debtMonth) 
    {
        this.debtMonth = debtMonth;
    }

    public String getDebtMonth() 
    {
        return debtMonth;
    }
    public void setDebtPeriod(String debtPeriod) 
    {
        this.debtPeriod = debtPeriod;
    }

    public String getDebtPeriod() 
    {
        return debtPeriod;
    }
    public void setDebtQuarter(String debtQuarter) 
    {
        this.debtQuarter = debtQuarter;
    }

    public String getDebtQuarter() 
    {
        return debtQuarter;
    }
    public void setDebtWeek(String debtWeek) 
    {
        this.debtWeek = debtWeek;
    }

    public String getDebtWeek() 
    {
        return debtWeek;
    }
    public void setDebtYearWeek(String debtYearWeek) 
    {
        this.debtYearWeek = debtYearWeek;
    }

    public String getDebtYearWeek() 
    {
        return debtYearWeek;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("debtId", getDebtId())
            .append("debtName", getDebtName())
            .append("debtType", getDebtType())
            .append("debtImgs", getDebtImgs())
            .append("debtDesc", getDebtDesc())
            .append("debtDatetime", getDebtDatetime())
            .append("debtAmount", getDebtAmount())
            .append("enableStatus", getEnableStatus())
            .append("debtParentId", getDebtParentId())
            .append("createBy", getCreateBy())
            .append("debtParentName", getDebtParentName())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("icon", getIcon())
            .append("isDeleted", getIsDeleted())
            .append("orderSort", getOrderSort())
            .append("weight", getWeight())
            .append("debtUserId", getDebtUserId())
            .append("debtUserName", getDebtUserName())
            .append("debtAccountId", getDebtAccountId())
            .append("debtAccountName", getDebtAccountName())
            .append("debtBookId", getDebtBookId())
            .append("debtBookName", getDebtBookName())
            .append("debtCategoryId", getDebtCategoryId())
            .append("debtCategoryName", getDebtCategoryName())
            .append("debtCityId", getDebtCityId())
            .append("debtCityName", getDebtCityName())
            .append("debtEmotionId", getDebtEmotionId())
            .append("debtEmotionName", getDebtEmotionName())
            .append("debtEntityId", getDebtEntityId())
            .append("debtEntityName", getDebtEntityName())
            .append("debtLabelId", getDebtLabelId())
            .append("debtLabelName", getDebtLabelName())
            .append("debtMemberId", getDebtMemberId())
            .append("debtMemberName", getDebtMemberName())
            .append("debtMoneyId", getDebtMoneyId())
            .append("debtMoneyName", getDebtMoneyName())
            .append("debtProjectId", getDebtProjectId())
            .append("debtProjectName", getDebtProjectName())
            .append("debtWeatherId", getDebtWeatherId())
            .append("debtWeatherName", getDebtWeatherName())
            .append("debtAddress", getDebtAddress())
            .append("debtCity", getDebtCity())
            .append("debtCountry", getDebtCountry())
            .append("debtProvince", getDebtProvince())
            .append("debtCounty", getDebtCounty())
            .append("debtStreet", getDebtStreet())
            .append("debtYear", getDebtYear())
            .append("debtDate", getDebtDate())
            .append("debtDay", getDebtDay())
            .append("debtMonth", getDebtMonth())
            .append("debtPeriod", getDebtPeriod())
            .append("debtQuarter", getDebtQuarter())
            .append("debtWeek", getDebtWeek())
            .append("debtYearWeek", getDebtYearWeek())
            .toString();
    }
}
