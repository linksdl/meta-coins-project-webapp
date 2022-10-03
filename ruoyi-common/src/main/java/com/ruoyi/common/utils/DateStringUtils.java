package com.ruoyi.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateStringUtils {


    public static FullDate getFullDate(Date dateTime) throws ParseException {

		String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);

		int week_of_year = calendar.get(Calendar.WEEK_OF_YEAR);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
		String weekOfYear = String.valueOf(week_of_year);
		String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(dateTime);
		String year = timeStr.substring(0, 4);
		String quarter = "";
		String month = timeStr.substring(5, 7);
		String day = timeStr.substring(8, 10);
		String period = "";
		String dayOfWeek = "";

		// 季度
		if(month.equalsIgnoreCase("01") || month.equalsIgnoreCase("02") || month.equalsIgnoreCase("03")){
			quarter = "第一季度";
		}

		if(month.equalsIgnoreCase("04") || month.equalsIgnoreCase("05") || month.equalsIgnoreCase("06")){
			quarter = "第二季度";
		}

		if(month.equalsIgnoreCase("07") || month.equalsIgnoreCase("08") || month.equalsIgnoreCase("09")){
			quarter = "第三季度";
		}

		if(month.equalsIgnoreCase("10") || month.equalsIgnoreCase("11") || month.equalsIgnoreCase("12")){
			quarter = "第四季度";
		}

		// 星期

		if (day_of_week == 1){
			dayOfWeek = "星期一";
		}
		if (day_of_week == 2){
			dayOfWeek = "星期二";
		}
		if (day_of_week == 3){
			dayOfWeek = "星期三";
		}
		if (day_of_week == 4){
			dayOfWeek = "星期四";
		}
		if (day_of_week == 5){
			dayOfWeek = "星期五";
		}
		if (day_of_week == 6){
			dayOfWeek = "星期六";
		}
		if (day_of_week == 7){
			dayOfWeek = "星期日";
		}

		// 时间段
		Date time = new SimpleDateFormat("HH:mm:ss").parse(timeStr.substring(11, 18));
		Date gloaming = new SimpleDateFormat("HH:mm:ss").parse("00:00:00");
		Date morning = new SimpleDateFormat("HH:mm:ss").parse("06:00:00");
		Date forenoon = new SimpleDateFormat("HH:mm:ss").parse("09:00:00");
		Date midday = new SimpleDateFormat("HH:mm:ss").parse("12:00:00");
		Date after_noon = new SimpleDateFormat("HH:mm:ss").parse("18:00:00");
		Date night = new SimpleDateFormat("HH:mm:ss").parse("22:00:00");
		Date late = new SimpleDateFormat("HH:mm:ss").parse("24:00:00");

		if (time.equals(morning) || (time.after(gloaming) && time.before(morning))) {
			period = "黎明";
		}

		if (time.equals(morning) || (time.after(morning) && time.before(forenoon))) {
			period = "早晨";
		}

		if (time.equals(forenoon) || (time.after(forenoon) && time.before(midday))) {
			period = "上午";
		}

		if (time.equals(midday) || (time.after(midday) && time.before(after_noon))) {
			period = "下午";
		}

		if (time.equals(after_noon) || (time.after(after_noon) && time.before(night))) {
			period = "晚上";
		}

		if (time.equals(night) || (time.after(night) && time.before(late))) {
			period = "深夜";
		}


		FullDate fullDate = FullDate.builder()
				.fullTime(timeStr)
				.datetime(datetime)
				.date(date)
				.time(timeStr.substring(11, 18))
				.year(year)
				.day(day)
				.quarter(quarter)
				.month(month)
				.weekOfYear(weekOfYear)
				.dayOfWeek(dayOfWeek)
				.period(period)
				.build();

		return fullDate;
	}

}
