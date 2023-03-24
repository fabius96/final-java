package domain;

import java.time.LocalDate;
import view.MainView;

// 대출정보
public class Loan implements Comparable<Loan> {
    // ------ 추후 DB 연결 시 id(FK)값으로 리팩터링 필요 ------//
    private String bookTitle; // 도서명
    // ------ 추후 DB 연결 시 id(FK)값으로 리팩터링 필요 ------//
    private String memberName; // 회원명

    private boolean extensionStatus; // 연장여부
    private String loanDate; // 대출일
    private String deadline; // 반납기한

    // 생성자 메소드(도서 대출)
    public Loan(String bookName, String memberName) {
        this.bookTitle = bookName;
        this.memberName = memberName;
        this.extensionStatus = false;
        this.loanDate = LocalDate.now().format(MainView.formatter);
        this.deadline = String.valueOf(13);
    }

    public Loan(String bookName, String memberName, boolean extensionStatus, String loanDate,
                String deadline) {
        this.bookTitle = bookName;
        this.memberName = memberName;
        this.extensionStatus = extensionStatus;
        this.loanDate = loanDate;
        this.deadline = deadline;
    }

    // getter / setter
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookName) {
        this.bookTitle = bookName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public boolean isExtensionStatus() {
        return extensionStatus;
    }

    public void setExtensionStatus(boolean extensionStatus) {
        this.extensionStatus = extensionStatus;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "대출 현황 [도서명 : " + bookTitle + ", 회원명 : " + memberName + ", 연장여부 : "
                + extensionStatus + ", 대출일: " + loanDate + ", 반납기한 : " + deadline + "]";
    }

    @Override
    public int compareTo(Loan loan) {
        Integer x = Integer.parseInt(this.deadline);
        return x.compareTo(Integer.parseInt(loan.deadline));
    }

}