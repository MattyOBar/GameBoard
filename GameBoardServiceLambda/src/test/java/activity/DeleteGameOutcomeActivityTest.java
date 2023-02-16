package activity;

import dynamodb.GameOutcomeDao;
import dynamodb.models.GameOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteGameOutcomeActivityTest {
    @InjectMocks
    private DeleteGameOutcomeActivity deleteGameOutcomeActivity;

    @Mock
    private GameOutcomeDao gameOutcomeDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        deleteGameOutcomeActivity = new DeleteGameOutcomeActivity(gameOutcomeDao);
    }

    @Test
    void handleRequest_attemptsToDeleteGameOutcome() {
        // GIVEN
        GameOutcome gameOutcome = new GameOutcome();
        String gameOutcomeId = "GO74635";
        gameOutcome.setGameOutcomeId(gameOutcomeId);

        // WHEN
        gameOutcomeDao.deleteGameOutcome(gameOutcome);

        // THEN
        verify(gameOutcomeDao).deleteGameOutcome(gameOutcome);
    }
}
