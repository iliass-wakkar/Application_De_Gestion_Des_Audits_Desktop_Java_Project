package model.audit;

import java.util.UUID;

public class ExigenceStat {
    private final String idExigence;
    private String passStat;
    private FinalReport clauseReport = new FinalReport();
    private ClauseSolution ExigenceSolution = new ClauseSolution();

    // Default Constructor
    public ExigenceStat() {
        this.idExigence = UUID.randomUUID().toString();
        this.passStat = "notYet";
    }

    // Parameterized Constructor
    public ExigenceStat(String passStat, FinalReport clauseRapport, ClauseSolution autreExigenceSolution) {
        this.idExigence = UUID.randomUUID().toString();
        this.setPassStat(passStat);
        this.clauseReport = clauseRapport;
        this.ExigenceSolution = autreExigenceSolution;
    }

    // Getters and Setters
    public String getIdExigence() {
        return idExigence;
    }

    public String getPassStat() {
        return passStat;
    }

    public void setPassStat(String passStat) {

        if(passStat.equals("notYet") || passStat.equals("pass") || passStat.equals("fail"))  {
            this.passStat = passStat;
        }
        else {
            System.out.println("Invalid pass");
        }
    }

    public FinalReport getClauseReport() {
        return clauseReport;
    }

    public void setClauseReport(FinalReport clauseReport) {
        this.clauseReport = clauseReport;
    }

    public ClauseSolution getExigenceSolution() {
        return ExigenceSolution;
    }

    public void setExigenceSolution(ClauseSolution exigenceSolution) {
        this.ExigenceSolution = exigenceSolution;
    }

    @Override
    public String toString() {
        return "ExigenceStat{" +
                "idExigence='" + idExigence + '\'' +
                ", passStat='" + passStat + '\'' +
                ", clauseReport=" + clauseReport +
                ", ExigenceSolution=" + ExigenceSolution +
                '}';
    }
}
