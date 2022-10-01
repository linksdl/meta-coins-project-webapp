package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;

/**
 * 心情管理对象 config_emotion
 * 
 * @author metacoin
 * @date 2022-10-02
 */
@Table(name="config_emotion")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emotion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emotion_id")
    private Long emotionId;

    /** 中文名 */
    @Excel(name = "中文名")
    @Column(name="emotion_cname")
    private String emotionCname;

    /** 英文名 */
    @Excel(name = "英文名")
    @Column(name="emotion_ename")
    private String emotionEname;

    /** 作用范围 */
    @Excel(name = "作用范围")
    @Column(name="emotion_scope")
    private String emotionScope;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="emotion_desc")
    private String emotionDesc;

    /** 权重 */
    @Excel(name = "权重")
    @Column(name="weight")
    private Long weight;

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

    public void setEmotionId(Long emotionId) 
    {
        this.emotionId = emotionId;
    }

    public Long getEmotionId() 
    {
        return emotionId;
    }
    public void setEmotionCname(String emotionCname) 
    {
        this.emotionCname = emotionCname;
    }

    public String getEmotionCname() 
    {
        return emotionCname;
    }
    public void setEmotionEname(String emotionEname) 
    {
        this.emotionEname = emotionEname;
    }

    public String getEmotionEname() 
    {
        return emotionEname;
    }
    public void setEmotionScope(String emotionScope) 
    {
        this.emotionScope = emotionScope;
    }

    public String getEmotionScope() 
    {
        return emotionScope;
    }
    public void setEmotionDesc(String emotionDesc) 
    {
        this.emotionDesc = emotionDesc;
    }

    public String getEmotionDesc() 
    {
        return emotionDesc;
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
            .append("emotionId", getEmotionId())
            .append("emotionCname", getEmotionCname())
            .append("emotionEname", getEmotionEname())
            .append("emotionScope", getEmotionScope())
            .append("emotionDesc", getEmotionDesc())
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
