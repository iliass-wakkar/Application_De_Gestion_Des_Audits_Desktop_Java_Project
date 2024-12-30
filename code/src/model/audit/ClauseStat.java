package model.audit;

import java.util.UUID;

public class ClauseStat {
    private final String idClause;
    private String passStat;
    private ClauseSolution clauseSolution  = new ClauseSolution();

    // Default Constructor
    public ClauseStat() {
        this.idClause = UUID.randomUUID().toString();
        this.passStat = "notYet";
    }

    // Parameterized Constructor
    public ClauseStat(String passStat, ClauseSolution clauseSolution) {
        this.idClause = UUID.randomUUID().toString();
        this.setPassStat(passStat);
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

        if(passStat.equals("notYet") || passStat.equals("pass") || passStat.equals("fail"))  {
            this.passStat = passStat;
        }
        else {
            System.out.println("Invalid passStat");
        }

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
