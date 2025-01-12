package model.SystemManagement;

import java.util.UUID;
import model.SystemManagement.Standard.Process;

public class Requirement {
    private String idRequirement;
    private String idManagmentSystem;
    private String idOrganization;
    private String description;
    private String reference;
    private String name;

    public Requirement() {
        this.idRequirement = UUID.randomUUID().toString();
        this.description = "Unknown";
        this.name = "Unknown";
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setIdRequirement(String idRequirement) {
        this.idRequirement = idRequirement;
    }

    public Requirement(String idRequirement, String description, String name, String reference) {
        this.idRequirement = idRequirement;
        this.description = description;
        this.name = name;
        this.reference = reference;
    }

    public void editRequirement(Requirement updatedRequirement){
        this.setName(updatedRequirement.getName());
        this.setDescription(updatedRequirement.getDescription());
        this.setReference(updatedRequirement.getReference());
    }

    public String getIdRequirement() {
        return idRequirement;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdManagmentSystem() {
        return idManagmentSystem;
    }

    public void setIdManagmentSystem(String idManagmentSystem) {
        this.idManagmentSystem = idManagmentSystem;
    }

    public String getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "idRequirement='" + idRequirement + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", reference=" + reference +

                '}';
    }
}
