package com.software.jgodort.graffpaper;

import android.app.Application;

import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 * Created by javie on 08/04/2017.
 */

public class GraffpaperApplication extends Application {


    private static GraffpaperApplication app;

    private RepositoriesComponent mRepositoriesComponent;

    private NetworkComponent mNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        Stetho.initializeWithDefaults(app);

        Timber.plant();

        mRepositoriesComponent = DaggerRepositoriesComponent
                .builder()
                .repositoriesModule(new RepositoriesModule())
                .build();
        mNetworkComponent = DaggerNetworkComponent
                .builder()
                .retrofitModule(new RetrofitModule(app))
                .build();


    }

    public RepositoriesComponent getRepositoriesComponent() {
        return mRepositoriesComponent;
    }

    public NetworkComponent getmNetworkComponent() {
        return mNetworkComponent;
    }

    public static GraffpaperApplication getApp() {
        return app;
    }
}
