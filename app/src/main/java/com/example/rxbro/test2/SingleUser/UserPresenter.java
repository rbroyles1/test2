package com.example.rxbro.test2.SingleUser;

import com.example.rxbro.test2.Entities.User;

/**
 * Created by rxbro on 6/19/2017.
 */

public class UserPresenter implements UserContract.Presenter {
    private UserContract.View view;
    private User user;

    public UserPresenter(UserContract.View view) {
        this.view = view;
    }
    public void passUser(User user) {
        this.user = user;
        if (user != null) {
            view.displayUser(user.getName(), user.getAddress(), user.getEmail(), user.getGender(), user.getPhone(), user.getCell(), user.getDateOfBirth(), user.getNationality(), user.getRegistered(), user.getImage());

        } else {
            view.showErrorMessage();
        }
    }

}
