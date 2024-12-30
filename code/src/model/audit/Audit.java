package model.audit;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Audit {
    private final String idAudit;
    private String dateDebut;
    private String ExpDate;
    private String intitule;
    private String status;
    private String idAuditor;
    private String idOrg;
    private String idSystemManagement;
    private FinalReport finalReport;
    private boolean isTakeCertificate;
    private String isPass;
    private List<StandardStat> arrStandardStat;
    private List<ExigenceStat> arrExigenceStat;

    // Default Constructor
    public Audit() {
        this.idAudit = UUID.randomUUID().toString();
        this.dateDebut = "unknown";
        this.ExpDate = "unknown";
        this.intitule = "unknown";
        this.status = "pending";
        this.idAuditor = "unknown";
        this.idOrg = "unknown";
        this.idSystemManagement = "unknown";
        this.finalReport = new FinalReport();
        this.isTakeCertificate = false;
        this.isPass = "notYet";
        this.arrStandardStat =  new ArrayList<StandardStat>();
        this.arrExigenceStat = new ArrayList<ExigenceStat>();
    }

    // Parameterized Constructor
    public Audit(String dateDebut, String ExpDate, String intitule, String status, String idAuditor, String idOrg,
                 String idSystemManagement, FinalReport finalRapport, boolean isTakeCertificate, String isPass,
                 List<StandardStat> arrStandardStat, List<ExigenceStat> arrExigenceStat) {
        this.idAudit = UUID.randomUUID().toString();
        this.dateDebut = dateDebut;
        this.ExpDate = ExpDate;
        this.intitule = intitule;
        this.status = status;
        this.idAuditor = idAuditor;
        this.idOrg = idOrg;
        this.idSystemManagement = idSystemManagement;
        this.finalReport = finalRapport;
        this.isTakeCertificate = isTakeCertificate;
        this.isPass = isPass;
        this.arrStandardStat = arrStandardStat;
        this.arrExigenceStat = arrExigenceStat;
    }

    // Getters and Setters
    public String getIdAudit() {
        return idAudit;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getExpDate() {
        return ExpDate;
    }

    public void setExpDate(String ExpDate) {
        this.ExpDate = ExpDate;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status.equals("pending") || status.equals("notYet") || status.equals("in progress") || status.equals("completed"))  {
            this.status = status;
        }
        else {
            System.out.println("Invalid status");
        }

    }

    public String getIdAuditor() {
        return idAuditor;
    }

    public void setIdAuditor(String idAuditor) {
        this.idAuditor = idAuditor;
    }

    public String getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(String idOrg) {
        this.idOrg = idOrg;
    }

    public String getIdSystemManagement() {
        return idSystemManagement;
    }

    public void setIdSystemManagement(String idSystemManagement) {
        this.idSystemManagement = idSystemManagement;
    }

    public FinalReport getFinalReport() {
        return finalReport;
    }

    public void setFinalReport(FinalReport finalReport) {
        this.finalReport = finalReport;
    }

    public boolean isTakeCertificate() {
        return isTakeCertificate;
    }

    public void setTakeCertificate(boolean takeCertificate) {
        isTakeCertificate = takeCertificate;
    }

    public String getIsPass() {

        return isPass;
    }

    public void setIsPass(String isPass) {
      if(isPass.equals("notYet") || isPass.equals("pass") || isPass.equals("fail"))  {
          this.isPass = isPass;
      }
      else {
          System.out.println("Invalid pass");
      }

    }

    public List<StandardStat> getArrStandardStat() {
        return arrStandardStat;
    }

    public void setArrStandardStat(List<StandardStat> arrStandardStat) {
        this.arrStandardStat = arrStandardStat;
    }

    public List<ExigenceStat> getArrExigenceStat() {
        return arrExigenceStat;
    }

    public void setArrExigenceStat(List<ExigenceStat> arrExigenceStat) {
        this.arrExigenceStat = arrExigenceStat;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "idAudit='" + idAudit + '\'' +
                ", dateDebut='" + dateDebut + '\'' +
                ", ExpDate='" + ExpDate + '\'' +
                ", intitule='" + intitule + '\'' +
                ", status='" + status + '\'' +
                ", idAuditor='" + idAuditor + '\'' +
                ", idOrg='" + idOrg + '\'' +
                ", idSystemManagement='" + idSystemManagement + '\'' +
                ", finalRapport=" + finalReport +
                ", isTakeCertificate=" + isTakeCertificate +
                ", isPass='" + isPass + '\'' +
                ", arrStandardStat=" + arrStandardStat +
                ", arrExigenceStat=" + arrExigenceStat +
                '}';
    }
}
