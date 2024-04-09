package org.example.realestateadsclient.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.realestateadsclient.model.Request;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SendToServer {
    String action;
    Map<String, Object> body;

    public SendToServer(String action) {
        this.action = action;
        this.body = new HashMap<>();
    }
    public SendToServer(String action, Map<String, Object> body) {
        this.action = action;
        this.body = body;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public Map<String, Object> getBody() {
        return body;
    }
    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    private static final Gson gson = new GsonBuilder().create();
    public static void saveToServer(Request request) {
        System.out.println("checkPoint1");
        //Socket socket = null;
        try (
                // Create a socket connection to the server
                Socket myServer = new Socket("localhost", 12346);
                // Create a PrintWriter for writing data to the socket's output stream
                PrintWriter writer = new PrintWriter(myServer.getOutputStream(), true);
        ) {
            // Convert the request object to JSON
            String jsonRequest = gson.toJson(request);
            System.out.println("checkPoint2");
            // Send the JSON data to the server
            writer.println(jsonRequest);
            writer.flush(); // Flush the buffer to ensure data is sent immediately
        } catch (IOException e) {
            // Handle exceptions such as connection failure
            e.printStackTrace();
        }
    }
}
