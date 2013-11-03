package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

public class MultiQuestion {
	private int mQuestion;
	private int mAnswer;
	private int mChoice[];
	
	//create and set, don't create the objects. given already
	//could delete
	public MultiQuestion (int Question, int Answer, int Choice[]){
		mQuestion = Question;
		mAnswer =  Answer;
		mChoice =  Choice;
	}

	//can use getters and setters separately
	
	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}

	public int getAnswer() {
		return mAnswer;
	}

	public void setAnswer(int answer) {
		mAnswer = answer;
	}

	public int[] getChoice() {
		return mChoice;
	}

	public void setChoice(int choice[]) {
		mChoice = choice;
	}
	
}