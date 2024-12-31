package model.audit;

import java.util.UUID;

public class ClauseStat {
    private final String idClause;
    private String passStat;
    private Solution solution;

    // Default Constructor
    public ClauseStat() {
        this.idClause = UUID.randomUUID().toString();
        this.passStat = "notYet";
        this.solution = new Solution();
    }

    // Parameterized Constructor
    public ClauseStat(String passStat, Solution solution) {
        this.idClause = UUID.randomUUID().toString();
        this.passStat = passStat;
        this.solution = solution;
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

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "ClauseStat{" +
                "idClause='" + idClause + '\'' +
                ", passStat='" + passStat + '\'' +
                ", solution=" + solution +
                '}';
    }
}
