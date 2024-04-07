package org.example.realestateadsclient.server;

import com.google.gson.Gson;
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
    public static void saveToServer(Request request) {
        PrintWriter writer = null;
        try {
            Socket myServer = new Socket("localhost", 12346);
            writer = new PrintWriter(myServer.getOutputStream(), true);
            // Convert the body to JSON
            Gson gson = new Gson();
            String jsonBody = gson.toJson(request);
            writer.println(jsonBody);
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
