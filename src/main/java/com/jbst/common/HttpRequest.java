package com.jbst.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 * @author xiaolu.wu
 * @description
 */
public class HttpRequest {

    public static String sendGetRequest(String url, int timeout) {
        try {
            return Request.Get(url).connectTimeout(timeout)
                .socketTimeout(timeout).execute().returnContent().asString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String sendPostRequest(String url, int timeout,
        HashMap<String, String> params) {
        try {
            Iterator<String> iter = params.keySet().iterator();
            Form f = Form.form();
            while (iter.hasNext()) {
                String key = iter.next();
                String val = params.get(key);
                f.add(key, val);
            }
            return Request.Post(url).useExpectContinue()
                .version(HttpVersion.HTTP_1_1).bodyForm(f.build()).execute()
                .returnContent().asString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
