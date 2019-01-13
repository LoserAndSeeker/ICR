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


import ICR.com.activity.BaseActivity;


public class havereservedDao extends BaseActivity {
    public static String[][] sendLoginRequest(int i) {
       System.out.println("测试是否开始访问数据");
        String[][] request=new String [1][1];
        request[0][0]="conference read error";
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());//解决机型适配问题
        HttpURLConnection connection=null;
        BufferedReader reader = null;
        try {
            URL url;
            if(i==0) {
                url = new URL(static_connect+"havereserved.php");
            }
            else if(i==1){
                url = new URL(static_connect+"have_finished.php");
            }else
                url = new URL(static_connect+"havejoined.php");
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
            int j=0;
            JSONObject JsonObject = new JSONObject(jsonData);//类似二维数组
            JSONArray gradeObject = JsonObject.getJSONArray("conference");
            String [][] conferencedata = new String [gradeObject.length()][5];
          //  static_user_id="5";
            for (int i=0; i < gradeObject.length(); i++)    {
//会议室名，会议名称，会议id，开始时间，结束时间

                JSONObject jsonObject = gradeObject.getJSONObject(i);

                String st_id = jsonObject.getString("st_id");
                System.out.println("看看stid是否有效"+st_id  +static_user_id);
           /*     conferencedata[i][0] = jsonObject.getString("room_name");
                conferencedata[i][1] = jsonObject.getString("con_name");//电话
                conferencedata[i][2] = jsonObject.getString("confer_id");//密码
                conferencedata[i][3] = jsonObject.getString("start_time");
                conferencedata[i][4] = jsonObject.getString("end_time");*/
              if(st_id.equals(static_user_id)){
                    conferencedata[j][0] = jsonObject.getString("room_name");
                    conferencedata[j][1] = jsonObject.getString("con_name");//电话
                    conferencedata[j][2] = jsonObject.getString("confer_id");//密码
                    conferencedata[j][3] = jsonObject.getString("start_time");
                    conferencedata[j][4] = jsonObject.getString("end_time");
                    j++;

                }
                System.out.println("看看数组长度多少"+j+static_user_name);

            }
            String[][] ss = new String[j][5];
            for(int i=0;i<j;i++){
                ss[i][0] = conferencedata[i][0];
                ss[i][1] = conferencedata[i][1];
                ss[i][2] = conferencedata[i][2];
                ss[i][3] = conferencedata[i][3];
                ss[i][4] = conferencedata[i][4];
            }

            System.out.println("这是conference赋值"+conferencedata[0][2]);
            return ss;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return backerror;
    }
}







