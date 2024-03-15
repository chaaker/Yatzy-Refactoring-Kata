import java.util.Arrays;

/**
 * Represents a roll of five dice in the game of Yatzy.
 * Each die can have a value between 1 and 6, inclusively. This record ensures that
 * all dice values are within the valid range upon creation.
 */
public record DiceRoll(int firstDie, int secondDie, int thirdDie, int fourthDie, int fifthDie) {
    /**
     * Validates and initializes a new instance of a dice roll.
     * This constructor automatically validates each die value, ensuring they are within
     * the game's rules. An {@link IllegalArgumentException} is thrown for invalid values.
     */
    public DiceRoll {
        int[] diceValues = {firstDie, secondDie, thirdDie, fourthDie, fifthDie};
        Arrays.stream(diceValues).forEach(this::validateDieValue);
    }

    private void validateDieValue(int die) {
        if (die < 1 || die > 6) {
            throw new IllegalArgumentException("Die value out of bounds: " + die);
        }
    }

    /**
     * Gets the values of the dice in this roll.
     *
     * @return An array containing the values of the five dice.
     */
    public int[] getDiceValues() {
        return new int[]{firstDie, secondDie, thirdDie, fourthDie, fifthDie};
    }
}
