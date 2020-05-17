package com.example.umbeo.response_data;

public class loginrequest_response {
    private String status,token;

    public loginrequest_response(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
