package com.miller.test;

import cn.hutool.core.io.IoUtil;
import com.miller.springframework.core.io.DefaultResourceLoader;
import com.miller.springframework.core.io.Resource;
import com.miller.springframework.core.io.ResourceLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;

/**
 * @author miller
 * @version 1.0.0
 */
public class ResourceTest {
    private DefaultResourceLoader resourceLoader;

    @BeforeEach
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
     void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String context = IoUtil.readUtf8(inputStream);
        System.out.println(context);
    }

    @Test
    void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String context = IoUtil.readUtf8(inputStream);
        System.out.println(context);
    }

    @Test
    void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://udun-prod.oss-cn-hangzhou.aliyuncs.com/important.properties");
        InputStream inputStream = resource.getInputStream();
        String context = IoUtil.readUtf8(inputStream);
        System.out.println(context);
    }


}
