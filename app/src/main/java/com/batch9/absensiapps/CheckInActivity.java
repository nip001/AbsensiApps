package com.batch9.absensiapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.batch9.absensiapps.entity.Absensi;
import com.batch9.absensiapps.service.ApiClient;
import com.batch9.absensiapps.service.ApiInterface;
import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.File;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtGeo,txtDesc;
    Button btnCheckIn;
    int requestCode = 1;
    String mediaPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        imageView = findViewById(R.id.imageView);
        txtGeo = findViewById(R.id.txtGeo);
        txtDesc = findViewById(R.id.txtDesc);
        btnCheckIn = findViewById(R.id.btnLoginCheckIn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent library = new Intent(getApplicationContext(), ImageSelectActivity.class);
                library.putExtra(ImageSelectActivity.FLAG_COMPRESS,true);
                library.putExtra(ImageSelectActivity.FLAG_CAMERA,true);
                library.putExtra(ImageSelectActivity.FLAG_GALLERY,true);
                startActivityForResult(library,1);
            }
        });

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FusedLocationProviderClient mFusedLocationProviderClient = LocationServices
                        .getFusedLocationProviderClient(CheckInActivity.this);

                mFusedLocationProviderClient.getLastLocation()
                        .addOnSuccessListener(CheckInActivity.this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if(location != null){
                                    Absensi absen = new Absensi();
                                    absen.setLokasiGps(location.getLatitude()+", "+location.getLongitude());
                                    absen.setUsername(getIntent().getStringExtra("username"));

                                    Gson gson = new Gson();
                                    String json = gson.toJson(absen);
                                    File file = new File(mediaPath);
                                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),file);
                                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",file.getName(),requestBody);
                                    RequestBody data = RequestBody.create(MediaType.parse("text/plain"),json);

                                    ApiInterface aInterface = ApiClient.getRetrofit().create(ApiInterface.class);
                                    Call<Absensi> call = aInterface.checkin(fileToUpload,data);
                                    call.enqueue(new Callback<Absensi>() {
                                        @Override
                                        public void onResponse(Call<Absensi> call, Response<Absensi> response) {
                                            txtDesc.setText("Login Foto Berhasil! ");
                                            txtGeo.setText("Geo Tag : " + location.getLatitude()+", "+location.getLongitude());
                                            Glide.with(CheckInActivity.this).load("https://cdn.pixabay.com/photo/2017/01/31/17/55/check-mark-2025986_960_720.png").into(imageView);

                                            btnCheckIn.setText("Done");
                                            btnCheckIn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    finish();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onFailure(Call<Absensi> call, Throwable t) {
                                            txtDesc.setText("Login Foto Gagal! ");
                                            txtGeo.setText("");
                                            Glide.with(CheckInActivity.this).load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Black_x.svg/1200px-Black_x.svg.png").into(imageView);

                                        }
                                    });
                                }
                            }
                        });

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){

            mediaPath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);

            imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
        }
    }
}