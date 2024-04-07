package org.example.realestateadsclient.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.realestateadsclient.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

public class GetFromServer {
    private Socket someClient;
    Gson g = new GsonBuilder().create();

    public GetFromServer(){}

    public Map process() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.someClient.getInputStream()));
        String line = reader.readLine();
        Request request = g.fromJson(line, Request.class);
        //String action = request.getAction();

        return request.getBody();
    }

}
