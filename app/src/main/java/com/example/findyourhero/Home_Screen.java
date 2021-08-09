package com.example.findyourhero;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home_Screen extends AppCompatActivity {
    List_details list_details;
   public static List<String> list_name=new ArrayList<>();
    public static List<String> list_img_url=new ArrayList<>();
    public static List<Integer> list_id=new ArrayList<>();
    public static List<String> male_name=new ArrayList<>();
    public static List<Integer> male_list_id=new ArrayList<>();
    public static List<String> male_list_img_url=new ArrayList<>();
    public static List<String> female_name=new ArrayList<>();
    public static List<Integer> female_list_id=new ArrayList<>();
    public static List<String> female_list_img_url=new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://akabab.github.io/superhero-api/api/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonInterface jsonInterface=retrofit.create(JsonInterface.class);
        Call<List<SuperHero>> call=jsonInterface.getSuperHero();
        call.enqueue(new Callback<List<SuperHero>>() {
            @Override
            public void onResponse(Call<List<SuperHero>> call, Response<List<SuperHero>> response) {
                if(!response.isSuccessful())
                    Toast.makeText(getApplicationContext(),"Response failed="+response,Toast.LENGTH_LONG).show();
                List<SuperHero> superHeroList=response.body();
                for(SuperHero superHero:superHeroList)
                {
                    list_name.add(superHero.getName());
                    list_id.add(superHero.getId());
                    list_img_url.add(superHero.getImages().getLg());
                    if(superHero.getAppearance().getGender().toLowerCase().equals("male")){
                        male_name.add(superHero.getName());
                        male_list_id.add(superHero.getId());
                        male_list_img_url.add(superHero.getImages().getLg());
                    }
                    if(superHero.getAppearance().getGender().toLowerCase().equals("female")){
                        female_name.add(superHero.getName());
                        female_list_id.add(superHero.getId());
                        female_list_img_url.add(superHero.getImages().getLg());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SuperHero>> call, Throwable t) {

            }

        });
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Home_Screen.this,MainActivity.class);
                startActivity(intent);
            }
        },5000);

    }
}
