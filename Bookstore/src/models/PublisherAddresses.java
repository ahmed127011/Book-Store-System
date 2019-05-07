package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "publisher_addresses")
public class PublisherAddresses {

    @Column(name = "publisher_name")
    private String publisherName;
    private String address;

    public PublisherAddresses() {
    }

    public PublisherAddresses(String publisherName, String address) {
        this.publisherName = publisherName;
        this.address = address;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
