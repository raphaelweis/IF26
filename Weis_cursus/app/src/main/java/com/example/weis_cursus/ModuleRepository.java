package com.example.weis_cursus;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ModuleRepository {
    /** @noinspection FieldCanBeLocal*/
    private final ModuleDAO moduleDAO;
    private final LiveData<List<ModuleEntity>> liveModuleEntityList;

    public ModuleRepository(Application application) {
        ModuleRoomDatabase db = ModuleRoomDatabase.getDatabase(application);
        moduleDAO = db.moduleDAO();
        liveModuleEntityList = moduleDAO.getAllModules();
    }

    public LiveData<List<ModuleEntity>> getLiveModuleEntityList() {
        return liveModuleEntityList;
    }

//    public void insertModule(ModuleEntity moduleEntity) {
//        ModuleRoomDatabase.databaseWriteExecutor.execute(() -> moduleDAO.insertModule(moduleEntity));
//    }

//    public void deleteBySigle(String sigle) {
//        ModuleRoomDatabase.databaseWriteExecutor.execute(() -> moduleDAO.deleteModuleBySigle(sigle));
//    }
}
