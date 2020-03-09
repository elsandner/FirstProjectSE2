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

        button=(Button)findViewById(R.id.btnRun);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Get Student-ID out of GUI
                final EditText inputSID = findViewById(R.id.StudentID);
                String StudentID=inputSID.getText().toString();


                //Communicate with Server
                final String hostname="se2-isys.aau.at";
                final int port=53212;


                Connect myCon = new Connect(StudentID, hostname, port);
                Thread serverConnection = new Thread(myCon);
                serverConnection.start();

                //Print response in GUI
                try {
                    serverConnection.join();        //Wait till Thread is finished
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TextView ServerAnswer = findViewById(R.id.ServerAnswer);
                ServerAnswer.setText(myCon.getResponse());
            }
        });
    }


}


