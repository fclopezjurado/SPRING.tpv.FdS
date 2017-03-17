package wrappers;

import java.util.Calendar;

public class StatisticsDateWrapper {

    private Calendar inicio;

    private Calendar fin;

    public StatisticsDateWrapper() {
    }

    public StatisticsDateWrapper(Calendar inicio, Calendar fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getFin() {
        return fin;
    }

    public void setFin(Calendar fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "StatisticsDateWrapper [inicio=" + inicio + ", fin=" + fin + "]";
    }

}
