package com.batch9.absensiapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.batch9.absensiapps.service.ApiClient;
import com.batch9.absensiapps.service.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edUsername, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        edPassword = findViewById(R.id.editPassword);
        edUsername = findViewById(R.id.editUsername);

        ApiInterface aInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Integer> call = aInterface.login(edUsername.getText().toString(),edPassword.getText().toString());
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.body() == 1){
                            Toast.makeText(MainActivity.this,"Password atau username salah",Toast.LENGTH_LONG);
                            Intent i = new Intent(MainActivity.this, MenuActivity.class);
                            i.putExtra("username",edUsername.getText().toString());
                            startActivity(i);

                        }else{
                            Toast.makeText(MainActivity.this,"Password atau username salah",Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        System.out.println(t);
                    }
                });

            }
        });
    }
}