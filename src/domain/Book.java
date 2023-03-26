package domain;

import java.util.concurrent.atomic.AtomicInteger;

// 책
public class Book implements Comparable<Book> {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    private int id; // PK
    private String title; // 제목
    private final String releaseDate; // 출간일
    private boolean returnStatus; // 반납여부

    // 생성자 메소드(도서 등록)
    public Book(String title, String releaseDate) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.title = title;
        this.releaseDate = releaseDate;
        this.returnStatus = true;
    }

    public Book(String title, String releaseDate, boolean returnStatus) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.returnStatus = returnStatus;
    }

    // getter / setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public boolean isReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(boolean returnStatus) {
        this.returnStatus = returnStatus;
    }

    @Override
    public String toString() {
        return "도서목록 [제목 : " + title + ", 출간일 : " + releaseDate + ", 반납여부 : "
                + returnStatus + "]";
    }

    @Override
    public int compareTo(Book book) {
        return this.getReleaseDate().compareTo(book.releaseDate);
    }
}