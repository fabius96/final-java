package view;

import java.util.Scanner;
import domain.Loan;
import service.BookService;
import service.LoanService;

public class LoanView {
    LoanService loanService = new LoanService();
    BookService bookService = new BookService();

    public LoanView() {}

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[0]뒤로 \t [1]대출현황조회 \t [2]도서대출 \t [3]대출연장 \t [4]도서반납");

            String userInput = scanner.nextLine();

            switch (userInput) {
                case ("0"): // 뒤로
                    return;
                case ("1"): // 대출현황조회
                    loanService.inquiryLoanData();
                    break;
                case ("2"): // 도서대출
                    System.out.println("대출 가능 도서 목록");
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    bookService.inquiryLoanableBook();
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("대출을 신청하실 도서명을 입력해주세요");
                    userInput = scanner.nextLine();
                    String title = userInput;

                    System.out.println("회원명 입력해주세요");
                    userInput = scanner.nextLine();
                    String name = userInput;

                    Loan loan = new Loan(title, name);
                    loanService.bookLoan(loan);
                    bookService.changeReturnStatus(title);
                    break;
                case ("3"): // 대출연장
                    loanService.inquiryLoanData();
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("대출을 연장시킬 도서의 제목을 입력해주세요.");
                    userInput = scanner.nextLine();
                    loanService.updateLoanData(userInput);
                    break;
                case ("4"): // 도서반납
                    loanService.inquiryLoanData();
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("반납하실 도서의 제목을 입력해주세요.");
                    userInput = scanner.nextLine();
                    loanService.returnBook(userInput);
                    bookService.changeReturnStatus(userInput);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }
}