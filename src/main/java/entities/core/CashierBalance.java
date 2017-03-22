package entities.core;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CashierBalance {

    @Id
    private int id;

    private Calendar day;

    private BigDecimal balance = new BigDecimal(0);

    @Column(name= "dayChange")
    private BigDecimal change;

    private BigDecimal cash;

    private BigDecimal checks;

    private BigDecimal dataphone;

    private BigDecimal totalTiketsMoney;

    public CashierBalance() {
        day = Calendar.getInstance();
    }

    public CashierBalance(BigDecimal change, BigDecimal cash, BigDecimal checks, BigDecimal dataphone) {
        super();
        this.change = change;
        this.cash = cash;
        this.checks = checks;
        this.dataphone = dataphone;
        this.day = Calendar.getInstance();
    }

    public CashierBalance(double change, double total, double cash, double checks, double dataphone) {
        this.change = new BigDecimal(change, MathContext.DECIMAL64);
        this.totalTiketsMoney = new BigDecimal(total, MathContext.DECIMAL64);
        this.cash = new BigDecimal(cash, MathContext.DECIMAL64);
        this.checks = new BigDecimal(checks, MathContext.DECIMAL64);
        this.dataphone = new BigDecimal(dataphone, MathContext.DECIMAL64);
        this.balance = this.totalTiketsMoney.subtract(this.change.add(this.cash.add(this.checks.add(this.dataphone))));
        this.day = Calendar.getInstance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDay() {
        return day;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getChecks() {
        return checks;
    }

    public void setChecks(BigDecimal checks) {
        this.checks = checks;
    }

    public BigDecimal getDataphone() {
        return dataphone;
    }

    public void setDataphone(BigDecimal dataphone) {
        this.dataphone = dataphone;
    }

    public BigDecimal getTotalTiketsMoney() {
        return totalTiketsMoney;
    }

    public void setTotalTiketsMoney(BigDecimal totalTiketsMoney) {
        this.totalTiketsMoney = totalTiketsMoney;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CashierBalance other = (CashierBalance) obj;
        if (id != other.id)
            return false;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        return true;
    }

}
