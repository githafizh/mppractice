package com.example.mppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView hdr;
    boolean t = true;
    int i = 0;
    File path, file;
    FileOutputStream stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        path = Environment.getExternalStorageDirectory();

        file = new File(path, "abc.txt");
        //tmr();
//        try {
//            String rootPath = Environment.getExternalStorageDirectory()
//                    .getAbsolutePath() + "/MyFolder/";
//            File root = new File(rootPath);
//            if (!root.exists()) {
//                root.mkdirs();
//            }
//            File f = new File(rootPath + "mttext.txt");
//            if (f.exists()) {
//                f.delete();
//            }
//            f.createNewFile();
//
//            FileOutputStream out = new FileOutputStream(f);
//            String str = "data";
//            out.write(str.getBytes());
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
        try {
            stream = new FileOutputStream(file);
            stream.write("abc 123".getBytes());
            stream.close();
        } catch (IOException ioe) {
            Log.d("ioe",ioe.toString());
        }
        hdr = findViewById(R.id.hdr);
        hdr.setText(path.toString());
        /*hdr.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                int x = (int)event.getX();
                int y = (int)event.getY();
                hdr.setText("X: " + x + ", Y: " + y);
                return true;
            }
        });*/
    }

    private void tmr(){
        t = true;
        Handler handler = new Handler();
        Runnable runnable = new Runnable(){
            public void run() {
                while (t) {
                    try {
                        i++;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        public void run() {
                            hdr.setText("" + i);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    public void thr(View view) {
    tmr();
    }
}