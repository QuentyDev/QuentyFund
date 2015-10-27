package com.quenty.quentyfund.service;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public class RestClient {

    private static QuentyApiInterface quentyApiInterface;
    private static String baseUrl = "http://192.168.1.236:8086/" ;


    public static QuentyApiInterface getDGMClient() {
        if (quentyApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            quentyApiInterface = client.create(QuentyApiInterface.class);
        }
        return quentyApiInterface;
    }

    public interface QuentyApiInterface {

        @FormUrlEncoded
        @POST("quentyfund/v1/register")
        Call<Message> registerTest(@Field("name") String name, @Field("email") String email, @Field("password") String password);

        @GET("hwslim/projects")
        Call<Message> getAllProjectsTest();

        /*TODO
         */
        @GET("hwslim/projects")
        Call<MessageProyecto> getAllProjects();


    }
}
