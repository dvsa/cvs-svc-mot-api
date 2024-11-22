package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.CvsMotCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class HgvPRSBilingualTests {
    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private CvsMotCertificate testCertificate;
    private PDFParser pdfParser;
    private PdfReader pdfReader;

    public HgvPRSBilingualTests() {
        this.testCertificate = CvsCertificateTestDataProvider.getCvsHgvPRSBilingual();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void setup() throws Exception {
        byte[] pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
        try (FileOutputStream fos = new FileOutputStream("generatedPdf.pdf")) {
            fos.write(pdfData);
            System.out.println("PDF file has been saved.");
        } catch (IOException ioe) {
            System.err.println("Failed to save the PDF file: " + ioe.getMessage());
            throw ioe;
        }
    }

    @Test
    public void verifyTitle() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("MOT test certificate (HGV)"));
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains("Refusal of MOT test certificate"));
        assertTrue(pdfParser.getRawText(pdfReader, 4).contains("Tystysgrif prawf MOT (HGV)"));
        assertTrue(pdfParser.getRawText(pdfReader, 6).contains("Gwrthod tystysgrif prawf MOT"));
    }

    @Test
    public void verifySinglePageWithInvalidXMLCharacter() throws Exception {
        this.testCertificate = CvsCertificateTestDataProvider.getCvsHgvPRSBilingualHavingInvalidXMLCharacter();
        pdfParser.getRawText(pdfReader, 1);
    }
}
