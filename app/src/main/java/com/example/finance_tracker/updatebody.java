package com.example.finance_tracker;

import androidx.annotation.Nullable;

public class updatebody {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;

    public updatebody(@Nullable String username, @Nullable String password) {
        this.username = username;
        this.password = password;
    }
}
