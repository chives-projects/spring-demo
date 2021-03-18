package com.csc.spring.demo.mongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/20 23:55
 */
@Configuration
public class MongoConfig {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 移除 _class 字段
     */
    @PostConstruct
    public void init() {
        MongoConverter converter = mongoTemplate.getConverter();
        if (converter.getTypeMapper().isTypeKey("_class")) {
            ((MappingMongoConverter) converter).setTypeMapper(new DefaultMongoTypeMapper(null));
        }
//        ((MappingMongoConverter) mongoTemplate.getConverter())
//                .setTypeMapper(new DefaultMongoTypeMapper(null));
    }
}
