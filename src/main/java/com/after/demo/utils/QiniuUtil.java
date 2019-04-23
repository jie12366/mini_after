package com.after.demo.utils;

import com.after.demo.service.impl.UploadServiceImpl;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/28 15:17
 */

@Data
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuUtil {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String path;
}