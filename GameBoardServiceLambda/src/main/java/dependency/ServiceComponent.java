package dependency;

import activity.*;

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

    /**
     * Provides the relevant activity.
     * @return CreateGameOutcomeActivity
     */
    CreateGameOutcomeActivity provideCreateGameOutcomeActivity();

    /**
     * Provides the relevant activity.
     * @return GetGameOutcomeActivity
     */
    GetGameOutcomeActivity provideGetGameOutcomeActivity();

    /**
     * Provides the relevant activity.
     * @return GetGroupsByPlayerActivity
     */
    GetGroupsByPlayerActivity provideGetGroupsByPlayerActivity();

    /**
     * Provides the relevant activity.
     * @return GetGamesByGroupIdActivity
     */
    GetGamesByGroupIdActivity provideGetGamesByGroupIdActivity();
}
