
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {

    @Test
    @DisplayName("TC01: Biên dưới cạnh a nhỏ hơn 1")
    public void testTC01() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(0, 50, 50));
    }

    @Test
    @DisplayName("TC02: Biên trên cạnh a lớn hơn 100")
    public void testTC02() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(101, 50, 50));
    }

    @Test
    @DisplayName("TC03: Biên dưới cạnh b nhỏ hơn 1")
    public void testTC03() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(50, 0, 50));
    }

    @Test
    @DisplayName("TC04: Biên trên cạnh c lớn hơn 100")
    public void testTC04() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(50, 50, 101));
    }

    @Test
    @DisplayName("TC04_b: Bổ sung biên trên cạnh b lớn hơn 100")
    public void testTC04b() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(50, 101, 50));
    }

    @Test
    @DisplayName("TC04_c: Bổ sung biên dưới cạnh c nhỏ hơn 1")
    public void testTC04c() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(50, 50, 0));
    }

    @Test
    @DisplayName("TC05: Tổng hai cạnh nhỏ hơn cạnh còn lại (10+20 < 50)")
    public void testTC05() {
        assertEquals("Not a Triangle", Triangle.classifyTriangle(10, 20, 50));
    }

    @Test
    @DisplayName("TC06: Đường biên nhạy cảm tổng hai cạnh bằng cạnh còn lại (1+2 = 3)")
    public void testTC06() {
        assertEquals("Not a Triangle", Triangle.classifyTriangle(1, 2, 3));
    }

    @Test
    @DisplayName("TC07: Đảo vị trí - Tổng b+c nhỏ hơn a")
    public void testTC07() {
        assertEquals("Not a Triangle", Triangle.classifyTriangle(50, 10, 20));
    }

    @Test
    @DisplayName("TC08: Tam giác đều - Giá trị danh nghĩa ở giữa")
    public void testTC08() {
        assertEquals("Equilateral", Triangle.classifyTriangle(50, 50, 50));
    }

    @Test
    @DisplayName("TC09: Tam giác cân tại c (a = b)")
    public void testTC09() {
        assertEquals("Isosceles", Triangle.classifyTriangle(50, 50, 20));
    }

    @Test
    @DisplayName("TC10: Tam giác cân tại a (b = c)")
    public void testTC10() {
        assertEquals("Isosceles", Triangle.classifyTriangle(20, 50, 50));
    }

    @Test
    @DisplayName("TC11: Tam giác cân tại b (a = c)")
    public void testTC11() {
        assertEquals("Isosceles", Triangle.classifyTriangle(50, 20, 50));
    }

    @Test
    @DisplayName("TC12: Tam giác thường - Bộ số kinh điển")
    public void testTC12() {
        assertEquals("Scalene", Triangle.classifyTriangle(3, 4, 5));
    }

    @Test
    @DisplayName("TC13: Tam giác thường - Sát biên trên")
    public void testTC13() {
        assertEquals("Scalene", Triangle.classifyTriangle(98, 99, 100));
    }
}
