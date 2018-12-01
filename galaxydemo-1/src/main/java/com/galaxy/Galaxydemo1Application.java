package com.galaxy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.application.model.Metal;
import com.application.model.MetalValue;
import com.application.model.SymbolMapping;
import com.application.repository.Repository;
import com.application.service.GalacticToDecimalMapping;
import com.application.service.RomanToDecimalConverter;
import com.application.usecase.declareMetal.DeclareMetal;
import com.application.usecase.declareSymbol.DeclareSymbol;
import com.application.usecase.queryMetal.QueryMetal;
import com.application.usecase.querySymbol.QuerySymbol;
import com.console.ProgramLoop;
import com.console.command.DeclareMetalCommand;
import com.console.command.DeclareSymbolCommand;
import com.console.command.QueryMetalCommand;
import com.console.command.QuerySymbolCommand;
import com.console.display.DisplayMetal;
import com.console.display.DisplaySymbol;
import com.inmemory.InMemoryMetalValueRepository;
import com.inmemory.InMemorySymbolMappingRepository;

@SpringBootApplication
public class Galaxydemo1Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Galaxydemo1Application.class, args);
		
	}
}
