package com.example.rxbro.test2.Utils;

import com.example.rxbro.test2.Entities.Info;
import com.example.rxbro.test2.Entities.Result;

import java.util.List;

/**
 * Created by rxbro on 6/19/2017.
 */

public class RandomAPI {

    private List<Result> results = null;
    private Info info;
    public List<Result> getResults() {
        return results;

    }
    public void setResults(List<Result> results) {
        this.results = results;

    }
    public Info getInfo() {
        return info;
    }
    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "RandomAPI{" +
                "results=" + results +
                ", info=" + info +
                '}';
    }
}
