package com.syun.springboottest13.utils;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/*
 * @description:
 * @program: springboottest13
 * @author: syun
 * @create: 2018-07-18 09:59
 */
//@Service
public class SolrUtil {


//    @Autowired
//    private static SolrClient client;


    private static SolrClient client;
    private static String url;
    static {
//      手动更改core
        url = "http://localhost:8983/solr/artilces";
        client = new HttpSolrClient.Builder(url).build();
    }

    /**
     * 保存或者更新solr数据
     *
     */
    public static <T> boolean saveSolrResource(T solrEntity) {

        DocumentObjectBinder binder = new DocumentObjectBinder();
        SolrInputDocument doc = binder.toSolrInputDocument(solrEntity);
        try {
            client.add(doc);
            client.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除solr 数据
     * 通过id值删除,一般不使用
     * @param id
     */
    public static boolean removeSolrData(String id) {
        try {
            client.deleteById(id);
            client.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 查询
     *
     */
    public static QueryResponse query(String key,String value) throws SolrServerException, IOException, IOException {
        SolrQuery query = new SolrQuery();
        String condition = key + ":" + value;
//        普通查询
        query.set("q",condition);
//        query.setQuery(keywords);

        QueryResponse rsp = client.query(query);
        return rsp;
    }
}
