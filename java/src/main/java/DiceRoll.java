import java.util.Arrays;

public record DiceRoll(int firstDie, int secondDie, int thirdDie, int fourthDie, int fifthDie) {
    public DiceRoll {
        int[] diceValues = {firstDie, secondDie, thirdDie, fourthDie, fifthDie};
        Arrays.stream(diceValues).forEach(this::validateDieValue);
    }

    private void validateDieValue(int die) {
        if (die < 1 || die > 6) {
            throw new IllegalArgumentException("Die value out of bounds: " + die);
        }
    }

    public int[] getDiceValues() {
        return new int[]{firstDie, secondDie, thirdDie, fourthDie, fifthDie};
    }
}
