package com.example.rxbro.test2.List;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rxbro.test2.Entities.User;
import com.example.rxbro.test2.R;
import com.example.rxbro.test2.SingleUser.ContactProvider;
import com.example.rxbro.test2.Utils.RetrofitService;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements UserRecyclerAdapter.ItemClickListener, UserListContract.View {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final String RETROFIT_URL = "https://randomuser.me";
    ArrayList<User> userList;
    RecyclerView recyclerView;
    UserRecyclerAdapter adapter;
    UserListContract.Presenter presenter;
    RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        presenter = new UserListPresenter(this, service);
        presenter.downloadList();
    }
    @Override
    protected void onResume() {
        super.onResume();
        displayList(userList);
    }
    public void OnClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(ContactProvider.NAME)
    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, ViewUserActivity.class);
        intent.putExtra("user", userList.get(position));
        startActivity(intent);
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void displayList(ArrayList<User> list) {
        userList = list;
        adapter = new UserRecyclerAdapter(userList, this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

}
