package com.csc.spring.demo.mongo.repository;

import com.csc.spring.demo.mongo.domain.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/19 0:14
 */
@Component
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增一个，存在则覆盖
     */
    public User save(User user) {
        return mongoTemplate.save(user);
    }

    /**
     * 新增一个，存在抛异常：DuplicateKeyException
     */
    public User insert(User user) {
        return mongoTemplate.insert(user);
    }

    public Collection<User> saveBatchUser(List<User> users) {
//        Collection<User> userCollection = mongoTemplate.insertAll(users);
        return mongoTemplate.insert(users, User.class);
    }

    public DeleteResult delUserById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, User.class);
    }

    public DeleteResult delAll() {
        // 删除存在主键的（主键不为空），即删除所有数据
        Query query = new Query(Criteria.where("id").exists(true));
        return mongoTemplate.remove(query, User.class);
    }

    public long upadteUserById(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        update.set("name", user.getName()).set("age", user.getAge());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
//        UpdateResult multi = mongoTemplate.updateMulti(query, update, User.class);
        return updateResult.getModifiedCount();
    }

    public User findUserByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * 查询所有
     */
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    /**
     * name模糊查找
     */
    public List<User> findUserByLikeName(String name) {
        Query query = new Query();
        query.fields().include("id");
        query.fields().include("name");
        query.fields().exclude("weight");
        query.addCriteria(Criteria.where("name").regex(".*" + name + ".*"));
        return mongoTemplate.find(query, User.class);
    }

    /*
     * project:列出所有本次查询的字段，包括查询条件的字段和需要搜索的字段；
     * match:搜索条件criteria
     * unwind：某一个字段是集合，将该字段分解成数组
     * group：分组的字段，以及聚合相关查询
     *      sum：求和(同sql查询)
     *      count：数量(同sql查询)
     *      as:别名(同sql查询)
     *      addToSet：将符合的字段值添加到一个集合或数组中
     * sort：排序
     * skip&limit：分页查询
     */
    public List<Object> Aggregation() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("name").is("韩信")),
//                Aggregation.project("id", "name"),
//                Aggregation.fields("name").and()
//                Aggregation.unwind("id"),
                Aggregation.group("_id").count().as("count")
                        .max("_id").as("max")
                        .sum("_id").as("sum")
                        .avg("_id").as("avg")
//                Aggregation.group("name").count().as("count")
//                Aggregation.sort(Sort.Direction.ASC, "age")
//                Aggregation.skip(1),
//                Aggregation.limit(2)
        );
        AggregationResults<Object> aggregate = mongoTemplate.aggregate(aggregation, "User", Object.class);
        return aggregate.getMappedResults();
    }
}
