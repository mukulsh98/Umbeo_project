package com.example.umbeo.response_data;

public class shop {
    private String id,name,address,district;
    public shop(String id, String name, String address, String district) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.district = district;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }



}
