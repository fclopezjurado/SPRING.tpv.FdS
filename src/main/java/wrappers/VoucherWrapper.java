package wrappers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VoucherWrapper {

    private String reference;

    private BigDecimal value;

    private Calendar created;

    private Calendar dateOfUse;

    private Calendar expiration;

    public VoucherWrapper() {
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setCreated(Calendar created) {
        this.created = dateOfUse;
    }

    public Calendar getCreated() {
        return created;
    }

    public Calendar getDateOfUse() {
        return dateOfUse;
    }

    public void setDateOfUse(Calendar dateOfUse) {
        this.dateOfUse = dateOfUse;
    }

    public boolean used() {
        return dateOfUse != null;
    }

    public Calendar getExpiration() {
        return expiration;
    }

    public void setExpiration(Calendar expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {

        String createTime;
        if (created != null) {
            createTime = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(created.getTime());
        } else {
            createTime = "---";
        }

        String useTime;
        if (this.used()) {
            useTime = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(dateOfUse.getTime());
        } else {
            useTime = "---";
        }
        String expiredTime;
        if (expiration != null) {
            expiredTime = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(expiration.getTime());
        } else {
            expiredTime = "---";
        }

        return "Voucher[ reference=" + reference + ", value=" + value + ", created=" + createTime + ", dateOfUse=" + useTime
                + ", expiredDate=" + expiredTime + "]";
    }
}
