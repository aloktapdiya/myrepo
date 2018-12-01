package com.application.usecase.declareSymbol;

import com.application.model.SymbolMapping;
import com.application.repository.Repository;
import com.application.usecase.UseCaseRequest;

public class DeclareSymbol implements UseCaseRequest<SymbolMapping>
{

  private final Repository<String,SymbolMapping> repository;

  public DeclareSymbol(Repository<String,SymbolMapping> repository)
  {
    this.repository = repository;
  }

  public void execute(SymbolMapping mapping)
  {
    repository.add(mapping);
  }

}
