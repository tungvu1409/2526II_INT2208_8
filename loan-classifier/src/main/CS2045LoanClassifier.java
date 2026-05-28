
public class CS2045LoanClassifier {

    public static String evaluateLoan(int age, double income, int creditScore, String employment) {

        if (age < 18 || age > 65) {
            return "Invalid Input";
        }
        if (income < 5.0 || income > 500.0) {
            return "Invalid Input";
        }
        if (creditScore < 300 || creditScore > 850) {
            return "Invalid Input";
        }
        if (!"C".equals(employment) && !"F".equals(employment)) {
            return "Invalid Input";
        }

        String risk;
        if (creditScore >= 300 && creditScore <= 500) {
            risk = "High";
        } else if (creditScore >= 501 && creditScore <= 700) {
            risk = "Medium";
        } else {
            risk = "Low";
        }

        if ("High".equals(risk)) {
            return "REJECT";
        }

        if (income < 15.0) {
            if ("Medium".equals(risk) || "F".equals(employment)) {
                return "REJECT";
            }
            return "MANUAL REVIEW";
        } else {

            if ("C".equals(employment)) {
                return "APPROVE";
            } else {
                return "MANUAL REVIEW";
            }
        }
    }
}
