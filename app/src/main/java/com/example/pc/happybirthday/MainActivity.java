package com.example.pc.happybirthday;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder notification;
    public static final int UniqueId=123747;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);


        TextView textView= (TextView) findViewById(R.id.output);
        textView.setText("You are "+findDays()+ " days old!");
        Calendar thatDay =Calendar.getInstance();

        if((thatDay.get(Calendar.DAY_OF_MONTH)==23)&&(thatDay.get(Calendar.MONTH)==6)){

            notime();
        }
    }
    public Long findDays(){
        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DAY_OF_MONTH,23);
        thatDay.set(Calendar.MONTH,6); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, 2006);

        Calendar today = Calendar.getInstance();

        long diff = today.getTimeInMillis() - thatDay.getTimeInMillis();
        diff= diff/(24*60*60*1000);
        return diff;
    }
    public void notime(){
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setContentTitle("Happy Birthday Chota!");
        notification.setContentText("From Shivaji");
        notification.setTicker("Happy Birthday!");
        notification.setWhen(System.currentTimeMillis());

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(UniqueId,notification.build());
    }

}
