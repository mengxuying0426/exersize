package com.example.entitys2;

import java.util.List;

public class BankInfo {
	List<QuestionBank> questionBanks;

	public List<QuestionBank> getQuestionBanks() {
		return questionBanks;
	}

	public void setQuestionBanks(List<QuestionBank> questionBanks) {
		this.questionBanks = questionBanks;
	}

	@Override
	public String toString() {
		return "BankInfo [questionBanks=" + questionBanks + "]";
	}
	
	
}