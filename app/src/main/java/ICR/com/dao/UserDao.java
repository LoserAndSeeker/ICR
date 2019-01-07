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

public class UserDao extends BaseActivity{
    public static int sendLoginRequest(String tel,String password) {
                 int request=0;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());//解决机型适配问题
                HttpURLConnection connection=null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://192.168.43.210/ICR_connect/login.php");
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
                   request= parseJSONWithJSONObject(response.toString(),tel,password);
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
    public static int parseJSONWithJSONObject(String jsonData,String tel,String password) {
        try
        {

            JSONObject JsonObject = new JSONObject(jsonData);//类似二维数组

            JSONArray gradeObject = JsonObject.getJSONArray("icruser");
            for (int i=0; i < gradeObject.length(); i++)    {

                JSONObject jsonObject = gradeObject.getJSONObject(i);

                String tl = jsonObject.getString("tel");//电话
                String pw = jsonObject.getString("user_password");//密码
                String nam=jsonObject.getString("sta_name");//员工姓名
                String isad=jsonObject.getString("is_admin");//是否为管理员
                String sti=jsonObject.getString("staff_id");  //员工id
                String posi=jsonObject.getString("position");//员工职称
                String emi=jsonObject.getString("email");//员工邮箱
                String sx=jsonObject.getString("sex");//员工性别

//                String english = jsonObject.getString("english");
               // String chinese = jsonObject.getString("chinese");
               // String philosophy = jsonObject.getString("philosophy");
               // Log.d("experiment",name+"'s grade "+math+" "+english+" "+chinese+" "+philosophy);
                if(tl.equals(tel)&&pw.equals(password))
                {
                    static_user_name=nam;
                    static_user_postition=posi;
                    static_user_email=emi;
                    static_user_sex=sx;
                    static_user_id=sti;
                    static_user_isadmin=isad;


                    return 1;
                }

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






