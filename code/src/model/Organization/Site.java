package model.Organization;

import java.util.UUID;

public class Site {
    private String idArrSite;
    private String address;
    private String description;
    private String nom;
    private Organization organization;

    public Site() {
        this.idArrSite = UUID.randomUUID().toString();
        this.address = "unknown";
        this.description = "unknown";
        this.nom = "unknown";
        this.organization = new Organization();
    }
    public Site(String idArrSite, String address, String description, String nom, Organization organization , String FK_Organization) {
        this.idArrSite = idArrSite;
        this.address = address;
        this.description = description;
        this.nom = nom;
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getIdArrSite() {
        return idArrSite;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Site{" +
                "idArrSite='" + idArrSite + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
