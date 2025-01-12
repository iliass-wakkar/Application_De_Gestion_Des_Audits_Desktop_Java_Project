package model.Organization;

import java.util.UUID;

public class Site {
    private String idSite;
    private String idOrg ;
    private String address;
    private String description;
    private String name;

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public String getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(String idOrg) {
        this.idOrg = idOrg;
    }

    public Site() {
        this.idSite = UUID.randomUUID().toString();
        this.address = "unknown";
        this.description = "unknown";
        this.name = "unknown";
    }

    public Site(String idSite, String idOrg,String address, String description, String name) {
        this.idSite = idSite;
        this.idOrg = idOrg;
        this.address = address;
        this.description = description;
        this.name = name;

    }
    public void editSite(Site updatedSite){
         this.setAddress(updatedSite.getAddress());
         this.setDescription(updatedSite.getDescription());
         this.setName(updatedSite.getName());
    }


    public String getIdSite() {
        return idSite;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Site{" +
                "idSite='" + idSite + '\'' +
                "idOrg='" + idOrg + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
