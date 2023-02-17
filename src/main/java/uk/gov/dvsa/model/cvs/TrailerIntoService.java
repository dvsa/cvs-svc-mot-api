package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.ApplicantDetails;

public class TrailerIntoService extends Document {

    @JsonProperty()
    private ApplicantDetails applicantDetails;

    @JsonProperty()
    private String letterDateRequested;

    @JsonProperty()
    private String vin;

    @JsonProperty()
    private String trailerId;

    @JsonProperty()
    private String typeApprovalNumber;

    @JsonProperty()
    private int paragraphId;

    public ApplicantDetails getApplicantDetails() {
        return applicantDetails;
    }

    public void setApplicantDetails(ApplicantDetails applicantDetails) {
        this.applicantDetails = applicantDetails;
    }

    public String getLetterDateRequested() { return this.letterDateRequested; }

    public void setLetterDateRequested(String letterDateRequested) {
        this.letterDateRequested = letterDateRequested;
    }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public String getTrailerId() { return trailerId; }
    public void setTrailerId(String trailerId) { this.trailerId = trailerId; }

    public String getTypeApprovalNumber() { return typeApprovalNumber; }
    public void setTypeApprovalNumber(String typeApprovalNumber) { this.typeApprovalNumber = typeApprovalNumber; }

    public int getParagraphId() { return this.paragraphId; }
    public void setParagraphId(int paragraphId) { this.paragraphId = paragraphId; }
}
