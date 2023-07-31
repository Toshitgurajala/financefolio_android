package com.example.finance_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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

public class homepage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homepage);
        Intent intent = getIntent();
        TextView username = findViewById(R.id.username);
       String userid= intent.getStringExtra("userid");
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.121.177:8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiservice api = retrofit.create(apiservice.class);
        Call<ResponseBody> call = api.getusername(userid);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String u = null;
                    try {
                        u = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    username.setText("Welcome, "+u);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Error Fetching username", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Registration Error", t.getMessage());
            }
        });

        EditText phonenumber = findViewById(R.id.phoneEditText);
        EditText name = findViewById(R.id.upiIdEditText);
        EditText reason = findViewById(R.id.reasonEditText);
        EditText amount = findViewById(R.id.amountEditText);
        RadioButton phonepe = findViewById(R.id.phonePeRadioButton);
        RadioButton googlepay = findViewById(R.id.googlePeRadioButton);
        RadioButton other = findViewById(R.id.otherUpiRadioButton);
        ImageView profile = findViewById(R.id.profileMenu);
    ImageView transactions = findViewById(R.id.transactionsMenu);

    transactions.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(homepage.this,transactions.class);
           intent.putExtra("user_id",userid);
            startActivity(intent);
        }
    });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage.this,profile_update.class);
           intent.putExtra("user_iid",userid);
                startActivity(intent);
            }
        });





    }
}
