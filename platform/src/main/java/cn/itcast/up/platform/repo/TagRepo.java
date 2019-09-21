package cn.itcast.up.platform.repo;

import cn.itcast.up.platform.entity.po.TagPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<TagPo, Long> {

    /**
     * 根据级别查询标签
     */
    List<TagPo> findByLevel(Integer level);

    List<TagPo> findByPid(Long pid);

    TagPo findFirstByName(String name);
}
