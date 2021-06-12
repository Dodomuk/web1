package javastory.club.stage3.step1.entity.club;

public class Address {

    private String zipCode;
    private String zipAddress;
    private String streetAddress;
    private String country;
    private AddressType addressType;

    public Address() {

    }

    public Address(String zipCode, String zipAddress, String streetAddress){

        this.zipCode = zipCode;
        this.zipAddress = zipAddress;
        this.streetAddress = streetAddress;
        this.country = "한국";
        this.addressType = AddressType.Office;

    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("ZipCode:").append(zipCode);
        builder.append(", zip address:").append(zipAddress);
        builder.append(", street address:").append(streetAddress);
        builder.append(", country:").append(country);
        builder.append(", address type:").append(addressType);

        return builder.toString();
    }

    public static Address getHomeAddressSample(){

        Address address = new Address("134-321", "서울, 강남구,역삼동" , "333");
        address.setAddressType(AddressType.Home);

        return address;
    }

    public static Address getOfficeAddressSample(){
        Address address = new Address("133-111", "서울,서초구,서초동", "567");
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipAddress() {
        return zipAddress;
    }

    public void setZipAddress(String zipAddress) {
        this.zipAddress = zipAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }
}
