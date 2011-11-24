package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="GoogleCheckout_Notifications")
public class GoogleCheckoutNotification extends Model {
    public String SerialNumber;

    public static boolean existsForSerialNumber(String serialNumber) {
        return find("bySerialNumber", serialNumber).first() != null;
    }
}