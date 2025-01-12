package model.SystemManagement.Standard;

import java.util.UUID;

public class Clause {
    private String idClause;
    private String name;
    private String description;
    private String reference;


    public Clause() {
        this.idClause = UUID.randomUUID().toString();
        this.name = "Unknown";
        this.description = "Unknown";
        this.reference = "Unknown";
    }

    public Clause(String idClause, String name, String description, String reference) {
        this.idClause = idClause;
        this.name = name;
        this.description = description;
        this.reference = reference;

    }

    public String getIdClause() {
        return idClause;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


    @Override
    public String toString() {
        return "Clause{" +
                "idClause='" + idClause + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", reference='" + reference + '\'' +

                '}';
    }
}
