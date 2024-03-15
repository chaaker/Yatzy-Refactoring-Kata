import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceRollTest {

    @Test
    public void testValidDice() {
        assertDoesNotThrow(() -> new DiceRoll(1, 2, 3, 4, 5));
        assertDoesNotThrow(() -> new DiceRoll(6, 6, 6, 6, 6));
    }

    @Test
    public void testInvalidDice() {
        assertThrows(IllegalArgumentException.class, () -> new DiceRoll(0, 2, 3, 4, 5));
        assertThrows(IllegalArgumentException.class, () -> new DiceRoll(1, 2, 3, 4, 7));
    }
}