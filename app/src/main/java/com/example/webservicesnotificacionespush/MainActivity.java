package com.example.webservicesnotificacionespush;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.webservicesnotificacionespush.retrofit.Model.Interface.JsonPlaceHolderApi;
import com.example.webservicesnotificacionespush.retrofit.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView mJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mJsonTextView = findViewById( R.id.jsontext );
        getPosts();
    }
    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory( GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create( JsonPlaceHolderApi.class );

        Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue( new Callback<List<Posts>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if(!response.isSuccessful()){
                    mJsonTextView.setText( "Codigo: " + response.code() );
                    return;
                }

                List<Posts> postsLists = response.body();

                assert postsLists != null;
                for (Posts posts:postsLists){
                    String content = "";
                    content += "userId"+posts.getUserId()+"\n";
                    content += "id"+posts.getId()+"\n";
                    content += "title"+posts.getTitle()+"\n";
                    content += "body"+posts.getBody()+"\n\n";
                    mJsonTextView.append( content );
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
              mJsonTextView.setText( t.getMessage() );
            }
        } );
    }
}