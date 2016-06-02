package com.robot.ddagger.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 当这里将@Singleton去掉之后，会发生如下错误，
 * AppComponent (unscoped) may not reference scoped bindings: @Provides @Singleton android.content.Context
 *
 * http://stackoverflow.com/questions/28170292/problems-with-singletons-when-using-component-dependencies
 *
 * 原因是：
 * Dagger 2 not allows unscoped components to use modules with scoped bindings.
 * 没有scoped的component使用了有scoped的module导致的。
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    /**
     * 可以看到这里并没有提供啥inject方法，仅仅是一个接口，这个接口其实也是需要依赖AppModule里面的内容。
     *
     *
     *
     * 假设把这个方法注释掉，则会出现如下错误，
     *
     *     @Provides
    //    @Singleton
    //    Context providerApplicationContext() {
    //        return app.getApplicationContext();
    //    }

    Error:(26, 13) 错误: android.content.Context cannot be provided without an @Provides-annotated method.
     原因：也就是说在Module中没有提供相应的方法。
     */
    Context getApplicationContext();
}
