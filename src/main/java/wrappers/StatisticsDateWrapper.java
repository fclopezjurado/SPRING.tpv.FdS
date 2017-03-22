package wrappers;

public class StatisticsDateWrapper {

    private String inicio;

    private String fin;

    public StatisticsDateWrapper() {
    }

    public StatisticsDateWrapper(String inicio, String fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "StatisticsDateWrapper [inicio=" + inicio + ", fin=" + fin + "]";
    }

}
