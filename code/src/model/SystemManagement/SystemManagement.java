package model.SystemManagement;

import model.Organization.Organization;
import model.SystemManagement.Standard.Standard;

import java.util.ArrayList;
import java.util.UUID;

public class SystemManagement {
    private String idSystemManagement;
    private String description;
    private String certificate;
    private Organization organization = new Organization();
    private ArrayList<Standard> standards = new ArrayList<>();
    private ArrayList<OtherRequirement> otherRequirements = new ArrayList<>();

    public SystemManagement() {
        this.idSystemManagement = UUID.randomUUID().toString();
        this.description = "unknown";
        this.certificate = "unknown";
    }

    public SystemManagement(String idSystemManagement, String description, String certificate, ArrayList<Standard> standards, ArrayList<OtherRequirement> otherRequirements, Organization organization) {
        this.idSystemManagement = idSystemManagement;
        this.description = description;
        this.certificate = certificate;
        this.standards = standards;
        this.otherRequirements = otherRequirements;
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getIdSystemManagement() {
        return idSystemManagement;
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
        return "SystemManagement{" +
                "idSystemManagement='" + idSystemManagement + '\'' +
                ", description='" + description + '\'' +
                ", certificate='" + certificate + '\'' +
                ", standards=" + standards +
                ", otherRequirements=" + otherRequirements +
                '}';
    }
}
