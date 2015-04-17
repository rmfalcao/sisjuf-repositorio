package br.com.sisjuf;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ParserPdfToStringArray {
	
	private int numeroPaginas;
	private String pathFile;
	private PDDocument document;
	
	public ParserPdfToStringArray(String pathFile) throws IOException{
		this.pathFile = pathFile;
		this.document = PDDocument.load(pathFile);
		this.numeroPaginas = document.getNumberOfPages();
	}
	
	public String[] parserPdfContentToStringArray() throws IOException{
		return parserUsingPdfBox(0,-1);
	}
	
	public String[] parserPdfContentToStringArraySpecificPage(int startPage,int endPage) throws IOException{
		return parserUsingPdfBox(startPage,endPage);
	}

	private String[] parserUsingPdfBox(int startPage,int endPage) throws IOException, UnsupportedEncodingException {
		if(endPage == -1){
			endPage = numeroPaginas;
		}
		
		InputStream file = new FileInputStream(pathFile);
		PDFParser parser = new PDFParser(file);
		parser.parse();
		COSDocument cosDoc = parser.getDocument();
        PDDocument  pdDoc = new PDDocument(cosDoc);
		PDFTextStripper stripper = new PDFTextStripper();
		stripper.setStartPage(startPage);
		stripper.setEndPage(endPage);
		String st = stripper.getText(pdDoc);
		String[] linhasArquivo = st.split("\n");
		releaseResources(parser, cosDoc, pdDoc,file);
		return linhasArquivo;
	}

	private void releaseResources(PDFParser parser, COSDocument cosDoc,PDDocument pdDoc,InputStream stream) throws IOException {
		pdDoc.close();
		cosDoc.close();
		parser.clearResources();
		stream.close();
		document.close();
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}
}
