package com.example.rxbro.test2.SingleUser;

import com.example.rxbro.test2.Entities.User;

/**
 * Created by rxbro on 6/19/2017.
 */

public interface UserContract {

    interface View {
        void displayUser(String name, String address, String email, String gender, String phone, String cell, String dateOfBirth, String nationality, String registered, String picture);

        void showErrorMessage();
    }

    interface Presenter {
        void passUser(User user);

    }




}
