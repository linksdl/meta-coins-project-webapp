package com.ruoyi.bill.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 支出账单对象 bill_consume
 * 
 * @author metacoin
 * @date 2022-10-01
 */
public class ConsumeBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消费ID */
    private Long consumeId;

    /** 消费名 */
    @Excel(name = "消费名")
    private String consumeName;

    /** 消费类型 */
    @Excel(name = "消费类型")
    private String consumeType;

    /** 消费时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "消费时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date consumeDatetime;

    /** 消费金额 */
    @Excel(name = "消费金额")
    private Long consumeAmount;

    /** 描述 */
    @Excel(name = "描述")
    private String consumeDesc;

    /** 凭证 */
    @Excel(name = "凭证")
    private String consumeImgs;

    /** 父类ID */
    private Long consumeParentId;

    /** 父类名 */
    @Excel(name = "父类名")
    private String consumeParentName;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private Long enableStatus;

    /** 图标 */
    private String icon;

    /** 权重 */
    private Long weight;

    /** 是否删除 */
    private Integer isDeleted;

    /** 排序 */
    private Long orderSort;

    /** 用户ID */
    private Long consumeUserId;

    /** 用户名 */
    private String consumeUserName;

    /** 天气ID */
    private Long consumeWeatherId;

    /** 天气名 */
    @Excel(name = "天气名")
    private String consumeWeatherName;

    /** 项目ID */
    private Long consumeProjectId;

    /** 项目名 */
    @Excel(name = "项目名")
    private String consumeProjectName;

    /** 标签ID */
    private Long consumeLabelId;

    /** 标签名 */
    @Excel(name = "标签名")
    private String consumeLabelName;

    /** 成员ID */
    private Long consumeMemberId;

    /** 成员名 */
    @Excel(name = "成员名")
    private String consumeMemberName;

    /** 币种ID */
    private Long consumeMoneyId;

    /** 币种名 */
    @Excel(name = "币种名")
    private String consumeMoneyName;

    /** 实体ID */
    private Long consumeEntityId;

    /** 实体名 */
    @Excel(name = "实体名")
    private String consumeEntityName;

    /** 分类ID */
    private Long consumeCategoryId;

    /** 分类名 */
    @Excel(name = "分类名")
    private String consumeCategoryName;

    /** 账户ID */
    private Long consumeAccountId;

    /** 账户名 */
    @Excel(name = "账户名")
    private String consumeAccountName;

    /** 账本ID */
    private Long consumeBookId;

    /** 账本名 */
    private String consumeBookName;

    /** 郡市区县 */
    private String consumeCounty;

    /** 省州 */
    private String consumeProvince;

    /** 城市 */
    private String consumeCity;

    /** 国家 */
    private String consumeCountry;

    /** 地址 */
    private String consumeAddress;

    /** 镇街道 */
    private String consumeStreet;

    /** 日期 */
    private String consumeDate;

    /** 年份 */
    private String consumeYear;

    /** 季度 */
    private String consumeQuarter;

    /** 月份 */
    private String consumeMonth;

    /** 星期 */
    private String consumeWeek;

    /** 天 */
    private String consumeDay;

    /** 区间 */
    private String consumePeriod;

    /** 周 */
    private String consumeYearWeek;

    public void setConsumeId(Long consumeId) 
    {
        this.consumeId = consumeId;
    }

    public Long getConsumeId() 
    {
        return consumeId;
    }
    public void setConsumeName(String consumeName) 
    {
        this.consumeName = consumeName;
    }

    public String getConsumeName() 
    {
        return consumeName;
    }
    public void setConsumeType(String consumeType) 
    {
        this.consumeType = consumeType;
    }

    public String getConsumeType() 
    {
        return consumeType;
    }
    public void setConsumeDatetime(Date consumeDatetime) 
    {
        this.consumeDatetime = consumeDatetime;
    }

    public Date getConsumeDatetime() 
    {
        return consumeDatetime;
    }
    public void setConsumeAmount(Long consumeAmount) 
    {
        this.consumeAmount = consumeAmount;
    }

    public Long getConsumeAmount() 
    {
        return consumeAmount;
    }
    public void setConsumeDesc(String consumeDesc) 
    {
        this.consumeDesc = consumeDesc;
    }

    public String getConsumeDesc() 
    {
        return consumeDesc;
    }
    public void setConsumeImgs(String consumeImgs) 
    {
        this.consumeImgs = consumeImgs;
    }

    public String getConsumeImgs() 
    {
        return consumeImgs;
    }
    public void setConsumeParentId(Long consumeParentId) 
    {
        this.consumeParentId = consumeParentId;
    }

    public Long getConsumeParentId() 
    {
        return consumeParentId;
    }
    public void setConsumeParentName(String consumeParentName) 
    {
        this.consumeParentName = consumeParentName;
    }

    public String getConsumeParentName() 
    {
        return consumeParentName;
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
    public void setConsumeUserId(Long consumeUserId) 
    {
        this.consumeUserId = consumeUserId;
    }

    public Long getConsumeUserId() 
    {
        return consumeUserId;
    }
    public void setConsumeUserName(String consumeUserName) 
    {
        this.consumeUserName = consumeUserName;
    }

    public String getConsumeUserName() 
    {
        return consumeUserName;
    }
    public void setConsumeWeatherId(Long consumeWeatherId) 
    {
        this.consumeWeatherId = consumeWeatherId;
    }

    public Long getConsumeWeatherId() 
    {
        return consumeWeatherId;
    }
    public void setConsumeWeatherName(String consumeWeatherName) 
    {
        this.consumeWeatherName = consumeWeatherName;
    }

    public String getConsumeWeatherName() 
    {
        return consumeWeatherName;
    }
    public void setConsumeProjectId(Long consumeProjectId) 
    {
        this.consumeProjectId = consumeProjectId;
    }

    public Long getConsumeProjectId() 
    {
        return consumeProjectId;
    }
    public void setConsumeProjectName(String consumeProjectName) 
    {
        this.consumeProjectName = consumeProjectName;
    }

    public String getConsumeProjectName() 
    {
        return consumeProjectName;
    }
    public void setConsumeLabelId(Long consumeLabelId) 
    {
        this.consumeLabelId = consumeLabelId;
    }

    public Long getConsumeLabelId() 
    {
        return consumeLabelId;
    }
    public void setConsumeLabelName(String consumeLabelName) 
    {
        this.consumeLabelName = consumeLabelName;
    }

    public String getConsumeLabelName() 
    {
        return consumeLabelName;
    }
    public void setConsumeMemberId(Long consumeMemberId) 
    {
        this.consumeMemberId = consumeMemberId;
    }

    public Long getConsumeMemberId() 
    {
        return consumeMemberId;
    }
    public void setConsumeMemberName(String consumeMemberName) 
    {
        this.consumeMemberName = consumeMemberName;
    }

    public String getConsumeMemberName() 
    {
        return consumeMemberName;
    }
    public void setConsumeMoneyId(Long consumeMoneyId) 
    {
        this.consumeMoneyId = consumeMoneyId;
    }

    public Long getConsumeMoneyId() 
    {
        return consumeMoneyId;
    }
    public void setConsumeMoneyName(String consumeMoneyName) 
    {
        this.consumeMoneyName = consumeMoneyName;
    }

    public String getConsumeMoneyName() 
    {
        return consumeMoneyName;
    }
    public void setConsumeEntityId(Long consumeEntityId) 
    {
        this.consumeEntityId = consumeEntityId;
    }

    public Long getConsumeEntityId() 
    {
        return consumeEntityId;
    }
    public void setConsumeEntityName(String consumeEntityName) 
    {
        this.consumeEntityName = consumeEntityName;
    }

    public String getConsumeEntityName() 
    {
        return consumeEntityName;
    }
    public void setConsumeCategoryId(Long consumeCategoryId) 
    {
        this.consumeCategoryId = consumeCategoryId;
    }

    public Long getConsumeCategoryId() 
    {
        return consumeCategoryId;
    }
    public void setConsumeCategoryName(String consumeCategoryName) 
    {
        this.consumeCategoryName = consumeCategoryName;
    }

    public String getConsumeCategoryName() 
    {
        return consumeCategoryName;
    }
    public void setConsumeAccountId(Long consumeAccountId) 
    {
        this.consumeAccountId = consumeAccountId;
    }

    public Long getConsumeAccountId() 
    {
        return consumeAccountId;
    }
    public void setConsumeAccountName(String consumeAccountName) 
    {
        this.consumeAccountName = consumeAccountName;
    }

    public String getConsumeAccountName() 
    {
        return consumeAccountName;
    }
    public void setConsumeBookId(Long consumeBookId) 
    {
        this.consumeBookId = consumeBookId;
    }

    public Long getConsumeBookId() 
    {
        return consumeBookId;
    }
    public void setConsumeBookName(String consumeBookName) 
    {
        this.consumeBookName = consumeBookName;
    }

    public String getConsumeBookName() 
    {
        return consumeBookName;
    }
    public void setConsumeCounty(String consumeCounty) 
    {
        this.consumeCounty = consumeCounty;
    }

    public String getConsumeCounty() 
    {
        return consumeCounty;
    }
    public void setConsumeProvince(String consumeProvince) 
    {
        this.consumeProvince = consumeProvince;
    }

    public String getConsumeProvince() 
    {
        return consumeProvince;
    }
    public void setConsumeCity(String consumeCity) 
    {
        this.consumeCity = consumeCity;
    }

    public String getConsumeCity() 
    {
        return consumeCity;
    }
    public void setConsumeCountry(String consumeCountry) 
    {
        this.consumeCountry = consumeCountry;
    }

    public String getConsumeCountry() 
    {
        return consumeCountry;
    }
    public void setConsumeAddress(String consumeAddress) 
    {
        this.consumeAddress = consumeAddress;
    }

    public String getConsumeAddress() 
    {
        return consumeAddress;
    }
    public void setConsumeStreet(String consumeStreet) 
    {
        this.consumeStreet = consumeStreet;
    }

    public String getConsumeStreet() 
    {
        return consumeStreet;
    }
    public void setConsumeDate(String consumeDate) 
    {
        this.consumeDate = consumeDate;
    }

    public String getConsumeDate() 
    {
        return consumeDate;
    }
    public void setConsumeYear(String consumeYear) 
    {
        this.consumeYear = consumeYear;
    }

    public String getConsumeYear() 
    {
        return consumeYear;
    }
    public void setConsumeQuarter(String consumeQuarter) 
    {
        this.consumeQuarter = consumeQuarter;
    }

    public String getConsumeQuarter() 
    {
        return consumeQuarter;
    }
    public void setConsumeMonth(String consumeMonth) 
    {
        this.consumeMonth = consumeMonth;
    }

    public String getConsumeMonth() 
    {
        return consumeMonth;
    }
    public void setConsumeWeek(String consumeWeek) 
    {
        this.consumeWeek = consumeWeek;
    }

    public String getConsumeWeek() 
    {
        return consumeWeek;
    }
    public void setConsumeDay(String consumeDay) 
    {
        this.consumeDay = consumeDay;
    }

    public String getConsumeDay() 
    {
        return consumeDay;
    }
    public void setConsumePeriod(String consumePeriod) 
    {
        this.consumePeriod = consumePeriod;
    }

    public String getConsumePeriod() 
    {
        return consumePeriod;
    }
    public void setConsumeYearWeek(String consumeYearWeek) 
    {
        this.consumeYearWeek = consumeYearWeek;
    }

    public String getConsumeYearWeek() 
    {
        return consumeYearWeek;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("consumeId", getConsumeId())
            .append("consumeName", getConsumeName())
            .append("consumeType", getConsumeType())
            .append("consumeDatetime", getConsumeDatetime())
            .append("consumeAmount", getConsumeAmount())
            .append("consumeDesc", getConsumeDesc())
            .append("consumeImgs", getConsumeImgs())
            .append("consumeParentId", getConsumeParentId())
            .append("consumeParentName", getConsumeParentName())
            .append("enableStatus", getEnableStatus())
            .append("icon", getIcon())
            .append("remark", getRemark())
            .append("weight", getWeight())
            .append("isDeleted", getIsDeleted())
            .append("orderSort", getOrderSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("consumeUserId", getConsumeUserId())
            .append("consumeUserName", getConsumeUserName())
            .append("consumeWeatherId", getConsumeWeatherId())
            .append("consumeWeatherName", getConsumeWeatherName())
            .append("consumeProjectId", getConsumeProjectId())
            .append("consumeProjectName", getConsumeProjectName())
            .append("consumeLabelId", getConsumeLabelId())
            .append("consumeLabelName", getConsumeLabelName())
            .append("consumeMemberId", getConsumeMemberId())
            .append("consumeMemberName", getConsumeMemberName())
            .append("consumeMoneyId", getConsumeMoneyId())
            .append("consumeMoneyName", getConsumeMoneyName())
            .append("consumeEntityId", getConsumeEntityId())
            .append("consumeEntityName", getConsumeEntityName())
            .append("consumeCategoryId", getConsumeCategoryId())
            .append("consumeCategoryName", getConsumeCategoryName())
            .append("consumeAccountId", getConsumeAccountId())
            .append("consumeAccountName", getConsumeAccountName())
            .append("consumeBookId", getConsumeBookId())
            .append("consumeBookName", getConsumeBookName())
            .append("consumeCounty", getConsumeCounty())
            .append("consumeProvince", getConsumeProvince())
            .append("consumeCity", getConsumeCity())
            .append("consumeCountry", getConsumeCountry())
            .append("consumeAddress", getConsumeAddress())
            .append("consumeStreet", getConsumeStreet())
            .append("consumeDate", getConsumeDate())
            .append("consumeYear", getConsumeYear())
            .append("consumeQuarter", getConsumeQuarter())
            .append("consumeMonth", getConsumeMonth())
            .append("consumeWeek", getConsumeWeek())
            .append("consumeDay", getConsumeDay())
            .append("consumePeriod", getConsumePeriod())
            .append("consumeYearWeek", getConsumeYearWeek())
            .toString();
    }
}
