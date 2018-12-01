package com.application.usecase.querySymbol;

import com.application.model.GalacticSymbol;
import com.application.service.GalacticToDecimalMapping;
import com.application.usecase.SymbolNotFoundException;
import com.application.usecase.UseCaseRequestReply;

public class QuerySymbol implements UseCaseRequestReply<GalacticSymbol,QuerySymbolResponse>
{

  private final GalacticToDecimalMapping mapping;

  public QuerySymbol(GalacticToDecimalMapping mapping)
  {
    this.mapping = mapping;
  }

  public QuerySymbolResponse execute(GalacticSymbol request)
  {
    Integer value = mapping.map(request).orElseThrow(SymbolNotFoundException::new);
    return new QuerySymbolResponse(request, value);
  }

}
