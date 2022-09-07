package com.miller.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author miller
 * @version 1.0.0
 */
public class UrlResource implements Resource {
    private URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "URL must not be null.");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = null;
        try {
            con = this.url.openConnection();
            return con.getInputStream();
        } catch (IOException e) {
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw e;
        }
    }
}
