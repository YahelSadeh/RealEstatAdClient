package org.example.realestateadsclient.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.realestateadsclient.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class GetFromServer {
    private Socket fromServer;
    Gson g = new GsonBuilder().create();
    int port;
    public GetFromServer() throws IOException {
        this.port = 12346;
        fromServer = new Socket("localhost",this.port);
    }

    public Map<String, Object> process() throws IOException {
        System.out.println("checkPoint3");
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.fromServer.getInputStream()));
        String line = reader.readLine();
        Request request = g.fromJson(line, Request.class);
        //String action = request.getAction();

        return request.getBody();
    }
}


