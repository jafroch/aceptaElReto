package acr.estructuras;

import java.util.List;

import org.simpleframework.xml.Root;

@Root(name="submission")
public class SubmissionsListWSType {
	
	public String prevLink;
	public String nextLink;
	public List<SubmissionWSType> submission;
	
}
