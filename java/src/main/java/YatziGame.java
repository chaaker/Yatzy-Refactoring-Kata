public class YatziGame {
    public int score(DiceRoll roll, ScoringCategory category) {
        return category.score(roll);
    }
}
