package com.application.usecase.queryMetal;

import com.application.model.GalacticSymbol;
import com.application.model.Metal;

import java.math.BigDecimal;

public class QueryMetalResponse
{

  public final Metal metal;
  public final GalacticSymbol numerosity;
  public final BigDecimal value;

  public QueryMetalResponse(Metal metal, GalacticSymbol numerosity, BigDecimal value)
  {
    this.metal = metal;
    this.numerosity = numerosity;
    this.value = value;
  }

}
