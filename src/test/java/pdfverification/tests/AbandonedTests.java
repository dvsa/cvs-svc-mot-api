package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.AbandonedCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public abstract class AbandonedTests {
    protected HtmlGenerator htmlGenerator;
    protected PDFGenerationService pdfGenerationService;
    protected AbandonedCertificate testCertificate;
    protected PDFParser pdfParser;
    protected PdfReader pdfReader;
    protected byte[] pdfData;

    protected static String DYNAMIC_TITLE_SECTION = "Examination (VTG12)";
    protected static String REGULATION_LINE1 = "Regulations 7 and 8 of the Goods Vehicles (Plating";
    protected static String REGULATION_LINE2 = "and Testing) Regulations 1988 as Amended";
    protected static String VEHICLE_TYPE_TEXT_LINE1 = "In respect of the goods vehicle with registration number / chassis serial number / trailer";
    protected static String VEHICLE_TYPE_TEXT_LINE2 = "identification mark :";
    protected static String VIN = "P O I U Y T R E W Q 0 1 2 3 0 1 0 9 5 6 7 8 9 1";
    protected static String REASONS_FOR_REFUSAL_LINE1 = "Reason 1 exists VTG12";
    protected static String REASONS_FOR_REFUSAL_LINE2 = "Reason 2 exists VTG12";
    protected static String ROLLING_FOOTER_LEFT = "VTG12 (DVSA0440)";
    protected static String ADDITIONAL_COMMENTS = "additional comments VTG12";
    protected static String ROLLING_HEADER_LEFT = "VTG12";
    protected static String ROLLING_HEADER_RIGHT = "Acceptance of a Goods Vehicle for Examination";
    protected static String PRINT_NAME = "fake tester";
    protected static String LOCATION = "fake12312312";
    protected static String LOCATION_NUMBER = "fake12312312";
    protected static String DATE_OF_THE_TEST = "01.02.2024";
    protected static String SECTION_TEXT = "having been submitted for an examination under Section 49 and 51 of the Road Traffic Act 1998, it";

    public AbandonedTests() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfParser = new PDFParser();
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
    }

    @Before
    public void setup() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
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
    public void verifySectionText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(SECTION_TEXT));
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

    @Test
    public void verifyPrintName() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(PRINT_NAME));
    }

    @Test
    public void verifyLocation() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(LOCATION));
    }

    @Test
    public void verifyLocationNumber() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(LOCATION_NUMBER));
    }

    @Test
    public void verifyDate() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(DATE_OF_THE_TEST));
    }
}
