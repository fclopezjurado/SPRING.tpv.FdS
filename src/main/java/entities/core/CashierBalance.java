package entities.core;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CashierBalance {

    @Id
    private int id;
    
    private Calendar day;
    
    private double balance = 0;
    
    private double change;
    
    private double cash;
    
    private double checks;
    
    private double dataphone;
    
    private double totalTiketsMoney;       
    
    public CashierBalance()
    {
        day = Calendar.getInstance();
    }

    public CashierBalance(double change, double cash, double checks, double dataphone) {
        super();
        this.change = change;
        this.cash = cash;
        this.checks = checks;
        this.dataphone = dataphone;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getChecks() {
        return checks;
    }

    public void setChecks(double checks) {
        this.checks = checks;
    }

    public double getDataphone() {
        return dataphone;
    }

    public void setDataphone(double dataphone) {
        this.dataphone = dataphone;
    }
    
    public double getTotalTiketsMoney() {
        return totalTiketsMoney;
    }

    public void setTotalTiketsMoney(double totalTiketsMoney) {
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
