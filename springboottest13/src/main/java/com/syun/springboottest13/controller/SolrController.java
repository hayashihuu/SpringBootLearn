package com.syun.springboottest13.controller;

import com.syun.springboottest13.model.Article;
import com.syun.springboottest13.model.User;
import com.syun.springboottest13.utils.SolrUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/*
 * @description: 测试项目中使用solr
 * @program: springboottest13
 * @author: syun
 * @create: 2018-07-17 16:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@RestController
public class SolrController {

    @Autowired
    private SolrClient client;

    /**
     * 各种取值方法测试
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void search() throws IOException, SolrServerException {
//        SolrDocument document = null;
//        try {
//            document = client.getById("artilces", "1");
//            System.out.println(document);
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        {
            SolrQuery sq = new SolrQuery();
            sq.set("q","company:汉得");
            QueryResponse qr = client.query("user",sq);
            List<User> list = qr.getBeans(User.class);
            List<Article> articles = qr.getBeans(Article.class);
            System.out.println(list.toString());
            System.out.println(articles.toString());

            SolrDocumentList documents = qr.getResults();
            documents.forEach((p)-> System.out.println(p.toString()));
        }


    }



    @Test
    public void add(){
        //随机id进行插入
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", uuid);
            doc.setField("content_ik", "我是中国人, 我爱中国");
            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
             * 下面都是一样的
             */
            client.add("artilces", doc);

            client.commit("artilces");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete(){
        try {
            client.deleteById("artilces","1");
            client.commit("artilces");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试添加javaBean数据
     */
    @Test
    public void test01(){
        Article article = new Article();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        article.setId(uuid);
        article.setName("我的测试2");
        article.setContent("吉林市长春药店");
        article.setCreateTime(new Date());
        SolrUtil.saveSolrResource(article);
    }

    /**
     * 测试获取javaBean数据
     */
    @Test
    public void test02(){
//        设置中文分词查询
        try {

            QueryResponse respone = SolrUtil.query("content","长春");
            List<Article> articleList = respone.getBeans(Article.class);
            articleList.forEach((p)-> System.out.println(p.toString()));
//            System.out.println(articleList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试返回高亮
     */
    @Test
    @GetMapping("{keyword}")
    public List<Article> test03(@PathVariable("keyword") String keyword) throws IOException, SolrServerException {
//        设置中文分词查询

        System.out.println(keyword);
//        SolrQuery sq = new SolrQuery();
//        sq.set("q","company:汉得");
//        QueryResponse qr = client.query("user",sq);
//        List<User> list = qr.getBeans(User.class);
//        List<Article> articles = qr.getBeans(Article.class);
//        System.out.println(list.toString());
//        System.out.println(articles.toString());
//
//        SolrDocumentList documents = qr.getResults();
//        documents.forEach((p)-> System.out.println(p.toString()));
        SolrQuery sq = new SolrQuery();
//        solrQuery.setQuery("title:" + keywords + "or content:" + keywords+ "or author:" + keywords+ "or category:" + keywords); // 设置查询关键字
        sq.set("q", "content:'"+keyword+"'");
        //开启高亮字段
        sq.setHighlight(true);
        //设置高亮字段
        sq.addHighlightField("content");
        //设置高亮的样式
        sq.setHighlightSimplePre("<font color = 'red'>");
        sq.setHighlightSimplePost("</font>");

        //从最近时间开始排序
        sq.setSort("createTime", SolrQuery.ORDER.desc);

        QueryResponse qr = client.query("artilces", sq);

        List<Article> articleList = qr.getBeans(Article.class);
        articleList.forEach((p)-> System.out.println(p.toString()));

        //获取启用高亮后的数据
        Map<String, Map<String, List<String>>> highlightresult = qr.getHighlighting();

        //进行javaBean的高亮设置
        for (int i = 0; i < articleList.size(); ++i) {
            // System.out.println("文章："+articleList.get(i));
            String id = articleList.get(i).getId();
            if (highlightresult.get(id) != null && highlightresult.get(id).get("content") != null) {
                articleList.get(i).setContent(highlightresult.get(id).get("content").get(0));
            }
        }
        System.out.println("---------------------------------");
        articleList.forEach((p)-> System.out.println(p.toString()));

        return articleList;

    }




}
