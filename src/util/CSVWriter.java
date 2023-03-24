package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import domain.Book;
import domain.Loan;
import domain.Member;
import view.MainView;

public class CSVWriter {
    public static final String NEWLINE = System.lineSeparator(); // 개행

    /**
     * 도서 등록 메소드
     *
     * @param book
     */
    public void writeCSV(Book book) {
        BufferedWriter bufferedWriter = null;
        boolean isFileExists = new File("book.csv").exists();

        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("book.csv", true), "EUC-KR"));

            if (!isFileExists) {
                bufferedWriter.write("제목,출간일,반납여부");
                bufferedWriter.write(NEWLINE);
            }

            bufferedWriter.write(
                    book.getTitle() + "," + book.getReleaseDate() + "," + book.isReturnStatus());

            bufferedWriter.write(NEWLINE);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 회원 등록 메소드
     *
     * @param member
     */
    public void writeCSV(Member member) {
        BufferedWriter bufferedWriter = null;
        boolean isFileExists = new File("member.csv").exists();

        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("member.csv", true), "EUC-KR"));

            if (!isFileExists) {
                bufferedWriter.write("이름,가입일,주소,연락처,생일,나이");
                bufferedWriter.write(NEWLINE);
            }

            bufferedWriter.write(member.getName() + "," + member.getSignUpDay() + ","
                    + member.getAddress() + "," + member.getPhoneNumber() + ","
                    + member.getBirthday() + "," + member.getAge());

            bufferedWriter.write(NEWLINE);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 도서 대출 메소드
     *
     * @param loan
     */
    public void writeCSV(Loan loan) {
        BufferedWriter bufferedWriter = null;
        boolean isFileExists = new File("loan.csv").exists();

        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("loan.csv", true), "EUC-KR"));

            if (!isFileExists) {
                bufferedWriter.write("도서명,회원명,연장여부,대출일,반납기한");
                bufferedWriter.write(NEWLINE);
            }

            bufferedWriter.write(loan.getBookTitle() + "," + loan.getMemberName() + ","
                    + loan.isExtensionStatus() + "," + loan.getLoanDate() + ","
                    + loan.getDeadline());

            bufferedWriter.write(NEWLINE);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 대출 연장 메소드
     *
     * @param bookTitle
     * @param extensionStatus
     */
    public void loanExtention(String bookTitle) {
        File inputFile = new File("loan.csv");
        File tempFile = new File("temp.csv");

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "EUC-KR"));
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(tempFile), "EUC-KR"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                // 수정하고자 하는 도서의 정보인 경우
                if (fields[0].equals(bookTitle) && !Boolean.parseBoolean(fields[2])) {
                    // 새로운 정보로 수정
                    fields[2] = "TRUE";
                    fields[4] = String.valueOf(Integer.parseInt(fields[4]) + 6);
                    // 새로운 정보를 csv 형식으로 작성하여 파일에 저장
                    writer.write(String.join(",", fields));
                    writer.write(NEWLINE);
                } else {
                    // 수정하지 않는 도서 정보는 그대로 유지하여 파일에 저장
                    writer.write(line);
                    writer.write(NEWLINE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 기존 csv 파일 삭제하고 수정된 파일로 변경
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    /**
     * 회원 삭제 메소드
     *
     * @param bookName
     * @param extensionStatus
     */
    public void updateCSV(String memberName) {
        File inputFile = new File("member.csv");
        File tempFile = new File("temp.csv");

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "EUC-KR"));
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(tempFile), "EUC-KR"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                // 수정하고자 하는 도서의 정보인 경우
                if (fields[0].equals(memberName)) {
                    continue;
                } else {
                    // 수정하지 않는 도서 정보는 그대로 유지하여 파일에 저장
                    writer.write(line);
                    writer.write(NEWLINE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 기존 csv 파일 삭제하고 수정된 파일로 변경
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    /**
     * 도서 삭제 메소드
     *
     * @param bookTitle
     */
    public void deleteBook(String bookTitle) {
        File inputFile = new File("book.csv");
        File tempFile = new File("temp.csv");

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "EUC-KR"));
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(tempFile), "EUC-KR"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                // 수정하고자 하는 도서의 정보인 경우
                if (fields[0].equals(bookTitle)) {
                    continue;
                } else {
                    // 수정하지 않는 도서 정보는 그대로 유지하여 파일에 저장
                    writer.write(line);
                    writer.write(NEWLINE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 기존 csv 파일 삭제하고 수정된 파일로 변경
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    /**
     * 도서 반납 메소드
     *
     * @param bookTitle
     */
    public void returnBook(String bookTitle) {
        File inputFile = new File("loan.csv");
        File tempFile = new File("temp.csv");

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "EUC-KR"));
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(tempFile), "EUC-KR"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                // 반납하고자 하는 도서인 경우
                if (fields[0].equals(bookTitle)) {
                    continue;
                } else {
                    // 수정하지 않는 대출 정보는 그대로 유지하여 파일에 저장
                    writer.write(line);
                    writer.write(NEWLINE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 기존 csv 파일 삭제하고 수정된 파일로 변경
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    /**
     * 반납여부 변경 메소드
     *
     * @param bookTitle
     */
    public void changeReturnStatus(String bookTitle) {
        File inputFile = new File("book.csv");
        File tempFile = new File("temp.csv");

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "EUC-KR"));
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(tempFile), "EUC-KR"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                // 수정하고자 하는 도서의 정보인 경우
                if (fields[0].equals(bookTitle) && !Boolean.parseBoolean(fields[2])) {
                    // 새로운 정보로 수정
                    fields[2] = "TRUE";
                    // 새로운 정보를 csv 형식으로 작성하여 파일에 저장
                    writer.write(String.join(",", fields));
                    writer.write(NEWLINE);
                } else if (fields[0].equals(bookTitle) && Boolean.parseBoolean(fields[2])) {
                    // 새로운 정보로 수정
                    fields[2] = "FALSE";
                    // 새로운 정보를 csv 형식으로 작성하여 파일에 저장
                    writer.write(String.join(",", fields));
                    writer.write(NEWLINE);
                } else {
                    // 수정하지 않는 도서 정보는 그대로 유지하여 파일에 저장
                    writer.write(line);
                    writer.write(NEWLINE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 기존 csv 파일 삭제하고 수정된 파일로 변경
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void changeMemberInformation(String userInput, String name, String address, String phoneNumber, String birthday) {
        File inputFile = new File("member.csv");
        File tempFile = new File("temp.csv");

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "EUC-KR"));
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(tempFile), "EUC-KR"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                // 수정하고자 하는 회원정보인 경우
                if (fields[0].equals(userInput)) {

                    // 새로운 정보로 수정
                    fields[0] = name;
                    fields[2] = address;
                    fields[3] = phoneNumber;
                    fields[4] = birthday;
                    fields[5] = String.valueOf(Period.between(LocalDate.now(), LocalDate.parse(birthday, MainView.formatter))
                            .getYears() * -1);
                    // 새로운 정보를 csv 형식으로 작성하여 파일에 저장
                    writer.write(String.join(",", fields));
                    writer.write(NEWLINE);
                } else {
                    // 수정하지 않는 회원 정보는 그대로 유지하여 파일에 저장
                    writer.write(line);
                    writer.write(NEWLINE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 기존 csv 파일 삭제하고 수정된 파일로 변경
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }
}

