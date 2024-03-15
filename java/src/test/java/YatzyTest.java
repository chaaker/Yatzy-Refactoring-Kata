import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YatzyTest {
    private final YatziGame game = new YatziGame();

    @ParameterizedTest
    @MethodSource("diceRollsAndScores")
    void testScoring(ScoringCategory category, int d1, int d2, int d3, int d4, int d5, int expectedScore) {
        assertEquals(expectedScore, game.score(new DiceRoll(d1, d2, d3, d4, d5), category));
    }

    private static Stream<Arguments> diceRollsAndScores() {
        return Stream.of(
            Arguments.of(ScoringCategory.CHANCE, 2, 3, 4, 5, 1, 15),
            Arguments.of(ScoringCategory.CHANCE, 3, 3, 4, 5, 1, 16),

            Arguments.of(ScoringCategory.YATZY, 4, 4, 4, 4, 4, 50),
            Arguments.of(ScoringCategory.YATZY, 6, 6, 6, 6, 6, 50),
            Arguments.of(ScoringCategory.YATZY, 6, 6, 6, 6, 3, 0),

            Arguments.of(ScoringCategory.ONES, 1, 2, 3, 4, 5, 1),
            Arguments.of(ScoringCategory.ONES, 1, 2, 1, 4, 5, 2),
            Arguments.of(ScoringCategory.ONES, 6, 2, 2, 4, 5, 0),
            Arguments.of(ScoringCategory.ONES, 1, 2, 1, 1, 1, 4),

            Arguments.of(ScoringCategory.TWOS, 1, 2, 3, 2, 6, 4),
            Arguments.of(ScoringCategory.TWOS, 2, 2, 2, 2, 2, 10),

            Arguments.of(ScoringCategory.THREES, 1, 2, 3, 2, 3, 6),
            Arguments.of(ScoringCategory.THREES, 2, 3, 3, 3, 3, 12),

            Arguments.of(ScoringCategory.FOURS, 4, 4, 4, 5, 5, 12),
            Arguments.of(ScoringCategory.FOURS, 4, 4, 5, 5, 5, 8),
            Arguments.of(ScoringCategory.FOURS, 4, 5, 5, 5, 5, 4),

            Arguments.of(ScoringCategory.FIVES, 4, 4, 4, 5, 5, 10),
            Arguments.of(ScoringCategory.FIVES, 4, 4, 5, 5, 5, 15),
            Arguments.of(ScoringCategory.FIVES, 4, 5, 5, 5, 5, 20),

            Arguments.of(ScoringCategory.SIXES, 4, 4, 4, 5, 5, 0),
            Arguments.of(ScoringCategory.SIXES, 4, 4, 6, 5, 5, 6),
            Arguments.of(ScoringCategory.SIXES, 6, 5, 6, 6, 5, 18),

            Arguments.of(ScoringCategory.ONE_PAIR, 3, 4, 3, 5, 6, 6),
            Arguments.of(ScoringCategory.ONE_PAIR, 5, 3, 3, 3, 5, 10),
            Arguments.of(ScoringCategory.ONE_PAIR, 5, 3, 6, 6, 5, 12),
            Arguments.of(ScoringCategory.ONE_PAIR, 1, 3, 2, 6, 5, 0),
            Arguments.of(ScoringCategory.ONE_PAIR, 1, 1, 1, 1, 1, 2),

            Arguments.of(ScoringCategory.TWO_PAIR, 3, 3, 5, 4, 5, 16),
            Arguments.of(ScoringCategory.TWO_PAIR, 3, 3, 5, 5, 5, 16),
            Arguments.of(ScoringCategory.TWO_PAIR, 3, 1, 5, 5, 5, 0),
            Arguments.of(ScoringCategory.TWO_PAIR, 1, 1, 1, 1, 1, 0),

            Arguments.of(ScoringCategory.THREE_OF_A_KIND, 3, 3, 3, 4, 5, 9),
            Arguments.of(ScoringCategory.THREE_OF_A_KIND, 5, 3, 5, 4, 5, 15),
            Arguments.of(ScoringCategory.THREE_OF_A_KIND, 3, 3, 3, 3, 5, 9),
            Arguments.of(ScoringCategory.THREE_OF_A_KIND, 3, 3, 3, 3, 3, 9),
            Arguments.of(ScoringCategory.THREE_OF_A_KIND, 1, 2, 4, 3, 3, 0),

            Arguments.of(ScoringCategory.FOUR_OF_A_KIND, 3, 3, 3, 3, 5, 12),
            Arguments.of(ScoringCategory.FOUR_OF_A_KIND, 5, 5, 5, 4, 5, 20),
            Arguments.of(ScoringCategory.FOUR_OF_A_KIND, 5, 5, 5, 4, 1, 0),

            Arguments.of(ScoringCategory.SMALL_STRAIGHT, 1, 2, 3, 4, 5, 15),
            Arguments.of(ScoringCategory.SMALL_STRAIGHT, 2, 3, 4, 5, 1, 15),
            Arguments.of(ScoringCategory.SMALL_STRAIGHT, 1, 2, 2, 4, 5, 0),

            Arguments.of(ScoringCategory.LARGE_STRAIGHT, 6, 2, 3, 4, 5, 20),
            Arguments.of(ScoringCategory.LARGE_STRAIGHT, 2, 3, 4, 5, 6, 20),
            Arguments.of(ScoringCategory.LARGE_STRAIGHT, 1, 2, 2, 4, 5, 0),

            Arguments.of(ScoringCategory.FULL_HOUSE, 6, 2, 2, 2, 6, 18),
            Arguments.of(ScoringCategory.FULL_HOUSE, 2, 3, 4, 5, 6, 0),
            Arguments.of(ScoringCategory.FULL_HOUSE, 2, 2, 2, 1, 3, 0)
        );
    }
}
