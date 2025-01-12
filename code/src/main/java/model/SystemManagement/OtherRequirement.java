package model.SystemManagement;

import java.util.UUID;
import model.SystemManagement.Standard.Process;
import model.SystemManagement.Standard.Standard;

public class OtherRequirement {
    private String idOtherRequirement;
    private String description;
    private String reference;
    private String name;
    private Process process = new Process();

    public OtherRequirement() {
        this.idOtherRequirement = UUID.randomUUID().toString();
        this.description = "Unknown";
        this.name = "Unknown";
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setIdOtherRequirement(String idOtherRequirement) {
        this.idOtherRequirement = idOtherRequirement;
    }

    public OtherRequirement(String idOtherRequirement, String description, String name, Process process, String reference) {
        this.idOtherRequirement = idOtherRequirement;
        this.description = description;
        this.name = name;
        this.process = process;
        this.reference = reference;
    }

    public String getIdOtherRequirement() {
        return idOtherRequirement;
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
                "idOtherRequirement='" + idOtherRequirement + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", process=" + process +
                ", reference=" + reference +

                '}';
    }
}
