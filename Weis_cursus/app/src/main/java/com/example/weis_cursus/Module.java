package com.example.weis_cursus;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Module implements Serializable {

    private final String sigle;
    private String parcours;
    private final String categorie;
    private final int credit;

    public Module(String sigle, String parcours, String categorie, int credit) {
        this.sigle = sigle;
        this.setParcours(parcours);
        this.categorie = categorie;
        this.credit = credit;

    }

    public String getSigle() {
        return sigle;
    }

    public String getParcours() {
        return parcours;
    }

    public void setParcours(String parcours) {
        this.parcours = parcours;
    }


    public String getCategorie() {
        return categorie;
    }

    public int getCredit() {
        return credit;
    }


    @NonNull
    @Override
    public String toString() {
        return "Module{" +
                "sigle_item='" + sigle + '\'' +
                ", parcours='" + parcours + '\'' +
                ", categorie='" + categorie + '\'' +
                ", credit=" + credit +
                '}';
    }
}