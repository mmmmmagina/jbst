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

	// private String sendGetRequest() {
	//
	// CloseableHttpClient httpclient = HttpClients.createDefault();
	// HttpGet httpGet = new HttpGet(url);
	// CloseableHttpResponse res = null;
	// String result = null;
	// try {
	// res = httpclient.execute(httpGet);
	// InputStream is = res.getEntity().getContent();
	// result = IOUtils.toString(is, "UTF-8");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// } catch (ClientProtocolException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// res.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return result;
	// }
	//
	// private String sendPostRequest() {
	//
	// CloseableHttpClient httpclient = HttpClients.createDefault();
	// HttpPost httpPost = new HttpPost(url);
	// List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	// Iterator<String> iter = params.keySet().iterator();
	// while (iter.hasNext()) {
	// String key = iter.next();
	// String val = params.get(key);
	// nvps.add(new BasicNameValuePair(key, val));
	// }
	// CloseableHttpResponse res = null;
	// String result = null;
	// try {
	// httpPost.setEntity(new UrlEncodedFormEntity(nvps));
	// res = httpclient.execute(httpPost);
	// InputStream is = res.getEntity().getContent();
	// result = IOUtils.toString(is, "UTF-8");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// } catch (ClientProtocolException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// res.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return result;
	// }

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
