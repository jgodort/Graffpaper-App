package com.software.jgodort.graffpaper;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;


/**
 * Class that represent the application.
 * <p>
 * Created by javier on 08/04/2017.
 */
public class GraffpaperApplication extends Application {


    private static GraffpaperApplication app;

    private RepositoriesComponent mRepositoriesComponent;

    private NetworkComponent mNetworkComponent;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        Stetho.initializeWithDefaults(app);

        Timber.plant();

        configureDaggerComponents();
        configureLeakCanary();
        configureStetho();


    }

    private void configureStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void configureDaggerComponents() {
        mRepositoriesComponent = DaggerRepositoriesComponent
                .builder()
                .repositoriesModule(new RepositoriesModule())
                .build();
        mNetworkComponent = DaggerNetworkComponent
                .builder()
                .retrofitModule(new RetrofitModule(app))
                .build();
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(app))
                .build();
    }

    private void configureLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
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
