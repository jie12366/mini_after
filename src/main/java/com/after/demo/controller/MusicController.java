package com.after.demo.controller;

import com.after.demo.entity.Music;
import com.after.demo.service.impl.MusicServiceImpl;
import com.after.demo.spider.HtmlManage;
import com.after.demo.spider.HttpGetConnect;
import com.after.demo.utils.GetString;
import com.after.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/19 21:18
 */
@RestController
public class MusicController {

    @Autowired
    MusicServiceImpl musicService;

    public static String mp3 = "https://wwwapi.kugou.com/yy/index.php?r=play/getdata&callback=jQuery191027067069941080546_1546235744250&"
            + "hash=HASH&album_id=0&_=TIME";

    public static final String LINK = "https://www.kugou.com/yy/rank/home/PAGE-33164.html?from=rank";

    @GetMapping("/music/save")
    @ApiOperation("将酷狗歌单爬取存入数据库")
    public JsonResult saveMusic() throws IOException{
        for(int i = 1 ; i < 10 ; i++){
            String url = LINK.replace("PAGE", i + "");
            getTitle(url);
        }
        return JsonResult.ok();
    }

    @PostMapping("/music/getOne")
    @ApiOperation("随机获取一首歌")
    public JsonResult getMusic(){
        int id = GetString.getId();
        Music music = musicService.getMusicById(id);
        return JsonResult.ok(music);
    }

    public String getTitle(String url) throws IOException {
        String content = HttpGetConnect.connect(url, "utf-8");
        HtmlManage html = new HtmlManage();
        Document doc = html.manage(content);
        Element ele = doc.getElementsByClass("pc_temp_songlist").get(0);
        Elements eles = ele.getElementsByTag("li");
        for(int i = 0 ; i < eles.size() ; i++){
            Element item = eles.get(i);
            String title = item.attr("title").trim();
            String link = item.getElementsByTag("a").first().attr("href");
            download(link,title);
        }
        return null;
    }

    public String download(String url,String name) throws IOException{
        String hash = "";
        String content = HttpGetConnect.connect(url, "utf-8");
        HtmlManage html = new HtmlManage();
        String regEx = "\"hash\":\"[0-9A-Z]+\"";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            hash = matcher.group();
            hash = hash.replace("\"hash\":\"", "");
            hash = hash.replace("\"", "");
        }
        //爬取歌曲的封面图
        Document doc = html.manage(content);
        Element ele = doc.getElementsByClass("albumImg").get(0);
        String imgUrl = ele.getElementsByTag("img").attr("src");
        //利用hash值构造歌曲mp3地址
        String item = mp3.replace("HASH", hash);
        item = item.replace("TIME", System.currentTimeMillis() + "");

        String mp = HttpGetConnect.connect(item, "utf-8");

        mp = mp.substring(mp.indexOf("(") + 1, mp.length() - 3);

        JSONObject json = JSONObject.fromObject(mp);
        String playUrl = json.getJSONObject("data").getString("play_url");

        //如果图片地址或mp3地址为空，则不爬取（歌曲是收费的无法爬取）
        if (StringUtils.isNotBlank(playUrl) && StringUtils.isNotBlank(imgUrl)){
            musicService.saveMusic(name,imgUrl,playUrl);
        }
        return playUrl;
    }
}