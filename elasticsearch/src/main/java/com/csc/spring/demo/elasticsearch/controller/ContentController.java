package com.csc.spring.demo.elasticsearch.controller;

import com.csc.spring.demo.elasticsearch.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/27 0:21
 */
@RestController
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword") String keyword) throws Exception {
        return contentService.parseContent(keyword);
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String, Object>> search(@PathVariable("keyword") String keyword,
                                            @PathVariable("pageNo") int pageNo,
                                            @PathVariable("pageSize") int pageSize) throws Exception {
//        contentService.parseContent(keyword);
        return contentService.searchPage(keyword, pageNo, pageSize);
    }
}
