package models;


public class PublisherPhones {

    private String publisherName;
    private String phone;

    public PublisherPhones() {
    }

    public PublisherPhones(String publisherName, String phone) {
        this.publisherName = publisherName;
        this.phone = phone;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
