
public class Triangle {

    public static String classifyTriangle(int a, int b, int c) {

        if (a < 1 || a > 100 || b < 1 || b > 100 || c < 1 || c > 100) {
            return "Invalid Input";
        }

        if ((a + b <= c) || (a + c <= b) || (b + c <= a)) {
            return "Not a Triangle";
        }

        if (a == b && b == c) {
            return "Equilateral";
        }
        if (a == b || b == c || a == c) {
            return "Isosceles";
        }
        return "Scalene";
    }
}
