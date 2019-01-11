package ICR.com.dao;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static ICR.com.activity.BaseActivity.static_connect;

public class name_equal_tel {
    public static int sendLoginRequest(String tel,String sta_name) {
        int request=0;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());//解决机型适配问题
        HttpURLConnection connection=null;
        BufferedReader reader = null;
        try {
            URL url = new URL(static_connect+"name_equal_tel.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream in = connection.getInputStream();
            reader= new BufferedReader((new InputStreamReader(in)));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = reader.readLine())!=null){
                response.append(line);
            }
            request= parseJSONWithJSONObject(response.toString(),tel,sta_name);
        }

        catch (Exception e)  {e.printStackTrace();}
        finally
        {
            if(reader!=null){
                try
                {reader.close(); }
                catch (IOException e){e.printStackTrace();}
            }
            if(connection!=null){connection.disconnect();}
        }
        return request;
    }

   /* private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("show","name+math+english+chinese+philosophy");
                // responseText.setText(response);

                parseJSONWithJSONObject(response);//解析数据
            }
        });
    }*/

    //解析json数据
    public static int parseJSONWithJSONObject(String jsonData,String tel,String sta_name) {
        try
        {

            JSONObject JsonObject = new JSONObject(jsonData);//类似二维数组

            JSONArray gradeObject = JsonObject.getJSONArray("icruser");
            for (int i=0; i < gradeObject.length(); i++)    {

                JSONObject jsonObject = gradeObject.getJSONObject(i);

                String tl = jsonObject.getString("tel");//电话
                String sn = jsonObject.getString("sta_name");//姓名
//                String english = jsonObject.getString("english");
                // String chinese = jsonObject.getString("chinese");
                // String philosophy = jsonObject.getString("philosophy");
                // Log.d("experiment",name+"'s grade "+math+" "+english+" "+chinese+" "+philosophy);
                if(tl.equals(tel)&&sn.equals(sta_name))
                    return 1;

                //   System.out.println("id" + id + ";name" + name + ";version" + version);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}






