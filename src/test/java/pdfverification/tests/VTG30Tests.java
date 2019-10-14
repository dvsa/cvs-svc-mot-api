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

public class VTG30Tests {

    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private CvsMotCertificate testCertificate;
    private PDFParser pdfParser;
    private byte[] pdfData;

    public VTG30Tests() {
        this.testCertificate = CvsCertificateTestDataProvider.getVtg30();

        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void before() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
    }

    @Test
    public void verifySinglePageWithInvalidXMLCharacter() throws Exception {

        this.testCertificate = CvsCertificateTestDataProvider.getVtg30HavingInvalidXMLCharacter();
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));

        PdfReader reader = pdfParser.readPdf(pdfData);

        pdfParser.getRawText(reader, 1);
    }
}
