package cn.itcast.up.platform.entity.dto;

import lombok.Data;

/**
 * 封装给前端的数据
 * 或者从前端获取的数据
 */
@Data
public class TagDto {
    private Long id = -1L;
    private Long pid = -1L;
    private Integer level = -1;

    private String name;
}
