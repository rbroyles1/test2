package com.example.rxbro.test2.SingleUser;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rxbro.test2.Entities.User;
import com.example.rxbro.test2.R;

/**
 * Created by rxbro on 6/19/2017.
 */

public class ViewUserActivity extends AppCompatActivity implements UserContract.View {
    ImageView userImage;
    TextView userData;
    User myUser;
    UserContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_view_user);
        userImage = (ImageView)findViewById(R.id.imageTV);
        userData = (TextView)findViewById(R.id.userTv);
        presenter = new UserPresenter(this);
        myUser = getIntent().getParcelableExtra("user");
        presenter.passUser(myUser);

    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "ERROR, Aborting program...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayUser(String name, String address, String email, String gender, String phone, String cell, String dateOfBirth, String nationality, String registered, String image) {
        Glide.with(this)
                .load(myUser.getImage())
                .into(userImage);
        userData.setText("Name: " + myUser.getName() + "\nAddress:  " + myUser.getAddress() + "\nEmail:  " + myUser.getEmail() + "\nGender: " + myUser.getGender() + "\nPhone: " + myUser.getPhone() + "\nCell:  " + myUser.getCell() + "\nDate of birth:  " + myUser.getDateOfBirth() + "\nNationality:  " + myUser.getNationality() + "\nRegistered:  " + myUser.getRegistered());

    }
}
