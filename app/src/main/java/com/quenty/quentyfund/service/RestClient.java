package com.quenty.quentyfund.service;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.uagrmsoe.dgm.model.GitResult;
import com.uagrmsoe.dgm.model.Item;
import com.uagrmsoe.dgm.model.Message;
import com.uagrmsoe.dgm.model.Users;
import com.uagrmsoe.dgm.testingslimretrofit.User;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Ashiq Uz Zoha on 9/13/15.
 * Dhrubok Infotech Services Ltd.
 * ashiq.ayon@gmail.com
 */
public class RestClient {

    private static GitApiInterface gitApiInterface ;
    private static DgmApiInterface dgmApiInterface ;
    private static String baseUrl = "https://api.github.com" ;
    private static String baseUrl2 = "http://192.168.43.230:8086/" ;

    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {

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
//                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(GitApiInterface.class);
        }
        return gitApiInterface ;
    }

    public static DgmApiInterface getDGMClient() {
        if (dgmApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl2)
//                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            dgmApiInterface = client.create(DgmApiInterface.class);
        }
        return dgmApiInterface ;
    }


    public interface GitApiInterface {

        @Headers("User-Agent: Retrofit2.0Tutorial-App")
        @GET("/search/users")
        Call<GitResult> getUsersNamedTom(@Query("q") String name);

        @POST("/user/create")
        Call<Item> createUser(@Body String name, @Body String email);

        @PUT("/user/{id}/update")
        Call<Item> updateUser(@Path("id") String id, @Body Item user);
    }

    public interface DgmApiInterface {

//        @Headers("User-Agent: Retrofit2.0Tutorial-App")
        @GET("/users/{username}")
        Call<User> getDiego(@Path("username") String username);

        @GET("/hello/{name}")
        Call<User> getHello(@Path("name") String name);

        @FormUrlEncoded
        @POST("task_manager/v1/register")
        Call<Message> register(@Field("name") String name, @Field("email") String email, @Field("password") String password);
//        Call<Message> register(@Body Users user);

//        @POST("/register")
//        Call<Message> register(@Body Users user);
    }
}
