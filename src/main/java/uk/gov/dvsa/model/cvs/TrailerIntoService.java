package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.ApplicantDetails;

public class TrailerIntoService extends Document {

    @JsonProperty
    private ApplicantDetails applicantDetails;

    @JsonProperty
    private String letterDateRequested;

    @JsonProperty
    private String vin;

    @JsonProperty
    private String trailerId;

    @JsonProperty
    private String typeApprovalNumber;

    @JsonProperty
    private int paragraphId;

    public ApplicantDetails getApplicantDetails() {
        return applicantDetails;
    }
    public TrailerIntoService setApplicantDetails(ApplicantDetails applicantDetails) {
        this.applicantDetails = applicantDetails;
        return this;
    }

    public String getLetterDateRequested() { return this.letterDateRequested; }
    public TrailerIntoService setLetterDateRequested(String letterDateRequested) {
        this.letterDateRequested = letterDateRequested;
        return this;
    }

    public String getVin() { return vin; }
    public TrailerIntoService setVin(String vin) {
        this.vin = vin;
        return this;
    }

    public String getTrailerId() { return trailerId; }
    public TrailerIntoService setTrailerId(String trailerId) {
        this.trailerId = trailerId;
        return this;
    }

    public String getTypeApprovalNumber() { return typeApprovalNumber; }
    public TrailerIntoService setTypeApprovalNumber(String typeApprovalNumber) {
        this.typeApprovalNumber = typeApprovalNumber;
        return this;
    }

    public int getParagraphId() { return this.paragraphId; }
    public TrailerIntoService setParagraphId(int paragraphId) {
        this.paragraphId = paragraphId;
        return this;
    }
}
