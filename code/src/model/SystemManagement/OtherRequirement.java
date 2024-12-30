package model.SystemManagement;

import java.util.UUID;

public class OtherRequirement {
    private String idOtherRequirement;
    private String description;
    private String name;
    private Process process = new Process();
    private SystemManagement systemManagement = new SystemManagement();

    public OtherRequirement() {
        this.idOtherRequirement = UUID.randomUUID().toString();
        this.description = "Unknown";
        this.name = "Unknown";
    }

    public OtherRequirement(String idOtherRequirement, String description, String name, Process process, SystemManagement systemManagement) {
        this.idOtherRequirement = idOtherRequirement;
        this.description = description;
        this.name = name;
        this.process = process;
        this.systemManagement = systemManagement;
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

    public SystemManagement getSystemManagement() {
        return systemManagement;
    }

    public void setSystemManagement(SystemManagement systemManagement) {
        this.systemManagement = systemManagement;
    }

    @Override
    public String toString() {
        return "OtherRequirement{" +
                "idOtherRequirement='" + idOtherRequirement + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", process=" + process +
                ", systemManagement=" + systemManagement +
                '}';
    }
}
