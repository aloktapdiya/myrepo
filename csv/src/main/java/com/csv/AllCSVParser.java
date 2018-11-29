package com.csv;

import java.io.IOException;



/**
 *  methods common to all types 
 */

public interface AllCSVParser {


    char DEFAULT_SEPARATOR = ',';
    char DEFAULT_QUOTE_CHARACTER = '"';
    char DEFAULT_ESCAPE_CHARACTER = '\\';
    char getSeparator();
    char getQuotechar();
    int READ_BUFFER_SIZE = 128;
    String[] parseLineMulti(String nextLine) throws IOException;

    String[] parseLine(String nextLine) throws IOException;

}