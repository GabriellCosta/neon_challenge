package neon.gabrielcosta.gvcneon.dagger;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;
import neon.gabrielcosta.gvc.neon.compiler.ViewModelModule;
import neon.gabrielcosta.gvcneon.NeonApplication;
import neon.gabrielcosta.gvcneon.dagger.viewmodel.ViewModelFactoryModule;

@Component(modules = {RetrofitModule.class,
        ViewModelModule.class,
        ServiceModule.class,
        ActivityModule.class,
        ViewModelFactoryModule.class,
        AndroidSupportInjectionModule.class})
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(NeonApplication application);
}
