package com.example.findyourhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> list_name, list_image;
    List<Integer> list_id;
    List<String> name = new ArrayList<>();
    List<String> image_url = new ArrayList<>();
    List<Integer> id = new ArrayList<>();
    List<List_details> list_filter = new ArrayList<>();
    String c = "";
    List_details list_details;
    EditText editText;
    RecyclerView_Adapter recyclerview_adapter;
    Fragment fragment_all,fragment_male,fragment_female;


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            fragment_all=new activity_fragment_all();
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView_All, activity_fragment_all.class, null)
                    .commit();
        }
        list_id = new ArrayList<>();
        list_name = new ArrayList<>();
        list_image = new ArrayList<>();
        Log.d(TAG, "Length" + Home_Screen.list_name.get(0));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.drawer,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.all_hero:
                Toast.makeText(getApplicationContext(),"All",Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainerView_All, activity_fragment_all.class, null)
                        .commit();
                break;
            case R.id.male_hero:
                Toast.makeText(getApplicationContext(),"Male",Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().remove(fragment_all).commit();
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView_Male,activity_fragment_male.class,null).commit();
                break;
            case R.id.female_heros:
                Toast.makeText(getApplicationContext(),"Female",Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainerView_Female,activity_fragment_female.class,null).commit();
                break;
            case R.id.exit:
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
