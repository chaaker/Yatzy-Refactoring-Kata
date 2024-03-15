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
            return findHighestMultiple(counts, 2) * 2;
        }
    },
    TWO_PAIR {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] counts = countDice(roll);
            int firstPair = findHighestMultiple(counts, 2);
            if (firstPair > 0) {
                counts[firstPair - 1] = 0;
                int secondPair = findHighestMultiple(counts, 2);
                if (secondPair > 0) {
                    return firstPair * 2 + secondPair * 2;
                }
            }
            return 0;
        }
    },
    FOUR_OF_A_KIND {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] counts = countDice(roll);
            int fourOfAKind = findHighestMultiple(counts, 4);
            return fourOfAKind * 4;
        }
    },
    THREE_OF_A_KIND {
        @Override
        public int calculateScore(DiceRoll roll) {
            int[] counts = countDice(roll);
            int threeOfAKind = findHighestMultiple(counts, 3);
            return threeOfAKind * 3;
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

    private static int findHighestMultiple(int[] counts, int multiple) {
        for (int i = 5; i >= 0; i--) {
            if (counts[i] >= multiple) {
                return i + 1;
            }
        }
        return 0;
    }
}