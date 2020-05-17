package com.example.umbeo.response_data;

public class UpdateResponse {
    private String status;
    private shopKeeper shopKeeper;

    public UpdateResponse(String status, shopKeeper shopKeeper) {
        this.status = status;

        this.shopKeeper = shopKeeper;
    }

    public String getStatus() {
        return status;
    }



    public shopKeeper getShopKeeper() {
        return shopKeeper;
    }
}
