package com.example.d_eventbus;

public class Message {
    private String account;
    private String password;


    public Message(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
