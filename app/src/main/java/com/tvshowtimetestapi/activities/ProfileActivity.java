package com.tvshowtimetestapi.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.tvshowtimetestapi.R;



/**
 * Created by Kévin on 02/04/2016.
 */
public class ProfileActivity extends AppCompatActivity{

    private String name;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getUserInfo();

    }

    private void getUserInfo() {
        SharedPreferences sharedPref = getSharedPreferences("userData", Context.MODE_PRIVATE);
        name = sharedPref.getString("name", "Prénom");
        image = sharedPref.getString("image", "Oups");
        init();
    }

    private void init() {
        TextView nameTextView = (TextView) findViewById(R.id.textViewName);
        Picasso.with(ProfileActivity.this).load(image).into(((CircularImageView) findViewById(R.id.imageProfile)));
        nameTextView.setText(name);


    }

}
