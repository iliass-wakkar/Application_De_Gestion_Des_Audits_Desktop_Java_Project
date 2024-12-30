package model.SystemManagement.Standard;

public class Clause {
    private String idClause;
    private String name;
    private String description;
    private String reference;
    private Standard standard = new Standard();

    public Clause() {
        this.idClause = idClause;
        this.name = "Unknown";
        this.description = "Unknown";
        this.reference = "Unknown";
    }

    public Clause(String idClause, String name, String description, String reference, Standard standard) {
        this.idClause = idClause;
        this.name = name;
        this.description = description;
        this.reference = reference;
        this.standard = standard;
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

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    @Override
    public String toString() {
        return "Clause{" +
                "idClause='" + idClause + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", reference='" + reference + '\'' +
                ", standard=" + standard +
                '}';
    }
}
