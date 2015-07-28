package acr.estructuras;

import java.util.List;

import org.simpleframework.xml.Root;

@Root(name="submission")
public class SubmissionsListWSType {
	
	public String prevLink;
	public String nextLink;
	public List<SubmissionWSType> submissionlist;
	public List<SubmissionWSType> getSubmissionlis() {
		return submissionlist;
	}
	public void setSubmissionlist(List<SubmissionWSType> submission) {
		this.submissionlist = submission;
	}
	
}
