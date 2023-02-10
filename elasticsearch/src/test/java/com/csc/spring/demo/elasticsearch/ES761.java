package com.csc.spring.demo.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.csc.spring.demo.elasticsearch.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/26 16:06
 */
@SpringBootTest
public class ES761 {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    private static final String INDEX = "csc_index";

    @Test
    void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(INDEX);
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    void existsIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest(INDEX);
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(INDEX);
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    @Test
    void addDocument() throws IOException {
        User user = new User(1, "111", 1);
        IndexRequest request = new IndexRequest(INDEX);
        request.id(String.valueOf(user.getId()));
        request.timeout("1s");
        request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(response.status());
    }

    @Test
    void existsDocument() throws IOException {
        GetRequest request = new GetRequest(INDEX, "1");
        request.fetchSourceContext(new FetchSourceContext(false));
        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void getDocument() throws IOException {
        GetRequest request = new GetRequest(INDEX, "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        System.out.println(request);
    }

    @Test
    void updateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest(INDEX, "1");
        request.timeout("1s");
        User user = new User(1, "我爱中国", 1);
        request.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    @Test
    void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest(INDEX, "1");
        request.timeout("1s");
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    @Test
    void addBulk() throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout("10s");
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(1, "我爱中国", 1));
        userList.add(new User(2, "好好学习", 2));
        userList.add(new User(3, "天天向上", 3));
        userList.add(new User(4, "jxzlzh", 4));
        userList.add(new User(5, "jxzlzh", 5));
        userList.add(new User(6, "jxzlzh", 6));
//        userList.stream().map(user -> {
//            request.add(new IndexRequest(INDEX)
//                    .id(String.valueOf(user.getId()))
//                    .source(JSON.toJSONString(user), XContentType.JSON));
//            return user;
//        });
        for (User user : userList) {
            request.add(new IndexRequest(INDEX)
                    .id(String.valueOf(user.getId()))
                    .source(JSON.toJSONString(user), XContentType.JSON)
            );
        }
        BulkResponse responses = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(responses.hasFailures());
    }

    @Test
    void search() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "csc");
        sourceBuilder.query(matchQueryBuilder);

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "csc");
        sourceBuilder.query(termQueryBuilder);

        sourceBuilder.from(2);
        sourceBuilder.size(3);
        sourceBuilder.sort("id", SortOrder.ASC);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightName = new HighlightBuilder.Field("name");
        highlightName.highlighterType("unified");
        highlightBuilder.field(highlightName);
        sourceBuilder.highlighter(highlightBuilder);

        request.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getHits().getTotalHits());
        for (SearchHit documentFields : response.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }


    }


}


