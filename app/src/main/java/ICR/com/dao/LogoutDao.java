package ICR.com.dao;

import android.util.Log;

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

import ICR.com.activity.BaseActivity;

public class LogoutDao extends BaseActivity{
    static JSONObject jsonlogout=new JSONObject();
    public static void logoutPost(){


        try {
            //jsoncontainer.put("sta_name",sta_name);
            jsonlogout.put("tel",userTel);
         //   jsonlogout.put("user_password",user_password);
//            jsoncontainer.put("chinese",chinese);
            //           jsoncontainer.put("philosophy",philosophy);
            //  System.setOut(new PrintStream("D:/out.txt"));
            // System.out.println("jsoncontainer's data"+jsoncontainer);
        } catch (JSONException e) {
            e.printStackTrace();
        };
        executeHttpPost();
    }
    private static void executeHttpPost() {
        HttpURLConnection con=null;
        String path=static_connect+"logout.php";
        try {
            URL url=new URL(path);
            con= (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Connection", "keep-alive");
//           con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.connect();

            DataOutputStream out=new DataOutputStream(con.getOutputStream());
            //  InputStream in;
            //  in=out.OutputStream();
            out.writeBytes(jsonlogout.toString());

            out.flush();
            out.close();
            //    int i=out.size();
            // Log.isLoggable("out.flush", i);
            System.out.println("jsoncontainer's data in connect"+jsonlogout);
            if (con.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream in=con.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(in));
                StringBuilder sb=new StringBuilder();
                String line=null;
                while ((line=br.readLine())!=null){
                    sb.append(line+"\n");
                }
                // Log.i(TAG, "parseInfo: sb:"+sb.toString());//把错误输出来
                //   byte [] buf=new byte[in.available()];
                //in.read(buf);
                //  String str=new String(buf);
                //      Log.d("return message",str);


                Log.d("connect is ok?","success connect");

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
