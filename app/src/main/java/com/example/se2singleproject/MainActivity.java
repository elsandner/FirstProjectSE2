package com.example.se2singleproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.net.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


   @RequiresApi(api = Build.VERSION_CODES.KITKAT)
   public void onClick(View view){
       System.out.println("Execute onClick");

        final EditText inputSID =(EditText) findViewById(R.id.StudentID);
        String StudentID=inputSID.getText().toString();

        TextView ServerAnswer = (TextView)findViewById(R.id.ServerAnswer);
        ServerAnswer.setText(StudentID);

    }







    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String askDate(){

        String hostname = "time.nist.gov";
        int port = 13;

        try (Socket socket = new Socket(hostname, port)) {

            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);

            int character;
            StringBuilder data = new StringBuilder();

            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }

            System.out.println(data);
            return data.toString();


        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }

        return "Hardcoded String TimeRequest";
    }


}


