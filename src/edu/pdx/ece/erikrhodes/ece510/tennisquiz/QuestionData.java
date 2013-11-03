package edu.pdx.ece.erikrhodes.ece510.tennisquiz;
import java.util.ArrayList;

import android.content.Context;

//simpleton class, create the complete data model of all questions, answers, etc
public class QuestionData {
	private ArrayList<MultiQuestion> mMultiQuestions;
	
	private static QuestionData sQuestionData;
		//private constructor
	private Context mAppContext;
	private String mCorrectAnswer;
	private MultiQuestion multiquestion;
	
	private QuestionData (Context appContext) {
		mAppContext = appContext;
		//create array of questions
		mMultiQuestions = new ArrayList<MultiQuestion>();
		
		
		//create choice array made up of the 4 choices
		int[] choice0 = {R.string.choice01, R.string.choice02, R.string.choice03, R.string.choice04};
		int[] choice1 = {R.string.choice11, R.string.choice12, R.string.choice13, R.string.choice14};
		int[] choice2 = {R.string.choice21, R.string.choice22, R.string.choice23, R.string.choice24};
		int[] choice3 = {R.string.choice31, R.string.choice32, R.string.choice33, R.string.choice34};
		int[] choice4 = {R.string.choice41, R.string.choice42, R.string.choice43, R.string.choice44};
		
		
		//Passed answer not by number but by the correct choice
		mCorrectAnswer = multiquestion.getAnswer();
		MultiQuestion temp = new MultiQuestion(R.string.question0,mCorrectAnswer, choice0);		//answer always option 3
		mMultiQuestions.add(temp);
		temp = new MultiQuestion(R.string.question1,mCorrectAnswer, choice1);
		mMultiQuestions.add(temp);
		temp = new MultiQuestion(R.string.question2,mCorrectAnswer, choice2);
		mMultiQuestions.add(temp);
		temp = new MultiQuestion(R.string.question3,mCorrectAnswer, choice3);
		mMultiQuestions.add(temp);
		temp = new MultiQuestion(R.string.question4,mCorrectAnswer, choice4);
		mMultiQuestions.add(temp);
		
	}
	
	public static QuestionData get(Context c) {
		if (sQuestionData == null){
			sQuestionData = new QuestionData(c.getApplicationContext());
		}
		return sQuestionData;
	}
	public ArrayList<MultiQuestion> getQuestions() {
		return mMultiQuestions;
	}
	
	
}

