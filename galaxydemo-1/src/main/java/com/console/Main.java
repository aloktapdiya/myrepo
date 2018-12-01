package com.console;

import com.application.model.Metal;
import com.application.model.MetalValue;
import com.application.model.SymbolMapping;
import com.inmemory.InMemoryMetalValueRepository;
import com.inmemory.InMemorySymbolMappingRepository;
import com.application.repository.Repository;
import com.application.service.GalacticToDecimalMapping;
import com.application.service.RomanToDecimalConverter;
import com.application.usecase.declareMetal.DeclareMetal;
import com.application.usecase.declareSymbol.DeclareSymbol;
import com.application.usecase.queryMetal.QueryMetal;
import com.application.usecase.querySymbol.QuerySymbol;
import com.console.command.DeclareMetalCommand;
import com.console.command.DeclareSymbolCommand;
import com.console.command.QueryMetalCommand;
import com.console.command.QuerySymbolCommand;
import com.console.display.DisplayMetal;
import com.console.display.DisplaySymbol;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

public class Main
{
  public static void main(String[] args)
  {

    Repository<String,SymbolMapping> symbolRepo = new InMemorySymbolMappingRepository(
      new HashMap<>()
    );
    Repository<Metal, MetalValue> metalRepo = new InMemoryMetalValueRepository(
      new HashMap<>()
    );
    RomanToDecimalConverter converter = new RomanToDecimalConverter();
    GalacticToDecimalMapping galacticToDecimalMapping = new GalacticToDecimalMapping(symbolRepo, converter);

    Reader reader;
    try
    {
      if(args.length==1)
      {
        reader = new FileReader(args[0]);
      } else
      {
        System.out.println("Merchant Guide to Galaxy enter value to convert ");
        reader = new InputStreamReader(System.in);
      }

      new ProgramLoop(
        new DeclareSymbolCommand(
          new DeclareSymbol(symbolRepo)
        ),
        new DeclareMetalCommand(
          new DeclareMetal(metalRepo, galacticToDecimalMapping)
        ),
        new QuerySymbolCommand(
          new QuerySymbol(galacticToDecimalMapping),
          new DisplaySymbol(System.out)
        ),
        new QueryMetalCommand(
          new QueryMetal(metalRepo, galacticToDecimalMapping),
          new DisplayMetal(System.out)
        )
      ).run(reader,System.out);
    }
    catch (FileNotFoundException e)
    {
      System.out.println(e.getMessage());
    }
  }
}
