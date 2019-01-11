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

public class conference_readDao {
    public static String[][] sendLoginRequest() {

        String[][] request=new String [1][1];
        request[0][0]="conference read error";
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());//解决机型适配问题
        HttpURLConnection connection=null;
        BufferedReader reader = null;
        try {
            URL url = new URL(static_connect+"conference_read.php");
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
          String backstr[][]=  parseJSONWithJSONObject(response.toString());
            return backstr;
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



    //解析json数据
    public static String[][] parseJSONWithJSONObject(String jsonData) {

        String [][] backerror = new String [1][1];
        backerror[0][0]="conference record get error";
        try
        {

            JSONObject JsonObject = new JSONObject(jsonData);//类似二维数组
             JSONArray gradeObject = JsonObject.getJSONArray("conference");
            String [][] conferencedata = new String [gradeObject.length()][8];

            for (int i=0; i < gradeObject.length(); i++)    {

                JSONObject jsonObject = gradeObject.getJSONObject(i);
               conferencedata[i][0]=jsonObject.getString("confer_id");
                conferencedata[i][1]= jsonObject.getString("con_name");//电话
                conferencedata[i][2] = jsonObject.getString("st_id");//密码
                conferencedata[i][3] = jsonObject.getString("order_time");
                conferencedata[i][4] = jsonObject.getString("start_time");
                conferencedata[i][5] = jsonObject.getString("end_time");
                conferencedata[i][6]=jsonObject.getString("room_name");
                conferencedata[i][7]=jsonObject.getString("ro_id");


            }
            System.out.println("这是conference赋值"+conferencedata[1][2]);
            return conferencedata;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return backerror;
    }
}






