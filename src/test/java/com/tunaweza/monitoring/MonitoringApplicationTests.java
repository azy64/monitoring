package com.tunaweza.monitoring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.services.CompanyService;

@SpringBootTest
class MonitoringApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private CompanyService companyService;

	@BeforeEach
	public void setup(){
		
	}
	@Test
	void contextLoads() {
		Company company = new Company();
		company.setActivated(true);
		company.setAddress("19BIS RUE DU FAUBOURGS");
		company.setEmail("allysaidi64@gmail.com");
		company.setSiret("10990993933");
		company.setReferenceNumber("#2323232233");
		when(companyService.save(company)).thenReturn(company);
		try {
			mockMvc.perform(post("/users/company").contentType(MediaType.APPLICATION_JSON)
			);
			assertNotNull(company.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
