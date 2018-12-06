package com.application.service;

import com.application.model.RomanNumber;
import org.junit.Test;

import static org.junit.Assert.fail;


public class RomanToDecimalConverter2ndConstraintTest extends RomanToDecimalConverterTestBase
{

  @Test
  public void checkIcanBeSubtractedFromVandXonly()
  {
    RomanNumber[] list = new RomanNumber[]
    {
      new RomanNumber("IL"),
      new RomanNumber("IC"),
      new RomanNumber("ID"),
      new RomanNumber("IM"),
      new RomanNumber("IL")
    };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }

  @Test
  public void checkXcanBeSubtractedFromLandConly()
  {
    RomanNumber[] list = new RomanNumber[]
    {
      new RomanNumber("XD"),
      new RomanNumber("XM"),
    };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }

  @Test
  public void checkVneverBeSubtracted()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("VX"),
        new RomanNumber("VL"),
        new RomanNumber("VC"),
        new RomanNumber("VD"),
        new RomanNumber("VM"),
      };

    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }

  @Test
  public void checkLneverBeSubtracted()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("LC"),
        new RomanNumber("LD"),
        new RomanNumber("LM"),
      };

    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }

  @Test
  public void checkDneverBeSubtracted()
  {
    try
    {
      romanToDecimalConverter.convert(new RomanNumber("DM"));
      fail();
    }
    catch (InvalidRomanNumberException e)
    {}
  }

}
