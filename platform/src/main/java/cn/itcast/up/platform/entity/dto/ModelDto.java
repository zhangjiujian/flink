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

    /**
     * 拼接规则是无所谓的, 但是, 要一致
     * 拼的时候如何拼, 拆的时候就怎么拆
     */
    @Data
    public static class Schedule {
        private Integer frequency;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private Date startTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private Date endTime;

        private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        public String toPattern() {
            String startStr = formatter.format(startTime);
            String endStr = formatter.format(endTime);
            return frequency + "," + startStr + "," + endStr;
        }
    }
}
