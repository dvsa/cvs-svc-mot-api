package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;

public class CvsPsvPRSBilingual extends CvsPsvPRS {
    public String getPresentedDocumentNamePassWelsh() {
        return "VTP20W";
    }

    public String getVersionNumberPassWelsh() {
        return "1.0";
    }

    public Document setData(CvsMotCertificateDataWelsh data) {
        this.data = data;
        return this;
    }
}
