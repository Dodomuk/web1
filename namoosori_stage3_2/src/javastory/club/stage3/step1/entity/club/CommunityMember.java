package javastory.club.stage3.step1.entity.club;

import javastory.club.stage3.step1.entity.Entity;
import javastory.club.stage3.step1.util.InvalidEmailException;

import java.util.ArrayList;
import java.util.List;

public class CommunityMember implements Entity {

    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;
    private List<Address> addresses;
    private List<ClubMembership> membershipList;

    public CommunityMember() {

        this.membershipList = new ArrayList<>();
        this.addresses = new ArrayList<>();

    }

    public CommunityMember(String email, String name, String phoneNumber) throws InvalidEmailException {

        this();
        setEmail(email);
        this.name = name;
        this.phoneNumber = phoneNumber;

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

        return sb.toString();

    }

    public static CommunityMember getSample() {
        CommunityMember member = null;
        try {
            member = new CommunityMember("dongmuk@nextree.co.kr", "동묵", "010-3333-3333");
            member.setBirthDay("2021.06.12");
            member.getAddresses().add(Address.getHomeAddressSample());
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        }
        return member;
    }

    @Override
    public String getId() {
        return email;
    }

    public List<ClubMembership> getMembershipList(){
    return this.membershipList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException{
       if(!this.isValidEmailAddress(email)){
           throw new InvalidEmailException("잘못된 이메일 형식입니다. ----> " + email);
       }
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

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
