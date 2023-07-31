package com.example.finance_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class profile_update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        TextView usernamelabel = findViewById(R.id.usernameLabel);
        TextView passwordlabel= findViewById(R.id.passwordLabel);
        EditText username = findViewById(R.id.usernameEditText);
        EditText password = findViewById(R.id.passwordEditText);
        Button save = findViewById(R.id.saveButton);
        Intent intent = getIntent();
        String userid= intent.getStringExtra("user_iid");
        usernamelabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernamelabel.setVisibility(View.GONE);
                username.setVisibility(View.VISIBLE);
            }
        });

        passwordlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordlabel.setVisibility(View.GONE);
                password.setVisibility(View.VISIBLE);
            }
        });
        Toast.makeText(getApplicationContext(),""+userid,Toast.LENGTH_SHORT).show();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.121.177:8080")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                apiservice api = retrofit.create(apiservice.class);

                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(user) && TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Please provide a new username or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                

                updatebody body = new updatebody(user, pass);
                Call<ResponseBody> call = api.update(userid,body);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            String token = null;
                            try {
                                token = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), ""+token, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
        }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
    }
}
