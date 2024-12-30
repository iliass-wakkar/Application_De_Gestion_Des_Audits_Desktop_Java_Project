package model.audit;

import java.util.UUID;

public class ClauseStat {
    private final String idClause;
    private String passStat;
    private ClauseSolution clauseSolution;

    // Default Constructor
    public ClauseStat() {
        this.idClause = UUID.randomUUID().toString();
        this.passStat = "notYet";
        this.clauseSolution = new ClauseSolution();
    }

    // Parameterized Constructor
    public ClauseStat(String passStat, ClauseSolution clauseSolution) {
        this.idClause = UUID.randomUUID().toString();
        this.passStat = passStat;
        this.clauseSolution = clauseSolution;
    }

    // Getters and Setters
    public String getIdClause() {
        return idClause;
    }

    public String getPassStat() {
        return passStat;
    }

    public void setPassStat(String passStat) {
        this.passStat = passStat;
    }

    public ClauseSolution getClauseSolution() {
        return clauseSolution;
    }

    public void setClauseSolution(ClauseSolution clauseSolution) {
        this.clauseSolution = clauseSolution;
    }

    @Override
    public String toString() {
        return "ClauseStat{" +
                "idClause='" + idClause + '\'' +
                ", passStat='" + passStat + '\'' +
                ", clauseSolution=" + clauseSolution +
                '}';
    }
}
