package model.SystemManagement.Standard;

import model.SystemManagement.SystemManagement;

import java.util.ArrayList;
import java.util.UUID;

public class Standard {
    private String id;
    private String name;
    private String description;
    private String reference;
    private Process processe = new Process();
    private ArrayList<Clause> clauses = new ArrayList<>();
    private SystemManagement systemManagement = new SystemManagement();

    public Standard() {
        this.id = UUID.randomUUID().toString();
        this.name = "unknown";
        this.description = "unknown";
        this.reference = "unknown";
        this.processe = null;
        this.systemManagement = null;
    }

    public Standard(String id, String name, String description, String reference, Process processe, ArrayList<Clause> clauses, SystemManagement systemManagement) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.reference = reference;
        this.processe = processe;
        this.clauses = clauses;
        this.systemManagement = systemManagement;
    }

    public Process getProcesse() {
        return processe;
    }

    public void setProcesse(Process processe) {
        this.processe = processe;
    }

    public SystemManagement getSystemManagement() {
        return systemManagement;
    }

    public void setSystemManagement(SystemManagement systemManagement) {
        this.systemManagement = systemManagement;
    }

    public String getId() {
        return id;
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

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public ArrayList<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(ArrayList<Clause> clauses) {
        this.clauses = clauses;
    }

    @Override
    public String toString() {
        return "Standard{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", reference='" + reference + '\'' +
                ", processes=" + processes +
                ", clauses=" + clauses +
                '}';
    }
}
