package dependency;

import activity.GetPlayerActivity;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the GameBoardService.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    /**
     * Provides the relevant activity.
     * @return GetPlayerActivity
     */
    GetPlayerActivity provideGetPlayerActivity();
 }
