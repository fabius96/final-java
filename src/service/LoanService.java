package service;

import java.util.List;
import domain.Loan;
import util.CSVReader;
import util.CSVWriter;

public class LoanService {
    CSVWriter csvWriter = new CSVWriter();
    CSVReader csvReader = new CSVReader();

    /**
     * 대출 연장 메소드
     * @param bookTitle
     */
    public void updateLoanData(String bookTitle) {
        csvWriter.loanExtention(bookTitle);
    }

    /**
     * 대출 현황 조회 메소드
     */
    public void inquiryLoanData() {
        List<Loan> loans = csvReader.readCSV();
        for(Loan loan : loans) {
            System.out.println(loan);
        }
    }

    /**
     * 도서 대출 메소드
     * @param Loan
     */
    public void bookLoan(Loan loan) {
        csvWriter.writeCSV(loan);
        System.out.println("대출신청이 접수되었습니다.");
    }

    /**
     * 도서 반납 메소드
     * @param bookTitle
     */
    public void returnBook(String bookTitle) {
        csvWriter.returnBook(bookTitle);
        System.out.println("반납처리가 완료되었습니다.");
    }
}