package model.SystemManagement;

import model.SystemManagement.Standard.Standard;

import java.util.ArrayList;
import java.util.UUID;

public class ManagementSystem {
    private String idManagementSystem;
    private String idOrg; // ID of the organization to which this system belongs
    private String description;
    private String certificate;
    private ArrayList<Standard> standards = new ArrayList<>();
    private ArrayList<OtherRequirement> otherRequirements = new ArrayList<>();

    public void setIdManagementSystem(String idManagementSystem) {
        this.idManagementSystem = idManagementSystem;
    }

    // Constructors
    public ManagementSystem() {
        this.idManagementSystem = UUID.randomUUID().toString();
        this.idOrg = "unknown"; // Default value
        this.description = "unknown";
        this.certificate = "unknown";
    }

    public ManagementSystem(String idManagementSystem, String idOrg, String description, String certificate, ArrayList<Standard> standards, ArrayList<OtherRequirement> otherRequirements) {
        this.idManagementSystem = idManagementSystem;
        this.idOrg = idOrg;
        this.description = description;
        this.certificate = certificate;
        this.standards = standards;
        this.otherRequirements = otherRequirements;
    }

    // Getters and Setters
    public String getIdManagementSystem() {
        return idManagementSystem;
    }

    public String getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(String idOrg) {
        this.idOrg = idOrg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public ArrayList<Standard> getStandards() {
        return standards;
    }

    public void setStandards(ArrayList<Standard> standards) {
        this.standards = standards;
    }

    public ArrayList<OtherRequirement> getOtherRequirements() {
        return otherRequirements;
    }

    public void setOtherRequirements(ArrayList<OtherRequirement> otherRequirements) {
        this.otherRequirements = otherRequirements;
    }

    @Override
    public String toString() {
        return "ManagementSystem{" +
                "idManagementSystem='" + idManagementSystem + '\'' +
                ", idOrg='" + idOrg + '\'' +
                ", description='" + description + '\'' +
                ", certificate='" + certificate + '\'' +
                ", standards=" + standards +
                ", otherRequirements=" + otherRequirements +
                '}';
    }
}