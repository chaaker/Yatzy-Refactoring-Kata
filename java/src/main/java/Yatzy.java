import java.util.Arrays;

public class Yatzy {

    public static int chance(DiceRoll roll) {
        return roll.getD1() + roll.getD2() + roll.getD3() + roll.getD4() + roll.getD5();
    }

    public static int yatzy(int... dice) {
        int[] counts = new int[6];
        for (int die : dice)
            counts[die - 1]++;
        for (int i = 0; i != 6; i++)
            if (counts[i] == 5)
                return 50;
        return 0;
    }

    public static int ones(DiceRoll roll) {
        return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
            .filter(die -> die == 1)
            .sum();
    }

    public static int twos(DiceRoll roll) {
        return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
            .filter(die -> die == 2)
            .sum();
    }

    public static int threes(DiceRoll roll) {
        return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
            .filter(die -> die == 3)
            .sum();
    }

    public static int fours(DiceRoll roll) {
        return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
            .filter(die -> die == 4)
            .sum();

    }

    public static int fives(DiceRoll roll) {
        return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
            .filter(die -> die == 5)
            .sum();
    }

    public static int sixes(DiceRoll roll) {
        return Arrays.stream(new int[]{roll.getD1(), roll.getD2(), roll.getD3(), roll.getD4(), roll.getD5()})
            .filter(die -> die == 6)
            .sum();
    }

    public static int score_pair(DiceRoll roll) {
        int[] counts = new int[6];
        counts[roll.getD1() - 1]++;
        counts[roll.getD2() - 1]++;
        counts[roll.getD3() - 1]++;
        counts[roll.getD4() - 1]++;
        counts[roll.getD5() - 1]++;
        int at;
        for (at = 0; at != 6; at++)
            if (counts[6 - at - 1] >= 2)
                return (6 - at) * 2;
        return 0;
    }

    public static int two_pair(DiceRoll roll) {
        int[] counts = new int[6];
        counts[roll.getD1() - 1]++;
        counts[roll.getD2() - 1]++;
        counts[roll.getD3() - 1]++;
        counts[roll.getD4() - 1]++;
        counts[roll.getD5() - 1]++;
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

    public static int four_of_a_kind(DiceRoll roll) {
        int[] tallies;
        tallies = new int[6];
        tallies[roll.getD1() - 1]++;
        tallies[roll.getD2() - 1]++;
        tallies[roll.getD3() - 1]++;
        tallies[roll.getD4() - 1]++;
        tallies[roll.getD5() - 1]++;
        for (int i = 0; i < 6; i++)
            if (tallies[i] >= 4)
                return (i + 1) * 4;
        return 0;
    }

    public static int three_of_a_kind(DiceRoll roll) {
        int[] tallies;
        tallies = new int[6];
        tallies[roll.getD1() - 1]++;
        tallies[roll.getD2() - 1]++;
        tallies[roll.getD3() - 1]++;
        tallies[roll.getD4() - 1]++;
        tallies[roll.getD5() - 1]++;
        for (int i = 0; i < 6; i++)
            if (tallies[i] >= 3)
                return (i + 1) * 3;
        return 0;
    }

    public static int smallStraight(DiceRoll roll) {
        int[] tallies;
        tallies = new int[6];
        tallies[roll.getD1() - 1] += 1;
        tallies[roll.getD2() - 1] += 1;
        tallies[roll.getD3() - 1] += 1;
        tallies[roll.getD4() - 1] += 1;
        tallies[roll.getD5() - 1] += 1;
        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(DiceRoll roll) {
        int[] tallies;
        tallies = new int[6];
        tallies[roll.getD1() - 1] += 1;
        tallies[roll.getD2() - 1] += 1;
        tallies[roll.getD3() - 1] += 1;
        tallies[roll.getD4() - 1] += 1;
        tallies[roll.getD5() - 1] += 1;
        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1
            && tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(DiceRoll roll) {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;


        tallies = new int[6];
        tallies[roll.getD1() - 1] += 1;
        tallies[roll.getD2() - 1] += 1;
        tallies[roll.getD3() - 1] += 1;
        tallies[roll.getD4() - 1] += 1;
        tallies[roll.getD5() - 1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
}



