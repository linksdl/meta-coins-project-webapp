package com.ruoyi.bill.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 转账账单对象 bill_transfer
 * 
 * @author metacoin
 * @date 2022-10-01
 */
public class TransferBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 转账ID */
    private Long transferId;

    /** 转账名称 */
    @Excel(name = "转账名称")
    private String transferName;

    /** 转账类型 */
    @Excel(name = "转账类型")
    private String transferType;

    /** 转账金额 */
    @Excel(name = "转账金额")
    private Long transferAmount;

    /** 描述 */
    @Excel(name = "描述")
    private String transferDesc;

    /** 凭证 */
    @Excel(name = "凭证")
    private String transferImgs;

    /** 转账日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转账日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transferDatetime;

    /** 父类ID */
    private Long transferParentId;

    /** 父类名 */
    @Excel(name = "父类名")
    private String transferParentName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderSort;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private Long enableStatus;

    /** 图标 */
    private String icon;

    /** 权重 */
    @Excel(name = "权重")
    private Long weight;

    /** 用户ID */
    private Long transferUserId;

    /** 用户名 */
    private String transferUserName;

    /** 账本ID */
    private Long transferBookId;

    /** 账本名 */
    @Excel(name = "账本名")
    private String transferBookName;

    /** 分类ID */
    private Long transferCategoryId;

    /** 分类名 */
    @Excel(name = "分类名")
    private String transferCategoryName;

    /** 实体ID */
    private Long transferEntityId;

    /** 实体名 */
    @Excel(name = "实体名")
    private String transferEntityName;

    /** 账户ID */
    private Long transferAccountId;

    /** 账户名 */
    @Excel(name = "账户名")
    private String transferAccountName;

    /** 城市ID */
    private Long transferCityId;

    /** 城市名 */
    @Excel(name = "城市名")
    private String transferCityName;

    /** 心情ID */
    private Long transferEmotionId;

    /** 心情名 */
    @Excel(name = "心情名")
    private String transferEmotionName;

    /** 标签ID */
    private Long transferLabelId;

    /** 标签名 */
    @Excel(name = "标签名")
    private String transferLabelName;

    /** 成员名 */
    @Excel(name = "成员名")
    private String transferMemberName;

    /** 成员ID */
    private Long transferMemberId;

    /** 币种名 */
    @Excel(name = "币种名")
    private String transferMoneyName;

    /** 币种ID */
    private Long transferMoneyId;

    /** 项目ID */
    private Long transferProjectId;

    /** 项目名 */
    @Excel(name = "项目名")
    private String transferProjectName;

    /** 天气ID */
    private Long transferWeatherId;

    /** 天气名 */
    @Excel(name = "天气名")
    private String transferWeatherName;

    /** 地址 */
    private String transferAddress;

    /** 城市 */
    private String transferCity;

    /** 国家 */
    private String transferCountry;

    /** 镇街道 */
    private String transferStreet;

    /** 郡市区县 */
    private String transferCounty;

    /** 省州 */
    private String transferProvince;

    /** 日期 */
    private String transferDate;

    /** 年份 */
    private String transferYear;

    /** 季度 */
    private String transferQuarter;

    /** 月份 */
    private String transferMonth;

    /** 区间 */
    private String transferPeriod;

    /** 星期 */
    private String transferWeek;

    /** 周数 */
    private String transferYearWeek;

    /** 天 */
    private String transferDay;

    public void setTransferId(Long transferId) 
    {
        this.transferId = transferId;
    }

    public Long getTransferId() 
    {
        return transferId;
    }
    public void setTransferName(String transferName) 
    {
        this.transferName = transferName;
    }

    public String getTransferName() 
    {
        return transferName;
    }
    public void setTransferType(String transferType) 
    {
        this.transferType = transferType;
    }

    public String getTransferType() 
    {
        return transferType;
    }
    public void setTransferAmount(Long transferAmount) 
    {
        this.transferAmount = transferAmount;
    }

    public Long getTransferAmount() 
    {
        return transferAmount;
    }
    public void setTransferDesc(String transferDesc) 
    {
        this.transferDesc = transferDesc;
    }

    public String getTransferDesc() 
    {
        return transferDesc;
    }
    public void setTransferImgs(String transferImgs) 
    {
        this.transferImgs = transferImgs;
    }

    public String getTransferImgs() 
    {
        return transferImgs;
    }
    public void setTransferDatetime(Date transferDatetime) 
    {
        this.transferDatetime = transferDatetime;
    }

    public Date getTransferDatetime() 
    {
        return transferDatetime;
    }
    public void setTransferParentId(Long transferParentId) 
    {
        this.transferParentId = transferParentId;
    }

    public Long getTransferParentId() 
    {
        return transferParentId;
    }
    public void setTransferParentName(String transferParentName) 
    {
        this.transferParentName = transferParentName;
    }

    public String getTransferParentName() 
    {
        return transferParentName;
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
    public void setTransferUserId(Long transferUserId) 
    {
        this.transferUserId = transferUserId;
    }

    public Long getTransferUserId() 
    {
        return transferUserId;
    }
    public void setTransferUserName(String transferUserName) 
    {
        this.transferUserName = transferUserName;
    }

    public String getTransferUserName() 
    {
        return transferUserName;
    }
    public void setTransferBookId(Long transferBookId) 
    {
        this.transferBookId = transferBookId;
    }

    public Long getTransferBookId() 
    {
        return transferBookId;
    }
    public void setTransferBookName(String transferBookName) 
    {
        this.transferBookName = transferBookName;
    }

    public String getTransferBookName() 
    {
        return transferBookName;
    }
    public void setTransferCategoryId(Long transferCategoryId) 
    {
        this.transferCategoryId = transferCategoryId;
    }

    public Long getTransferCategoryId() 
    {
        return transferCategoryId;
    }
    public void setTransferCategoryName(String transferCategoryName) 
    {
        this.transferCategoryName = transferCategoryName;
    }

    public String getTransferCategoryName() 
    {
        return transferCategoryName;
    }
    public void setTransferEntityId(Long transferEntityId) 
    {
        this.transferEntityId = transferEntityId;
    }

    public Long getTransferEntityId() 
    {
        return transferEntityId;
    }
    public void setTransferEntityName(String transferEntityName) 
    {
        this.transferEntityName = transferEntityName;
    }

    public String getTransferEntityName() 
    {
        return transferEntityName;
    }
    public void setTransferAccountId(Long transferAccountId) 
    {
        this.transferAccountId = transferAccountId;
    }

    public Long getTransferAccountId() 
    {
        return transferAccountId;
    }
    public void setTransferAccountName(String transferAccountName) 
    {
        this.transferAccountName = transferAccountName;
    }

    public String getTransferAccountName() 
    {
        return transferAccountName;
    }
    public void setTransferCityId(Long transferCityId) 
    {
        this.transferCityId = transferCityId;
    }

    public Long getTransferCityId() 
    {
        return transferCityId;
    }
    public void setTransferCityName(String transferCityName) 
    {
        this.transferCityName = transferCityName;
    }

    public String getTransferCityName() 
    {
        return transferCityName;
    }
    public void setTransferEmotionId(Long transferEmotionId) 
    {
        this.transferEmotionId = transferEmotionId;
    }

    public Long getTransferEmotionId() 
    {
        return transferEmotionId;
    }
    public void setTransferEmotionName(String transferEmotionName) 
    {
        this.transferEmotionName = transferEmotionName;
    }

    public String getTransferEmotionName() 
    {
        return transferEmotionName;
    }
    public void setTransferLabelId(Long transferLabelId) 
    {
        this.transferLabelId = transferLabelId;
    }

    public Long getTransferLabelId() 
    {
        return transferLabelId;
    }
    public void setTransferLabelName(String transferLabelName) 
    {
        this.transferLabelName = transferLabelName;
    }

    public String getTransferLabelName() 
    {
        return transferLabelName;
    }
    public void setTransferMemberName(String transferMemberName) 
    {
        this.transferMemberName = transferMemberName;
    }

    public String getTransferMemberName() 
    {
        return transferMemberName;
    }
    public void setTransferMemberId(Long transferMemberId) 
    {
        this.transferMemberId = transferMemberId;
    }

    public Long getTransferMemberId() 
    {
        return transferMemberId;
    }
    public void setTransferMoneyName(String transferMoneyName) 
    {
        this.transferMoneyName = transferMoneyName;
    }

    public String getTransferMoneyName() 
    {
        return transferMoneyName;
    }
    public void setTransferMoneyId(Long transferMoneyId) 
    {
        this.transferMoneyId = transferMoneyId;
    }

    public Long getTransferMoneyId() 
    {
        return transferMoneyId;
    }
    public void setTransferProjectId(Long transferProjectId) 
    {
        this.transferProjectId = transferProjectId;
    }

    public Long getTransferProjectId() 
    {
        return transferProjectId;
    }
    public void setTransferProjectName(String transferProjectName) 
    {
        this.transferProjectName = transferProjectName;
    }

    public String getTransferProjectName() 
    {
        return transferProjectName;
    }
    public void setTransferWeatherId(Long transferWeatherId) 
    {
        this.transferWeatherId = transferWeatherId;
    }

    public Long getTransferWeatherId() 
    {
        return transferWeatherId;
    }
    public void setTransferWeatherName(String transferWeatherName) 
    {
        this.transferWeatherName = transferWeatherName;
    }

    public String getTransferWeatherName() 
    {
        return transferWeatherName;
    }
    public void setTransferAddress(String transferAddress) 
    {
        this.transferAddress = transferAddress;
    }

    public String getTransferAddress() 
    {
        return transferAddress;
    }
    public void setTransferCity(String transferCity) 
    {
        this.transferCity = transferCity;
    }

    public String getTransferCity() 
    {
        return transferCity;
    }
    public void setTransferCountry(String transferCountry) 
    {
        this.transferCountry = transferCountry;
    }

    public String getTransferCountry() 
    {
        return transferCountry;
    }
    public void setTransferStreet(String transferStreet) 
    {
        this.transferStreet = transferStreet;
    }

    public String getTransferStreet() 
    {
        return transferStreet;
    }
    public void setTransferCounty(String transferCounty) 
    {
        this.transferCounty = transferCounty;
    }

    public String getTransferCounty() 
    {
        return transferCounty;
    }
    public void setTransferProvince(String transferProvince) 
    {
        this.transferProvince = transferProvince;
    }

    public String getTransferProvince() 
    {
        return transferProvince;
    }
    public void setTransferDate(String transferDate) 
    {
        this.transferDate = transferDate;
    }

    public String getTransferDate() 
    {
        return transferDate;
    }
    public void setTransferYear(String transferYear) 
    {
        this.transferYear = transferYear;
    }

    public String getTransferYear() 
    {
        return transferYear;
    }
    public void setTransferQuarter(String transferQuarter) 
    {
        this.transferQuarter = transferQuarter;
    }

    public String getTransferQuarter() 
    {
        return transferQuarter;
    }
    public void setTransferMonth(String transferMonth) 
    {
        this.transferMonth = transferMonth;
    }

    public String getTransferMonth() 
    {
        return transferMonth;
    }
    public void setTransferPeriod(String transferPeriod) 
    {
        this.transferPeriod = transferPeriod;
    }

    public String getTransferPeriod() 
    {
        return transferPeriod;
    }
    public void setTransferWeek(String transferWeek) 
    {
        this.transferWeek = transferWeek;
    }

    public String getTransferWeek() 
    {
        return transferWeek;
    }
    public void setTransferYearWeek(String transferYearWeek) 
    {
        this.transferYearWeek = transferYearWeek;
    }

    public String getTransferYearWeek() 
    {
        return transferYearWeek;
    }
    public void setTransferDay(String transferDay) 
    {
        this.transferDay = transferDay;
    }

    public String getTransferDay() 
    {
        return transferDay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("transferId", getTransferId())
            .append("transferName", getTransferName())
            .append("transferType", getTransferType())
            .append("transferAmount", getTransferAmount())
            .append("transferDesc", getTransferDesc())
            .append("transferImgs", getTransferImgs())
            .append("transferDatetime", getTransferDatetime())
            .append("transferParentId", getTransferParentId())
            .append("transferParentName", getTransferParentName())
            .append("isDeleted", getIsDeleted())
            .append("orderSort", getOrderSort())
            .append("enableStatus", getEnableStatus())
            .append("icon", getIcon())
            .append("remark", getRemark())
            .append("weight", getWeight())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("transferUserId", getTransferUserId())
            .append("transferUserName", getTransferUserName())
            .append("transferBookId", getTransferBookId())
            .append("transferBookName", getTransferBookName())
            .append("transferCategoryId", getTransferCategoryId())
            .append("transferCategoryName", getTransferCategoryName())
            .append("transferEntityId", getTransferEntityId())
            .append("transferEntityName", getTransferEntityName())
            .append("transferAccountId", getTransferAccountId())
            .append("transferAccountName", getTransferAccountName())
            .append("transferCityId", getTransferCityId())
            .append("transferCityName", getTransferCityName())
            .append("transferEmotionId", getTransferEmotionId())
            .append("transferEmotionName", getTransferEmotionName())
            .append("transferLabelId", getTransferLabelId())
            .append("transferLabelName", getTransferLabelName())
            .append("transferMemberName", getTransferMemberName())
            .append("transferMemberId", getTransferMemberId())
            .append("transferMoneyName", getTransferMoneyName())
            .append("transferMoneyId", getTransferMoneyId())
            .append("transferProjectId", getTransferProjectId())
            .append("transferProjectName", getTransferProjectName())
            .append("transferWeatherId", getTransferWeatherId())
            .append("transferWeatherName", getTransferWeatherName())
            .append("transferAddress", getTransferAddress())
            .append("transferCity", getTransferCity())
            .append("transferCountry", getTransferCountry())
            .append("transferStreet", getTransferStreet())
            .append("transferCounty", getTransferCounty())
            .append("transferProvince", getTransferProvince())
            .append("transferDate", getTransferDate())
            .append("transferYear", getTransferYear())
            .append("transferQuarter", getTransferQuarter())
            .append("transferMonth", getTransferMonth())
            .append("transferPeriod", getTransferPeriod())
            .append("transferWeek", getTransferWeek())
            .append("transferYearWeek", getTransferYearWeek())
            .append("transferDay", getTransferDay())
            .toString();
    }
}
