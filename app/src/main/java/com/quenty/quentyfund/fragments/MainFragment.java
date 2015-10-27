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
import com.quenty.quentyfund.service.MessageProyecto;
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


//        recyclerView.setAdapter(new ProjectAdapter(getActivity(), 30));

        loadProject();


        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    public void loadProject() {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");
        RestClient.QuentyApiInterface service = RestClient.getDGMClient();
        Call<MessageProyecto> call = service.getAllProjects();
        call.enqueue(new Callback<MessageProyecto>() {
            @Override
            public void onResponse(Response<MessageProyecto> response, Retrofit retrofit) {
                dialog.dismiss();
                Log.d("MainActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    MessageProyecto result = response.body();
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
//            recyclerView.setBackground(getResources().getDrawable(R.drawable.cloud_offline));
//            recyclerView.set
                Gson gson = new Gson();
                Type type = new TypeToken<Message>() {
                }.getType();
//                    System.out.println(result.getMessage());
                String test = "{\"error\":false,\"message\":[{\"nombre\":\"Super Dungeon Explore: Legends\",\"descripcionLarga\":\"Super Dungeon Explore is a combat board game inspired by classic hack-and-slash video games. But that is only the beginning. Super Dungeon is also built upon a deep love for classic Japanese roleplaying games (JRPGs), adventure games, and traditional pencil-and-paper tabletop games. Super Dungeon Legends draws its inspiration from these well loved genres to allow players to craft their own stories in the world of Crystalia.\",\"descripcionCorta\":\"Super Dungeon Explore, the chibi board game, returns with LEGENDS \\u2014 featuring a new starter box, campaign play, and RPG action!\",\"monto\":80000,\"diasVigencia\":30,\"proyectoID\":2,\"categoria\":\"Juegos\",\"ciudad\":\"Juegos\",\"estado\":\"APROBADO\",\"usuarioID\":8,\"usuario\":\"Alexander\"},{\"nombre\":\"Toys 2.0: Smart Toy Robots, Built by Kids - Cannybots\",\"descripcionLarga\":\"Dear Parents - Meet Cannybots! The smart robotic toy car to introduce your little genius to programming, robotics, A.I. and 3D printing.\",\"descripcionCorta\":\"LEGO compatible, smart robotic toy car. Give your child a head start in programming, robotics, A.I. and 3D printing.\",\"monto\":40000,\"diasVigencia\":30,\"proyectoID\":5,\"categoria\":\"Tecnolog\\u00eda\",\"ciudad\":\"Tecnolog\\u00eda\",\"estado\":\"APROBADO\",\"usuarioID\":9,\"usuario\":\"Benjamin \"},{\"nombre\":\"HUDWAY Glass: keeps your eyes on the road while driving\",\"descripcionLarga\":\"We introduced the early prototype of HUDWAY Glass at CeBIT 2015 in Hannover for the first time. It is a simple accessory, which turns any smartphone into a head-up display. Moreover, it eliminates many issues associated with projecting an image onto a windshield, including doubling of the image, lack of reflection during daytime, image size and a few others. We received very positive response from potential customers, dealers and retailers, as well as from many major representatives of the automotive industry.\",\"descripcionCorta\":\"A universal vehicle accessory turning your smartphone into a head-up display (HUD) for any car\",\"monto\":100000,\"diasVigencia\":30,\"proyectoID\":6,\"categoria\":\"Tecnolog\\u00eda\",\"ciudad\":\"Tecnolog\\u00eda\",\"estado\":\"APROBADO\",\"usuarioID\":8,\"usuario\":\"Alexander\"},{\"nombre\":\"Range Dial. Better looking, smarter cooking thermometer\",\"descripcionLarga\":\"Cooking can be a joy when you make a delicious meal, a disappointment when you get it wrong, and disaster when you present the charred, leathery wreckage to your hungry friends and family. That's why we created Range Dial \\u2014 a precision cooking thermometer that works with or without your smartphone. Range helps you achieve that kitchen joy whether it's grilling, baking, brewing, or candymaking. Range Dial is the evolution of our previously Kickstarted thermometer, Range. We've learned a lot from how our customers use Range, and added the top requests: Bluetooth for wireless monitoring, and a second probe for ambient temperature. But we also thought carefully about the friction points and how we could address them. Range Dial works with or without a smartphone. Having a connected device shouldn't mean you have to mess with an app for the most basic functions. Just plug in the probe and turn the dial to a preset\\u2014Range Dial will beep at you, and you'll also get a push notification on your phone when your food is done. Or open the app to have full control. It's the best of both worlds.\",\"descripcionCorta\":\"A precision Bluetooth cooking tool with or without your iPhone. Tinker with your recipes, not your tools.\",\"monto\":60000,\"diasVigencia\":30,\"proyectoID\":7,\"categoria\":\"Tecnolog\\u00eda\",\"ciudad\":\"Tecnolog\\u00eda\",\"estado\":\"APROBADO\",\"usuarioID\":8,\"usuario\":\"Alexander\"}]}";
                Message message= gson.fromJson(test, type);
                proyectos=message.getMessage();
//                proyectos = gson.fromJson(test, type);
//            proyectos = result.getMessage();
                recyclerView.setAdapter(new ProjectAdapter(getActivity(), proyectos));
                Log.e("MainFragment", "Se ha producido un error: " + t.toString());
                dialog.dismiss();
            }
        });
    }

    public class LoadProjectsThread extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }

}
