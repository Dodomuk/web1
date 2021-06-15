package javastory.club.stage3.step4.service.dto;

import javastory.club.stage3.step1.entity.club.Address;
import javastory.club.stage3.step1.entity.club.ClubMembership;
import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.util.InvalidEmailException;

import java.util.ArrayList;
import java.util.List;

public class MemberDto {

    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    private List<Address> addresses;
    private List<ClubMembershipDto> membershipList;

    public MemberDto() {
        this.addresses = new ArrayList<>();
        this.membershipList = new ArrayList<>();
    }

    public MemberDto(String email, String name, String phoneNumber) {
        this();
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;

    }

    public MemberDto(CommunityMember communityMember) {
        this(communityMember.getEmail(), communityMember.getName(), communityMember.getPhoneNumber());
        this.nickName = communityMember.getNickName();
        this.birthDay = communityMember.getBirthDay();
        this.addresses = communityMember.getAddresses();

        for (ClubMembership clubMembership : communityMember.getMembershipList()) {
            membershipList.add(new ClubMembershipDto(clubMembership));
        }

    }

    public CommunityMember toMember() throws InvalidEmailException {
        CommunityMember member = new CommunityMember(email, name, phoneNumber);
        member.setNickName(nickName);
        member.setBirthDay(birthDay);

        for (Address address : addresses) {
            member.getAddresses().add(address);
        }

        for (ClubMembershipDto clubMembershipDto : membershipList) {
            member.getMembershipList().add(clubMembershipDto.toMembership());
        }

        return member;

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Name:").append(name);
        sb.append(", email:").append(email);
        sb.append(", nickname:").append(nickName);
        sb.append(", phone number:").append(phoneNumber);
        sb.append(", birthDay:").append(birthDay);

        if (addresses != null) {
            int i = 1;
            for (Address address : addresses) {
                sb.append(", Address[" + i + "]").append(address.toString());
            }
        }

        int i = 0;
        for (ClubMembershipDto membership : membershipList) {
            sb.append(" [" + i + "] Club member ").append(membership.toString()).append("\n");
            i++;
        }
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<ClubMembershipDto> getMembershipList() {
        return membershipList;
    }

}
