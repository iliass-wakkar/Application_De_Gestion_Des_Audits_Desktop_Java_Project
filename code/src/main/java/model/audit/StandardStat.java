package model.audit;

import java.util.List;
import java.util.UUID;

public class StandardStat {
    private final String idStandard;
    private String passStat;
    private Solution solution;


    // Default Constructor
    public StandardStat() {
        this.idStandard = UUID.randomUUID().toString();
    }

    // Parameterized Constructor


    public StandardStat(String idStandard, String passStat, Solution solution) {
        this.idStandard = idStandard;
        this.passStat = passStat;
        this.solution = solution;
    }

    public String getPassStat() {
        return passStat;
    }
    public Solution getSolution() {
        return solution;
    }
    // Getters and Setters
    public String getIdStandard() {
        return idStandard;
    }

    public void setPassStat(String passStat) {
        this.passStat = passStat;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "StandardStat{" +
                "idStandard='" + idStandard +
                '}';
    }
}
