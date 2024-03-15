/**
 * Represents a strategy for calculating the score of a dice roll in a specific Yatzy category.
 */
public interface ScoringStrategy {
    /**
     * Calculates the score for a given dice roll according to this strategy.
     *
     * @param roll The dice roll to be scored.
     * @return The score calculated based on the strategy's rules.
     */
    int calculateScore(DiceRoll roll);
}
