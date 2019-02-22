package com.casestudy.searchmetrics.exchangerate.builder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.casestudy.searchmetrics.util.GenericServiceConstant;
/**
 * Generate Signature for calling third party exchange rate.
 * @author Admin
 *
 */
@Component
public class ExchangeRateSignatureBuilder {

	@Value("${api.key}")
	String apiKey;
	
	@Value("${api.secret}")
	String apiSecret;
	
	public  String getSignature() throws Exception {
		long timestamp = System.currentTimeMillis() / 1000L;
		String payload = timestamp + "." + apiKey;
		Mac sha256_Mac = Mac.getInstance(GenericServiceConstant.HMACSH256);
		SecretKeySpec secretKeySpec = new SecretKeySpec(apiSecret.getBytes(), GenericServiceConstant.HMACSH256);
		sha256_Mac.init(secretKeySpec);
		String hashHex = DatatypeConverter.printHexBinary(sha256_Mac.doFinal(payload.getBytes())).toLowerCase();
		String signature = payload + "." + hashHex;
		return signature;
	}
}
