package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.cvs.certificateData.CvsMotFailCertificateDataWelsh;

public class CvsTrlPRSBilingual extends CvsMotFailCertificate {
    public String getTestType() {
        return "TRL";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5A";
    }

    public String getPresentedDocumentNamePassWelsh() {
        return "VTG5AW";
    }


    public String getPresentedDocumentNameFail() {
        return "VTG30";
    }

    public String getPresentedDocumentNameFailWelsh() {
        return "VTG30W";
    }

    public String getVersionNumberPass() {
        return "1.0";
    }

    public String getVersionNumberPassWelsh() {
        return "1.0";
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
