package wrappers;

import java.math.BigDecimal;

public class TotalVouchersWrapper {

    private BigDecimal total;

    public TotalVouchersWrapper() {

    }

    public TotalVouchersWrapper(BigDecimal total) {
        super();
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setType(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "TotalVouchersWrapper [total=" + total + "]";
    }

}
