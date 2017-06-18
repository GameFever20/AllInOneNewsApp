package utils;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Created by bunny on 30/05/17.
 */

public class UrlShortner extends AsyncTask<String,String,String> {

    String longUrl="https://play.google.com/store/apps/details?id=app.news.allinone.craftystudio.allinonenewsapp";

    UrlShortnerListner urlShortnerListner;

    public UrlShortner(String longUrl , UrlShortnerListner urlShortnerListner) {
        //
         this.longUrl = longUrl;
        this.urlShortnerListner =urlShortnerListner;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //progressBar.setVisibility(View.GONE);
        System.out.println("JSON RESP:" + s);
        String response=s;
        try {
            JSONObject jsonObject=new JSONObject(response);
            String id=jsonObject.getString("id");
            System.out.println("ID:"+id);
            urlShortnerListner.onUrlShort(id ,longUrl);
        } catch (JSONException e) {
            e.printStackTrace();
            urlShortnerListner.onCancel(longUrl);
        }catch (Exception e){
            e.printStackTrace();
            urlShortnerListner.onCancel(longUrl);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader reader;
        StringBuffer buffer;
        String res=null;
        String json = "{\"longUrl\": \""+longUrl+"\"}";
        try {
            URL url = new URL("https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyBj8KJ9cPZ3EdjGfOyZ3EZn3b1yVan6uQw");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(40000);
            con.setConnectTimeout(40000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            int status=con.getResponseCode();
            InputStream inputStream;
            if(status==HttpURLConnection.HTTP_OK)
                inputStream=con.getInputStream();
            else
                inputStream = con.getErrorStream();

            reader= new BufferedReader(new InputStreamReader(inputStream));

            buffer= new StringBuffer();

            String line="";
            while((line=reader.readLine())!=null)
            {
                buffer.append(line);
            }

            res= buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;




    }

    public interface UrlShortnerListner{

        public void onUrlShort(String shortUrl , String longUrl);
        public void onCancel(String longUrl);


    }
}
