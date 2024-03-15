import java.util.Arrays;

public enum ScoringCategory implements ScoringStrategy {
    CHANCE {
        @Override
        public int calculateScore(DiceRoll roll) {
            return roll.getD1() + roll.getD2() + roll.getD3() + roll.getD4() + roll.getD5();
        }
    },
    YATZY {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] counts = countDice(roll);
            for (int i = 0; i != 6; i++)
                if (counts[i] == 5)
                    return 50;
            return 0;
        }
    },
    ONES {
        @Override
        public int calculateScore(DiceRoll roll) {
            return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
                .filter(die -> die == 1)
                .sum();
        }
    },
    TWOES {
        @Override
        public int calculateScore(DiceRoll roll) {
            return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
                .filter(die -> die == 2)
                .sum();
        }
    },
    THREES {
        @Override
        public int calculateScore(DiceRoll roll) {
            return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
                .filter(die -> die == 3)
                .sum();
        }
    },
    FOURS {
        @Override
        public int calculateScore(DiceRoll roll) {
            return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
                .filter(die -> die == 4)
                .sum();
        }
    },
    FIVES {
        @Override
        public int calculateScore(DiceRoll roll) {
            return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
                .filter(die -> die == 5)
                .sum();
        }
    },
    SIXES {
        @Override
        public int calculateScore(DiceRoll roll) {
            return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
                .filter(die -> die == 6)
                .sum();
        }
    },
    ONE_PAIR {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] counts = countDice(roll);
            int at;
            for (at = 0; at != 6; at++)
                if (counts[6 - at - 1] >= 2)
                    return (6 - at) * 2;
            return 0;
        }
    },
    TWO_PAIR {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] counts = countDice(roll);
            int n = 0;
            int score = 0;
            for (int i = 0; i < 6; i += 1)
                if (counts[6 - i - 1] >= 2) {
                    n++;
                    score += (6 - i);
                }
            if (n == 2)
                return score * 2;
            else
                return 0;
        }
    },
    FOUR_OF_A_KIND {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] tallies = countDice(roll);
            for (int i = 0; i < 6; i++)
                if (tallies[i] >= 4)
                    return (i + 1) * 4;
            return 0;
        }
    },
    THREE_OF_A_KIND {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] tallies = countDice(roll);
            for (int i = 0; i < 6; i++)
                if (tallies[i] >= 3)
                    return (i + 1) * 3;
            return 0;
        }
    }, SMALL_STRAIGHT {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] tallies = countDice(roll);
            if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1)
                return 15;
            return 0;
        }
    }, LARGE_STRAIGHT {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] tallies = countDice(roll);
            if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1)
                return 20;
            return 0;
        }
    }, FULL_HOUSE {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] counts = countDice(roll);
            boolean hasThreeOfAKind = false;
            boolean hasPair = false;
            for (int count : counts) {
                if (count == 3) hasThreeOfAKind = true;
                if (count == 2) hasPair = true;
            }
            return hasThreeOfAKind && hasPair ? roll.getD1() + roll.getD2() + roll.getD3() + roll.getD4() + roll.getD5() : 0;
        }
    };

    private static int[] countDice(DiceRoll roll) {
        int[] counts = new int[6];
        int[] dice = {roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()};
        for (int die : dice) {
            counts[die - 1]++;
        }
        return counts;
    }
}