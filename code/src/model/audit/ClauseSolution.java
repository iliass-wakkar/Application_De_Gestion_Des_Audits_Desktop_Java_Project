package model.audit;

import java.util.UUID;

public class ClauseSolution {
    private final String idSolution;
    private String type;
    private String idResponsable;

    // Default Constructor
    public ClauseSolution() {
        this.idSolution = UUID.randomUUID().toString();
        this.type = "correction";
        this.idResponsable = "unknown";
    }

    // Parameterized Constructor
    public ClauseSolution(String type, String idResponsable) {
        this.idSolution = UUID.randomUUID().toString();
        this.setType(type);
        this.idResponsable = idResponsable;
    }

    // Getters and Setters
    public String getIdSolution() {
        return idSolution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {

        if(type.equals("correction") || type.equals("action"))  {
            this.type = type;
        }
        else {
            System.out.println("Invalid pass");
        }
    }

    public String getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(String idResponsable) {
        this.idResponsable = idResponsable;
    }

    @Override
    public String toString() {
        return "ClauseSolution{" +
                "idSolution='" + idSolution + '\'' +
                ", type='" + type + '\'' +
                ", idResponsable='" + idResponsable + '\'' +
                '}';
    }
}
