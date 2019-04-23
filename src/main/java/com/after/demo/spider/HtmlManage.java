package com.after.demo.spider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/19 19:06
 */
public class HtmlManage {

    public Document manage(String html){
        Document doc = Jsoup.parse(html);
        return doc;
    }

    public Document manageDirect(String url) throws IOException{
        Document doc = Jsoup.connect( url ).get();
        return doc;
    }

    public List<String> element(Elements elements){
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < elements.size() ; i++){
            String str = elements.get(i).html();
            list.add(str);
        }
        return list;
    }

    public List<String> manageHtmlTag(Document doc,String tag ){
        Elements elements = doc.getElementsByTag(tag);
        return element(elements);
    }

    public List<String> manageHtmlClass(Document doc,String clas ){
        Elements elements = doc.getElementsByClass(clas);
        return element(elements);
    }

    public List<String> manageHtmlKey(Document doc,String key,String value ){
        Elements elements = doc.getElementsByAttributeValue(key, value);
        return element(elements);
    }

    private static Log log = LogFactory.getLog(HtmlManage.class);
}