package dependency;

import activity.CreateGameActivity;
import activity.CreateGroupActivity;
import activity.CreatePlayerActivity;
import activity.GetGameActivity;
import activity.GetGroupActivity;
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
     * @return CreatePlayerActivity
     */
    CreatePlayerActivity provideCreatePlayerActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlayerActivity
     */
    GetPlayerActivity provideGetPlayerActivity();

    /**
     * Provides the relevant activity.
     * @return CreateGroupActivity
     */
    CreateGroupActivity provideCreateGroupActivity();
    /**
     * Provides the relevant activity.
     * @return GetGroupActivity
     */
    GetGroupActivity provideGetGroupActivity();

    /**
     * Provides the relevant activity.
     * @return GetGameActivity
     */
    GetGameActivity provideGetGameActivity();

    /**
     * Provides the relevant activity.
     * @return CreateGameActivity
     */
    CreateGameActivity provideCreateGameActivity();
}
