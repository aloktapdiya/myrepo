package com.csv.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csv.domainobject.CsvDomainData;

public class CSVParser implements AllCSVParser<CsvDomainData> {


	private final char separator=DEFAULT_SEPARATOR;

    private String pending;
    private boolean inField = false;

    protected String[] parseLine(String nextLine, boolean multi) throws IOException {

        if (!multi && pending != null) {
            pending = null;
        }

        if (nextLine == null) {
            if (pending != null) {
                String s = pending;
                pending = null;
                return new String[]{s};
            }
            return null;
        }

        List<String> tokensOnThisLine = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(nextLine.length() + READ_BUFFER_SIZE);
        boolean inQuotes = false;
        if (pending != null) {
            sb.append(pending);
            pending = null;
           // inQuotes = !this.ignoreQuotations;
        }
        // line is done - check status
        if (inQuotes ) {
            if (multi) {
                // continuing a quoted section, re-append newline
                sb.append('\n');
                pending = sb.toString();
                sb = null; // this partial content is not to be added to field list yet
            } else {
                throw new IOException("Un-terminated quoted field at end of CSV line");
            }
            if (inField) {
            }
        } else {
            inField = false;
        }


        return tokensOnThisLine.toArray(new String[tokensOnThisLine.size()]);

    }

	public char getSeparator() {
		// TODO Auto-generated method stub
		return 0;
	}

	public char getQuotechar() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String[] parseLineMulti(String nextLine) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] parseLine(String nextLine) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CsvDomainData parse(CsvDomainData inData) {
		inData.setKey(inData.getKey().toUpperCase());
		return inData;
	}


}