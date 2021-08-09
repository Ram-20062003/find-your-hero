package com.example.findyourhero;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link activity_fragment_male#newInstance} factory method to
 * create an instance of this fragment.
 */
public class activity_fragment_male extends Fragment {
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public activity_fragment_male() {
        super(R.layout.fragment_activity_male);
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment activity_fragment_male.
     */
    // TODO: Rename and change types and number of parameters
    public static activity_fragment_male newInstance(String param1, String param2) {
        activity_fragment_male fragment = new activity_fragment_male();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (name != null)
            name.clear();
        if (id != null)
            id.clear();
        if (image_url != null)
            image_url.clear();
        View view= inflater.inflate(R.layout.fragment_activity_male, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerview_adapter = new RecyclerView_Adapter(Home_Screen.male_name, Home_Screen.male_list_id, Home_Screen.male_list_img_url);
        recyclerView.setAdapter(recyclerview_adapter);
        editText = (EditText) view.findViewById(R.id.editTextTextPersonName);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().isEmpty()) {
                    editText.clearFocus();
                    recyclerview_adapter = new RecyclerView_Adapter(Home_Screen.male_name, Home_Screen.male_list_id, Home_Screen.male_list_img_url);
                    recyclerView.setAdapter(recyclerview_adapter);
                }

                if (!s.toString().trim().isEmpty()) {
                    filter_search(s.toString().toLowerCase().trim());
                    recyclerview_adapter = new RecyclerView_Adapter(name, id, image_url);
                    recyclerView.setAdapter(recyclerview_adapter);
                }
            }
        });

        return view;
    }
    private void filter_search(String trim) {
        //  Toast.makeText(MainActivity.this, trim, Toast.LENGTH_SHORT).show();

        if (name != null)
            name.clear();
        if (id != null)
            id.clear();
        if (image_url != null)
            image_url.clear();
        for (int i = 0; i < Home_Screen.male_name.size(); i++) {

            if (Home_Screen.male_name.get(i).toLowerCase().contains(trim)||Home_Screen.male_list_id.get(i).toString().contains(trim)) {

                name.add(Home_Screen.male_name.get(i));
                id.add(Home_Screen.male_list_id.get(i));
                image_url.add(Home_Screen.male_list_img_url.get(i));
            }
        }

    }
}