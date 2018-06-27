package com.xyauto.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class HttpUtil {
	public static String httpClientGet(String urlParam,
			Map<String, Object> params, String charset) {
		StringBuffer resultBuffer = null;
		HttpClient client = new DefaultHttpClient();
		BufferedReader br = null;
		// 构建请求参数
		StringBuffer sbParams = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (Entry<String, Object> entry : params.entrySet()) {
				sbParams.append(entry.getKey());
				sbParams.append("=");
				try {
					sbParams.append(URLEncoder.encode(
							String.valueOf(entry.getValue()), charset));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
				sbParams.append("&");
			}
		}
		if (sbParams != null && sbParams.length() > 0) {
			urlParam = urlParam + "?"
					+ sbParams.substring(0, sbParams.length() - 1);
		}
		HttpGet httpGet = new HttpGet(urlParam);
		try {
			HttpResponse response = client.execute(httpGet);
			// 读取服务器响应数据
			br = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			String temp;
			resultBuffer = new StringBuffer();
			while ((temp = br.readLine()) != null) {
				resultBuffer.append(temp);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				}
			}
		}
		return resultBuffer.toString();
	}

	public static String httpClientPost(String urlParam,
			Map<String, Object> params, String charset) {
		StringBuffer resultBuffer = null;
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(urlParam);
		// 构建请求参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> elem = iterator.next();
			list.add(new BasicNameValuePair(elem.getKey(), String.valueOf(elem
					.getValue())));
		}
		BufferedReader br = null;
		try {
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
						charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = client.execute(httpPost);
			 
			HttpEntity entity = response.getEntity();
	        String result = EntityUtils.toString(entity, "UTF-8");
	        JSONObject res = JSONObject.parseObject(result);
	        System.out.println(res.toJSONString());
			// 读取服务器响应数据
			resultBuffer = new StringBuffer();
			br = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			String temp;
			while ((temp = br.readLine()) != null) {
				resultBuffer.append(temp);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				}
			}
		}
		return resultBuffer.toString();
	}
	
	  public static JSONObject doPost(String url,JSONObject json){	        
	        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
	        HttpPost post = new HttpPost(url);
	        JSONObject response = null;
	        try {
	            StringEntity s = new StringEntity(json.toString());
	            s.setContentEncoding("UTF-8");
	            s.setContentType("application/json");//发送json数据需要设置contentType
	            post.setEntity(s);
	            HttpResponse res = httpclient.execute(post);
	            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
	                response = JSONObject.parseObject(result);
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	        return response;
	    }
	  
	  
	  public static String doPostStr(String url,JSONObject json){	        
	        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
	        HttpPost post = new HttpPost(url);
	        try {
	            StringEntity s = new StringEntity(json.toString());
	            s.setContentEncoding("UTF-8");
	            s.setContentType("application/json");//发送json数据需要设置contentType
	            post.setEntity(s);
	            HttpResponse res = httpclient.execute(post);
	            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
	               return result;
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	        return null;
	    }
	
//	public static void main(String[] args) {
		/*try {
			String appKey="QA";
			String appsecret="3B4BF673-1065-4478-8704-C3EA4ABB21C7"; 
			Long time2=System.currentTimeMillis();
			int time=(int)(System.currentTimeMillis()/1000);
			Long uid=44668l;
			String encPass = appKey+appsecret+time+uid;
//			String encPass ="testkeyC581F7FC-1639-4298-9C3C-570BCC4D9A111007201201702261146";
			System.out.println("加密前的数据："+encPass);
		    MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		    byte[] sha1Passbytes= sha1.digest(encPass.getBytes());
		    String Signature="";
		    if (sha1Passbytes != null) {
		    	Signature = new Base64().encodeToString(sha1Passbytes);		        
		    	Signature=URLEncoder.encode(Signature);
		        System.out.println("加密后的是数据："+Signature);
		    }
		    String url= "http://api.xingyuanauto.com/QA/GetBrokerInfo?AppKey="+appKey
		    		+"&Signature="+Signature+"&Timestamp="+time;
		    JSONObject jsonObject=new JSONObject();
		    jsonObject.put("uid", uid);
//		    httpClientPost(url, map, "utf-8");
		    jsonObject=doPost(url, jsonObject);		    
		    JSONObject resultData = JSONObject.parseObject(jsonObject
					.getString("Data"));
		    JSONArray serialArray=resultData.getJSONArray("serial");
		    List<Integer> serialIds=new ArrayList<Integer>();
		    for (int i = 0; i < serialArray.size(); i++) {
		    	JSONObject array=serialArray.getJSONObject(i);
		    	Integer serialId=array.getInteger("SerialID");
		    	serialIds.add(serialId);
			}
		    System.out.println(jsonObject.toString());
		} catch (Exception e) {
			
		}*/
		/*JSONObject dataInfo=new JSONObject();
		dataInfo.put("entityid", 63456);
		JSONArray data=new JSONArray();
		data.add(dataInfo);
		JSONObject info=new JSONObject();
		info.put("appid", 1);
		info.put("entitytype", 4);
		info.put("opertype", 2);
		info.put("datatype", -1);			
		info.put("data", data);			
		Long time=System.currentTimeMillis();
		String url="http://mq.manager.xingyuanauto.com/mq/send.do?businessId="+time+"&routingKey=news.news_zuixin.del_ask";
		String result=HttpUtil.doPostStr(url, info);
		System.out.println(result);*/
//	}
	
}
