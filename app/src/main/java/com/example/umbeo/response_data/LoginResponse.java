package com.example.umbeo.response_data;

public class LoginResponse {
    private String status;
    private shopKeeper shopKeeper;

    public LoginResponse(String status,  shopKeeper shopKeeper) {
        this.status = status;

        this.shopKeeper = shopKeeper;
    }

    public String getStatus() {
        return status;
    }

    /*
    public String getToken() {
        return token;
    }


     */
    public shopKeeper getShopKeeper() {
        return shopKeeper;
    }
}
