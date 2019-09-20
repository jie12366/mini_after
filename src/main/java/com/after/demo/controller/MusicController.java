package com.after.demo.controller;

import com.after.demo.entity.Music;
import com.after.demo.service.impl.MusicServiceImpl;
import com.after.demo.service.impl.UploadServiceImpl;
import com.after.demo.spider.FileDownload;
import com.after.demo.spider.HtmlManage;
import com.after.demo.spider.HttpGetConnect;
import com.after.demo.utils.GetString;
import com.after.demo.utils.JsonResult;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/19 21:18
 */
@Slf4j
@RestController
public class MusicController {

    @Autowired
    MusicServiceImpl musicService;
    @Autowired
    UploadServiceImpl uploadService;

    public static String FILEPATH = "F:/music/";
    public static String mp3 = "https://wwwapi.kugou.com/yy/index.php?r=play/getdata&callback=jQuery191027067069941080546_1546235744250&"
            + "hash=HASH&album_id=0&_=TIME";

    public static final String LINK = "https://www.kugou.com/yy/rank/home/PAGE-33164.html?from=rank";

    /**
     * 线程池
     */
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10,20,0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(255), new ThreadFactoryBuilder().setNameFormat("music-%d").build());

    @GetMapping("/music/save")
    @ApiOperation("将酷狗歌单爬取存入数据库")
    public JsonResult saveMusic(){
        for(int i = 1 ; i < 10 ; i++){
                String url = LINK.replace("PAGE", i + "");
                try {
                    getTitle(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return JsonResult.ok();
    }

    @PostMapping("/music/getOne")
    @ApiOperation("随机获取一首歌")
    public JsonResult getMusic(){
        int maxSize = musicService.getSize();
        int id = (int)(Math.random() * maxSize);
        Music music = musicService.getMusicById(id);
        return JsonResult.ok(music);
    }

    private String getTitle(String url) throws IOException {
        String content = HttpGetConnect.connect(url, "utf-8");
        HtmlManage html = new HtmlManage();
        Document doc = html.manage(content);
        Element ele = doc.getElementsByClass("pc_temp_songlist").get(0);
        Elements eles = ele.getElementsByTag("li");
        for(int i = 0 ; i < eles.size() ; i++){
            int num = i;
            executor.submit(() -> {
                Element item = eles.get(num);
                String title = item.attr("title").trim();
                String link = item.getElementsByTag("a").first().attr("href");
                try {
                    download(link,title);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
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
        if (hash.equals("")){
            return null;
        }
        //爬取歌曲的封面图
        Document doc = html.manage(content);
        Element ele = doc.getElementsByClass("albumImg").get(0);
        String imgUrl = ele.getElementsByTag("img").attr("src");
        //利用hash值构造歌曲mp3地址
        String item = mp3.replace("HASH", hash);
        item = item.replace("TIME", System.currentTimeMillis() + "");

        System.out.println(item);
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url1 = new URL(item);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url1.openConnection();
            // 必须携带一个cookie头，否则无法获取数据
            httpUrlConn.setRequestProperty("Cookie","kg_mid=2333");
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("GET");

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            httpUrlConn.disconnect();
            String result= buffer.substring(buffer.indexOf("(") + 1, buffer.length() - 2);
            jsonObject = JSONObject.fromObject(result);
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }

        System.out.println(jsonObject);
        String playUrl = jsonObject.getJSONObject("data").getString("play_url");

        String src = null;
        //如果图片地址或mp3地址为空，则不爬取（歌曲是收费的无法爬取）
        if (!("").equals(playUrl) && !("").equals(imgUrl)) {
            //将该歌曲信息先下载到本地文件
            FileDownload fileDownload = new FileDownload();
            fileDownload.download(playUrl,FILEPATH + name + ".mp3");
            try{
                //把音乐文件上传到七牛云
                File file = new File(FILEPATH + name + ".mp3");
                Response response = uploadService.uploadFile(file);
                //解析上传成功的结果
                DefaultPutRet  putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                src = "http://cdn2.jie12366.xyz/" + putRet.key;
            }catch (QiniuException e){
                e.printStackTrace();
            }
        }else {
            return null;
        }

        musicService.saveMusic(name,imgUrl,src);
        return playUrl;
    }

    private static class MyX509TrustManager implements X509TrustManager
    {

        private X509Certificate[] certificates;

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException
        {
            if (this.certificates == null)
            {
                this.certificates = certificates;
            }

        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException
        {
            if (this.certificates == null)
            {
                this.certificates = certificates;
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            // TODO Auto-generated method stub
            return null;
        }

    };
}