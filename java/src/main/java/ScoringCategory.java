import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Enumerates the scoring categories in the Yatzy game, each with a specific scoring strategy.
 */
public enum ScoringCategory {
    /**
     * Calculates the score for a given dice roll according to the CHANCE rule.
     * The CHANCE score is the sum of all dice values.
     */
    CHANCE(roll -> Arrays.stream(roll.getDiceValues()).sum()),
    /**
     * Calculates the score for a given dice roll according to the YATZY rule.
     * YATZY scores 50 points if all dice have the same value, otherwise scores 0.
     */
    YATZY(roll -> Arrays.stream(roll.getDiceValues()).distinct().count() == 1 ? 50 : 0),
    /**
     * Calculates the score for dice matching the specified value.
     * This strategy is used for scoring categories ONES to SIXES.
     */
    ONES(sumOfDiceMatching(1)),
    TWOS(sumOfDiceMatching(2)),
    THREES(sumOfDiceMatching(3)),
    FOURS(sumOfDiceMatching(4)),
    FIVES(sumOfDiceMatching(5)),
    SIXES(sumOfDiceMatching(6)),
    /**
     * Calculates the score for a dice roll based on finding the highest value with at least two occurrences.
     * Used for scoring categories like ONE_PAIR.
     */
    ONE_PAIR(roll -> findHighestValueWithOccurrences(roll, 2) * 2),
    TWO_PAIR(roll -> {
        int[] pairs = findDiceValuesFormingPairs(roll);
        return pairs.length >= 2 ? Arrays.stream(pairs).sorted().skip(pairs.length - 2).sum() * 2 : 0;
    }),
    THREE_OF_A_KIND(roll -> findHighestValueWithOccurrences(roll, 3) * 3),
    FOUR_OF_A_KIND(roll -> findHighestValueWithOccurrences(roll, 4) * 4),
    /**
     * Determines if a roll is a straight by checking for sequential dice values.
     * This method is used for scoring SMALL_STRAIGHT and LARGE_STRAIGHT categories.
     */
    SMALL_STRAIGHT(roll -> checkForSequentialDiceValues(roll, 1, 5) ? 15 : 0),
    LARGE_STRAIGHT(roll -> checkForSequentialDiceValues(roll, 2, 6) ? 20 : 0),
    /**
     * Evaluates the dice roll for a FULL HOUSE, requiring a three-of-a-kind and a pair.
     * Scores the sum of all dice if the criteria are met, otherwise scores 0.
     */
    FULL_HOUSE(roll -> {
        Map<Integer, Long> counts = countDiceValuesOccurrences(roll);
        boolean hasThreeOfAKind = counts.containsValue(3L);
        boolean hasPair = counts.containsValue(2L);
        return hasThreeOfAKind && hasPair ? Arrays.stream(roll.getDiceValues()).sum() : 0;
    });

    private final ScoringStrategy strategy;

    /**
     * Initializes the category with a specific scoring strategy.
     *
     * @param strategy The scoring strategy unique to the scoring category.
     */
    ScoringCategory(ScoringStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates and returns the score for a given dice roll in this category.
     *
     * @param roll The dice roll to be scored.
     * @return The calculated score according to the category's rules.
     */
    public int score(DiceRoll roll) {
        return this.strategy.calculateScore(roll);
    }

    /**
     * Calculates the score for a given value by summing dice that match the value
     * @param value The target value to sum
     * @return The scoring strategy for summing dice of the given value
     */
    private static ScoringStrategy sumOfDiceMatching(int value) {
        return roll -> Arrays.stream(roll.getDiceValues()).filter(die -> die == value).sum();
    }

    /**
     * Finds the occurrences of each die in a roll keyed by the die value
     * @param roll The roll to analyze
     * @return A map of die values to the number of times they appear in the roll
     */
    private static Map<Integer, Long> countDiceValuesOccurrences(DiceRoll roll) {
        return Arrays.stream(roll.getDiceValues())
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Finds the highest occurrence of a die in a roll
     * @param roll The roll to analyze
     * @param occurrence The minimum number of times a die must appear to be considered
     * @return The value of the die that appears the most times in the roll
     */
    private static int findHighestValueWithOccurrences(DiceRoll roll, int occurrence) {
        return countDiceValuesOccurrences(roll).entrySet().stream()
            .filter(entry -> entry.getValue() >= occurrence)
            .mapToInt(Map.Entry::getKey)
            .max()
            .orElse(0);
    }

    /**
     * Finds the pairs in a roll
     * @param roll The roll to analyze
     * @return An array of the values of the dice that appear in pairs
     */
    private static int[] findDiceValuesFormingPairs(DiceRoll roll) {
        return countDiceValuesOccurrences(roll).entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .mapToInt(Map.Entry::getKey)
            .toArray();
    }

    /**
     * Determines if a roll is a straight
     * @param roll The roll to analyze
     * @param start The value of the first die in the straight
     * @param end The value of the last die in the straight
     * @return True if the roll is a straight, false otherwise
     */
    private static boolean checkForSequentialDiceValues(DiceRoll roll, int start, int end) {
        var diceSet = Arrays.stream(roll.getDiceValues()).boxed().collect(Collectors.toSet());
        return IntStream.rangeClosed(start, end).allMatch(diceSet::contains);
    }

}
