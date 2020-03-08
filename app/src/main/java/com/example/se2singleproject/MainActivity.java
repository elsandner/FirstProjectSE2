package com.example.se2singleproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.*;
import java.io.*;
import java.util.Observable;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.btnRun);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                demoObservableFrom();

            }
        });
    }

    private void demoObservableFrom() {

        rx.Observable.from(new Integer[]{1,2,3}).subscribe(new Subscriber<Integer>(){

            @Override
            public void onCompleted() {
                
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("onNext",String.valueOf(integer));
            }
        });
    }




/*

   @RequiresApi(api = Build.VERSION_CODES.KITKAT)
   public void onClick(View view){
       System.out.println("Execute onClick");

        final EditText inputSID =(EditText) findViewById(R.id.StudentID);
        String StudentID=inputSID.getText().toString();

        TextView ServerAnswer = (TextView)findViewById(R.id.ServerAnswer);
        ServerAnswer.setText(StudentID);

    }

*/

}


