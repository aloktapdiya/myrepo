/**
 * 
 */
package com.csv.domainobject;


public class CsvDomainData {
	
	private String id;
	
	private String key;
	
	private String value;
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}




	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}





	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getId())
		.append(" | ")
		.append(this.getKey())
		.append(" | ")
		.append(this.getValue());
		return sb.toString();
	}
	
}
