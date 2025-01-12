package model.Organization;


import model.SystemManagement.ManagementSystem;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class Organization {
    private String idOrg;
    private String name;
    private String description;
    private ArrayList<Site> sites = new ArrayList<>();
    private ArrayList<ManagementSystem> managementSystems = new ArrayList<>();

    // Constructors
    public Organization() {
        this.idOrg = UUID.randomUUID().toString();
        this.name = "unknown";
        this.description = "unknown";
    }

    public Organization(String idOrg, String name, String description) {
        this.idOrg = idOrg;
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getIdOrg() {
        return idOrg;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public ArrayList<ManagementSystem> getManagementSystems() {
        return managementSystems;
    }

    // Setters
    public void setName(String name) {
      if(name != null && !name.isEmpty())
          this.name = name;


    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSites(ArrayList<Site> sites) {
        this.sites = sites;
    }

    public void setManagementSystems(ArrayList<ManagementSystem> ManagementSystems) {
        this.managementSystems = ManagementSystems;
    }


    public void editOrganization(Organization updatedOrganization) {
            this.setName(updatedOrganization.name);
            this.setDescription(updatedOrganization.description);
    }



    public void addSite(Site site) {
        if (site != null) {
            this.sites.add(site);
        }
    }


    public boolean editSite(Site updatedSite) {
        for (int i = 0; i < sites.size(); i++) {
            if (sites.get(i).getIdSite().equals(updatedSite.getIdSite())) {
                // Replace the site at the same index
                sites.set(i, updatedSite);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSite(String siteId) {
        Optional<Site> siteToDelete = sites.stream()
                .filter(site -> site.getIdSite().equals(siteId))
                .findFirst();

        if (siteToDelete.isPresent()) {
            sites.remove(siteToDelete.get());
            return true;
        }
        return false;
    }


    public Site findSiteById(String siteId) {
        return sites.stream()
                .filter(site -> site.getIdSite().equals(siteId))
                .findFirst()
                .orElse(null);
    }


    public void addSystem(ManagementSystem system) {
        if (system != null) {
            this.managementSystems.add(system);
        }
    }



    public boolean editSystem(ManagementSystem updatedSystem) {
        for (int i = 0; i < managementSystems.size(); i++) {
            if (managementSystems.get(i).getIdManagementSystem().equals(updatedSystem.getIdManagementSystem())) {
                // Replace the system at the same index
                managementSystems.set(i, updatedSystem);
                return true;
            }
        }
        return false;
    }

    // Delete a System by ID
    public boolean deleteSystem(String systemId) {
        Optional<ManagementSystem> systemToDelete = managementSystems.stream()
                .filter(system -> system.getIdManagementSystem().equals(systemId))
                .findFirst();

        if (systemToDelete.isPresent()) {
            managementSystems.remove(systemToDelete.get());
            return true;
        }
        return false;
    }

    // Find a System by ID
    public ManagementSystem findSystemById(String systemId) {
        return managementSystems.stream()
                .filter(system -> system.getIdManagementSystem().equals(systemId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "idOrg='" + idOrg + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sites=" + sites +
                ", systems=" + managementSystems +
                '}';
    }
}