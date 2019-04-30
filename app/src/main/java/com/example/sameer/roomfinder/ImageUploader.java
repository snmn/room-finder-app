package com.example.sameer.roomfinder;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
/*
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
*/
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;

/**
 * Created by Sameer on 7/31/2016.
 */
public class ImageUploader{

    private static final String TAG = "ImageUploader";
    MyTask mytask;

    public void uploadImage(Context context, String fileName, final String imagePath) {


        this.mytask= new MyTask();
        mytask.execute(imagePath);
    }


        class MyTask extends AsyncTask<String,String,String> {
            @Override
            protected void onPreExecute() {
               // Toast.makeText(getApplicationContext(), "uploading..",Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {
                int serverResponseCode = 0;
                String imagePath=params[0];
                Log.i(TAG,"imagepath:"+imagePath);
                HttpURLConnection connection;
                DataOutputStream dataOutputStream;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                String status="failed";
                String size;
                int bytesRead,bytesAvailable,bufferSize;
                long fileSize;
                String aFileName,type;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
                File selectedFile = new File(params[0]);
                Log.i(TAG,"length:"+selectedFile.length());

                String[] parts = params[0].split("/");
                String SERVER_URL="http://192.168.137.1/roomfinder/upload.php";
                try{
                    final String fileName = parts[parts.length-1];
                    String[] part=fileName.split("\\.");
                    aFileName=part[0];
                    type=part[1];

                    Log.i(TAG,"filename:"+fileName);
                    Log.i(TAG,"afilename:"+aFileName);
                    Log.i(TAG,"type:"+type);
                    FileInputStream fileInputStream = new FileInputStream(selectedFile);
                    URL url = new URL(SERVER_URL);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);//Allow Inputs
                    connection.setDoOutput(true);//Allow Outputs
                    connection.setUseCaches(false);//Don't use a cached Copy
//    System.setProperty("http.keepAlive", "false");
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                    connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    connection.setRequestProperty("uploaded_file",params[0]);
                    Log.i(TAG,"connected");
                    Log.i(TAG,"imagepath:"+params[0]);
                     dataOutputStream = new DataOutputStream(connection.getOutputStream());

                    //writing bytes to data outputstream
                    dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                            + params[0] + "\"" + lineEnd);

                    dataOutputStream.writeBytes(lineEnd);


                    //returns no. of bytes present in fileInputStream
                    bytesAvailable = fileInputStream.available();
                    //selecting the buffer size as minimum of available bytes or 1 MB
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    //setting the buffer as byte array of size of bufferSize
                    buffer = new byte[bufferSize];

                    //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);


                    //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                    while (bytesRead > 0){
                        //write the bytes read from inputstream
                        dataOutputStream.write(buffer,0,bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable,maxBufferSize);
                        bytesRead = fileInputStream.read(buffer,0,bufferSize);
                    }

                    dataOutputStream.writeBytes(lineEnd);
                    dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    serverResponseCode = connection.getResponseCode();
                    String serverResponseMessage = connection.getResponseMessage();

                    Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                    //response code of 200 indicates the server status OK
                    if(serverResponseCode == 200){
                        status="responsed";
                    }




                    //closing the input and output streams
                    fileInputStream.close();
                    dataOutputStream.flush();
                    dataOutputStream.close();

                }catch(Exception e){
                    status=e.getMessage();
                }

                return status;
            }
            @Override
            protected void onPostExecute(String result) {
                // TODO Auto-generated method stub
               Log.i(TAG,"result:"+result);
               // Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            }
        }
    }


