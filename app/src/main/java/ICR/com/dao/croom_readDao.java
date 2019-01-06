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

public class croom_readDao{
    public static int sendLoginRequest() {
        int request=0;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());//解决机型适配问题
        HttpURLConnection connection=null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://192.168.43.210/ICR_connect/croom_read.php");
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
            parseJSONWithJSONObject(response.toString());
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
        backerror[0][0]="croom status get error";
        try
        {

            JSONObject JsonObject = new JSONObject(jsonData);//类似二维数组
            JSONArray gradeObject = JsonObject.getJSONArray("conference");
            String [][] croom = new String [gradeObject.length()][6];

            for (int i=0; i < gradeObject.length(); i++)    {

                JSONObject jsonObject = gradeObject.getJSONObject(i);
                croom[i][0]=jsonObject.getString("room_id");
                croom[i][1]= jsonObject.getString("room_name");//电话
                croom[i][2] = jsonObject.getString("capacity");//密码
                croom[i][3] = jsonObject.getString("authority");
                croom[i][4] = jsonObject.getString("address");
                croom[i][5] = jsonObject.getString("equipment");



                // Log.d("experiment",name+"'s grade "+math+" "+english+" "+chinese+" "+philosophy);


                //   System.out.println("id" + id + ";name" + name + ";version" + version);
            }
            return croom;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return backerror;
    }
}






