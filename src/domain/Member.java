package domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.atomic.AtomicInteger;

import view.MainView;

// 회원
public class Member {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private String id;
    private String name; // 이름
    private final String signUpDay; // 가입날짜
    private String address; // 주소
    private String phoneNumber; // 연락처
    private String birthday; // 생일
    private String age; // 생일

    // 생성자 메소드(회원 가입)
    public Member(String id, String name, String address, String phoneNumber, String birthday) {
        this.id = id;
        this.name = name;
        this.signUpDay = LocalDate.now().format(MainView.formatter);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.age = String.valueOf(Period.between(LocalDate.now(), LocalDate.parse(birthday, MainView.formatter))
                .getYears() * -1);
    }

    public Member(String id, String name, String signUpDay, String address, String phoneNumber,
                  String birthday, String age) {
        this.id = id;
        this.name = name;
        this.signUpDay = signUpDay;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.age = age;
    }


    // getter / setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignUpDay() {
        return signUpDay;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "회원정보 [ID : " + id + ", 이름 :"  +name + ", 가입일 : " + signUpDay + ", 주소 : " + address + ", 연락처 : "
                + phoneNumber + ", 생일 : " + birthday + ", 나이 : " + age + "]";
    }


}