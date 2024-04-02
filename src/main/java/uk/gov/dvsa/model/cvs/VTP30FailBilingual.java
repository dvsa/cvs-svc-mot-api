package uk.gov.dvsa.model.cvs;

public class VTP30FailBilingual extends VTP30W {

    public String getTestType() {
        return "PSV";
    }

    public String getPresentedDocumentNameFail() {
        return "VTP30";
    }

    public String getPresentedDocumentNameFailWelsh() {
        return "VTP30W";
    }

    public String getVersionNumberFail() {
        return "1.0";
    }

    public String getVersionNumberFailWelsh() {
        return "1.0";
    }

    public String getRegOrIdHeading() {
        return "Identification number";
    }

    public String getRegOrIdHeadingWelsh() {
        return "Rhif adnabod";
    }
}
