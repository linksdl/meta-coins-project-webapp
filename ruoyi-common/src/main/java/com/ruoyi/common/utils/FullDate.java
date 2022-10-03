package com.ruoyi.common.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 时间全量
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullDate {

    private String fullTime;   // 2019-10-09 12:00:00

    private String date;       // 2019-10-09

    private String time;       // 12:00:00

    private String year;       // 2019

    private String quarter;    // 04

    private String month;      // 10

    private String weekOfYear; // 45

    private String dayOfWeek;  // 星期一

    private String day;        // 09

    private String period;     // 清晨

}
