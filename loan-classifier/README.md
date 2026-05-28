
## HỆ THỐNG NGÂN HÀNG CS2045

---

## 1. TỔNG QUAN HỆ THỐNG & RÀNG BUỘC ĐẦU VÀO
Mô-đun phần mềm chịu trách nhiệm tự động ra quyết định phê duyệt các khoản vay cá nhân dựa trên 4 tham số đầu vào. Nếu bất kỳ tham số nào vi phạm ràng buộc miền giá trị, hệ thống ngay lập tức từ chối xử lý và trả về kết quả `Invalid Input`.

### Miền giá trị của các tham số:
1. **age (Số nguyên):** $18 \le age \le 65$.
2. **income (Số thực, 1 chữ số thập phân - triệu VNĐ):** $5.0 \le income \le 500.0$.
3. **credit_score (Số nguyên):** Thang điểm chuẩn từ $300 \le credit\_score \le 850$.
4. **employment (Ký tự):** Chỉ nhận giá trị `"C"` (Có hợp đồng) hoặc `"F"` (Làm tự do).

---

## 2. THIẾT KẾ KIỂM THỬ HỘP ĐEN (TEST DESIGN)

### 2.1. Phân hoạch tương đương (EP) & Phân tích giá trị biên (BVA)

| Tham số | Vùng không hợp lệ dưới (Biên) | Vùng hợp lệ (Biên / Danh nghĩa) | Vùng không hợp lệ trên (Biên) |
| :--- | :--- | :--- | :--- |
| **age** | $age < 18$ (`17`) | $18 \le age \le 65$ (`18`, `30`, `65`) | $age > 65$ (`66`) |
| **income** | $income < 5.0$ (`4.9`) | $5.0 \le income \le 500.0$<br>- Thấp: $[5.0, 15.0)$ (`5.0`, `14.9`) <br>- Cao: $[15.0, 500.0]$ (`15.0`, `500.0`) | $income > 500.0$ (`500.1`) |
| **credit_score**| $credit\_score < 300$ (`299`) | $300 \le credit\_score \le 850$<br>- High Risk: $[300, 500]$ (`300`, `500`) <br>- Med Risk: $[501, 700]$ (`501`, `700`) <br>- Low Risk: $[701, 850]$ (`701`, `850`) | $credit\_score > 850$ (`851`) |
| **employment** | Không thuộc `{"C", "F"}` (`"X"`) | `{"C", "F"}` | Không có |

### 2.2. Bảng quyết định rút gọn (Decision Table) cho Logic hợp lệ
Hệ thống tổ hợp logic dựa trên 3 yếu tố: Mức rủi ro (3 nhánh), Mức thu nhập (2 nhánh mốc 15.0), và Hình thức việc làm (2 nhánh). Áp dụng quy tắc rút gọn (gộp các điều kiện không ảnh hưởng đến kết quả), ta thu được bảng kịch bản tối thiểu:

| Kịch bản / Điều kiện | R1 | R2 | R3 | R4 | R5 | R6 |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| **C1: Mức rủi ro (Risk Level)** | **High** | Medium | Low | Low | Medium/Low | Medium/Low |
| **C2: Thu nhập (Income)** | Bất kỳ (-) | **< 15.0** | **< 15.0** | **< 15.0** | **$\ge 15.0$** | **$\ge 15.0$** |
| **C3: Việc làm (Employment)** | Bất kỳ (-) | Bất kỳ (-) | **"F"** | **"C"** | **"C"** | **"F"** |
| **Hành động (Action)** | | | | | | |
| Kết quả: `REJECT` | **X** | **X** | **X** | | | |
| Kết quả: `MANUAL REVIEW` | | | | **X** | | **X** |
| Kết quả: `APPROVE` | | | | | **X** | |

---

## 3. DANH SÁCH CÁC CA KIỂM THỬ (TEST CASES SPECIFICATION)

Bộ dữ liệu kiểm thử gồm **17 test cases** bao phủ toàn bộ các giá trị biên đầu vào và các luật logic nghiệp vụ:

