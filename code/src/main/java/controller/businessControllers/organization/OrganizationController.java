package controller.businessControllers.organization;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Organization.Organization;
import model.Organization.Site;
import model.SystemManagement.ManagementSystem;
import utils.JsonFileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizationController {
    private static final String ORGANIZATION_FILE_PATH = JsonFileHandler.ORGANIZATION_FILE_PATH;
    private static ArrayList<Organization> organizations = new ArrayList<>();


    public OrganizationController() {
        loadOrganizations();
        System.out.println("Organizations loaded successfully: " + organizations);
    }

    // Load organizations from the JSON file
    public void loadOrganizations() {
        try {
            List<Organization> loadedOrganizations = JsonFileHandler.loadData(ORGANIZATION_FILE_PATH, new TypeReference<List<Organization>>() {});
            organizations = new ArrayList<>(loadedOrganizations);
            System.out.println(organizations.size() + " organizations loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading organizations: " + e.getMessage());
        }
    }

    // Save organizations to the JSON file
    public void saveOrganizations() {
        try {
            JsonFileHandler.saveData(ORGANIZATION_FILE_PATH, organizations);
            System.out.println("Organizations saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving organizations: " + e.getMessage());
        }
    }


    public void createOrganization(Organization organizationData) {
        organizations.add(organizationData);
        System.out.println("New Organization added successfully.");

        saveOrganizations();
    }

    // Add or edit an organization
    public boolean editOrganization(String id,Organization updatedOrganization) {
        Optional<Organization> existingOrganization = organizations.stream()
                .filter(org -> org.getIdOrg().equals(id))
                .findFirst();

        if (existingOrganization.isPresent()) {
            existingOrganization.get().editOrganization(updatedOrganization);
            int index = organizations.indexOf(existingOrganization.get());
            organizations.get(index).editOrganization(updatedOrganization);
            System.out.println("Organization updated successfully.");
            saveOrganizations();
            return true;
        }
        else{
            System.out.println("Organization not found.");
            return false;
        }


    }

    // Delete an organization by ID
    public boolean deleteOrganization(String idOrg) {
        Optional<Organization> organizationToDelete = organizations.stream()
                .filter(org -> org.getIdOrg().equals(idOrg))
                .findFirst();

        if (organizationToDelete.isPresent()) {
            organizations.remove(organizationToDelete.get());
            saveOrganizations();
            System.out.println("Organization deleted successfully.");
            return true;
        } else {
            System.out.println("Organization not found.");
            return false;
        }
    }

    // Get an organization by ID
    public Organization getOrganizationById(String idOrg) {
        Optional<Organization> organization = organizations.stream()
                .filter(org -> org.getIdOrg().equals(idOrg))
                .findFirst();
        return organization.orElse(null);
    }

    // Get all organizations
    public List<Organization> getOrganizations() {
        return new ArrayList<>(organizations);
    }

    // Add a management system to an organization
    public void addManagementSystemToOrganization(String idOrg, ManagementSystem managementSystem) throws Exception {
        Organization organization = getOrganizationById(idOrg);
        if (organization != null) {
            organization.addSystem(managementSystem);
            saveOrganizations();
            System.out.println("Management System added to organization successfully.");
        } else {
            // Throw an exception if the organization is not found
            throw new Exception("Organization with ID " + idOrg + " not found.");
        }
    }

    // Edit a management system in an organization
    public boolean editManagementSystemInOrganization(String idOrg, String idManagementSystem, ManagementSystem updatedSystem) throws Exception {
        Organization organization = getOrganizationById(idOrg);
        if (organization != null) {
            boolean result = organization.editSystem(idManagementSystem, updatedSystem);
            if (result) {
                saveOrganizations();
                System.out.println("Management System updated successfully.");
            } else {
                throw new Exception("Management System not found." + idManagementSystem + " not found.");
            }
            return result;
        } else {
            throw new Exception("Organization with ID " + idOrg + " not found.");
        }
    }

    // Delete a management system from an organization
    public boolean deleteManagementSystemFromOrganization(String idOrg, String idManagementSystem) throws Exception {
        Organization organization = getOrganizationById(idOrg);
        if (organization != null) {
            boolean result = organization.deleteSystem(idManagementSystem);
            if (result) {
                saveOrganizations();
                System.out.println("Management System deleted successfully.");
            } else {
                throw new Exception("Management System not found." + idManagementSystem + " not found.");
            }
            return result;
        } else {
            throw new Exception("Organization with ID " + idOrg + " not found.");
        }
    }



    // Get all management systems across all organizations
    public List<ManagementSystem> getAllManagementSystems() {
        List<ManagementSystem> allManagementSystems = new ArrayList<>();
        for (Organization organization : organizations) {
            allManagementSystems.addAll(organization.getManagementSystems());
        }
        return allManagementSystems;
    }
    // Get all sites across all organizations
    public List<Site> getAllSites() {
        List<Site> allSites = new ArrayList<>();
        for (Organization organization : organizations) {
            allSites.addAll(organization.getSites());
        }


        return allSites;
    }

    // Add a site to an organization
    public void addSiteToOrganization(String idOrg, Site site) throws Exception {
        Organization organization = getOrganizationById(idOrg);
        if (organization != null) {
            organization.addSite(site);
            saveOrganizations();
            System.out.println("Site added to organization successfully.");
        } else {
            throw new Exception("Organization with ID " + idOrg + " not found.");
        }
    }

    // Edit a site in an organization
    public boolean editSiteInOrganization(String idOrg, String idSite, Site updatedSite) throws Exception {
        Organization organization = getOrganizationById(idOrg);
        if (organization != null) {
            boolean result = organization.editSite(idSite, updatedSite);
            if (result) {
                saveOrganizations();
                System.out.println("Site updated successfully.");
            } else {
                throw new Exception("Site not found. ID: " + idSite);
            }
            return result;
        } else {
            throw new Exception("Organization with ID " + idOrg + " not found.");
        }
    }

    // Create a site in an organization
    public void createSiteInOrganization(String idOrg, Site site) throws Exception {
        Organization organization = getOrganizationById(idOrg);
        if (organization != null) {
            organization.addSite(site);
            saveOrganizations();
            System.out.println("Site created successfully.");
        } else {
            throw new Exception("Organization with ID " + idOrg + " not found.");
        }
    }

    // Delete a site from an organization
    public boolean deleteSiteFromOrganization(String idOrg, String idSite) throws Exception {
        Organization organization = getOrganizationById(idOrg);
        if (organization != null) {
            boolean result = organization.deleteSite(idSite);
            if (result) {
                saveOrganizations();
                System.out.println("Site deleted successfully.");
            } else {
                throw new Exception("Site not found. ID: " + idSite);
            }
            return result;
        } else {
            throw new Exception("Organization with ID " + idOrg + " not found.");
        }
    }



}