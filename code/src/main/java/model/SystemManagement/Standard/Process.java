package model.SystemManagement.Standard;

import java.util.UUID;

public class Process {
    private String idProcess;
    private String name;
    private String description;

    public Process() {
        this.idProcess = UUID.randomUUID().toString();
        this.name = "Unknown";
        this.description = "Unknown";
    }

    public Process(String idProcess, String name, String description) {
        this.idProcess = idProcess;
        this.name = name;
        this.description = description;
    }

    public String getIdProcess() {
        return idProcess;
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

    @Override
    public String toString() {
        return "Process{" +
                "idProcess='" + idProcess + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
