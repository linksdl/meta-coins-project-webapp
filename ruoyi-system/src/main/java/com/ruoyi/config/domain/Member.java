package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;
import javax.persistence.Entity;


/**
 * 成员管理对象 config_member
 * 
 * @author metacoin
 * @date 2022-09-29
 */
@Table(name="config_member")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="member_id")
    private Long memberId;

    /** 名称 */
    @Excel(name = "名称")
    @Column(name="member_name")
    private String memberName;

    /** 功能类型 */
    @Excel(name = "功能类型")
    @Column(name="member_type")
    private String memberType;

    /** 功能范围 */
    @Excel(name = "功能范围")
    @Column(name="member_scope")
    private String memberScope;

    /** 成员类型 */
    @Excel(name = "成员类型")
    @Column(name="member_type_id")
    private Long memberTypeId;

    /** 成员类型名称 */
    @Excel(name = "类型名称")
    @Column(name="member_type_name")
    private String memberTypeName;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="member_desc")
    private String memberDesc;

    /** 是否可用 */
    @Excel(name = "是否可用")
    @Column(name="enable_status")
    private Long enableStatus;

    /** 图标 */
    @Excel(name = "图标")
    @Column(name="icon")
    private String icon;

    /** 排序 */
    @Excel(name = "排序")
    @Column(name="order_sort")
    private Long orderSort;

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

    /** 用户名 */
    @Column(name="user_name")
    private String userName;

    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }
    public void setMemberName(String memberName) 
    {
        this.memberName = memberName;
    }

    public String getMemberName() 
    {
        return memberName;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public void setMemberScope(String memberScope)
    {
        this.memberScope = memberScope;
    }

    public String getMemberScope() 
    {
        return memberScope;
    }
    public void setMemberTypeId(Long memberTypeId)
    {
        this.memberTypeId = memberTypeId;
    }

    public Long getMemberTypeId()
    {
        return memberTypeId;
    }
    public void setMemberTypeName(String memberTypeName)
    {
        this.memberTypeName = memberTypeName;
    }

    public String getMemberTypeName()
    {
        return memberTypeName;
    }
    public void setMemberDesc(String memberDesc) 
    {
        this.memberDesc = memberDesc;
    }

    public String getMemberDesc() 
    {
        return memberDesc;
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
            .append("memberId", getMemberId())
            .append("memberName", getMemberName())
            .append("memberType", getMemberType())
            .append("memberScope", getMemberScope())
            .append("memberTypeId", getMemberTypeId())
            .append("memberTypeName", getMemberTypeName())
            .append("memberDesc", getMemberDesc())
            .append("enableStatus", getEnableStatus())
            .append("icon", getIcon())
            .append("orderSort", getOrderSort())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("weight", getWeight())
            .append("bookId", getBookId())
            .append("bookName", getBookName())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .toString();
    }
}
