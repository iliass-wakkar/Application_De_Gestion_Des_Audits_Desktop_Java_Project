package model.SystemManagement;

import java.util.UUID;
import model.SystemManagement.Standard.Process;

public class Requirement {
    private String idRequirement;
    private String description;
    private String reference;
    private String name;
    private Process process = new Process();

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

    public Requirement(String idOtherRequirement, String description, String name, Process process, String reference) {
        this.idRequirement = idOtherRequirement;
        this.description = description;
        this.name = name;
        this.process = process;
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

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }



    @Override
    public String toString() {
        return "OtherRequirement{" +
                "idRequirement='" + idRequirement + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", process=" + process +
                ", reference=" + reference +

                '}';
    }
}
