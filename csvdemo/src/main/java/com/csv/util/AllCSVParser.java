package com.csv.util;

import java.io.IOException;

import com.csv.domainobject.CsvDomainData;



/**
 *  methods common to all types of csv
 */

public interface AllCSVParser<T> {


    char DEFAULT_SEPARATOR = ',';
    char getSeparator();
    int READ_BUFFER_SIZE = 128;
    String[] parseLineMulti(String nextLine) throws IOException;
    String[] parseLine(String nextLine) throws IOException;
    public T parse(T inData);
	CsvDomainData parse(CsvDomainData inData);
}