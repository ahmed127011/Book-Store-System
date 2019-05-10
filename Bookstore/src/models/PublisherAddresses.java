package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "publisher_addresses")
public class PublisherAddresses {
    @EmbeddedId
    private Pk pk;

    public PublisherAddresses() {
        pk=new Pk();

    }

    public PublisherAddresses(String publisherName, String address) {
        pk=new Pk();
        this.pk.publisherName = publisherName;
        this.pk.address = address;
    }

    public String getPublisherName() {
        return pk.publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.pk.publisherName = publisherName;
    }


    public String getAddress() {
        return pk.address;
    }

    public void setAddress(String address) {
        this.pk.address = address;
    }

}

@Embeddable
class Pk implements Serializable{
    @Column(name = "publisher_name")
     String publisherName;
     String address;

}