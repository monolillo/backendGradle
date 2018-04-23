package com.hdsupply.xmi.repository;

public interface AzureBlobDao {
	
	String uploadBlob(String fileName, byte[] fileBytes, String contentType);

}
