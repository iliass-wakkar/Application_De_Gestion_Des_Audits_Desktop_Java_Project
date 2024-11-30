package model;

public class Audit {
    private int id;
    private String type;
    private String status;
    private int auditorId;
    private int organizationId;

    public Audit(int id, String type, String status, int auditorId, int organizationId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.auditorId = auditorId;
        this.organizationId = organizationId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}

