interface DocumentFactory {
    void createDocument();
}
interface WordDocumentAbs {
    void wordSpecific();
}
interface PdfDocumentAbs {
    void pdfSpecific();
}
interface ExcelDocumentAbs {
    void excelSpecific();
}
class WordDocument implements WordDocumentAbs, DocumentFactory {
    public void createDocument() {
        System.out.println("Word document file created");
    }
    public void wordSpecific() {
        System.out.println("Function to perform operations specific to word document");
    }
}
class PdfDocument implements PdfDocumentAbs, DocumentFactory {
    public void createDocument() {
        System.out.println("PDF document file created");
    }
    public void pdfSpecific() {
        System.out.println("Function to perform operations specific to pdf document");
    }
}
class ExcelDocument implements ExcelDocumentAbs, DocumentFactory {
    public void createDocument() {
        System.out.println("Excel document file created");
    }
    public void excelSpecific() {
        System.out.println("Function to perform operations specific to excel document");
    }
}
public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        WordDocument wrdFile = new WordDocument();
        wrdFile.createDocument();
        wrdFile.wordSpecific();
        PdfDocument pdfFile = new PdfDocument();
        pdfFile.createDocument();
        pdfFile.pdfSpecific();
        ExcelDocument exlFile = new ExcelDocument();
        exlFile.createDocument();
        exlFile.excelSpecific();
    }
}