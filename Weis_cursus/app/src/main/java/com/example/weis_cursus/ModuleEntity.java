package com.example.weis_cursus;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "module")
public class ModuleEntity {
    @PrimaryKey
    @NonNull
    private String sigle;

    @NonNull
    private final String parcours;

    @NonNull
    private final String categorie;

    @NonNull
    private final Integer credit;

    public ModuleEntity(
            @NonNull String sigle,
            @NonNull String parcours,
            @NonNull String categorie,
            @NonNull Integer credit) {
        this.sigle = sigle;
        this.parcours = parcours;
        this.categorie = categorie;
        this.credit = credit;
    }

    @NonNull
    public String getSigle() {
        return sigle;
    }

//    public void setSigle(@NonNull String sigle) {
//        this.sigle = sigle;
//    }

    @NonNull
    public String getParcours() {
        return parcours;
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
