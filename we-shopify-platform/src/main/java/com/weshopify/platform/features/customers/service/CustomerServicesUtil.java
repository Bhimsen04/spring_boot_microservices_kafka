package com.weshopify.platform.features.customers.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weshopify.platform.features.customers.commons.EmailDomainStatus;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServicesUtil {

	@Autowired // we're pulling the bean from the Spring Container
	private RestTemplate restTemplate;

	@Value("${email.domain.validate.api.endPoint}")
	private String emailValidateApi;

	@Value("${email.domain.validate.api.key}")
	private String emailValidateApiKey;

	@Value("${email.domain.validate.api.value}")
	private String emailValidateApiValue;

	public boolean isValidEmailDomain(String email) {
		log.info("Inside isValidEmailDomain() of CustomerServicesUtil");
		boolean isValidEmailDomain = false;

//		HttpHeaders headers = new HttpHeaders();
//		headers.add(emailValidateApiKey, emailValidateApiValue);
//
//		HttpEntity entity = new HttpEntity<>(headers);

		emailValidateApi = emailValidateApi + "?" + emailValidateApiKey + "=" + emailValidateApiValue + "&email="
				+ email;
		log.info("email API Is: {} ", emailValidateApi);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(emailValidateApi, String.class);
		if (responseEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
			String emailDeliveryData = responseEntity.getBody();
			JSONObject jsonData = new JSONObject(emailDeliveryData);
			if (jsonData.has("deliverability")) {
				String deliveryStatus = jsonData.getString("deliverability");
				log.info("email: {} \t is deliverable: {}", email, deliveryStatus);
				if (EmailDomainStatus.DELIVERABLE.getEmailDeliveryStatus().equals(deliveryStatus))
					isValidEmailDomain = true;
			}
		}
		log.info("Is Valid email domain:\t {} ", isValidEmailDomain);
		return isValidEmailDomain;

	}
}
