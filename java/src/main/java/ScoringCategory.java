import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum ScoringCategory {
    CHANCE(roll -> Arrays.stream(roll.getDice()).sum()),
    YATZY(roll -> Arrays.stream(roll.getDice()).distinct().count() == 1 ? 50 : 0),
    ONES(scoreForValue(1)),
    TWOS(scoreForValue(2)),
    THREES(scoreForValue(3)),
    FOURS(scoreForValue(4)),
    FIVES(scoreForValue(5)),
    SIXES(scoreForValue(6)),
    ONE_PAIR(roll -> findHighestMultiple(roll, 2) * 2),
    TWO_PAIR(roll -> {
        int[] pairs = findPairs(roll);
        return pairs.length >= 2 ? Arrays.stream(pairs).sorted().skip(pairs.length - 2).sum() * 2 : 0;
    }),
    THREE_OF_A_KIND(roll -> findHighestMultiple(roll, 3) * 3),
    FOUR_OF_A_KIND(roll -> findHighestMultiple(roll, 4) * 4),
    SMALL_STRAIGHT(roll -> IntStream.rangeClosed(1, 5).allMatch(i -> Arrays.stream(roll.getDice()).anyMatch(die -> die == i)) ? 15 : 0),
    LARGE_STRAIGHT(roll -> IntStream.rangeClosed(2, 6).allMatch(i -> Arrays.stream(roll.getDice()).anyMatch(die -> die == i)) ? 20 : 0),
    FULL_HOUSE(roll -> {
        Map<Integer, Long> counts = findMultiples(roll);
        boolean hasThreeOfAKind = counts.containsValue(3L);
        boolean hasPair = counts.values().removeIf(v -> v == 3) && counts.containsValue(2L);
        return hasThreeOfAKind && hasPair ? Arrays.stream(roll.getDice()).sum() : 0;
    });

    private final ScoringStrategy strategy;

    ScoringCategory(ScoringStrategy strategy) {
        this.strategy = strategy;
    }

    public int score(DiceRoll roll) {
        return this.strategy.calculateScore(roll);
    }

    /**
     * Calculates the score for a given value by summing dice that match the value
     * @param value The target value to sum
     * @return The scoring strategy for summing dice of the given value
     */
    private static ScoringStrategy scoreForValue(int value) {
        return roll -> Arrays.stream(roll.getDice()).filter(die -> die == value).sum();
    }

    /**
     * Finds the multiples of each die in a roll keyed by the die value
     * @param roll The roll to analyze
     * @return A map of die values to the number of times they appear in the roll
     */
    private static Map<Integer, Long> findMultiples(DiceRoll roll) {
        return Arrays.stream(roll.getDice())
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Finds the highest multiple of a die in a roll
     * @param roll The roll to analyze
     * @param multiple The minimum number of times a die must appear to be considered
     * @return The value of the die that appears the most times in the roll
     */
    private static int findHighestMultiple(DiceRoll roll, int multiple) {
        Map<Integer, Long> counts = findMultiples(roll);
        return counts.entrySet().stream()
            .filter(entry -> entry.getValue() >= multiple)
            .mapToInt(Map.Entry::getKey)
            .max()
            .orElse(0);
    }

    /**
     * Finds the pairs in a roll
     * @param roll The roll to analyze
     * @return An array of the values of the dice that appear in pairs
     */
    private static int[] findPairs(DiceRoll roll) {
        Map<Integer, Long> counts = findMultiples(roll);
        return counts.entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .mapToInt(Map.Entry::getKey)
            .toArray();
    }

}
