package com.application.usecase.queryMetal;

import com.application.model.Metal;
import com.application.model.MetalValue;
import com.application.repository.Repository;
import com.application.service.GalacticToDecimalMapping;
import com.application.usecase.SymbolNotFoundException;
import com.application.usecase.UseCaseRequestReply;

import java.math.BigDecimal;

public class QueryMetal implements UseCaseRequestReply<QueryMetalRequest, QueryMetalResponse>
{
  private final Repository<Metal,MetalValue> repository;
  private final GalacticToDecimalMapping mapping;

  public QueryMetal(Repository<Metal,MetalValue> repository, GalacticToDecimalMapping mapping)
  {
    this.repository = repository;
    this.mapping = mapping;
  }

  @Override
  public QueryMetalResponse execute(QueryMetalRequest request)
  {
    MetalValue metalValue = repository.findBy(request.metal)
                                .orElseThrow(MetalNotFoundException::new);
    Integer numerosity = mapping.map(request.numerosity)
                                .orElseThrow(SymbolNotFoundException::new);
    BigDecimal calculatedValue = BigDecimal.valueOf(numerosity).multiply(metalValue.value);
    return new QueryMetalResponse(request.metal,
      request.numerosity,calculatedValue);
  }

}
