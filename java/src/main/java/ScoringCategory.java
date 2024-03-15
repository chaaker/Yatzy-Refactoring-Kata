import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum ScoringCategory {
    CHANCE(roll -> Arrays.stream(roll.getDice()).sum()),
    YATZY(roll -> Arrays.stream(roll.getDice()).distinct().count() == 1 ? 50 : 0),
    ONES(roll -> sumForValue(roll, 1)),
    TWOS(roll -> sumForValue(roll, 2)),
    THREES(roll -> sumForValue(roll, 3)),
    FOURS(roll -> sumForValue(roll, 4)),
    FIVES(roll -> sumForValue(roll, 5)),
    SIXES(roll -> sumForValue(roll, 6)),
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

    private static int sumForValue(DiceRoll roll, int value) {
        return Arrays.stream(roll.getDice()).filter(die -> die == value).sum();
    }

    private static Map<Integer, Long> findMultiples(DiceRoll roll) {
        return Arrays.stream(roll.getDice())
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static int findHighestMultiple(DiceRoll roll, int multiple) {
        Map<Integer, Long> counts = findMultiples(roll);
        return counts.entrySet().stream()
            .filter(entry -> entry.getValue() >= multiple)
            .mapToInt(Map.Entry::getKey)
            .max()
            .orElse(0);
    }

    private static int[] findPairs(DiceRoll roll) {
        Map<Integer, Long> counts = findMultiples(roll);
        return counts.entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .mapToInt(Map.Entry::getKey)
            .toArray();
    }

}
