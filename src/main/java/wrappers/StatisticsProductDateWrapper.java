package wrappers;

import java.util.Calendar;

public class StatisticsProductDateWrapper extends StatisticsDateWrapper {

    private long productId;

    public StatisticsProductDateWrapper() {
        super();
    }

    public StatisticsProductDateWrapper(long productId, Calendar inicio, Calendar fin) {
        super(inicio, fin);
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Calendar getInicio() {
        return super.getInicio();
    }

    public void setInicio(Calendar inicio) {
        super.setInicio(inicio);
    }

    public Calendar getFin() {
        return super.getFin();
    }

    public void setFin(Calendar fin) {
        super.setFin(fin);
    }

    @Override
    public String toString() {
        return "StatisticsDateWrapper [productId=" + productId + ", inicio=" + super.getInicio() + ", fin=" + super.getFin() + "]";
    }

}
