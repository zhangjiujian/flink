package cn.itcast.up.platform.entity.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "tbl_basic_tag")
public class TagPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       // 代理主键
    private Long pid;      // 父主键
    private Integer level; // 级别

    private String name;   // 名称

    private Date ctime;    // 创建时间
    private Date utime;    // 更新时间
}
