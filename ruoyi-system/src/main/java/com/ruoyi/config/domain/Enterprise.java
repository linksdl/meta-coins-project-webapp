package com.ruoyi.config.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商家管理对象 config_enterprise
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public class Enterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long enterpriseId;

    /** 商家名称 */
    @Excel(name = "商家名称")
    private String enterpriseName;

    /** 商家地址 */
    @Excel(name = "商家地址")
    private String enterpriseAddress;

    /** 描述 */
    @Excel(name = "描述")
    private String enterpriseDesc;

    /** 图片 */
    @Excel(name = "图片")
    private String enterpriseImgs;

    /** 地图地址 */
    @Excel(name = "地图地址")
    private String enterpriseMapLocation;

    /** 功能范围 */
    @Excel(name = "功能范围")
    private String enterpriseScope;

    /** 类型ID */
    @Excel(name = "类型ID")
    private Long enterpriseTypeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String enterpriseTypeName;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private Long enableStatus;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderSort;

    /** 权重 */
    @Excel(name = "权重")
    private Long weight;

    /** 账本ID */
    private Long bookId;

    /** 账本名称 */
    private String bookName;

    /** 用户ID */
    private Long userId;

    /** 用户名 */
    private String userName;

    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
    }
    public void setEnterpriseName(String enterpriseName) 
    {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() 
    {
        return enterpriseName;
    }
    public void setEnterpriseAddress(String enterpriseAddress) 
    {
        this.enterpriseAddress = enterpriseAddress;
    }

    public String getEnterpriseAddress() 
    {
        return enterpriseAddress;
    }
    public void setEnterpriseDesc(String enterpriseDesc) 
    {
        this.enterpriseDesc = enterpriseDesc;
    }

    public String getEnterpriseDesc() 
    {
        return enterpriseDesc;
    }
    public void setEnterpriseImgs(String enterpriseImgs) 
    {
        this.enterpriseImgs = enterpriseImgs;
    }

    public String getEnterpriseImgs() 
    {
        return enterpriseImgs;
    }
    public void setEnterpriseMapLocation(String enterpriseMapLocation) 
    {
        this.enterpriseMapLocation = enterpriseMapLocation;
    }

    public String getEnterpriseMapLocation() 
    {
        return enterpriseMapLocation;
    }
    public void setEnterpriseScope(String enterpriseScope) 
    {
        this.enterpriseScope = enterpriseScope;
    }

    public String getEnterpriseScope() 
    {
        return enterpriseScope;
    }
    public void setEnterpriseTypeId(Long enterpriseTypeId) 
    {
        this.enterpriseTypeId = enterpriseTypeId;
    }

    public Long getEnterpriseTypeId() 
    {
        return enterpriseTypeId;
    }
    public void setEnterpriseTypeName(String enterpriseTypeName) 
    {
        this.enterpriseTypeName = enterpriseTypeName;
    }

    public String getEnterpriseTypeName() 
    {
        return enterpriseTypeName;
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
    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }
    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookName() 
    {
        return bookName;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("enterpriseId", getEnterpriseId())
            .append("enterpriseName", getEnterpriseName())
            .append("enterpriseAddress", getEnterpriseAddress())
            .append("enterpriseDesc", getEnterpriseDesc())
            .append("enterpriseImgs", getEnterpriseImgs())
            .append("enterpriseMapLocation", getEnterpriseMapLocation())
            .append("enterpriseScope", getEnterpriseScope())
            .append("enterpriseTypeId", getEnterpriseTypeId())
            .append("enterpriseTypeName", getEnterpriseTypeName())
            .append("enableStatus", getEnableStatus())
            .append("icon", getIcon())
            .append("orderSort", getOrderSort())
            .append("remark", getRemark())
            .append("weight", getWeight())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("bookId", getBookId())
            .append("bookName", getBookName())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .toString();
    }
}
