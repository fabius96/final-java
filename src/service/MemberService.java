package service;

import java.util.ArrayList;
import java.util.List;
import domain.Member;
import util.CSVReader;
import util.CSVWriter;

public class MemberService {
    CSVWriter csvWriter = new CSVWriter();
    CSVReader csvReader = new CSVReader();

    /**
     * 회원 조회 메소드
     */
    public void inquiryMemberList() {
        List<Member> members = csvReader.readMemberCSV();
        for (Member member : members) {
            System.out.println(member);
        }
    }


    public void inquirySingleMember(String memberName) {
        Member member = csvReader.getMember(memberName);
        System.out.println(member);
    }

    /**
     * 회원 등록 메소드
     *
     * @param member
     */
    public void memberRegistration(Member member) {
        csvWriter.writeCSV(member);
        System.out.println("회원등록이 완료되었습니다.");
    }

    /**
     * 회원 삭제 메소드
     *
     * @param memberId
     */
    public void deleteMember(String memberId) {
        csvWriter.updateCSV(memberId);
        System.out.println("회원삭제가 완료되었습니다.");
    }

    /**
     * 회원 정보 수정 메소드
     *
     * @param origin
     * @param name
     * @param address
     * @param phoneNumber
     * @param birthday
     */
    public void changeMemberinformation(String origin, String name, String address,
                                        String phoneNumber, String birthday) {
        csvWriter.changeMemberInformation(origin, name, address, phoneNumber, birthday);
        System.out.println("회원정보가 수정되었습니다");
    }

    /**
     * 회원 정보 임시 저장 메소드
     * @param memberName
     * @return
     */
    public Member createTemporaryMember(String memberName) {
        Member member = csvReader.getMember(memberName);
        return member;
    }

    /**
     * 회원 존재 확인 메소드
     * @param id
     * @return
     */
    public boolean isExistId(String id){
        ArrayList<String> idList = csvReader.makeIdList();
        return idList.contains(id);
    }
}