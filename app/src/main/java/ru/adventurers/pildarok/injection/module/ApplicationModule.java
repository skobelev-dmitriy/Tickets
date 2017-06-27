package ru.adventurers.pildarok.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.adventurers.pildarok.injection.ApplicationContext;

/**
 * Provide application-level dependencies. Mainly singleton object that can be injected from
 * anywhere in the app.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;
    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }




}