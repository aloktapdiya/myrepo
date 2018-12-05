package com.application.service;

import com.application.model.RomanNumber;
import org.junit.Before;

public class RomanToDecimalConverterTestBase
{

  protected RomanToDecimalConverter romanToDecimalConverter;

  @Before
  public void setUp()
  {
    romanToDecimalConverter = new RomanToDecimalConverter();
  }
}
