package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdrPassCertificateData {
    private final String SUBSTANCES_PERMITTED_OPTION_1 = "Substances permitted under the tank code and any special provisions specified in 9 may be carried";
    private final String PERMITTED_DANGEROUS_GOODS_EXPLOSIVES_2 = "Explosives (type 2)";
    private final String PERMITTED_DANGEROUS_GOODS_EXPLOSIVES_3 = "Explosives (type 3)";

    @JsonProperty("vin")
    private String vin;
    @JsonProperty("make")
    private String make;
    @JsonProperty("vrm")
    private String vrm;
    @JsonProperty("applicantDetails")
    private ApplicantDetails applicantDetails;
    @JsonProperty("adrVehicleType")
    private String adrVehicleType;
    @JsonProperty("permittedDangerousGoods")

    private String[] permittedDangerousGoods;
    @JsonProperty("brakeEndurance")

    private boolean brakeEndurance;
    @JsonProperty("weight")

    private String weight;
    @JsonProperty("tankManufacturer")

    private String tankManufacturer;
    @JsonProperty("tankManufactureSerialNo")

    private String tankManufactureSerialNo;

    @JsonProperty("tc2InitApprovalNo")

    private String tc2InitApprovalNo;
    @JsonProperty("yearOfManufacture")

    private String yearOfManufacture;

    @JsonProperty("tankCode")

    private String tankCode;
    @JsonProperty("specialProvisions")

    private String specialProvisions;
    @JsonProperty("tankStatement")

    private TankStatement tankStatement;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("replacement")
    private boolean replacement;

    public String getVin() {
        return vin;
    }

    public AdrPassCertificateData setVin(String vin) {
        this.vin = vin;
        return this;
    }

    public String getMake() {
        return make;
    }

    public AdrPassCertificateData setMake(String make) {
        this.make = make;
        return this;
    }

    public String getVrm() {
        return vrm;
    }

    public AdrPassCertificateData setVrm(String vrm) {
        this.vrm = vrm;
        return this;
    }

    public ApplicantDetails getApplicantDetails() {
        return applicantDetails;
    }

    public AdrPassCertificateData setApplicantDetails(ApplicantDetails applicantDetails) {
        this.applicantDetails = applicantDetails;
        return this;
    }

    public String getAdrVehicleType() {
        return adrVehicleType;
    }

    public AdrPassCertificateData setAdrVehicleType(String adrVehicleType) {
        this.adrVehicleType = adrVehicleType;
        return this;
    }

    public String[] getPermittedDangerousGoods() {
        return permittedDangerousGoods;
    }

    public AdrPassCertificateData setPermittedDangerousGoods(String[] permittedDangerousGoods) {
        this.permittedDangerousGoods = permittedDangerousGoods;
        return this;
    }

    public boolean getBrakeEndurance() {
        return brakeEndurance;
    }

    public AdrPassCertificateData setBrakeEndurance(boolean brakeEndurance) {
        this.brakeEndurance = brakeEndurance;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public AdrPassCertificateData setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getTankManufacturer() {
        return tankManufacturer;
    }

    public AdrPassCertificateData setTankManufacturer(String tankManufacturer) {
        this.tankManufacturer = tankManufacturer;
        return this;
    }

    public String getTc2InitApprovalNo() {
        return tc2InitApprovalNo;
    }

    public AdrPassCertificateData setTc2InitApprovalNo(String tc2InitApprovalNo) {
        this.tc2InitApprovalNo = tc2InitApprovalNo;
        return this;
    }

    public String getTankManufactureSerialNo() {
        return tankManufactureSerialNo;
    }

    public AdrPassCertificateData setTankManufactureSerialNo(String tankManufactureSerialNo) {
        this.tankManufactureSerialNo = tankManufactureSerialNo;
        return this;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public AdrPassCertificateData setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public String getTankCode() {
        return tankCode;
    }

    public AdrPassCertificateData setTankCode(String tankCode) {
        this.tankCode = tankCode;
        return this;
    }

    public String getSpecialProvisions() {
        return specialProvisions;
    }

    public AdrPassCertificateData setSpecialProvisions(String specialProvisions) {
        this.specialProvisions = specialProvisions;
        return this;
    }

    public TankStatement getTankStatement() {
        return tankStatement;
    }

    public AdrPassCertificateData setTankStatement(TankStatement tankStatement) {
        this.tankStatement = tankStatement;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public AdrPassCertificateData setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public boolean getHasNotes(){
        if(this.notes != null){
            return !this.notes.equals("");
        }
        return false;
    }

    public boolean getHasNotes1Lines(){
        return this.notes.length() <= 168;
    }

    public boolean getHasNotes2Lines(){
        return this.notes.length() >= 158 && this.notes.length() <= 316;
    }

    public boolean getHasNotes3Lines(){
        return this.notes.length() >= 316 && this.notes.length() <= 474;
    }

    public boolean getHasNotes4Lines(){
        return this.notes.length() >= 474 && this.notes.length() <= 632;
    }

    public String getFormattedPermittedDangerousGoods() {
        StringBuilder formattedPermittedDangerousGoods =  new StringBuilder("");
        if(this.permittedDangerousGoods == null)
            return "";
        for (String permittedDangerousGood : this.permittedDangerousGoods) {
            formattedPermittedDangerousGoods.append(permittedDangerousGood + " ");
        }
        return formattedPermittedDangerousGoods.toString();
    }

    public boolean getFormattedSubstancesPermitted() { // returns true for the first value that tankStatement can have and false for the other one so it can be processed in view
        if(this.tankStatement != null && tankStatement.getSubstancesPermitted() != null && this.tankStatement.getSubstancesPermitted().equals(SUBSTANCES_PERMITTED_OPTION_1)){
            return true;
        } else {
            return false;
        }
    }

    public boolean getIsTankStatementNull() {
        return this.tankStatement == null;
    }

    public boolean getIsApplicantDetailsNull() {
        return this.applicantDetails == null;
    }

    public boolean getIsVehicleTypeNull() {
        return this.adrVehicleType == null;
    }

    public boolean getIsExplosivesType2() {
        if(this.permittedDangerousGoods == null){
            return false;
        }
        for (String permittedDangerousGood : this.permittedDangerousGoods) {
            if(permittedDangerousGood.equals(PERMITTED_DANGEROUS_GOODS_EXPLOSIVES_2)){
                return true;
            }
        }
        return false;
    }

    public boolean getIsExplosivesType3() {
        if(this.permittedDangerousGoods == null){
            return false;
        }
        for (String permittedDangerousGood : this.permittedDangerousGoods) {
            if(permittedDangerousGood.equals(PERMITTED_DANGEROUS_GOODS_EXPLOSIVES_3)){
                return true;
            }

        }
        return false;
    }

    public boolean isReplacement() {
        return replacement;
    }

    public void setReplacement(boolean replacement) {
        this.replacement = replacement;
    }
}
