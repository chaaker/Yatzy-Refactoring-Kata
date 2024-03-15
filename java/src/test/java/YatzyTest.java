import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YatzyTest {
    YatziGame game = new YatziGame();

    @Test
    public void chance_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = game.score(new DiceRoll(2,3,4,5,1), ScoringCategory.CHANCE);
        assertEquals(expected, actual);
        assertEquals(16, game.score(new DiceRoll(3,3,4,5,1), ScoringCategory.CHANCE));
    }

    @Test public void yatzy_scores_50() {
        int expected = 50;
        int actual = game.score(new DiceRoll(4,4,4,4,4), ScoringCategory.YATZY);
        assertEquals(expected, actual);
        assertEquals(50, game.score(new DiceRoll(6,6,6,6,6), ScoringCategory.YATZY));
        assertEquals(0, game.score(new DiceRoll(6,6,6,6,3), ScoringCategory.YATZY));
    }

    @Test public void test_1s() {
        assertEquals(1, game.score(new DiceRoll(1, 2, 3, 4, 5), ScoringCategory.ONES));
        assertEquals(2, game.score(new DiceRoll(1,2,1,4,5), ScoringCategory.ONES));
        assertEquals(0, game.score(new DiceRoll(6,2,2,4,5), ScoringCategory.ONES));
        assertEquals(4, game.score(new DiceRoll(1,2,1,1,1), ScoringCategory.ONES));
    }

    @Test
    public void test_2s() {
        assertEquals(4, game.score(new DiceRoll(1,2,3,2,6), ScoringCategory.TWOES));
        assertEquals(10, game.score(new DiceRoll(2,2,2,2,2), ScoringCategory.TWOES));
    }

    @Test
    public void test_threes() {
        assertEquals(6, game.score(new DiceRoll(1,2,3,2,3), ScoringCategory.THREES));
        assertEquals(12, game.score(new DiceRoll(2,3,3,3,3), ScoringCategory.THREES));
    }

    @Test
    public void fours_test()
    {
        assertEquals(12, game.score(new DiceRoll(4,4,4,5,5), ScoringCategory.FOURS));
        assertEquals(8, game.score(new DiceRoll(4,4,5,5,5), ScoringCategory.FOURS));
        assertEquals(4, game.score(new DiceRoll(4,5,5,5,5), ScoringCategory.FOURS));
    }

    @Test
    public void fives() {
        assertEquals(10, game.score(new DiceRoll(4,4,4,5,5), ScoringCategory.FIVES));
        assertEquals(15, game.score(new DiceRoll(4,4,5,5,5), ScoringCategory.FIVES));
        assertEquals(20, game.score(new DiceRoll(4,5,5,5,5), ScoringCategory.FIVES));
    }

    @Test
    public void sixes_test() {
        assertEquals(0, game.score(new DiceRoll(4,4,4,5,5), ScoringCategory.SIXES));
        assertEquals(6, game.score(new DiceRoll(4,4,6,5,5), ScoringCategory.SIXES));
        assertEquals(18, game.score(new DiceRoll(6,5,6,6,5), ScoringCategory.SIXES));
    }

    @Test
    public void one_pair() {
        assertEquals(6, game.score(new DiceRoll(3,4,3,5,6), ScoringCategory.ONE_PAIR));
        assertEquals(10, game.score(new DiceRoll(5,3,3,3,5), ScoringCategory.ONE_PAIR));
        assertEquals(12, game.score(new DiceRoll(5,3,6,6,5), ScoringCategory.ONE_PAIR));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, game.score(new DiceRoll(3,3,5,4,5), ScoringCategory.TWO_PAIR));
        assertEquals(16, game.score(new DiceRoll(3,3,5,5,5), ScoringCategory.TWO_PAIR));
    }

    @Test
    public void three_of_a_kind()
    {
        assertEquals(9, game.score(new DiceRoll(3,3,3,4,5), ScoringCategory.THREE_OF_A_KIND));
        assertEquals(15, game.score(new DiceRoll(5,3,5,4,5), ScoringCategory.THREE_OF_A_KIND));
        assertEquals(9, game.score(new DiceRoll(3,3,3,3,5), ScoringCategory.THREE_OF_A_KIND));
        assertEquals(9, game.score(new DiceRoll(3,3,3,3,3), ScoringCategory.THREE_OF_A_KIND));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, game.score(new DiceRoll(3,3,3,3,5), ScoringCategory.FOUR_OF_A_KIND));
        assertEquals(20, game.score(new DiceRoll(5,5,5,4,5), ScoringCategory.FOUR_OF_A_KIND));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, game.score(new DiceRoll(1,2,3,4,5), ScoringCategory.SMALL_STRAIGHT));
        assertEquals(15, game.score(new DiceRoll(2,3,4,5,1), ScoringCategory.SMALL_STRAIGHT));
        assertEquals(0, game.score(new DiceRoll(1,2,2,4,5), ScoringCategory.SMALL_STRAIGHT));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, game.score(new DiceRoll(6,2,3,4,5), ScoringCategory.LARGE_STRAIGHT));
        assertEquals(20, game.score(new DiceRoll(2,3,4,5,6), ScoringCategory.LARGE_STRAIGHT));
        assertEquals(0, game.score(new DiceRoll(1,2,2,4,5), ScoringCategory.LARGE_STRAIGHT));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, game.score(new DiceRoll(6,2,2,2,6), ScoringCategory.FULL_HOUSE));
        assertEquals(0, game.score(new DiceRoll(2,3,4,5,6), ScoringCategory.FULL_HOUSE));
    }
}
