package model.audit;

import java.util.UUID;

public class requirementStat {
    private final String idRequirement;
    private String passStat;
    private FinalReport clauseReport;
    private Solution solution;

    // Default Constructor
    public requirementStat() {
        this.idRequirement = UUID.randomUUID().toString();
        this.passStat = "notYet";
        this.clauseReport = new FinalReport();
        this.solution = new Solution();
    }

    // Parameterized Constructor
    public requirementStat(String passStat, FinalReport clauseRapport, Solution solution) {
        this.idRequirement = UUID.randomUUID().toString();
        this.passStat = passStat;
        this.clauseReport = clauseRapport;
        this.solution = solution;
    }

    // Getters and Setters
    public String getIdRequirement() {
        return idRequirement;
    }

    public String getPassStat() {
        return passStat;
    }

    public void setPassStat(String passStat) {
        this.passStat = passStat;
    }

    public FinalReport getClauseReport() {
        return clauseReport;
    }

    public void setClauseReport(FinalReport clauseReport) {
        this.clauseReport = clauseReport;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "ExigenceStat{" +
                "idExigence='" + idRequirement + '\'' +
                ", passStat='" + passStat + '\'' +
                ", clauseReport=" + clauseReport +
                ", Solution=" + solution +
                '}';
    }
}
