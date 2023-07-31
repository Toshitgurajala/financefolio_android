package com.example.finance_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finance_tracker.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registrationscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registrationscreen);


        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String username = usernameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
                    String phoneNumber = phoneEditText.getText().toString();

                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.121.177:8080")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

                    apiservice apireg = retrofit.create(apiservice.class);
                    loginbody registerbody = new loginbody(username, password);
                    Call<ResponseBody> call = apireg.register(registerbody);
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
                                Toast.makeText(getApplicationContext(), "" + token, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(registrationscreen.this,loginscreen.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("Registration Error", t.getMessage());
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
