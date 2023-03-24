package view;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainView {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemberView memberView = new MemberView();
        BookView bookView = new BookView();
        LoanView loanView = new LoanView();

        System.out.println(
                "==================================================================================================");
        System.out.println("도서 대출 관리 프로그램 - 0.1.0 version");
        System.out.println(
                "==================================================================================================");

        while (true) {
            System.out.println("[1]회원관리\t [2]도서관리 \t [3]대출관리 \t [4]종료");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case ("1"):
                    memberView.run();
                    break;
                case ("2"):
                    bookView.run();
                    break;
                case ("3"):
                    loanView.run();
                    break;
                case ("4"):
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    System.exit(0);;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }
}