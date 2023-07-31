package com.example.finance_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class transactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        Intent intent = getIntent();
        String userid=intent.getStringExtra("user_id");
        RecyclerView recyclerView = findViewById(R.id.recyclerViewTransactions);
        TransactionAdapter adapter = new TransactionAdapter(Collections.emptyList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Toast.makeText(getApplicationContext(), " "+userid, Toast.LENGTH_SHORT).show();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.121.177:8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiservice api = retrofit.create(apiservice.class);
        Call<transactionresponse> call = api.fetchtransactions(userid);
        call.enqueue(new Callback<transactionresponse>() {
            @Override
            public void onResponse(Call<transactionresponse> call, Response<transactionresponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    transactionresponse tr = response.body();
                    List<usertransaction> transactionsList = tr.getTransactions();

                    RecyclerView recyclerView = findViewById(R.id.recyclerViewTransactions);
                    TransactionAdapter adapter = new TransactionAdapter(transactionsList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(transactions.this));
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "No transactions or response error", Toast.LENGTH_SHORT).show();
                    Log.e("RetrofitResponse", "Response Code: " + response.code());
                    Log.e("RetrofitResponse", "Error Body: " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<transactionresponse> call, Throwable t) {
                if (t instanceof HttpException) {
                    HttpException httpException = (HttpException) t;
                    int responseCode = httpException.code();
                    String errorBody = null;
                    try {
                        errorBody = httpException.response().errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("RetrofitError", "Response Code: " + responseCode + ", Error Body: " + errorBody);
                } else {
                    Log.e("RetrofitError", "API call failed: " + t.getMessage(), t);
                }
                Toast.makeText(getApplicationContext(), "Fialed Fetching", Toast.LENGTH_SHORT).show();
            }
        });
    }
}