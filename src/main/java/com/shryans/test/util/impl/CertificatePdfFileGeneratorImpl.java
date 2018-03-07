package com.shryans.test.util.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import com.shryans.test.util.CertificateFileGenerator;

@Service
public class CertificatePdfFileGeneratorImpl implements CertificateFileGenerator{

	@Override
	public ByteArrayOutputStream generate(String[] content) {
		ByteArrayOutputStream output = new ByteArrayOutputStream(); 
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		try {
			PDPageContentStream contentStream;
			contentStream = new PDPageContentStream(document, page);
			contentStream.setFont(PDType1Font.COURIER, 12);
			contentStream.beginText();
			contentStream.newLineAtOffset(100,700);
			for(String line : content) {
				contentStream.showText(line);
				contentStream.newLine();
				contentStream.newLineAtOffset(0,-15);
				System.out.println(line);
			}
			contentStream.endText();
			contentStream.close();

			document.save(output);
			document.close();
			
			return output;
		} catch (IOException e) {
			return null;
		}
	}
	
	@Override
	public String getFileTypeExtension() {
		return ".pdf";
	}

}
