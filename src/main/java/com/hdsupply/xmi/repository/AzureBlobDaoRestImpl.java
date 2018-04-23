package com.hdsupply.xmi.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Implementation of AzureBlobDao that uses a REST service call to upload the file.
 * 
 * @author julian.nunez
 *
 */
@Repository
public class AzureBlobDaoRestImpl implements AzureBlobDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(AzureBlobDaoRestImpl.class);
	
	@Autowired
	private RestOperations restTemplate;
	
	private static final String API_KEY = "umh7nWop8PBEpSuPfJpB2v9NhE9FCJIr71i9fIjMDFZNRmXuHPAny5f54ZxeTKjhjMwCvfIvNfVW2wdJFYOyGg==";
	private static final String STORAGE_NAME = "elux1";
	private static final String CONT_NAME = "xmistorage";
	private static final String ENDPOINT = "https://{0}.blob.core.windows.net/{1}/{2}";
	private static final String STR_TO_SIGN = "PUT\n\n\n{0}\n\n{1}\n\n\n\n\n\n\nx-ms-blob-type:BlockBlob\nx-ms-date:{2}\nx-ms-version:2015-12-11\n/{3}{4}";
	private static final String AUTH_STR = "SharedKey {0}:{1}";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");

	/**
	 * Sends a request to the IFTTT endpoint with the following JSON body
	 * 
	 * { 
	 * 	"value1" : "{value1}",
	 *	"value2" : "{value2}",
	 *  "value3" : "{value3}" 
	 * }
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws IOException 
	 * 
	 */
	@Override
	public String uploadBlob(String fileName, byte[] fileBytes, String contentType) {
		
		DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
		String currTime = DATE_FORMAT.format(new Date());
		
		UriComponents fileUri = UriComponentsBuilder.fromUriString(ENDPOINT).build()
				.expand(STORAGE_NAME, CONT_NAME, fileName);
		
		String authHeader = null;
		try {
			authHeader = getAuthorization(currTime, fileUri.encode().getPath(), fileBytes.length, contentType);
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			LOG.error("Unable to generate crypto for upload.", e);
			throw new RuntimeException("Cryto error trying to upload file", e);
		}
	    
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", contentType);
		headers.setContentLength(fileBytes.length);
		headers.set("x-ms-blob-type", "BlockBlob");
		headers.set("x-ms-version", "2015-12-11");
		headers.set("x-ms-date", currTime);
		headers.set("Authorization", authHeader);
		
		LOG.debug("Making a PUT request to: {}", fileUri.toUriString());
		
		HttpEntity<byte[]> entity = new HttpEntity<>(fileBytes, headers);
		restTemplate.put(fileUri.toUriString(), entity);	    
		
		return fileUri.encode().toUriString();
	    
	}
	
	private String getAuthorization(String currTime, String filePath, int size, String contentType) throws NoSuchAlgorithmException, InvalidKeyException {
		
		String toSign = MessageFormat.format(STR_TO_SIGN, Long.toString(size), contentType, currTime, STORAGE_NAME, filePath);
		
		LOG.debug("Signing request with string: {}", toSign);
		
		byte[] secret = DatatypeConverter.parseBase64Binary(API_KEY);
		
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	    SecretKeySpec secret_key = new SecretKeySpec(secret, "HmacSHA256");
	    sha256_HMAC.init(secret_key);

	    String hashBase64 = DatatypeConverter.printBase64Binary(sha256_HMAC.doFinal(toSign.getBytes()));
	    
	    String authorization = MessageFormat.format(AUTH_STR, STORAGE_NAME, hashBase64);	
	    
	    return authorization;
		
	}
	
}
