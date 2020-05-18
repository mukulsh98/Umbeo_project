package com.example.umbeo.response_data;

public class loginrequest_response {
    private String status;
    private shopKeeper user;

    public loginrequest_response(String status, shopKeeper user) {
        this.status = status;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public shopKeeper getShopKeeper(){
        return user;
    }

}
