package com.example.se2singleproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.btnSend);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final String hostname="se2-isys.aau.at";
                final int port=53212;

                Connect myCon = new Connect(getSID(), hostname, port);
                Thread serverConnection = new Thread(myCon);
                serverConnection.start();

                //Print response in GUI
                try {
                    serverConnection.join();        //Wait till Thread is finished
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Communicate with Server
                TextView ServerAnswer = findViewById(R.id.ServerAnswer);
                ServerAnswer.setText(myCon.getResponse());
            }
        });

        button=findViewById(R.id.btnADS);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String StudentID=getSID();
                int[] array = StringToIntArr(StudentID);
                int ADS =0;

                for(int i=0;i<array.length;i++) {
                    if (i % 2 == 0) {
                        ADS += array[i];
                    } else {
                        ADS -= array[i];
                    }
                }

                String result;
                if(ADS%2==0){
                    result="The alternating digit sum is an even number!";
                }
                else{
                    result="The alternating digit sum is an odd number!";
                }


                TextView ServerAnswer = findViewById(R.id.ServerAnswer);
                ServerAnswer.setText(result);
            }
        });

    }

    private String getSID(){
        final EditText inputSID = findViewById(R.id.StudentID);
        return inputSID.getText().toString();
    }

    private int[] StringToIntArr(String str){
        char [] list = str.toCharArray();
        int[] retV = new int[str.length()];

        for (int i = 0; i < str.length(); i++){
            retV[i] = list[i];
        }
        return retV;
    }
}


