package acr.estructuras;

import java.util.List;

import org.simpleframework.xml.Root;

@Root(name="submission")
public class ProblemListWSType {
	
	public String prevLink;
	public String nextLink;
	public List<ProblemWSType> problemlist;
	public List<ProblemWSType> getSubmissionlis() {
		return problemlist;
	}
	public void setSubmissionlist(List<ProblemWSType> problem) {
		this.problemlist = problem;
	}
	
}