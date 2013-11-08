package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

//MultiQuestion is a class that creates a single question event (question, answer, possible choices)

public class MultiQuestion {
	private int mQuestion;
	private String mAnswer;
	private int mChoice[];
	
	public MultiQuestion (int Question, String Answer, int Choice[]){
		//create a object of type MultiQuestion and initialize its variable values to the arguments passed in
		mQuestion = Question;
		mAnswer =  Answer;			
		mChoice =  Choice;
	}

	public int getQuestion() {
		return mQuestion;
	}

	public String getAnswer() {
		return mAnswer;
	}

	public int[] getChoice() {
		return mChoice;
	}

	
}