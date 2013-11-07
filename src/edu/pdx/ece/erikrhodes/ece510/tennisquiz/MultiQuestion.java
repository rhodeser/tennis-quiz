package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

//MultiQuestion is a class that creates a single question event (question, answer, possible choices)

public class MultiQuestion {
	private int mQuestion;
	private String mAnswer;
	private int mChoice[];
	
	//create and set, don't create the objects. given already

	public MultiQuestion (int Question, String Answer, int Choice[]){
		mQuestion = Question;
		mAnswer =  Answer;
		mChoice =  Choice;
	}

	public int getQuestion() {
		return mQuestion;
	}
		//when question needs to be set, give it the question needing to be set
	public void setQuestion(int question) {
		mQuestion = question;
	}

	public String getAnswer() {
		return mAnswer;
	}

	public void setAnswer(String answer) {
		mAnswer = answer;
	}

	public int[] getChoice() {
		return mChoice;
	}

	public void setChoice(int choice[]) {
		mChoice = choice;
	}
	
}