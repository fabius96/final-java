package view;

import java.util.Scanner;
import domain.Book;
import service.BookService;

public class BookView {
    BookService bookService = new BookService();

    public BookView() {}

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[0]뒤로 \t [1]도서조회 \t [2]도서등록 \t [3]도서삭제");

            String userInput = scanner.nextLine();

            switch (userInput) {
                case ("0"): // 뒤로
                    return;
                case ("1"): // 도서조회
                    runinquiry();
                    break;
                case ("2"): // 도서등록
                    System.out.println("제목을 입력해주세요");
                    userInput = scanner.nextLine();
                    String title = userInput;

                    System.out.println("출간일 입력해주세요 \t (입력예시 : 2023/03/23)");
                    userInput = scanner.nextLine();
                    String releaseDate = userInput;

                    Book book = new Book(title, releaseDate);
                    bookService.bookRegistration(book);
                    break;
                case ("3"): // 도서삭제
                    bookService.inquiryBookListOrderByDESC();
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("삭제할 도서의 제목을 입력해주세요.");
                    userInput = scanner.nextLine();
                    bookService.deleteBook(userInput);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }

    public void runinquiry() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[0]뒤로 \t [1]전체도서조회 \t [2]대출가능도서조회");

            String userInput = scanner.nextLine();

            switch (userInput) {
                case ("0"): // 뒤로
                    return;
                case ("1"): // 전체도서조회
                    bookService.inquiryBookListOrderByDESC();
                    break;
                case ("2"): // 대출가능도서조회
                    bookService.inquiryLoanableBook();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }
}