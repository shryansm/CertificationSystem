package com.shryans.test.util;

import java.io.ByteArrayOutputStream;

public interface CertificateFileGenerator {
	
	ByteArrayOutputStream generate(String[] content);

	String getFileTypeExtension();
}
