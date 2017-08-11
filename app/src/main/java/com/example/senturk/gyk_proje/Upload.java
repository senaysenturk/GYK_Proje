package com.example.senturk.gyk_proje;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Senturk on 19.7.2017.
 */

@IgnoreExtraProperties
public class Upload{

    public String name;
    public String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Upload(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Upload(){}
}

