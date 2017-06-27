package ru.adventurers.pildarok.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.adventurers.pildarok.PilApplication;
import ru.adventurers.pildarok.data.DataManager;
import ru.adventurers.pildarok.data.PreferencesHelper;
import ru.adventurers.pildarok.injection.ApplicationContext;
import ru.adventurers.pildarok.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(PilApplication pilApplication);

    @ApplicationContext
    Context context();
    Application application();
    //NetworkService androidBoilerplateService();
    //InsuranceService insuranceService();
    PreferencesHelper preferencesHelper();
    DataManager dataManager();
   // LocalRepository localRepository();

}
