package com.example.findyourhero;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class hero_stats extends AppCompatActivity {
    TextView t_details;
    ImageView image;
    String output="";
    ProgressBar progressBar;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        t_details=(TextView)findViewById(R.id.hero_details);
        image=(ImageView)findViewById(R.id.hero_profile);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        Intent intent=getIntent();
        String chk=intent.getStringExtra("name");
        Toast.makeText(getApplicationContext(),chk,Toast.LENGTH_SHORT).show();
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://akabab.github.io/superhero-api/api/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonInterface jsonInterface=retrofit.create(JsonInterface.class);
        Call<List<SuperHero>> call=jsonInterface.getSuperHero();
        call.enqueue(new Callback<List<SuperHero>>() {
            @Override
            public void onResponse(Call<List<SuperHero>> call, Response<List<SuperHero>> response) {
                List<SuperHero> superHeroList=response.body();
                for (SuperHero superHero : superHeroList) {
                    if (superHero.getName().equals(chk)) {
                        Picasso.get().load(superHero.getImages().getLg()).resize(276, 249).into(image);
                    }
                }
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<List<SuperHero>> call, Throwable t) {

            }
        });
        Call<List<SuperHero>> call_details=jsonInterface.getSuperHero();
        call_details.enqueue(new Callback<List<SuperHero>>() {
            @Override
            public void onResponse(Call<List<SuperHero>> call, Response<List<SuperHero>> response) {
                List<SuperHero> superHeroList = response.body();
                for (SuperHero superHero : superHeroList) {
                    if(superHero.getName().equals(chk))
                    {
                        if (superHero.getSlug() != null)
                            output += "SLUG :-" + "\t" + superHero.getSlug() + "\n \n";
                        if (superHero.getPowerstats() != null) {
                            output += "POWERSTATS:-" + "\n";
                            if (superHero.getPowerstats().getIntelligence() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Intelligence- \t" + superHero.getPowerstats().getIntelligence() + "\n";
                            if (superHero.getPowerstats().getStrength() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Strength- \t" + superHero.getPowerstats().getStrength() + "\n";
                            if (superHero.getPowerstats().getDurability() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Durablity- \t" + superHero.getPowerstats().getDurability() + "\n";
                            if (superHero.getPowerstats().getPower() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Power- \t" + superHero.getPowerstats().getPower() + "\n";
                            if (superHero.getPowerstats().getCombat() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Combat- \t" + superHero.getPowerstats().getCombat() + "\n";
                            output += "\n";
                        }
                        if (superHero.getAppearance() != null) {
                            output += "APPEARANCE:-" + "\n";
                            if (superHero.getAppearance().getGender() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Gender- \t" + superHero.getAppearance().getGender() + "\n";
                            if (superHero.getAppearance().getRace() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Race- \t" + superHero.getAppearance().getRace() + "\n";
                            if (superHero.getAppearance().getHeight() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Height- \t" + superHero.getAppearance().getHeight().get(0) + "inches" + ",\t" + superHero.getAppearance().getHeight().get(1) + "\n";
                            if (superHero.getAppearance().getWeight() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Weight- \t" + superHero.getAppearance().getWeight().get(0) + ",\t" + superHero.getAppearance().getWeight().get(1) + "\n";
                            if (superHero.getAppearance().getEyeColor() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Eye-color- \t" + superHero.getAppearance().getEyeColor() + "\n";
                            if (superHero.getAppearance().getHairColor() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Hair-Color- \t" + superHero.getAppearance().getHairColor() + "\n";
                            output += "\n";
                        }
                        if (superHero.getBiography() != null) {
                            output += "BIOGRAPHY :-" + "\n";
                            if (superHero.getBiography().getFullName() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Full name- \t" + superHero.getBiography().getFullName() + "\n";
                            if (superHero.getBiography().getAlterEgos() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Alter-Egos- \t" + superHero.getBiography().getAlterEgos() + "\n";
                            if (superHero.getBiography().getAliases() != null) {
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Aliases-";
                                for (int i = 0; i < superHero.getBiography().getAliases().size(); i++) {
                                    output += " \t" + superHero.getBiography().getAliases().get(i) + ",";
                                }
                            }
                            if (superHero.getBiography().getPlaceOfBirth() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Place of birth- \t" + superHero.getBiography().getPlaceOfBirth() + "\n";
                            if (superHero.getBiography().getFirstAppearance() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "First Appearance- \t" + superHero.getBiography().getFirstAppearance() + "\n";
                            if (superHero.getBiography().getPublisher() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Publisher- \t" + superHero.getBiography().getPublisher() + "\n";
                            if (superHero.getBiography().getAlignment() != null)
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Alignment- \t" + superHero.getBiography().getAlignment() + "\n";
                            output += "\n";
                        }
                        if (superHero.getWork().getOccupation() != null)
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Occupation- \t" + superHero.getWork().getOccupation() + "\n ";
                        if (superHero.getWork().getBase() != null)
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Base- \t" + superHero.getWork().getBase() + "\n \n";
                        output += "CONNECTIONS :-" + "\n";
                        if (superHero.getConnections().getGroupAffiliation() != null)
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Group Affiliations- \t" + superHero.getConnections().getGroupAffiliation() + "\n ";
                        if (superHero.getConnections().getRelatives() != null)
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Relatives- \t" + superHero.getConnections().getRelatives() + "\n \n";
                        t_details.setText(output);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<SuperHero>> call, Throwable t) {

            }
        });

    }
}
