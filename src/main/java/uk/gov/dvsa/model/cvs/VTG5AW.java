package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData;

public class VTG5AW extends CvsMotCertificate {

    public Document setData(CvsMotCertificateData data) {
        this.data = data;
        return this;
    }

    public String getTestType() {
        return "Trailer";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5AW";
    }

    public String getVersionNumberPass() {
        return "1.0";
    }

    public String getRegOrIdHeading() { return "Rhif cofrestru";  }
}
