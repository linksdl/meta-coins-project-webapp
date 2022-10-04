package com.ruoyi.config.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 分类管理对象 config_category
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public class Category extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long categoryId;

    /** 分类名 */
    @Excel(name = "分类名")
    private String categoryName;

    /** 父ID */
    @Excel(name = "父ID")
    private Long categoryParentId;

    /** 父类名称 */
    @Excel(name = "父类名称")
    private String categoryParentName;

    /** 功能分类 */
    @Excel(name = "功能分类")
    private String categoryClass;

    /** 层次 */
    @Excel(name = "层次")
    private String categoryLevel;

    /** 描述 */
    @Excel(name = "描述")
    private String categoryDesc;

    /** 功能范围 */
    @Excel(name = "功能范围")
    private String categoryScope;

    /** 排序 */
    @Excel(name = "排序")
    private String categorySort;

    /** 功能类型 */
    @Excel(name = "功能类型")
    private String categoryType;

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

    /** 用户名称 */
    private String userName;

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setCategoryParentId(Long categoryParentId) 
    {
        this.categoryParentId = categoryParentId;
    }

    public Long getCategoryParentId() 
    {
        return categoryParentId;
    }
    public void setCategoryParentName(String categoryParentName) 
    {
        this.categoryParentName = categoryParentName;
    }

    public String getCategoryParentName() 
    {
        return categoryParentName;
    }
    public void setCategoryClass(String categoryClass) 
    {
        this.categoryClass = categoryClass;
    }

    public String getCategoryClass() 
    {
        return categoryClass;
    }
    public void setCategoryLevel(String categoryLevel) 
    {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryLevel() 
    {
        return categoryLevel;
    }
    public void setCategoryDesc(String categoryDesc) 
    {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryDesc() 
    {
        return categoryDesc;
    }
    public void setCategoryScope(String categoryScope) 
    {
        this.categoryScope = categoryScope;
    }

    public String getCategoryScope() 
    {
        return categoryScope;
    }
    public void setCategorySort(String categorySort) 
    {
        this.categorySort = categorySort;
    }

    public String getCategorySort() 
    {
        return categorySort;
    }
    public void setCategoryType(String categoryType) 
    {
        this.categoryType = categoryType;
    }

    public String getCategoryType() 
    {
        return categoryType;
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
            .append("categoryId", getCategoryId())
            .append("categoryName", getCategoryName())
            .append("categoryParentId", getCategoryParentId())
            .append("categoryParentName", getCategoryParentName())
            .append("categoryClass", getCategoryClass())
            .append("categoryLevel", getCategoryLevel())
            .append("categoryDesc", getCategoryDesc())
            .append("categoryScope", getCategoryScope())
            .append("categorySort", getCategorySort())
            .append("categoryType", getCategoryType())
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
