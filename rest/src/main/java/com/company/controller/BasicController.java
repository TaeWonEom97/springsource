package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.SampleDTO;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class BasicController {
	
	
	@GetMapping("/hello")
	public String hello() {
		log.info("hello");
		return "Hello World";
	}
	
	@GetMapping(value="/sample.xml",produces= {MediaType.APPLICATION_XML_VALUE})
	public SampleDTO sampleXml() {
		log.info("sample");
		SampleDTO sample = new SampleDTO();
		sample.setFirstName("hong");
		sample.setLastName("dong");
		return sample;
	}
	
	@GetMapping(value="/sample.json",produces= {MediaType.APPLICATION_JSON_VALUE})
	public SampleDTO sampleJson() {
		log.info("sample");
		SampleDTO sample = new SampleDTO();
		sample.setFirstName("hong");
		sample.setLastName("dong");
		return sample;
	}
	
	@GetMapping(value="/list",produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<SampleDTO> sampleJsonList() {
		log.info("sample");
		List<SampleDTO> list = new ArrayList<SampleDTO>();
		for(int i=0;i<10;i++) {
			SampleDTO sample = new SampleDTO();
			sample.setFirstName("hong");
			sample.setLastName("dong");
			list.add(sample);
		}
		return list;
	}
}
