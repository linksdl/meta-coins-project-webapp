package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.*;

/**
 * 天气管理对象 config_weather
 * 
 * @author metacoin
 * @date 2022-09-30
 */
@Table(name="config_weather")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="weather_id")
    private Long weatherId;

    /** 中文名 */
    @Excel(name = "中文名")
    @Column(name="weather_cname")
    private String weatherCname;

    /** 英文名 */
    @Excel(name = "英文名")
    @Column(name="weather_ename")
    private String weatherEname;

    /** 功能类型 */
    @Excel(name = "功能类型")
    @Column(name="weather_type")
    private String weatherType;

    /** 功能范围 */
    @Excel(name = "功能范围")
    @Column(name="weather_scope")
    private String weatherScope;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="weather_desc")
    private String weatherDesc;

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

    public void setWeatherId(Long weatherId) 
    {
        this.weatherId = weatherId;
    }

    public Long getWeatherId() 
    {
        return weatherId;
    }
    public void setWeatherCname(String weatherCname) 
    {
        this.weatherCname = weatherCname;
    }

    public String getWeatherCname() 
    {
        return weatherCname;
    }
    public void setWeatherEname(String weatherEname) 
    {
        this.weatherEname = weatherEname;
    }

    public String getWeatherEname() 
    {
        return weatherEname;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public void setWeatherScope(String weatherScope)
    {
        this.weatherScope = weatherScope;
    }

    public String getWeatherScope() 
    {
        return weatherScope;
    }
    public void setWeatherDesc(String weatherDesc) 
    {
        this.weatherDesc = weatherDesc;
    }

    public String getWeatherDesc() 
    {
        return weatherDesc;
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
            .append("weatherId", getWeatherId())
            .append("weatherCname", getWeatherCname())
            .append("weatherEname", getWeatherEname())
            .append("weatherType", getWeatherType())
            .append("weatherScope", getWeatherScope())
            .append("weatherDesc", getWeatherDesc())
            .append("weight", getWeight())
            .append("orderSort", getOrderSort())
            .append("icon", getIcon())
            .append("remark", getRemark())
            .append("enableStatus", getEnableStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
