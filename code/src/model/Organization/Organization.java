package model.Organization;

import model.SystemManagement.SystemManagement;

import java.util.ArrayList;
import java.util.UUID;

public class Organization {
    private String idOrg;
    private String name;
    private ArrayList<Site> sites = new ArrayList<>();
    private ArrayList<SystemManagement> systems = new ArrayList<>();

    public Organization() {
        this.idOrg = UUID.randomUUID().toString();
        this.name = "unknown";
    }
    public Organization(String idOrg, String name, ArrayList<Site> sites, ArrayList<SystemManagement> systems) {
        this.idOrg = idOrg;
        this.name = name;
        this.sites = sites;
        this.systems = systems;
    }

    public String getIdOrg() {
        return idOrg;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public ArrayList<SystemManagement> getSystems() {
        return systems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSites(ArrayList<Site> sites) {
        this.sites = sites;
    }

    public void setSystems(ArrayList<SystemManagement> systems) {
        this.systems = systems;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "idOrg='" + idOrg + '\'' +
                ", name='" + name + '\'' +
                ", sites=" + sites +
                ", systems=" + systems +
                '}';
    }

}
