package com.tvshowtimetestapi.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.tvshowtimetestapi.R;
import com.tvshowtimetestapi.Service;
import com.tvshowtimetestapi.classItems.Profile;
import com.tvshowtimetestapi.classItems.ResultUser;
import com.tvshowtimetestapi.classItems.User;
import com.tvshowtimetestapi.fragments.ListExplore;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private SharedPreferences sharedPreferences;
    private Service service;
    private Profile profile = new Profile();
    private User mUser;
    private ResultUser mResultUser;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        mDrawer.setDrawerListener(drawerToggle);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        getInfoUser();
        setupDrawerContent(nvDrawer);
        //Make on clik listener to go to profile
        nvDrawer.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        //makeNavHeaderBackground();
        setupDrawerContent(nvDrawer);
        loadListExplore();
    }

    private void getInfoUser() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        service = new RestAdapter.Builder().
                setEndpoint(Service.ENDPOINT).
                setConverter(new GsonConverter(gson)).
                build().
                create(Service.class);
        service.getUser(new Callback<ResultUser>() {
            @Override
            public void success(ResultUser resultUser, Response response) {
                mResultUser = resultUser;
                mUser = mResultUser.getUser();
                setNavHeader();
            }

            @Override
            public void failure(RetrofitError error) {
                errorDialog("Echec de la récupération de données via le serveur distant");
                finish();
            }
        });
    }

    private void setNavHeader() {
        ((TextView)nvDrawer.getHeaderView(0).findViewById(R.id.nav_header_text))
                .setText(mUser.getName());
        Picasso.with(MainActivity.this).load(mUser.getImage())
                .into(((CircularImageView) nvDrawer.getHeaderView(0)
                        .findViewById(R.id.imageNavProfile)));
        registerUserData();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.goldengate);
        nvDrawer.getHeaderView(0).setBackground(new BitmapDrawable(getResources(), bitmap));
    }

    private void registerUserData() {
        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", mUser.getName());
        editor.putString("image", mUser.getImage());
        editor.putString("id", mUser.getIdUser().toString());
        editor.apply();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void loadListExplore() {
        fragment = new ListExplore();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.commit();
    }

    public void selectDrawerItem(MenuItem menuItem) {

        Intent intent;
        switch(menuItem.getItemId()) {
            case R.id.profile:
                intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.explore:
                intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }

        // Highlight the selected item, update the title, and close the drawer
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
        return actionBarDrawerToggle;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    public void errorDialog(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Erreur");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }



}
