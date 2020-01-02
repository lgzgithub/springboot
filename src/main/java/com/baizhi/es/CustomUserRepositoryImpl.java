package com.baizhi.es;

import com.baizhi.entity.Users;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository{
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public List<Users> highlight(String es) {
        /**
         * 高亮相关
         */
        HighlightBuilder.Field field = new HighlightBuilder
                .Field("*")  //调用内部类指定对应参数
                .preTags("<span style='color:red'>")
                .postTags("</span>")
                .requireFieldMatch(false);
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                //.withQuery(QueryBuilders.termQuery("name", "语文")) //term关键词查询
                .withQuery(QueryBuilders.queryStringQuery(es).analyzer("ik_max_word").field("name"))
                .withFields("id", "name", "password","phone") //返回指定字段
                .withHighlightFields(field)
                .build();
        AggregatedPage<Users> userss = elasticsearchTemplate.queryForPage(build, Users.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                SearchHit[] hits = searchResponse.getHits().getHits();
                ArrayList<Users> list = new ArrayList<>();
                for (SearchHit hit : hits) {
                    Users users = new Users();
                    //元数据
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    if(sourceAsMap.get("id")!=null){
                        users.setId(sourceAsMap.get("id").toString());
                    }
                    if (sourceAsMap.get("name")!=null){
                        users.setName(sourceAsMap.get("name").toString());
                    }
                    if (sourceAsMap.get("password")!=null){
                        users.setPassword(sourceAsMap.get("password").toString());
                    }
                    /*if (sourceAsMap.get("birthday")!=null){
                        //users.setBirthday(new Date(Long.valueOf(sourceAsMap.get("birthday").toString())));
                        users.setBirthday(new Date(Long.valueOf(sourceAsMap.get("birthday").toString())));
                    }*/
                    if (sourceAsMap.get("phone")!=null){
                        users.setPhone(sourceAsMap.get("phone").toString());
                    }
                    if (sourceAsMap.get("status")!=null){
                        users.setStatus(sourceAsMap.get("status").toString());
                    }
                    //高亮字段
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (sourceAsMap.get("name")!=null){
                        if (highlightFields.get("name")!=null){
                            users.setName(highlightFields.get("name").getFragments()[0].toString());
                        }
                    }
                    /*if (sourceAsMap.get("status")!=null){
                        if (highlightFields.get("status")!=null){
                            users.setStatus(highlightFields.get("status").getFragments()[0].toString());
                        }
                    }*/
                    list.add(users);
                }
                return new AggregatedPageImpl<T>((List<T>) list);
            }
        });
        return userss.getContent();
    }
}
