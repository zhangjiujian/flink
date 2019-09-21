package cn.itcast.up.platform.service;

import cn.itcast.up.platform.entity.dto.ModelDto;
import cn.itcast.up.platform.entity.dto.TagDto;

import java.util.List;

public interface TagAndModelService {

    /**
     * 通过级别查询所有标签
     */
    List<TagDto> getTagByLevel(Integer level);

    /**
     * 根据 PID 查询
     */
    List<TagDto> getTagByPid(Long pid);

    void addTagsByRelation(List<TagDto> tagDtos);

    void addTagModel(TagDto tag, ModelDto model);
}
