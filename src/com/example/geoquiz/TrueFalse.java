package com.example.geoquiz;

public class TrueFalse {
	private int mQuestion;
	private boolean mTrueQuestion;
	
	public TrueFalse(int question, boolean trueQuestion){
		setQuestion(question);
		setTrueQuestion(trueQuestion);
	}

	/**
	 * @return the trueQuestion
	 */
	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}

	/**
	 * @param trueQuestion the trueQuestion to set
	 */
	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}

	/**
	 * @return the question
	 */
	public int getQuestion() {
		return mQuestion;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(int question) {
		mQuestion = question;
	}
}
