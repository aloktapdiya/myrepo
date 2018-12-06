package com.application.service;

import com.application.model.RomanNumber;
import org.junit.Test;

import static org.junit.Assert.fail;


public class RomanToDecimalConverter1StConstraintTest extends RomanToDecimalConverterTestBase
{

  @Test
  public void tooManyTimesInSuccessionForRomanDigits()
  {
    RomanNumber[] list = new RomanNumber[]{
      new RomanNumber("IIII"),
      new RomanNumber("XXXX"),
      new RomanNumber("CCCC"),
      new RomanNumber("MMMM")
    };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException ex)
      {}
    }
  }

  @Test
  public void repetitionOfSpecificDigits()
  {
    RomanNumber[] list = new RomanNumber[]{
      new RomanNumber("DD"),
      new RomanNumber("LL"),
      new RomanNumber("VV"),
    };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException ex)
      {}
    }
  }
}
