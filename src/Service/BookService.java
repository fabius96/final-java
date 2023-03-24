package service;

import java.util.List;
import domain.Book;
import util.CSVReader;
import util.CSVWriter;

public class BookService {
    CSVReader csvReader = new CSVReader();
    CSVWriter csvWriter = new CSVWriter();

    public BookService() {
    }

    /**
     * 대출 가능 도서 조회 메소드
     */
    public void inquiryLoanableBook() {
        List<Book> books = csvReader.readBookCSV();
        for(Book book : books) {
            System.out.println(book);
        }
    }

    /**
     * 도서 목록 조회(최신순 정렬)
     */
    public void inquiryBookListOrderByDESC() {
        List<Book> books = csvReader.readBookCSVOrderByreleaseDate();
        for(Book book : books) {
            System.out.println(book);
        }
    }

    /**
     * 도서 등록 메소드
     * @param book
     */
    public void bookRegistration(Book book) {
        csvWriter.writeCSV(book);
        System.out.println("도서 등록이 완료되었습니다.");
    }

    /**
     * 도서 삭제 메소드
     * @param bookTitle
     */
    public void deleteBook(String bookTitle) {
        csvWriter.deleteBook(bookTitle);
        System.out.println("도서 삭제가 완료되었습니다.");
    }

    /**
     * 도서 반납 상태 변경 메소드
     * @param bookTitle
     */
    public void changeReturnStatus(String bookTitle) {
        csvWriter.changeReturnStatus(bookTitle);
        System.out.println("도서 반납 상태가 변경되었습니다.");
    }
}