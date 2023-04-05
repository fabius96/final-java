package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import domain.Book;
import domain.Loan;
import domain.Member;

// ------ 추후 중복 코드들에 대한 리팩터링 필요 ------//
public class CSVReader {

    /**
     * 대출 목록 표시 메소드
     *
     * @return
     */
    public ArrayList<Loan> readCSV() {
        ArrayList<Loan> loans = new ArrayList<>();
        BufferedReader bufferedReader = null;

        /* csv 파일이 없을 경우의 예외처리 */
        if (!new File("loan.csv").exists()) {
            throw new IllegalArgumentException("등록된 대출 정보가 없습니다.");
        }

        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("loan.csv"), "EUC-KR"));

            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] loanData = line.split(",");

                int id = Integer.parseInt(loanData[0]);
                String bookName = loanData[1];
                String memberName = loanData[2];
                boolean extensionStatus = Boolean.parseBoolean(loanData[3]);
                String loanDate = loanData[4];
                String deadline = loanData[5];

                Loan loan = new Loan(id, bookName, memberName, extensionStatus, loanDate,
                        deadline);
                loans.add(loan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(loans);
        return loans;
    }

    /**
     * '대출 가능한 책만 보기' 메소드
     *
     * @return
     */
    public ArrayList<Book> readBookCSV() {
        ArrayList<Book> books = new ArrayList<>();
        BufferedReader bufferedReader = null;

        /* csv 파일이 없을 경우의 예외처리 */
        if (!new File("book.csv").exists()) {
            throw new IllegalArgumentException("등록된 도서가 없습니다.");
        }

        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("book.csv"), "EUC-KR"));

            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] bookData = line.split(",");

                int id = Integer.parseInt(bookData[0]);
                String title = bookData[1];
                String releaseDate = bookData[2];
                boolean returnStatus = Boolean.parseBoolean(bookData[3]);

                if (returnStatus) {
                    Book book = new Book(id, title, releaseDate, returnStatus);
                    books.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return books;
    }

    /**
     * '전체 도서 조회' 메소드
     *
     * @return
     */
    public ArrayList<Book> readBookCSVOrderByreleaseDate() {
        ArrayList<Book> books = new ArrayList<>();
        BufferedReader bufferedReader = null;

        /* csv 파일이 없을 경우의 예외처리 */
        if (!new File("book.csv").exists()) {
            throw new IllegalArgumentException("등록된 도서가 없습니다.");
        }

        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("book.csv"), "EUC-KR"));

            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] bookData = line.split(",");

                int id = Integer.parseInt(bookData[0]);
                String title = bookData[1];
                String releaseDate = bookData[2];
                boolean returnStatus = Boolean.parseBoolean(bookData[3]);

                Book book = new Book(id, title, releaseDate, returnStatus);
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(books, Collections.reverseOrder());
        return books;
    }

    /**
     * '회원 조회' 메소드
     *
     * @return
     */
    public ArrayList<Member> readMemberCSV() {
        ArrayList<Member> members = new ArrayList<>();
        BufferedReader bufferedReader = null;

        /* csv 파일이 없을 경우의 예외처리 */
        if (!new File("member.csv").exists()) {
            throw new IllegalArgumentException("등록된 회원 정보가 없습니다.");
        }

        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("member.csv"), "EUC-KR"));

            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] memberData = line.split(",");

                String id = memberData[0];
                String name = memberData[1];
                String signUpDay = memberData[2];
                String address = memberData[3];
                String phoneNumber = memberData[4];
                String birthday = memberData[5];
                String age = memberData[6];

                Member member = new Member(id, name, signUpDay, address, phoneNumber, birthday, age);
                members.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return members;
    }

    /**
     * 단일 회원 조회 메소드
     *
     * @param memberName
     * @return
     */
    public Member getMember(String memberName) {
        BufferedReader bufferedReader = null;
        Member member = null;

        /* csv 파일이 없을 경우의 예외처리 */
        if (!new File("member.csv").exists()) {
            throw new IllegalArgumentException("등록된 회원 정보가 없습니다.");
        }

        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("member.csv"), "EUC-KR"));

            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] memberData = line.split(",");

                if (!memberData[0].equals(memberName)) {
                    continue;
                }

                String id = memberData[0];
                String name = memberData[1];
                String signUpDay = memberData[2];
                String address = memberData[3];
                String phoneNumber = memberData[4];
                String birthday = memberData[5];
                String age = memberData[6];
                ;

                member = new Member(id, name, signUpDay, address, phoneNumber, birthday, age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return member;
    }
}