package cn.edu.sau.response;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.edu.sau.url.UrlString;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ResponseMethod {

    // 服务器端返回json对象的信息
    public static JSONObject sendQueryState(JSONObject jo2, String url) {
        InputStreamReader isr = null;
        DataOutputStream dos = null;
        HttpURLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        JSONObject jsonObject = null;
        try {
            URL url1 = new URL(url);
            urlConn = (HttpURLConnection) url1.openConnection();
            urlConn.setDoInput(true);//设置输入流采用字节流
            urlConn.setDoOutput(true);//设置输出流采用字节流
            urlConn.setRequestMethod("POST");//post方法
            urlConn.setUseCaches(false);//缓存
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "utf-8");//缓存
            urlConn.connect();//连接服务器发送消息
            dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write((jo2.toString()).getBytes());//写数据
            dos.close();
            dos.flush();


            isr = new InputStreamReader(urlConn.getInputStream());
            bufferedReader = new BufferedReader(isr);
            String result = "";
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                result += readLine;
            }
            result = removeBOM(result);
            jsonObject = new JSONObject(result);
            bufferedReader.close();
            isr.close();
            urlConn.disconnect();//关闭连接
            return jsonObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jsonObject;
        }
    }

    // 服务器端返回json字符串的信息
    public static JSONArray sendQuery(String url) {
        InputStreamReader isr = null;
        DataOutputStream dos = null;
        HttpURLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        JSONArray JsonArray = null;
        try {
            URL url1 = new URL(url);
            urlConn = (HttpURLConnection) url1.openConnection();
            urlConn.setDoInput(true);//设置输入流采用字节流
            urlConn.setDoOutput(true);//设置输出流采用字节流
            urlConn.setRequestMethod("POST");//post方法
            urlConn.setUseCaches(false);//缓存
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "utf-8");//缓存
            urlConn.connect();//连接服务器发送消息
            dos = new DataOutputStream(urlConn.getOutputStream());
     //       dos.write((jo2.toString()).getBytes());//写数据
            dos.close();
            dos.flush();
            isr = new InputStreamReader(urlConn.getInputStream());
            bufferedReader = new BufferedReader(isr);
            String result = "";
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                result += readLine;
            }
            result = removeBOM(result);
            JsonArray = new JSONArray(result);
            bufferedReader.close();
            isr.close();
            urlConn.disconnect();//关闭连接
            return JsonArray;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return JsonArray;
        } catch (IOException e) {
            e.printStackTrace();
            return JsonArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return JsonArray;
        }
    }

    // 服务器端返回json字符串的信息
    public static JSONArray sendQuery(JSONObject jo2, String url) {
        InputStreamReader isr = null;
        DataOutputStream dos = null;
        HttpURLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        JSONArray JsonArray = null;
        try {
            URL url1 = new URL(url);
            urlConn = (HttpURLConnection) url1.openConnection();
            urlConn.setDoInput(true);//设置输入流采用字节流
            urlConn.setDoOutput(true);//设置输出流采用字节流
            urlConn.setRequestMethod("POST");//post方法
            urlConn.setUseCaches(false);//缓存
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "utf-8");//缓存
            urlConn.connect();//连接服务器发送消息
            dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write((jo2.toString()).getBytes());//写数据
            dos.close();
            dos.flush();



            isr = new InputStreamReader(urlConn.getInputStream());
            bufferedReader = new BufferedReader(isr);
            String result = "";
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                result += readLine;
            }

            result = removeBOM(result);

            JsonArray = new JSONArray(result);

            bufferedReader.close();
            isr.close();
            urlConn.disconnect();//关闭连接
            return JsonArray;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return JsonArray;
        } catch (IOException e) {
            e.printStackTrace();
            return JsonArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return JsonArray;
        }
    }

    // 上传图片
    public static String doPost(String imagePath) {
        OkHttpClient mOkHttpClient = new OkHttpClient();

        String result = "error";
        MultipartBody.Builder builder = new MultipartBody.Builder();
        // 这里演示添加用户ID
//        builder.addFormDataPart("userId", "20160519142605");
        builder.addFormDataPart("image", imagePath,
                RequestBody.create(MediaType.parse("image/jpeg"), new File(imagePath)));

        RequestBody requestBody = builder.build();
        Request.Builder reqBuilder = new Request.Builder();
        Request request = reqBuilder
                .url(UrlString.uploadImage)
                .post(requestBody)
                .build();

        Log.e("joker", "请求地址 " + UrlString.uploadImage);

        //   Log.d(TAG, "请求地址 " + Constant.BASE_URL + "/uploadimage");
        try{
            Response response = mOkHttpClient.newCall(request).execute();

            Log.e("joker", "响应码 " + response.code());

            //     Log.d(TAG, "响应码 " + response.code());
            if (response.isSuccessful()) {
                String resultValue = response.body().string();

                Log.e("joker", "响应体 " + resultValue);

                //         Log.d(TAG, "响应体 " + resultValue);
                return resultValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 服务器端返回json对象的信息
    public static JSONObject sendQueryStatistics(String url) {
        InputStreamReader isr = null;
        DataOutputStream dos = null;
        HttpURLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        JSONObject jsonObject = null;
        try {
            URL url1 = new URL(url);
            urlConn = (HttpURLConnection) url1.openConnection();
            urlConn.setDoInput(true);//设置输入流采用字节流
            urlConn.setDoOutput(true);//设置输出流采用字节流
            urlConn.setRequestMethod("POST");//post方法
            urlConn.setUseCaches(false);//缓存
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "utf-8");//缓存
            urlConn.connect();//连接服务器发送消息
            dos = new DataOutputStream(urlConn.getOutputStream());
//            dos.write((jo2.toString()).getBytes());//写数据
            dos.close();
            dos.flush();
            isr = new InputStreamReader(urlConn.getInputStream());
            bufferedReader = new BufferedReader(isr);
            String result = "";
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                result += readLine;
            }
            result = removeBOM(result);
            jsonObject = new JSONObject(result);
            bufferedReader.close();
            isr.close();
            urlConn.disconnect();//关闭连接
            return jsonObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jsonObject;
        }
    }

    // 出现编码问题
    public static final String removeBOM(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }
        if (data.startsWith("\ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }
}