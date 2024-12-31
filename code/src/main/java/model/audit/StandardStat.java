package model.audit;

import java.util.List;
import java.util.UUID;

public class StandardStat {
    private final String idStandard;
    private List<ClauseStat> arrClauseStat;

    // Default Constructor
    public StandardStat() {
        this.idStandard = UUID.randomUUID().toString();
        this.arrClauseStat = null;
    }

    // Parameterized Constructor
    public StandardStat(List<ClauseStat> arrClauseStat) {
        this.idStandard = UUID.randomUUID().toString();
        this.arrClauseStat = arrClauseStat;
    }

    // Getters and Setters
    public String getIdStandard() {
        return idStandard;
    }

    public List<ClauseStat> getArrClauseStat() {
        return arrClauseStat;
    }

    public void setArrClauseStat(List<ClauseStat> arrClauseStat) {
        this.arrClauseStat = arrClauseStat;
    }

    @Override
    public String toString() {
        return "StandardStat{" +
                "idStandard='" + idStandard + '\'' +
                ", arrClauseStat=" + arrClauseStat +
                '}';
    }
}
