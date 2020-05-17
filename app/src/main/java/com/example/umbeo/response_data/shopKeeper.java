package com.example.umbeo.response_data;

public class shopKeeper {
    private String id,name,email,phone;
    public shopKeeper(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;

    }

    public String getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    public void setName(String name){
        this.name=name;
    }
}
