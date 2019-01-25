package com.xishui.beeger.datap.netty.http;

import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.netty.common.Holders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public enum HttpClient {
    POST {
        @Override
        public String send(Params params) throws Exception {
            return HttpClient.POST.send(params, Holders.pullHolderValue(Holders.Keys.SERVER_URL));
        }

        private final List<NameValuePair> nameValuePairs(Params params) {
            if (null != params && !params.getParamMap().isEmpty()) {
                final List<NameValuePair> nameValuePairs = new ArrayList<>();
                params.getParamMap().forEach((k, v) -> {
                    nameValuePairs.add(new BasicNameValuePair(k, v));
                });
                return nameValuePairs;
            }
            return new ArrayList<>();
        }

        @Override
        public String send(Params params, String url) throws Exception {
            HttpPost httpPost = null;
            try {
                CloseableHttpClient httpClient = HttpClients.custom()
                        .setConnectionTimeToLive(10, TimeUnit.MINUTES).build();

                httpPost = new HttpPost(url);

                UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(nameValuePairs(params), Charset.forName("UTF-8"));
                encodedFormEntity.setContentEncoding("UTF-8");
                httpPost.setEntity(encodedFormEntity);

                CloseableHttpResponse response = httpClient.execute(httpPost);

                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
                } else {
                    throw new IllegalStateException("Response Stauts is:" + response.getStatusLine().getStatusCode());
                }
            } finally {
                if (null != httpPost) {
                    httpPost.releaseConnection();
                }
            }
        }
    },
    GET {
        @Override
        public String send(Params params) {
            return null;
        }

        @Override
        public String send(Params params, String url) {
            return null;
        }
    };


    public abstract String send(Params params) throws Exception;

    public abstract String send(Params params, String url) throws Exception;
}
