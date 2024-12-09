package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.AbandonedCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;
import java.io.FileOutputStream;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class VTG12Tests {

    private static final String DYNAMIC_TITLE_SECTION = "Vehicle for Examination (VTG12)";
    private static final String REGULATION_LINE1 = "Regulations 7 and 8 of the Goods Vehicles (Plating";
    private static final String REGULATION_LINE2 = "and Testing) Regulations 1988 as Amended";
    private static final String VEHICLE_TYPE_TEXT_LINE1 = "In respect of the goods vehicle with registration number / chassis serial number / trailer";
    private static final String VEHICLE_TYPE_TEXT_LINE2 = "identification mark :";
    private static final String VIN = "P O I U Y T R E W Q 0 1 2 3 0 1 0 9 5 6 7 8 9 1";
    private static final String REASONS_FOR_REFUSAL_LINE1 = "Reason 1 exists VTG12";
    private static final String REASONS_FOR_REFUSAL_LINE2 = "Reason 2 exists VTG12";
    private static final String ROLLING_FOOTER_LEFT = "VTG12 (DVSA 0440)";
    private static final String ROLLING_FOOTER_RIGHT = "Date (2024 06)";
    private static final String ADDITIONAL_COMMENTS = "additional comments VTG12";
    private static final String ROLLING_HEADER_LEFT = "VTG12";
    private static final String ROLLING_HEADER_RIGHT = "Acceptance of a Goods Vehicle for Examination";

    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private AbandonedCertificate testCertificate;
    private PDFParser pdfParser;

    private PdfReader pdfReader;
    private byte[] pdfData;

    public VTG12Tests() {
        this.testCertificate = CvsCertificateTestDataProvider.getVTG12();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void setup() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);

        try (FileOutputStream fos = new FileOutputStream("testVTG12.pdf")) {
            fos.write(pdfData);
        }
    }

    @Test
    public void verifyTitle() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(DYNAMIC_TITLE_SECTION));
    }

    @Test
    public void verifyRegulationText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REGULATION_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REGULATION_LINE2));
    }

    @Test
    public void verifyVehicleTypeText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(VEHICLE_TYPE_TEXT_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(VEHICLE_TYPE_TEXT_LINE2));
    }

    @Test
    public void verifyVINText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(VIN));
    }

    @Test
    public void verifyReasonsForRefusal() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REASONS_FOR_REFUSAL_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REASONS_FOR_REFUSAL_LINE2));
    }

    @Test
    public void verifyRollingFooterLeftText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(ROLLING_FOOTER_LEFT));
    }

    @Test
    public void verifyRollingFooterRightText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(ROLLING_FOOTER_RIGHT));
    }

    @Test
    public void verifyAdditionalComments() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(ADDITIONAL_COMMENTS));
    }

    @Test
    public void verifyRollingHeaderLeftText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(ROLLING_HEADER_LEFT));
    }

    @Test
    public void verifyRollingHeaderRightText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(ROLLING_HEADER_RIGHT));
    }
}
