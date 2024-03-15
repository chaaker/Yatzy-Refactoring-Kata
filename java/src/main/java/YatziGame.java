public class YatziGame {
    public int score(DiceRoll roll, ScoringCategory category) {
        return category.calculateScore(roll);
    }
}
