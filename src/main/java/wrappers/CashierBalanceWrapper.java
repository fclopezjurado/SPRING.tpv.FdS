package wrappers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CashierBalanceWrapper {

    public static final String dateFormat = "dd-MM-yyyy";

    private int id;

    private BigDecimal change;

    private BigDecimal total;

    private BigDecimal checks;

    private BigDecimal dataphone;

    private BigDecimal cash;
    
    private BigDecimal balance;

    private String date;

    public CashierBalanceWrapper() {
        super();
    }

    public CashierBalanceWrapper(int id, double change, double total, double checks, double dataphone, String date) {
        super();
        this.id = id;
        this.change = new BigDecimal(change);
        this.total = new BigDecimal(total);
        this.checks = new BigDecimal(checks);
        this.dataphone = new BigDecimal(dataphone);
        this.date = date;
    }

    public CashierBalanceWrapper(int id, BigDecimal change, BigDecimal total, BigDecimal checks, BigDecimal dataphone, Calendar date) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
        this.id = id;
        this.change = change;
        this.total = total;
        this.checks = checks;
        this.dataphone = dataphone;
        this.date = dateFormater.format(date.getTime());
    }

    public CashierBalanceWrapper(double change, double total, double checks, double dataphone, String date) {
        super();
        this.change = new BigDecimal(change);
        this.total = new BigDecimal(total);
        this.checks = new BigDecimal(checks);
        this.dataphone = new BigDecimal(dataphone);
        this.date = date;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CashierBalanceWrapper {id=" + id + ", change=" + change + ", total=" + total + ", checks=" + checks + ", dataphone="
                + dataphone + ", date=" + date + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + id;
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
        CashierBalanceWrapper other = (CashierBalanceWrapper) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Calendar getDay() throws ParseException {
        SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormater.parse(this.getDate()));
        return cal;
    }

    public void setDay(Calendar day) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
        this.date = dateFormater.format(day.getTime());
    }

}
