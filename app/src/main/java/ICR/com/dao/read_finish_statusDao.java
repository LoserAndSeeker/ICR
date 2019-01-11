package ICR.com.dao;




import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static ICR.com.activity.BaseActivity.static_connect;

public class read_finish_statusDao {
    static JSONObject jsoncontainer=new JSONObject();
    public static  String[][] participatepost(String room_name,String s_time){


        try {
            jsoncontainer.put("room_name",room_name);
            jsoncontainer.put("start_time",s_time);

        } catch (JSONException e) {
            e.printStackTrace();
        };
        String str[][]=executeHttpPost();
        System.out.println("输出返回数据名字：："+str[0][0]);
        return str;
    }
    private static String[][] executeHttpPost() {
        String s[][]=new String[1][1];
        s[0][0]="出错了你个菜鸡:execute";
        HttpURLConnection con=null;
        String path=static_connect+"readfinishstatus.php";
        try {
            URL url=new URL(path);
            con= (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Connection", "keep-alive");
            // con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//setRequestProperty设置http的请求头
            //con.setRequestProperty("Accept-Language", "zh-CN");

//           con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.connect();

            DataOutputStream out=new DataOutputStream(con.getOutputStream());
            //  InputStream in;
            //  in=out.OutputStream();
            // out.writeBytes(jsoncontainer.toString());
            out.write(jsoncontainer.toString().getBytes());     //上传中文数据
            out.flush();
            out.close();
            //    int i=out.size();
            // Log.isLoggable("out.flush", i);
            System.out.println("jsoncontainer's data in connect"+jsoncontainer);
            if (con.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream in=con.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(in));
                StringBuilder sb=new StringBuilder();
                String line=null;
                while ((line=br.readLine())!=null){
                    sb.append(line+"\n");
                }

                String backstr[][] =  parseJSONWithJSONObject(sb.toString());
                // Log.i(TAG, "parseInfo: sb:"+sb.toString());//把错误输出来
                //   byte [] buf=new byte[in.available()];
                //in.read(buf);
                //  String str=new String(buf);
                //      Log.d("return message",str);
                //System.out.println("返回数据：：：："+backstr[0][]);
                return backstr;


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String[][] parseJSONWithJSONObject(String jsonData) {
        System.out.println("正常运行：：："+jsonData);
        String s[][]=new String[1][1];
        s[0][0]="出错了你个菜鸡";
        try
        {

            JSONObject JsonObject = new JSONObject(jsonData);//类似二维数组

            JSONArray gradeObject = JsonObject.getJSONArray("participate");
            String nam[][]=new String[gradeObject.length()][2];
            for (int i=0; i < gradeObject.length(); i++)    {

                JSONObject jsonObject = gradeObject.getJSONObject(i);


                nam[i][0]=jsonObject.getString("sta_name");//员工姓名
                nam[i][1]=jsonObject.getString("re_in");
//                String english = jsonObject.getString("english");
                // String chinese = jsonObject.getString("chinese");
                // String philosophy = jsonObject.getString("philosophy");
                // Log.d("experiment",name+"'s grade "+math+" "+english+" "+chinese+" "+philosophy);


                //   System.out.println("id" + id + ";name" + name + ";version" + version);
            }
            return nam;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return s;
    }
}

