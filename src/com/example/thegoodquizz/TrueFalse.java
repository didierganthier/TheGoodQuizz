package com.example.thegoodquizz;

public class TrueFalse {

	private int mQuestionID;
	private boolean mAnswer;
	
	public TrueFalse(int questionRessource,boolean questionAnswer){
		mQuestionID=questionRessource;
		mAnswer=questionAnswer;
	}

	public int getmQuestionID() {
		return mQuestionID;
	}

	public void setmQuestionID(int mQuestionID) {
		this.mQuestionID = mQuestionID;
	}

	public boolean ismAnswer() {
		return mAnswer;
	}

	public void setmAnswer(boolean mAnswer) {
		this.mAnswer = mAnswer;
	}
	
	
}
