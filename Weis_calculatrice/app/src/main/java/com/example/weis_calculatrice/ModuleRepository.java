package com.example.weis_calculatrice;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ModuleRepository {
    private final LiveData<List<Module>> modules;

    public ModuleRepository(Application application) {
        ModuleRoomDatabase db = ModuleRoomDatabase.getDatabase(application);
        ModuleDAO moduleDAO = db.moduleDAO();
        modules = moduleDAO.getAllModules();
    }

    public LiveData<List<Module>> getLiveModuleEntityList() {
        return modules;
    }
}
