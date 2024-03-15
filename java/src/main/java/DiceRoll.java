public class DiceRoll {
    private final int[] dice;

    public DiceRoll(int d1, int d2, int d3, int d4, int d5) {
        this.dice = new int[]{d1, d2, d3, d4, d5};
    }

    public int[] getDice() {
        return dice;
    }
}