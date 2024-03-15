import java.util.Arrays;

public record DiceRoll(int d1, int d2, int d3, int d4, int d5) {
    public DiceRoll {
        int[] dice = {d1, d2, d3, d4, d5};
        Arrays.stream(dice).forEach(die -> {
            if (die < 1 || die > 6) {
                throw new IllegalArgumentException("Dice value out of bounds: " + die);
            }
        });
    }

    public int[] getDice() {
        return new int[]{d1(), d2(), d3(), d4(), d5()};
    }
}
