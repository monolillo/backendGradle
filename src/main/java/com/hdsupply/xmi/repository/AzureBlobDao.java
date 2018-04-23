package com.hdsupply.xmi.repository;

public interface AzureBlobDao {
	
	void uploadBlob(String fileName, byte[] fileBytes, String contentType);

}
