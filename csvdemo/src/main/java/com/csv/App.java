package com.csv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.csv.domainobject.CsvDomainData;
import com.csv.util.CSVParser;
import com.csv.util.CSVReader;


@Component
public class App implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		
		String file = "src/main/resources/csvdata.csv";
		List<String> ord = new ArrayList<String>();
		ord.add("id");
		ord.add("key");
		ord.add("value");
		
		CSVReader<CsvDomainData> reader = new CSVReader<CsvDomainData>(CsvDomainData.class, file, true)
				.setOrder(ord)
				.read()
				.parse(new CSVParser());
		
		for(CsvDomainData msg :reader.getData()) {
			System.out.println(msg);
		}		
	}

}