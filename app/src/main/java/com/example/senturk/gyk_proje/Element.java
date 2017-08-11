package com.example.senturk.gyk_proje;

/**
 * Created by Senturk on 19.7.2017.
 */

public class Element {

    public String elementAdi;
    public String url;

    public String getElementAdi() {
        return elementAdi;
    }

    public String getUrl() {
        return url;
    }

    public Element(String elementAdi, String url) {
        this.elementAdi = elementAdi;
        this.url = url;
    }
    public Element(){

    }
}
