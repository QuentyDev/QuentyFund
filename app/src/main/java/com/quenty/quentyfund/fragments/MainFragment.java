package com.quenty.quentyfund.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quenty.quentyfund.R;
import com.quenty.quentyfund.adapter.ProjectAdapter;
import com.quenty.quentyfund.entity.Proyecto;
import com.quenty.quentyfund.service.Message;
import com.quenty.quentyfund.service.RestClient;
import com.quenty.quentyfund.util.JsonParser;

import java.lang.reflect.Type;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private Proyecto[] proyectos;
    RecyclerView recyclerView;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        // Setup list
         recyclerView = (RecyclerView) view.findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");
        RestClient.QuentyApiInterface service = RestClient.getDGMClient();
        Call<Message> call = service.getAllProjects();
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Response<Message> response, Retrofit retrofit) {
                dialog.dismiss();
                Log.d("MainActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    Message result = response.body();
//                    Gson gson = new Gson();
//                    Type type = new TypeToken<Proyecto[]>() {
//                    }.getType();
//                    System.out.println(result.getMessage());
//                    proyectos = gson.fromJson(result.getMessage(), type);
                    proyectos = result.getMessage();
                    recyclerView.setAdapter(new ProjectAdapter(getActivity(), proyectos));
                    Log.d("MainActivity", "response = " + new Gson().toJson(result));
                } else {
                    Log.e("MainFragment", "Se ha producido un error");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("MainFragment", "Se ha producido un error: " + t.toString());
                dialog.dismiss();
            }
        });

//        recyclerView.setAdapter(new ProjectAdapter(getActivity(), 30));




        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


    public class LoadProjectsThread extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }

}
