package com.console.command;

import com.application.model.GalacticSymbol;
import com.application.usecase.SymbolNotFoundException;
import com.application.usecase.querySymbol.QuerySymbol;
import com.application.usecase.querySymbol.QuerySymbolResponse;
import com.console.display.DisplaySymbol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuerySymbolCommand implements Command
{
  private final Pattern pattern = Pattern.compile("how\\smuch\\sis\\s([a-zA-Z\\s]+)\\?");

  private final QuerySymbol useCase;
  private final DisplaySymbol displaySymbol;

  public QuerySymbolCommand(QuerySymbol useCase, DisplaySymbol displaySymbol)
  {
    this.useCase = useCase;
    this.displaySymbol = displaySymbol;
  }

  @Override
  public boolean recognize(String commandPattern)
  {
    return pattern.matcher(commandPattern).matches();
  }

  @Override
  public void execute(String commandPattern)
  {
    GalacticSymbol galacticSymbol = parse(commandPattern);
    try
    {
      QuerySymbolResponse response = useCase.execute(galacticSymbol);
      displaySymbol.show(response);
    } catch (SymbolNotFoundException symbol)
    {
      displaySymbol.symbolNotFound();
    }
  }

  private GalacticSymbol parse(String commandPattern)
  {
    Matcher matcher = pattern.matcher(commandPattern);
    matcher.matches();
    String symbolName = matcher.group(1);
    String[] token = symbolName.split(" ");
    return new GalacticSymbol(token);
  }
}
