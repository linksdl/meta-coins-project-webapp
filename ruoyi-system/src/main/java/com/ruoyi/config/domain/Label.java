package com.ruoyi.config.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 标签管理对象 config_label
 * 
 * @author metacoin
 * @date 2022-10-02
 */
public class Label extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="label_id")
    private Long labelId;

    /** 标签名称 */
    @Excel(name = "标签名称")
    @Column(name="label_cname")
    private String labelCname;

    /** 英文名 */
    @Excel(name = "英文名")
    @Column(name="label_ename")
    private String labelEname;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="label_desc")
    private String labelDesc;

    /** 类型 */
    @Excel(name = "类型")
    @Column(name="label_type")
    private String labelType;

    /** 作用范围 */
    @Excel(name = "作用范围")
    @Column(name="label_scope")
    private String labelScope;

    /** 排序 */
    @Excel(name = "排序")
    @Column(name="order_sort")
    private Long orderSort;

    /** 图标 */
    @Excel(name = "图标")
    @Column(name="icon")
    private String icon;

    /** 是否可用 */
    @Excel(name = "是否可用")
    @Column(name="enable_status")
    private Long enableStatus;

    /** 权重 */
    @Excel(name = "权重")
    @Column(name="weight")
    private Long weight;

    /** 账本ID */
    @Column(name="book_id")
    private Long bookId;

    /** 账本名称 */
    @Column(name="book_name")
    private String bookName;

    /** 用户ID */
    @Column(name="user_id")
    private Long userId;

    /** 用户名称 */
    @Column(name="user_name")
    private String userName;

    public void setLabelId(Long labelId) 
    {
        this.labelId = labelId;
    }

    public Long getLabelId() 
    {
        return labelId;
    }
    public void setLabelCname(String labelCname) 
    {
        this.labelCname = labelCname;
    }

    public String getLabelCname() 
    {
        return labelCname;
    }
    public void setLabelEname(String labelEname) 
    {
        this.labelEname = labelEname;
    }

    public String getLabelEname() 
    {
        return labelEname;
    }
    public void setLabelDesc(String labelDesc) 
    {
        this.labelDesc = labelDesc;
    }

    public String getLabelDesc() 
    {
        return labelDesc;
    }
    public void setLabelType(String labelType) 
    {
        this.labelType = labelType;
    }

    public String getLabelType() 
    {
        return labelType;
    }
    public void setLabelScope(String labelScope) 
    {
        this.labelScope = labelScope;
    }

    public String getLabelScope() 
    {
        return labelScope;
    }
    public void setOrderSort(Long orderSort) 
    {
        this.orderSort = orderSort;
    }

    public Long getOrderSort() 
    {
        return orderSort;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setEnableStatus(Long enableStatus) 
    {
        this.enableStatus = enableStatus;
    }

    public Long getEnableStatus() 
    {
        return enableStatus;
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
            .append("labelId", getLabelId())
            .append("labelCname", getLabelCname())
            .append("labelEname", getLabelEname())
            .append("labelDesc", getLabelDesc())
            .append("labelType", getLabelType())
            .append("labelScope", getLabelScope())
            .append("remark", getRemark())
            .append("orderSort", getOrderSort())
            .append("icon", getIcon())
            .append("enableStatus", getEnableStatus())
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
