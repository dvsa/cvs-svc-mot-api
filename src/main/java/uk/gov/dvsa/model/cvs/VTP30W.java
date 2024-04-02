package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;

public class VTP30W extends CvsMotFailCertificate {

    public Document setData(CvsMotCertificateDataWelsh data) {
        this.data = data;
        return this;
    }

    public String getTestType() {
        return "PSV";
    }

    public String getPresentedDocumentNameFail() {
        return "VTP30W";
    }

    public String getVersionNumberFail() {
        return "1.0";
    }

    public String getRegOrIdHeadingWelsh() {
        return "Rhif cofrestru";
    }
}
