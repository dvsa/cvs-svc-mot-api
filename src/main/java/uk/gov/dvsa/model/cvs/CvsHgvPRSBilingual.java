package uk.gov.dvsa.model.cvs;

public class CvsHgvPRSBilingual extends CvsHgvTrlFailBilingual {

    public String getTestType() {
        return "HGV";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5";
    }

    public String getPresentedDocumentNamePassWelsh() {
        return "VTG5W";
    }

    public String getVersionNumberPass() {
        return "1.0";
    }

    public String getVersionNumberPassWelsh() {
        return "1.0";
    }
}