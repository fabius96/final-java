package view;

import java.util.Scanner;
import domain.Member;
import service.MemberService;

public class MemberView {
    MemberService memberService = new MemberService();

    public MemberView() {}

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Member deletedMember = null;
        while (true) {
            System.out
                    .println("[0]뒤로 \t [1]회원조회 \t [2]회원등록 \t [3]회원수정 \t [4]회원삭제 \t [5]삭제취소");

            String userInput = scanner.nextLine();
            switch (userInput) {
                case ("0"): // 뒤로
                    return;
                case ("1"): // 회원조회
                    memberService.inquiryMemberList();
                    break;
                case ("2"): // 회원등록
                    System.out.println("ID을 입력해주세요");
                    userInput = scanner.nextLine();
                    String id = userInput;

                    System.out.println("이름을 입력해주세요");
                    userInput = scanner.nextLine();
                    String name = userInput;

                    System.out.println("주소를 입력해주세요 \t (입력예시 : 경기도 광명시 철산동)");
                    userInput = scanner.nextLine();
                    String address = userInput;

                    System.out.println("연락처를 입력해주세요 \t (입력예시 : 010-1234-5678)");
                    userInput = scanner.nextLine();
                    String phoneNumber = userInput;

                    System.out.println("생일을 입력해주세요 \t (입력예시 : 1996/05/11)");
                    userInput = scanner.nextLine();
                    String birthday = userInput;

                    Member member = new Member(id, name, address, phoneNumber, birthday);
                    memberService.memberRegistration(member);
                    break;
                case ("3"):
                    memberService.inquiryMemberList();
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("정보를 수정할 회원의 ID를 입력해주세요");
                    String originId = scanner.nextLine();
                    memberService.inquirySingleMember(originId);
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("변경될 이름을 입력해주세요");
                    userInput = scanner.nextLine();
                    name = userInput;

                    System.out.println("변경될 주소를 입력해주세요 \t (입력예시 : 경기도 광명시 철산동)");
                    userInput = scanner.nextLine();
                    address = userInput;

                    System.out.println("변경될 연락처를 입력해주세요 \t (입력예시 : 010-1234-5678)");
                    userInput = scanner.nextLine();
                    phoneNumber = userInput;

                    System.out.println("변경될 생일을 입력해주세요 \t (입력예시 : 1996/05/11)");
                    userInput = scanner.nextLine();
                    birthday = userInput;
                    memberService.changeMemberinformation(originId, name, address, phoneNumber,
                            birthday);
                    break;
                case ("4"): // 회원삭제
                    memberService.inquiryMemberList();
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("삭제할 회원의 ID을 입력해주세요.");
                    userInput = scanner.nextLine();
                    deletedMember = memberService.createTemporaryMember(userInput);
                    memberService.deleteMember(userInput);
                    break;
                case ("5"): // 삭제취소
                    if (deletedMember == null) {
                        System.out.println("최근 삭제된 회원 데이터가 없습니다.");
                        break;
                    } else {
                        memberService.memberRegistration(deletedMember);
                        System.out.println("최근 삭제된 회원 데이터가 복구되었습니다.");
                        break;
                    }
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }

}