package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "publisher_phones")
public class PublisherPhones {
    @EmbeddedId
    PhonePK pk;

    public PublisherPhones() {
        this.pk=new PhonePK();

    }

    public PublisherPhones(String publisherName, String phone) {
        this.pk=new PhonePK();
        this.pk.publisherName = publisherName;
        this.pk.phone = phone;
    }

    public String getPublisherName() {
        return pk.publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.pk.publisherName = publisherName;
    }


    public String getPhone() {
        return pk.phone;
    }

    public void setPhone(String phone) {
        this.pk.phone = phone;
    }

}
@Embeddable
 class PhonePK implements Serializable{
    @Column(name = "publisher_name")
     String publisherName;
     String phone;
}