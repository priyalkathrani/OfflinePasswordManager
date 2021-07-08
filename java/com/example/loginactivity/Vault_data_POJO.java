package com.example.loginactivity;

public class Vault_data_POJO {
    String id,password,username,application;

    public Vault_data_POJO(String password, String username, String application) {
        this.password = password;
        this.username = username;
        this.application = application;
    }

    public Vault_data_POJO() {
    }
}
