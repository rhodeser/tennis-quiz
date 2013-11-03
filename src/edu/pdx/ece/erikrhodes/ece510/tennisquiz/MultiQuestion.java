package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

//MultiQuestion is a class that creates a single question event (question, answer, possible choices)

public class MultiQuestion {
	private int mQuestion;
	private String mAnswer;
	private int mChoice[];
	
	//create and set, don't create the objects. given already
	//could delete
	public MultiQuestion (int Question, String Answer, int Choice[]){
		mQuestion = Question;
		mAnswer =  Answer;
		mChoice =  Choice;
	}

	//public MultiQuestion(int question0, String choice03, int[] choice0) {
		// TODO Auto-generated constructor stub
	//}

	//can use getters and setters separately
	//when someone wants the question, they call the method, you return the question
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