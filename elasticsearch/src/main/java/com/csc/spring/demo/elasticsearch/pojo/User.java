package com.csc.spring.demo.elasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/26 16:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document(indexName = "csc_index",type = "user")
@Document(indexName = "csc_index")
public class User {
    @Id
    @Field(analyzer = "ik_smart",type = FieldType.Integer)
    private Integer id;

    @Field(analyzer = "ik_smart",type = FieldType.Text)
    private String name;

    @Field(analyzer = "ik_smart",type = FieldType.Integer)
    private Integer age;

}
