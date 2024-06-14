import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void isLarger() {
        assertEquals(8, Calculator.add(5, 3));
    }
}