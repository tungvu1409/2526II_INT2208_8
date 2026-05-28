
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CS2045LoanClassifierTest {

    @Test
    @DisplayName("TC01: Tuổi dưới biên dưới (17)")
    public void testTC01() {
        assertEquals("Invalid Input", CS2045LoanClassifier.evaluateLoan(17, 50.0, 750, "C"));
    }

    @Test
    @DisplayName("TC02: Tuổi vượt biên trên (66)")
    public void testTC02() {
        assertEquals("Invalid Input", CS2045LoanClassifier.evaluateLoan(66, 50.0, 750, "C"));
    }

    @Test
    @DisplayName("TC03: Thu nhập dưới biên dưới (4.9)")
    public void testTC03() {
        assertEquals("Invalid Input", CS2045LoanClassifier.evaluateLoan(30, 4.9, 750, "C"));
    }

    @Test
    @DisplayName("TC04: Thu nhập vượt biên trên (500.1)")
    public void testTC04() {
        assertEquals("Invalid Input", CS2045LoanClassifier.evaluateLoan(30, 500.1, 750, "C"));
    }

    @Test
    @DisplayName("TC05: Điểm tín dụng dưới biên dưới (299)")
    public void testTC05() {
        assertEquals("Invalid Input", CS2045LoanClassifier.evaluateLoan(30, 50.0, 299, "C"));
    }

    @Test
    @DisplayName("TC06: Điểm tín dụng vượt biên trên (851)")
    public void testTC06() {
        assertEquals("Invalid Input", CS2045LoanClassifier.evaluateLoan(30, 50.0, 851, "C"));
    }

    @Test
    @DisplayName("TC07: Sai định dạng ký tự việc làm (X)")
    public void testTC07() {
        assertEquals("Invalid Input", CS2045LoanClassifier.evaluateLoan(30, 50.0, 750, "X"));
    }

    @Test
    @DisplayName("TC08: Rule 1 - Biên dưới High Risk (300)")
    public void testTC08() {
        assertEquals("REJECT", CS2045LoanClassifier.evaluateLoan(30, 50.0, 300, "C"));
    }

    @Test
    @DisplayName("TC09: Rule 1 - Biên trên High Risk (500)")
    public void testTC09() {
        assertEquals("REJECT", CS2045LoanClassifier.evaluateLoan(30, 10.0, 500, "F"));
    }

    @Test
    @DisplayName("TC10: Rule 2 - Thu nhập thấp sát biên + Biên dưới Med Risk")
    public void testTC10() {
        assertEquals("REJECT", CS2045LoanClassifier.evaluateLoan(30, 14.9, 501, "C"));
    }

    @Test
    @DisplayName("TC11: Rule 2 - Thu nhập cực tiểu + Biên trên Med Risk")
    public void testTC11() {
        assertEquals("REJECT", CS2045LoanClassifier.evaluateLoan(30, 5.0, 700, "F"));
    }

    @Test
    @DisplayName("TC12: Rule 3 - Thu nhập thấp + Low Risk + Freelance")
    public void testTC12() {
        assertEquals("REJECT", CS2045LoanClassifier.evaluateLoan(30, 10.0, 701, "F"));
    }

    @Test
    @DisplayName("TC13: Rule 4 - Thu nhập thấp sát biên + Low Risk + Contract")
    public void testTC13() {
        assertEquals("MANUAL REVIEW", CS2045LoanClassifier.evaluateLoan(30, 14.9, 750, "C"));
    }

    @Test
    @DisplayName("TC14: Rule 5 - Biên thu nhập 15.0 + Med Risk + Contract")
    public void testTC14() {
        assertEquals("APPROVE", CS2045LoanClassifier.evaluateLoan(30, 15.0, 600, "C"));
    }

    @Test
    @DisplayName("TC15: Rule 5 - Thu nhập cực đại + Biên trên Low Risk + Contract")
    public void testTC15() {
        assertEquals("APPROVE", CS2045LoanClassifier.evaluateLoan(30, 500.0, 850, "C"));
    }

    @Test
    @DisplayName("TC16: Rule 6 - Thu nhập cao + Med Risk + Freelance")
    public void testTC16() {
        assertEquals("MANUAL REVIEW", CS2045LoanClassifier.evaluateLoan(30, 25.0, 650, "F"));
    }

    @Test
    @DisplayName("TC17: Rule 6 - Biên thu nhập 15.0 + Low Risk + Freelance")
    public void testTC17() {
        assertEquals("MANUAL REVIEW", CS2045LoanClassifier.evaluateLoan(30, 15.0, 800, "F"));
    }
}
