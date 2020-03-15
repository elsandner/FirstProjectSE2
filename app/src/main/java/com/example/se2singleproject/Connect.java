package com.example.se2singleproject;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.net.*;
import java.io.*;

public class Connect implements java.lang.Runnable{
    private String sid;
    private String hostname;
    private int port;
    private String response="No response till now";

    public Connect(String sid, String hostname, int port){
        this.sid=sid;
        this.hostname=hostname;
        this.port=port;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void run() {

        try (Socket socket = new Socket(hostname, port)) {

            //send StudentID to the Server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(sid); //Sends Bytestream to the Server

            //Read the Response
            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);

            //Build String
            int character;
            StringBuilder data = new StringBuilder();
            while ((character = reader.read()) != -1) {
                data.append((char) character);  //append = hinzufügen
            }
            response=data.toString();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    public String getResponse() {
        return response;
    }
}
