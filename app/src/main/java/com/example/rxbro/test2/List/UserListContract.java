package com.example.rxbro.test2.List;

import com.example.rxbro.test2.Entities.User;

import java.util.ArrayList;

/**
 * Created by rxbro on 6/19/2017.
 */

public interface UserListContract {
    interface View {
        void showErrorMessage();
        void displayList(ArrayList<User> list);
    }

    interface Presenter {
        void downloadList();
        ArrayList<User> getMyList();
    }
}
