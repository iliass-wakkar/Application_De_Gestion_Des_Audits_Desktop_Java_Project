package model.Organization;

import java.util.UUID;

public class Site {
    private String idSite;
    private String address;
    private String description;
    private String name;


    public Site() {
        this.idSite = UUID.randomUUID().toString();
        this.address = "unknown";
        this.description = "unknown";
        this.name = "unknown";
    }

    public Site(String idArrSite, String address, String description, String name, String FK_Organization) {
        this.idSite = idArrSite;
        this.address = address;
        this.description = description;
        this.name = name;

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
                "idArrSite='" + idSite + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
