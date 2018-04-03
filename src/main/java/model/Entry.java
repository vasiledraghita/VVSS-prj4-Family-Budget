package model;

public class Entry {	
	private int value;
	private String typeEntry;//cost or income
	private int idMember;

	public Entry(String typeEntry, int value,int idM){
		this.typeEntry=typeEntry;
		this.value=value;
		this.idMember=idM;
	}
	public void setType(String newType) {
		typeEntry = newType;
	}
	public String getType() {
		return this.typeEntry;
	}

	public void setValue(int newValue) {
		this.value = newValue;
	}
	public int getValue() {
		return this.value;
	}

	public void setIdMember(int newMemberId) {
		this.idMember= newMemberId;
	}

	public int getIdMember() {
		return this.idMember;
	}

	public String toString() {
		String e="idM=" +this.idMember+" " + this.typeEntry + " " + this.value;
		return e;   
	}
}
