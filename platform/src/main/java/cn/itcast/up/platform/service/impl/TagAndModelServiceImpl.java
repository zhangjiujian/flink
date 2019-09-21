package cn.itcast.up.platform.service.impl;

import cn.itcast.up.platform.entity.Codes;
import cn.itcast.up.platform.entity.dto.ModelDto;
import cn.itcast.up.platform.entity.dto.TagDto;
import cn.itcast.up.platform.entity.po.ModelPo;
import cn.itcast.up.platform.entity.po.TagPo;
import cn.itcast.up.platform.exception.UpException;
import cn.itcast.up.platform.repo.ModelRepo;
import cn.itcast.up.platform.repo.TagRepo;
import cn.itcast.up.platform.service.TagAndModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class TagAndModelServiceImpl implements TagAndModelService {
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private ModelRepo modelRepo;

    /**
     * 通过 Level 获取标签
     * @param level 标签级别
     * @return 这个级别对应的所有标签 DTO
     */
    @Override
    public List<TagDto> getTagByLevel(Integer level) {
        if (level == null || level < 1) {
            // 参数异常
            throw new UpException(Codes.ERROR_PARAM, "请提供级别参数");
        }

        // 1. 查询数据库, TagPo
        List<TagPo> pos = tagRepo.findByLevel(level);
        // 2. 将 Po 转为 Dto
        return pos.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<TagDto> getTagByPid(Long pid) {
        // 编写 repo
        List<TagPo> pos = tagRepo.findByPid(pid);
        return pos.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public void addTagsByRelation(List<TagDto> tagDtos) {
        // 1. 编写 repo, 把 DTO 所代表的 PO 对象, 保存到数据库中, 这个 save 方法已经被提供了
        // 2. 保留这些标签之间的依赖关系
        // 3. 根据名称判断, 如果重复, 则不插入

        // 2.1. 如果想要按照顺序保留他们之间的关系, 就需要先有顺序, 按照 level
        //[ { "name": "Hello", "level": "1" },
        //  { "name": "Hello", "level": "2" },
        //  { "name": "Hello", "level": "3" }
        //]
        tagDtos.sort((o1, o2) -> {
            if (o1.getLevel() > o2.getLevel()) {
                return 1;
            }
            if (o1.getLevel().equals(o2.getLevel())) {
                return 0;
            }
            if (o1.getLevel() < o2.getLevel()) {
                return -1;
            }
            return 0;
        });

        // 2.2. 按照顺序, 赋值 pid
        // 把当前对象的 pid 设置为上一个对象 id

        // FIXME: 按照名称查询会导致全局没有重复

        TagPo temp = null;

        for (TagDto dto : tagDtos) {
            if (temp != null) {
                dto.setPid(temp.getId());
            }

            TagPo poFromDB = tagRepo.findFirstByName(dto.getName());

            if (poFromDB == null) {
                TagPo po = convert(dto);
                temp = tagRepo.save(po);
            }

            if (poFromDB != null) {
                temp = poFromDB;
            }
        }
    }

    @Override
    public void addTagModel(TagDto tag, ModelDto model) {
        // 1. 把 tag 存入数据库, 获取 tagId
        TagPo po = tagRepo.save(convert(tag));

        // 2. 把 tagId 给 model po, 存储
        modelRepo.save(convert(model, po.getId()));
    }

    /**
     * 设计一个方法(对象)的时候, 想清楚方法(对象)是干啥的
     * 单一职责
     */
    private ModelPo convert(ModelDto dto, Long tagId) {
        ModelPo po = new ModelPo();
        po.setArgs(dto.getArgs());
        po.setCtime(new Date());
        po.setId(dto.getId());
        po.setMain(dto.getMainClass());
        po.setName(dto.getName());
        po.setPath(dto.getPath());
        po.setSchedule(dto.getSchedule().toPattern());
        po.setState(dto.getState());
        po.setTagId(tagId);
        po.setUtime(new Date());
        return po;
    }

    /**
     * 将 TagPo 转为 TagDto
     */
    private TagDto convert(TagPo po) {
        TagDto dto = new TagDto();
        dto.setId(po.getId());
        dto.setPid(po.getPid());
        dto.setName(po.getName());
        dto.setLevel(po.getLevel());
        return dto;
    }

    /**
     * 将 TagDto 转为 TagPo
     */
    private TagPo convert(TagDto dto) {
        TagPo po = new TagPo();
        po.setUtime(new Date());
        po.setPid(dto.getPid());
        po.setName(dto.getName());
        po.setLevel(dto.getLevel());
        po.setId(dto.getId());
        po.setCtime(new Date());
        return po;
    }
}
