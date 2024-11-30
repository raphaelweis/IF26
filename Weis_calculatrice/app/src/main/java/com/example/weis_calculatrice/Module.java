package com.example.weis_calculatrice;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "module")
public class Module {
    @PrimaryKey
    @NonNull
    private final String sigle;

    @NonNull
    private final String categorie;

    @NonNull
    private final Integer credit;

    public Module(
            @NonNull String sigle,
            @NonNull String categorie,
            @NonNull Integer credit) {
        this.sigle = sigle;
        this.categorie = categorie;
        this.credit = credit;
    }

    @NonNull
    public String getSigle() {
        return sigle;
    }

    @NonNull
    public String getCategorie() {
        return categorie;
    }

    @NonNull
    public Integer getCredit() {
        return credit;
    }
}
