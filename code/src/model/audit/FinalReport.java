package model.audit;

import java.util.UUID;

public class FinalReport {
    private final String idRapport;
    private String content;

    // Default Constructor
    public FinalReport() {
        this.idRapport = UUID.randomUUID().toString();
        this.content = "unknown";
    }

    // Parameterized Constructor
    public FinalReport(String content) {
        this.idRapport = UUID.randomUUID().toString();
        this.content = content;
    }

    // Getters and Setters
    public String getIdRapport() {
        return idRapport;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FinalRapport{" +
                "idRapport='" + idRapport + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
