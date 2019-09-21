package cn.itcast.up.platform.controller;

import cn.itcast.up.platform.entity.Codes;
import cn.itcast.up.platform.entity.HttpResult;
import cn.itcast.up.platform.entity.dto.TagDto;
import cn.itcast.up.platform.entity.dto.TagModelDto;
import cn.itcast.up.platform.service.TagAndModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
public class TagAndModelController {
    @Autowired
    private TagAndModelService service;

    /**
     * GET http://localhost:8081/tags?level=2
     * 请求方式 GET 路径 tags 参数 level
     * 获取标签列表
     *
     * 出错的情况: 数组下标越界, 参数异常, 参数为空, 数据库无法访问
     *
     * GET http://localhost:8081/tags?pid=...
     * 1. 判断需要使用什么查询, 是pid还是level
     * 2. 编写 Service 提供对应业务功能
     * 3. 数据库访问
     * 4. 返回公共结果
     */
    @GetMapping("/tags")
    public HttpResult<List<TagDto>> getTagByLevel(
            @RequestParam(value = "level", required = false) Integer level,
            @RequestParam(value = "pid", required = false) Long pid
    ) {
        // 1. 调用 Service
//        Integer levelTemp = level == null || level < 1 ? 1 : level;
//        List<TagDto> tags = service.getTagByLevel(level);

        List<TagDto> tags = null;

        // 判断
        if (level == null && pid != null) {
            // 根据pid查询
            tags = service.getTagByPid(pid);
        }

        if (level != null && pid == null) {
            // 根据 level 查询
            tags = service.getTagByLevel(level);
        }

        // 2. 封装统一结果返回给前端
        // 创建result
        // 创建 codes
        return new HttpResult<>(Codes.SUCCESS, "", tags);
    }

    /**
     * PUT http://localhost:8081/tags/relation
     *
     * [
     *   {
     *     "name": "Hello",
     *     "level": "1"
     *   },
     *   {
     *     "name": "Hello",
     *     "level": "2"
     *   },
     *   {
     *     "name": "Hello",
     *     "level": "3"
     *   }
     * ]
     */
    @PostMapping("/tags/relation")
    public HttpResult<Object> putTags(@RequestBody List<TagDto> dtos) {
        service.addTagsByRelation(dtos);
        return new HttpResult<>(Codes.SUCCESS, "", null);
    }

    /**
     * ### Create model tag
     * POST http://localhost:8081/tags/model
     * Content-Type: application/json
     *
     * {
     *   "tag": {
     *     "name": "HelloSpark",
     *     "business": "testing",
     *     "industry": "testing",
     *     "rule": "",
     *     "level": 4,
     *     "parentId": -1
     *   },
     *   "model": {
     *     "name": "HelloSpark",
     *     "path": "hdfs://bd001:8020/apps/tags_new/jars/7449f9a4-42ce-44a2-819f-13e946de1943.jar",
     *     "mainClass": "cn.itcast.up.model.HelloSpark",
     *     "args": "",
     *     "schedule": {
     *       "frequency": "1",
     *       "startTime": "2019-09-16 15:20",
     *       "endTime": "2019-09-16 15:20"
     *     }
     *   }
     * }
     */
    @PostMapping("/tags/model")
    public HttpResult<Object> postTagAndModel(@RequestBody TagModelDto dto) {
        service.addTagModel(dto.getTag(), dto.getModel());
        return new HttpResult<>(Codes.SUCCESS, "", null);
    }
}
