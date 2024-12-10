package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.AbandonedCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VTP12Test {

    private static final String DYNAMIC_TITLE_SECTION = "Public Services Vehicle for Examination";
    private static final String REGULATION_TEXT = "Regulation 13 of the Motor Vehicles (Tests) Regulations 1981 as amended";
    private static final String VEHICLE_TYPE_TEXT_LINE = "In respect of the public service vehicle with registration number / chassis serial number :";

    protected HtmlGenerator htmlGenerator;
    protected AbandonedCertificate testCertificate;
    protected CertificatePageObject certificatePageObject;

    public VTP12Test() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getVTP12();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyValuesSetByConstructor() {
        assertEquals(REGULATION_TEXT, testCertificate.getRegulationText());
        assertEquals(VEHICLE_TYPE_TEXT_LINE, testCertificate.getVehicleTypeText());
        assertEquals(DYNAMIC_TITLE_SECTION, testCertificate.getTitleTextIncludingRollingHeaders());
    }

    @Test
    public void verifyDocumentType() {
        assertEquals(testCertificate.getDocumentType(), certificatePageObject.getDocumentType());
    }

    @Test
    public void verifyFirstPageTitle() {
        String firstPageTitle = certificatePageObject.getFirstPageTitle();
        assertEquals("Notification of Failure to Comply with the Conditions of Acceptance of a " +
                testCertificate.getTitleTextIncludingRollingHeaders() + " (" + testCertificate.getDocumentType() + ")", firstPageTitle);
    }

    @Test
    public void verifyRegulationText() {
        assertEquals(testCertificate.getRegulationText(), certificatePageObject.getRegulationText());
    }

    @Test
    public void verifyFooterDocumentType() {
        assertEquals(testCertificate.getDocumentType(), certificatePageObject.getFooterDocumentType());
    }

    @Test
    public void verifyFooterTestNumber() {
        assertEquals("(DVSA" + testCertificate.getTestNumber() + ")", certificatePageObject.getFooterTestNumber());
    }

    @Test
    public void verifyFooterDatePopulated() {
        assertEquals("Date (" +testCertificate.getData().getDateOfTheTestYearMonth() + ")", certificatePageObject.getFooterDatePopulated());
    }

    @Test
    public void verifyVehicleTypeText() {
        assertEquals(testCertificate.getVehicleTypeText(), certificatePageObject.getVehicleTestType());
    }

    @Test
    public void verifyVin() {
        String[] vin = testCertificate.getData().getChassisNumber();
        for (int i = 0; i < vin.length; i++) {
            assertEquals(vin[i], certificatePageObject.getSpacedVin(i));
        }
    }

    @Test
    public void verifySectionText() {
        assertTrue(certificatePageObject.getSanctionText().contains(testCertificate.getSectionTextRef()));
    }

    @Test
    public void verifyRunningHeaderLeft() {
        assertEquals(testCertificate.getDocumentType(), certificatePageObject.getRunningHeaderLeft());
    }

    @Test
    public void verifyRunningHeaderRight() {
        assertEquals("Notification of Failure to Comply with the Conditions of Acceptance of a " +
                testCertificate.getTitleTextIncludingRollingHeaders(), certificatePageObject.getRunningHeaderRight());
    }

    @Test
    public void verifyReasonsForAbandonment() {
        String[] reasonsForAbandonment = testCertificate.getData().getReasonsForRefusal();
        for (int i = 0; i < reasonsForAbandonment.length; i++) {
            assertEquals(reasonsForAbandonment[i], certificatePageObject.getReasonsForAbandonment(i));
        }
    }

    @Test
    public void verifyAdditionalComments() {
        assertEquals(testCertificate.getData().getAdditionalComments(), certificatePageObject.getAdditionalComments());
    }

    @Test
    public void verifyDataProtectionWithDocumentType() {
        assertEquals("We collect, use and store your personal data so that we can correctly issue your vehicle with a " + testCertificate.getDocumentType() + " failure notification." , certificatePageObject.getDataProtectionWithDocumentType());
    }

    @Test
    public void verifySignature() {
        assertEquals(testCertificate.getSignature().getFormattedImageData(), certificatePageObject.getSignature());
    }

    @Test
    public void verifyPrintName() {
        assertEquals(testCertificate.getData().getIssuersName(), certificatePageObject.getPrintName());
    }

    @Test
    public void verifyLocation() {
        assertEquals(testCertificate.getData().getTestStationName(), certificatePageObject.getLocation());
    }

    @Test
    public void verifyLocationNumber() {
        assertEquals(testCertificate.getData().getTestStationPNumber(), certificatePageObject.getLocationNumber());
    }

    @Test
    public void verifyDateOfTheTest() {
        assertEquals(testCertificate.getData().getDateOfTheTest(), certificatePageObject.getDateOfTest());
    }
}