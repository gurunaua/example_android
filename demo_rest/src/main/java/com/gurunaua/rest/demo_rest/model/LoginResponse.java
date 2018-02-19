package com.gurunaua.rest.demo_rest.model;

public class LoginResponse {

    public static String status_success = "00";
    public static String status_failed = "01";
    public static String status_error= "03";
    public static String status_wrong_username_or_pass = "04";


    private String username;
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
