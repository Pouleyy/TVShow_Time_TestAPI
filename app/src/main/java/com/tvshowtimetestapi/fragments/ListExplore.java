package com.tvshowtimetestapi.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.paginate.Paginate;
import com.paginate.abslistview.LoadingListItemCreator;
import com.tvshowtimetestapi.R;
import com.tvshowtimetestapi.Service;
import com.tvshowtimetestapi.activities.ShowProfile;
import com.tvshowtimetestapi.classItems.ResultExplore;
import com.tvshowtimetestapi.classItems.Show;
import com.tvshowtimetestapi.tools.EndlessScrollListener;
import com.tvshowtimetestapi.tools.ShowAdapter;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by Kévin on 02/04/2016.
 */
public class ListExplore extends Fragment {

    private ListView listView;
    private ArrayList<Show> shows;
    private TextView textView;
    private Service service;
    private ShowAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean firstLoad = true;
    private boolean loading;
    private int pageNum = 0;
    private static final int limit = 15;

    public ListExplore() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_list_explore, container, false);
        listView = (ListView) view.findViewById(R.id.listExplore);
        //textView = (TextView) view.findViewById(R.id.TextIdShow);
        if (shows == null) {
            shows = new ArrayList<>();
        }
        listView.setEmptyView(view.findViewById(R.id.emptyElementShow));
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Toast.makeText(getActivity(), "Rafraichissement en cours", Toast.LENGTH_SHORT)
                // .show();
                refreshListView();
            }
        });
        listView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {

                pageNum += 1;
                Toast.makeText(getActivity(), "MOAR" + String.valueOf(page), Toast.LENGTH_SHORT)
                        .show();
                getListExplore(page);
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShowProfile.class);
                Bundle extras = new Bundle();
                extras.putInt("idShow", shows.get(position).getId());
                extras.putString("nameShow", shows.get(position).getName());
                intent.putExtras(extras);


                startActivity(intent);
            }
        });
        getListExplore(pageNum);
        //initListView();
        return view;
    }

    private void test(){

    }

    private void getListExplore(int numPage) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        service = new RestAdapter.Builder().
                setEndpoint(Service.ENDPOINT).
                setConverter(new GsonConverter(gson)).
                build().
                create(Service.class);
        service.getExplore(numPage, limit, new Callback<ResultExplore>() {
            @Override
            public void success(ResultExplore resultExplore, Response response) {
                shows = resultExplore.getShows();
                swipeRefreshLayout.setRefreshing(false);
                if (firstLoad) {
                    initListView();
                }else{
                    adapter.addAll(shows);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                errorDialog("Echec de la récupération de données via le serveur distant");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initListView() {
        adapter = new ShowAdapter(getActivity(), shows);
        listView.setAdapter(adapter);
        //getListExplore(page);
        pageNum += 1;
        firstLoad = false;

    }

    private void refreshListView() {
        pageNum = 0;
        //Toast.makeText(getActivity(), "REFRESH" +String.valueOf(page), Toast.LENGTH_SHORT ).show();
        getListExplore(pageNum);
        adapter.clear();
        adapter.addAll(shows);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        if (!firstLoad) {
            pageNum = 0;
            firstLoad = true;
            getListExplore(pageNum);
        }
    }

    public void errorDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
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
