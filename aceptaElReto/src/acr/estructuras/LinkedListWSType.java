package acr.estructuras;

import java.util.List;


import org.simpleframework.xml.Element;

public class LinkedListWSType {
	public String nextLink;
	public String prevLink;
	public List internalList;
	
	private int size;
	private int start;
	
	@Element(name="nextLink")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}

	@Element(name="prevLink")
	public String getPrevLink() {
		return prevLink;
	}

	public void setPrevLink(String prevLink) {
		this.prevLink = prevLink;
	}

	public void setList(List list) {
		this.internalList = list;
	}
	public void autoSize(){
		this.size=this.internalList.size();
	}
	public void autoStart(){
		String startString="";
		int pos = this.prevLink.indexOf("start=");
		pos = pos+6;
		while(Integer.getInteger(this.prevLink.substring(pos, pos+1))!=null){
			startString=startString+this.prevLink.substring(pos, pos+1);
			pos++;
		}
		this.start=Integer.parseInt(startString);
	}
	
}
