package com.example.wangbin.gymclub.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/5.
 */
public class HttpUtil {

    //发送http请求
    public static void sendHttpGetRequest(final String address,final Map<String, String> params, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                HttpURLConnection connection = null;
                try{
                    String address1=address+"?"+getRequestData(params, "UTF-8").toString();
                    URL url = new URL(address1);
                    connection = (HttpURLConnection) url.openConnection();
                    //设置连接超时、读取超时的毫秒数等
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    //获取服务器返回的输入流
                    InputStream in = connection.getInputStream();

                    byte[] data = readStream(in);

                    String json = new String(data);

                    //对获取到的输入流进行读取
                    //BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    // StringBuilder response = new StringBuilder();
                    //String line;
                    // while( (line=reader.readLine()) != null){
                    //response.append(line);
                    // }
                    if(listener !=null){
                        listener.onFinish(json);
                    }
                }catch (Exception e){
                    if(listener != null){
                        listener.onError(e);
                    }
                }finally{
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    public static void sendHttpGetRequestOfNone(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                HttpURLConnection connection = null;
                try{

                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    //设置连接超时、读取超时的毫秒数等
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    //获取服务器返回的输入流
                    InputStream in = connection.getInputStream();

                    byte[] data = readStream(in);

                    String json = new String(data);

                    //对获取到的输入流进行读取
                    //BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    // StringBuilder response = new StringBuilder();
                    //String line;
                    // while( (line=reader.readLine()) != null){
                    //response.append(line);
                    // }
                    if(listener !=null){
                        listener.onFinish(json);
                    }
                }catch (Exception e){
                    if(listener != null){
                        listener.onError(e);
                    }
                }finally{
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    public static void sendHttpPostRequest(final String address,final Map<String, String> params, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] content=getRequestData(params, "UTF-8").toString().getBytes();
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    //设置连接超时、读取超时的毫秒数等
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(content);
                    outputStream.flush();
                    outputStream.close();
                    //获取服务器返回的输入流
                    InputStream in = connection.getInputStream();

                    byte[] data = readStream(in);
                    String json = new String(data);

                    //对获取到的输入流进行读取
                    //BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    // StringBuilder response = new StringBuilder();
                    //String line;
                    // while( (line=reader.readLine()) != null){
                    //response.append(line);
                    // }
                    if(listener !=null){
                        listener.onFinish(json);
                    }
                }catch (Exception e){
                    if(listener != null){
                        listener.onError(e);
                    }
                }finally{
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    private static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }
        bout.close();
        inputStream.close();
        return bout.toByteArray();
    }

    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for(Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
}