| TC_ID | age | income | credit_score | employment | Kết quả mong đợi | Mục đích kiểm thử |
| :--- | :---: | :---: | :---: | :---: | :--- | :--- |
| **TC01** | **17** | 50.0 | 750 | "C" | `Invalid Input` | Biên dưới biến `age` |
| **TC02** | **66** | 50.0 | 750 | "C" | `Invalid Input` | Biên trên biến `age` |
| **TC03** | 30 | **4.9** | 750 | "C" | `Invalid Input` | Biên dưới biến `income` |
| **TC04** | 30 | **500.1**| 750 | "C" | `Invalid Input` | Biên trên biến `income` |
| **TC05** | 30 | 50.0 | **299** | "C" | `Invalid Input` | Biên dưới biến `credit_score` |
| **TC06** | 30 | 50.0 | **851** | "C" | `Invalid Input` | Biên trên biến `credit_score` |
| **TC07** | 30 | 50.0 | 750 | **"X"** | `Invalid Input` | Định dạng sai của biến `employment` |
| **TC08** | 30 | 50.0 | **300** | "C" | `REJECT` | **Rule 1**: Biên dưới nhóm High Risk |
| **TC09** | 30 | 10.0 | **500** | "F" | `REJECT` | **Rule 1**: Biên trên nhóm High Risk |
| **TC10** | 30 | **14.9**| **501** | "C" | `REJECT` | **Rule 2**: Thu nhập thấp + Biên dưới Med Risk |
| **TC11** | 30 | **5.0** | **700** | "F" | `REJECT` | **Rule 2**: Thu nhập cực tiểu + Biên trên Med Risk |
| **TC12** | 30 | 10.0 | **701** | **"F"** | `REJECT` | **Rule 3**: Thu nhập thấp + Low Risk + Freelance |
| **TC13** | 30 | **14.9**| 750 | **"C"** | `MANUAL REVIEW` | **Rule 4**: Thu nhập thấp sát biên + Low Risk + Contract |
| **TC14** | 30 | **15.0**| **600** | **"C"** | `APPROVE` | **Rule 5**: Mốc biên thu nhập 15.0 + Med Risk + Contract |
| **TC15** | 30 | **500.0**| **850** | **"C"** | `APPROVE` | **Rule 5**: Thu nhập cực đại + Biên trên Low Risk + Contract |
| **TC16** | 30 | 25.0 | 650 | **"F"** | `MANUAL REVIEW` | **Rule 6**: Thu nhập cao + Med Risk + Freelance |
| **TC17** | 30 | **15.0**| 800 | **"F"** | `MANUAL REVIEW` | **Rule 6**: Mốc biên thu nhập 15.0 + Low Risk + Freelance |

---

## 4. KẾT QUẢ THỰC THI KIỂM THỬ (TEST EXECUTION REPORT)

Toàn bộ **17/17 test cases** đã được chạy tự động thông qua công cụ build Maven kết hợp framework JUnit 5. Hệ thống vượt qua tất cả các bài thử nghiệm với tỷ lệ thành công tuyệt đối **100%**.

### Nhật ký chạy Test từ Terminal VS Code (`mvn test`):

```text
Microsoft Windows [Version 10.0.22631.3527]
(c) Microsoft Corporation. All rights reserved.

C:\Users\laptop\Downloads\2526II_INT2208_8\loan-classifier> mvn test

[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< com.bank.cs2045:loan-classifier >------------------
[INFO] Building CS2045 Loan Approval Classifier 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ java ]--------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ loan-classifier ---
[INFO] skip non existing resourceDirectory C:\Users\laptop\Downloads\2526II_INT2208_8\loan-classifier\src\main\resources
[INFO] 
[INFO] --- compiler:3.11.0:compile (default-compile) @ loan-classifier ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file with javac [debug target 11] to target\classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ loan-classifier ---
[INFO] skip non existing resourceDirectory C:\Users\laptop\Downloads\2526II_INT2208_8\loan-classifier\src\test\resources
[INFO] 
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ loan-classifier ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file with javac [debug target 11] to target\test-classes
[INFO] 
[INFO] --- surefire:3.1.2:test (default-test) @ loan-classifier ---
[INFO] Using configured provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running CS2045LoanClassifierTest
[INFO] 
[PASSED] CS2045LoanClassifierTest > TC01: Tuổi dưới biên dưới (17)
[PASSED] CS2045LoanClassifierTest > TC02: Tuổi vượt biên trên (66)
[PASSED] CS2045LoanClassifierTest > TC03: Thu nhập dưới biên dưới (4.9)
[PASSED] CS2045LoanClassifierTest > TC04: Thu nhập vượt biên trên (500.1)
[PASSED] CS2045LoanClassifierTest > TC05: Điểm tín dụng dưới biên dưới (299)
[PASSED] CS2045LoanClassifierTest > TC06: Điểm tín dụng vượt biên trên (851)
[PASSED] CS2045LoanClassifierTest > TC07: Sai định dạng ký tự việc làm (X)
[PASSED] CS2045LoanClassifierTest > TC08: Rule 1 - Biên dưới High Risk (300)
[PASSED] CS2045LoanClassifierTest > TC09: Rule 1 - Biên trên High Risk (500)
[PASSED] CS2045LoanClassifierTest > TC10: Rule 2 - Thu nhập thấp sát biên + Biên dưới Med Risk
[PASSED] CS2045LoanClassifierTest > TC11: Rule 2 - Thu nhập cực tiểu + Biên trên Med Risk
[PASSED] CS2045LoanClassifierTest > TC12: Rule 3 - Thu nhập thấp + Low Risk + Freelance
[PASSED] CS2045LoanClassifierTest > TC13: Rule 4 - Thu nhập thấp sát biên + Low Risk + Contract
[PASSED] CS2045LoanClassifierTest > TC14: Rule 5 - Biên thu nhập 15.0 + Med Risk + Contract
[PASSED] CS2045LoanClassifierTest > TC15: Rule 5 - Thu nhập cực đại + Biên trên Low Risk + Contract
[PASSED] CS2045LoanClassifierTest > TC16: Rule 6 - Thu nhập cao + Med Risk + Freelance
[PASSED] CS2045LoanClassifierTest > TC17: Rule 6 - Biên thu nhập 15.0 + Low Risk + Freelance
[INFO] 
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.054 s -- in CS2045LoanClassifierTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.185 s
[INFO] Finished at: 2026-05-28T19:40:12+07:00
[INFO] ------------------------------------------------------------------------

C:\Users\laptop\Downloads\2526II_INT2208_8\loan-classifier> _