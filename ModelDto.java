package cn.itcast.up.platform.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ModelDto {
    private Long id = -1L;
    private String name;
    private String path;
    private Integer state;
    private String mainClass;
    private String args;
    private Schedule schedule;

    @Data
    public static class Schedule {
        private Integer frequency;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private Date startTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private Date endTime;

        private String frequencyString;
        private String startTimeString;
        private String endTimeString;

        private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        public static Schedule from(String pattern) throws ParseException {
            Schedule schedule = new Schedule();

            String[] arr = pattern.split(",");
            schedule.setFrequency(Integer.parseInt(arr[0], 10));
            schedule.setStartTime(formatter.parse(arr[1]));
            schedule.setEndTime(formatter.parse(arr[2]));

            return schedule;
        }

        public String toPatternString() {
            return frequency + "," + formatter.format(startTime) + "," + formatter.format(endTime);
        }
    }
}
