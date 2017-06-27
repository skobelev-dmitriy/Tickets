package ru.adventurers.pildarok;

import android.app.Application;
import android.content.Context;


import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.adventurers.pildarok.data.DataManager;
import ru.adventurers.pildarok.injection.component.ApplicationComponent;
import ru.adventurers.pildarok.injection.component.DaggerApplicationComponent;
import ru.adventurers.pildarok.injection.module.ApplicationModule;

public class PilApplication extends Application {

    ApplicationComponent mApplicationComponent;
    @Inject
    DataManager dataManager;
    @Override
    public void onCreate() {
        super.onCreate();


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public static PilApplication get(Context context) {
        return (PilApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
