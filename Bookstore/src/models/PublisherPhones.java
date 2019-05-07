package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "publisher_phones")
public class PublisherPhones {

    @Column(name = "publisher_name")
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
