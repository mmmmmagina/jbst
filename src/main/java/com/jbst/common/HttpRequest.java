package com.jbst.common;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author xiaolu.wu
 * @description
 */
public class HttpRequest implements Callable<Object> {

    private String url;
    private String params;
    private String requestType;

    public HttpRequest(String url, String params, String requestType) {
        this.url = url;
        this.params = params;
        this.requestType = requestType;
    }

    @Override
    public Boolean call() throws Exception {
        if (this.requestType.equalsIgnoreCase("GET")) {
            return sendGetRequest();
        }
        return sendPostRequest();
    }

    /**
     * 获取指定链接的响应信息，GET方式
     *
     */
    private boolean sendGetRequest() {
        HttpClient lvClient = new DefaultHttpClient();
        HttpGet lvRequest = new HttpGet(this.url + this.params);
        try {
            HttpResponse response = lvClient.execute(lvRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取指定链接的响应信息，POST方式
     *
     */
    private boolean sendPostRequest() {
        HttpClient lvClient = new DefaultHttpClient();
        HttpPost lvHttpPost = new HttpPost(this.url);
        try {
            lvHttpPost.setEntity(new StringEntity(this.params));
            HttpResponse response = lvClient.execute(lvHttpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 统一提交请求接口
     *
     * @param pUrl
     * @param pParams
     * @param pHttpRequestType
     * @return
     */
    public static Boolean sendRequest(String pUrl, String pParams, String requestType) {
        Callable<Object> lvCallable = new HttpRequest(pUrl, pParams, requestType);
        Future<Object> lvFuture = HttpHandle.getInstance().submit(lvCallable);
        Boolean response = false;
        try {
            response = (Boolean) lvFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return response;
    }
}
