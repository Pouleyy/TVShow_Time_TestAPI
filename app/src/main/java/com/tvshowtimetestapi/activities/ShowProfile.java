package com.tvshowtimetestapi.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melnykov.fab.FloatingActionButton;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tvshowtimetestapi.R;
import com.tvshowtimetestapi.Service;
import com.tvshowtimetestapi.classItems.Result;
import com.tvshowtimetestapi.classItems.ResultExplore;
import com.tvshowtimetestapi.classItems.ResultShow;
import com.tvshowtimetestapi.classItems.Show;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by Kévin on 03/04/2016.
 */
public class ShowProfile extends AppCompatActivity {

    private int idShow;
    private String nameShow;
    private Service service;
    private Show show;
    private FloatingActionButton fab;
    private FloatingActionButton fabDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        Bundle extras = getIntent().getExtras();
        idShow = extras.getInt("idShow");
        nameShow = extras.getString("nameShow");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initLoadShowProfile();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabDone = (FloatingActionButton) findViewById(R.id.fabDone);
        fab.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tu viens de suivre la série", Snackbar.LENGTH_LONG)
                        .setAction("Annuler", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                switch (event) {
                                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                                        followShow();
                                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                                        followShow();
                                        break;
                                }
                            }

                            @Override
                            public void onShown(Snackbar snackbar) {
                                super.onShown(snackbar);
                            }
                        })
                        .show();
            }
        });

        fabDone.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Tu viens d'arrêter de suivre la série", Snackbar.LENGTH_LONG)
                        .setAction("Annuler", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                switch (event) {
                                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                                        unfollowShow();
                                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                                        unfollowShow();
                                        break;
                                }
                            }

                            @Override
                            public void onShown(Snackbar snackbar) {
                                super.onShown(snackbar);
                            }
                        })
                        .show();
            }
        });


    }

    private void initLoadShowProfile() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        service = new RestAdapter.Builder().
                setEndpoint(Service.ENDPOINT).
                setConverter(new GsonConverter(gson)).
                build().
                create(Service.class);
        service.getShow(idShow, nameShow, new Callback<ResultShow>() {
            @Override
            public void success(ResultShow resultShow, Response response) {
                show = resultShow.getShow();
                initShowProfile();
            }

            @Override
            public void failure(RetrofitError error) {
                errorDialog("Echec de la récupération de données via le serveur distant");
            }
        });
    }

    private void initShowProfile() {
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.header);
        CircularImageView profilePic = (CircularImageView) findViewById(R.id.imageProfileShow);
        ImageView fanartBanner = (ImageView) findViewById(R.id.fanart);
        TextView textViewNameShow = (TextView) findViewById(R.id.idShow);
        TextView statutShow = (TextView) findViewById(R.id.statutShow);
        TextView overviewShow = (TextView) findViewById(R.id.overviewShow);
        TextView nbFollowers = (TextView) findViewById(R.id.nbFollowers);
        TextView nbSeasonEp = (TextView) findViewById(R.id.nbSeasonEp);
        TextView lastCastEp = (TextView) findViewById(R.id.lastCastEp);

        if (show.getFollowed() == null) {
            fab.setVisibility(View.VISIBLE);
            fabDone.setVisibility(View.GONE);
        }else {
            if (show.getFollowed()) {
                fab.setVisibility(View.GONE);
                fabDone.setVisibility(View.VISIBLE);
            } else {
                fab.setVisibility(View.VISIBLE);
                fabDone.setVisibility(View.GONE);
            }
        }

        textViewNameShow.setText(show.getName());
        statutShow.setText(show.getStatus());
        overviewShow.setText(show.getOverview());
        nbFollowers.setText(String.valueOf(show.getNb_followers()) + " followers");
        if(show.getAired_episode() == null) {
            if(show.getSeen_episodes() == null) {
                nbSeasonEp.setText(String.valueOf(show.getNumber_of_seasons()) + " " +
                        "saisons diffusées");
            }else {
                nbSeasonEp.setText(String.valueOf(show.getNumber_of_seasons() + " saisons " +
                        "diffusées et tu as vu "
                        + String.valueOf(show.getSeen_episodes()) + " épisodes"));
            }
        } else {
            nbSeasonEp.setText(String.valueOf(show.getNumber_of_seasons() +
                    " saisons diffusés pour " + String.valueOf(show.getAired_episode())
                    + " épisodes diffusés et tu en as vu" +
                    String.valueOf(show.getSeen_episodes())));
        }
        if(show.getLast_aired().getSeason_number() == null || show.getLast_aired().
                getSeason_number() == null) {
            lastCastEp.setText("Pas de derniers épisodes sorties enregistré");
        }else {
            lastCastEp.setText("Dernier episode diffusés : " + String.
                    valueOf(show.getLast_aired().getNumber()) + " de la saison " +
                    String.valueOf(show.getLast_aired().getSeason_number()));
        }

        Picasso.with(this).load(show.getImage()).into(profilePic);
        Picasso.with(this).load(show.getAll_images().getFanart().getFanartZero()).into(fanartBanner);
        //FIT Cassé, déçu
        //Picasso.with(this).load(show.getAll_images().getFanart().getFanartZero()).fit().into(fanartBanner);
    }

    private void followShow() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        service = new RestAdapter.Builder().
                setEndpoint(Service.ENDPOINT).
                setConverter(new GsonConverter(gson)).
                build().
                create(Service.class);
        service.follow(idShow, new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                Snackbar.make(findViewById(android.R.id.content), "La série s'est bien ajouté à" +
                        " ta liste de série", Snackbar.LENGTH_LONG);
                fab.setVisibility(View.GONE);
                fabDone.setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void unfollowShow() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        service = new RestAdapter.Builder().
                setEndpoint(Service.ENDPOINT).
                setConverter(new GsonConverter(gson)).
                build().
                create(Service.class);
        service.unfollow(idShow, new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                //Toast.makeText(ShowProfile.this, "PLUS SUIVI", Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(android.R.id.content), "Vous ne suivez plus la série"
                        , Snackbar.LENGTH_LONG);
                fabDone.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }



    public void errorDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(ShowProfile.this).create();
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
