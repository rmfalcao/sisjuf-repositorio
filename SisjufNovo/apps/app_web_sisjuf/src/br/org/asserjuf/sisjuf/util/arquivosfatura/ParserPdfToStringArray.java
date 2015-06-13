package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ParserPdfToStringArray {
	
	private InputStream stream;
	private int numeroPaginas;
	
	public ParserPdfToStringArray(byte[] contentFile) throws IOException{
		this.stream = new ByteArrayInputStream(contentFile);
		PDDocument document = PDDocument.load(stream);
		document.close();	
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
		
		PDFParser parser = new PDFParser(stream);
		parser.parse();
		COSDocument cosDoc = parser.getDocument();
        PDDocument  pdDoc = new PDDocument(cosDoc);
		PDFTextStripper stripper = new PDFTextStripper();
		stripper.setStartPage(startPage);
		stripper.setEndPage(endPage);
		String st = stripper.getText(pdDoc);
		String[] linhasArquivo = st.split("\n");
		releaseResources(parser, cosDoc, pdDoc);
		return linhasArquivo;
	}

	private void releaseResources(PDFParser parser, COSDocument cosDoc,PDDocument pdDoc) throws IOException {
		pdDoc.close();
		cosDoc.close();
		parser.clearResources();
		stream.close();
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}
}
