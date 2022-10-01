package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;

/**
 * 成员类型对象 config_member_type
 * // 成员身份类型：本人，家人，同学，朋友，亲戚，老师，校友，房东等
 * @author metacoin
 * @date 2022-09-29
 */
@Table(name="config_member_type")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="member_type_id")
    private Long memberTypeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    @Column(name="member_type_name")
    private String memberTypeName;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="member_type_desc")
    private String memberTypeDesc;

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

    /** 是否可以下拉*/
    @Transient
    private boolean disabled;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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
    public void setMemberTypeDesc(String memberTypeDesc) 
    {
        this.memberTypeDesc = memberTypeDesc;
    }

    public String getMemberTypeDesc() 
    {
        return memberTypeDesc;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("memberTypeId", getMemberTypeId())
            .append("memberTypeName", getMemberTypeName())
            .append("memberTypeDesc", getMemberTypeDesc())
            .append("weight", getWeight())
            .append("remark", getRemark())
            .append("orderSort", getOrderSort())
            .append("icon", getIcon())
            .append("enableStatus", getEnableStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
