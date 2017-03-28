package entities.core;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CashierBalance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Calendar day;

    private BigDecimal balance;

    @Column(name= "dayChange")
    private BigDecimal change;

    private BigDecimal cash;

    private BigDecimal checks;

    private BigDecimal dataphone;

    private BigDecimal totalTiketsMoney;

    public CashierBalance() {
        day = Calendar.getInstance();
    }

    public CashierBalance(BigDecimal change, BigDecimal total, BigDecimal cash, BigDecimal checks, BigDecimal dataphone) {
        super();
        this.change = change;
        this.totalTiketsMoney = total;
        this.cash = cash;
        this.checks = checks;
        this.dataphone = dataphone;
        this.balance = this.calculateBalance();
        this.day = Calendar.getInstance();
    }
    
    private BigDecimal calculateBalance(){
        BigDecimal balance = this.totalTiketsMoney.subtract(this.change);
        if(this.cash != null)
            balance.subtract(this.cash);        
        if(this.checks != null)
            balance.subtract(this.checks);
        if(this.dataphone != null)
            balance.subtract(this.dataphone);
        
        return balance;
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

    @Override
    public String toString() {
        return "CashierBalance [id=" + id + ", day=" + day + ", balance=" + balance + ", change=" + change + ", cash=" + cash + ", checks="
                + checks + ", dataphone=" + dataphone + ", totalTiketsMoney=" + totalTiketsMoney + "]";
    }
    
    

}
