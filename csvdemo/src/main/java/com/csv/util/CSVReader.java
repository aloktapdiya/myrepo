package com.csv.util;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CSVReader<T> {
	
	private String seperator;
	private  Class<T> genericType;
	private List<T> data;
	
	private List<String> order;
	private List<String> headers;
	
	private String file;
	
	private boolean initCompleted;
	
	private boolean hasHeader;
	private Map<String, Field> privateFields = new LinkedHashMap<String, Field>();
	
	private void initialize() {
		if (!this.initCompleted) {
			Field[] allFields = genericType.getDeclaredFields();
			for (Field field : allFields) {
				if (Modifier.isPrivate(field.getModifiers())) {
					privateFields.put(field.getName(), field);
				}
			}

			try {
				readData();
			} catch (Exception e) {
				this.initCompleted = false;
			}
			this.initCompleted = true;
		}
	}
	
	public CSVReader(final Class<T> type, String file, boolean hasHeader) {
		this.file = file;
		this.hasHeader = hasHeader;
		this.genericType = type;
		this.seperator = ",";
	}
	
	public CSVReader(final Class<T> type, String file, boolean hasHeader, String separator) {
		this.file = file;
		this.hasHeader = hasHeader;
		this.genericType = type;
		this.seperator = separator;
	}
	
	/**
	 * @return the data
	 */
	public List<T> getData() {
		if(null == data) {
			data = new ArrayList<T>();
		}
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
	

	/**
	 * @return the headers
	 */
	public List<String> getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	/**
	 * @return the order
	 */
	public List<String> getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public CSVReader<T> setOrder(List<String> order) {
		this.order = order;
		return this;
	}
	
	public CSVReader<T> read(List<String> order) {
		this.setOrder(order);
		initialize();
		return this;
	}
	
	public CSVReader<T> read() {
		initialize();
		return this;
	}

	private void readData() throws InstantiationException, IllegalAccessException {

		BufferedReader  reader = null;
		String line =null;
		try{  
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				
				List<String> row = Arrays.asList(line.split(seperator));
				
				if (this.hasHeader){
					setHeaders(row);;
					this.hasHeader = false;
					continue;
				}
				
				T refObject = genericType.newInstance();
				int index = 0;				
				
				List<String> listOfFieldNames = (null != getOrder()) ? getOrder() : new ArrayList<String>(privateFields.keySet());
				
				for(String fieldName : listOfFieldNames) {
					if( index >= row.size()) {
						break;
					}
					assign(refObject,privateFields.get(fieldName),row.get(index++));
				}
				getData().add(refObject);
			}
			
			reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch ( IOException e ) { 	
			e.printStackTrace();
		}
		finally{  
			
			try { 
				reader.close(); 
				} 
			catch ( Exception e ) { 
				
			}
		}
	}
	
	private Field assign(T refObject, Field field, String value) throws IllegalArgumentException, IllegalAccessException {
		field.setAccessible(true);
		field.set(refObject, value);
		field.setAccessible(false);
		return field;
	}
	
/*	public CSVReader<T> process(CsvProcessor<T> processsor) {
		if(!this.initCompleted) {
			initialize();
		}
		
		if (null != processsor) {
			List<T> list = getData();
			for (T obj : list) {
				obj = processsor.process(obj);
			}
		}
		return this;
	}*/

}