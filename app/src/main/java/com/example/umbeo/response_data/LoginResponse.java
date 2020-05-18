package com.example.umbeo.response_data;

public class LoginResponse {
    private String status,token;
    private shopKeeper shopKeeper;

    public LoginResponse(String status, String token ,shopKeeper shopKeeper) {
        this.status = status;
        this.token=token;
        this.shopKeeper = shopKeeper;
    }

    public String getStatus() {
        return status;
    }


    public String getToken() {
        return token;
    }



    public shopKeeper getShopKeeper() {
        return shopKeeper;
    }
}
