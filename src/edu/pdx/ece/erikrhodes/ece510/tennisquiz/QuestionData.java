/* Class which creates the array of questions
 */

package edu.pdx.ece.erikrhodes.ece510.tennisquiz;
import java.util.ArrayList;

import android.content.Context;

//singleton class, create the complete data model of all questions, answers, etc
public class QuestionData {
	private ArrayList<MultiQuestion> mMultiQuestionsGroup;
	
	private static QuestionData sQuestionData;
		//private constructor
	private QuestionData (Context appContext) {
		//create array of questions
		mMultiQuestionsGroup = new ArrayList<MultiQuestion>();
		
		//create choice array made up of the 4 choices
		int[] choice0 = {R.string.choice01, R.string.choice02, R.string.choice03, R.string.choice04};
		int[] choice1 = {R.string.choice11, R.string.choice12, R.string.choice13, R.string.choice14};
		int[] choice2 = {R.string.choice21, R.string.choice22, R.string.choice23, R.string.choice24};
		int[] choice3 = {R.string.choice31, R.string.choice32, R.string.choice33, R.string.choice34};
		int[] choice4 = {R.string.choice41, R.string.choice42, R.string.choice43, R.string.choice44};
		int[] choice5 = {R.string.choice51, R.string.choice52, R.string.choice53, R.string.choice54};
		
		//Passed answer not by Resource ID but by the correct String
		MultiQuestion temp = new MultiQuestion(R.string.question0,"Great Britain", choice0);
		mMultiQuestionsGroup.add(temp);			//assemble the question object and add it to the array
		temp = new MultiQuestion(R.string.question1, "Wood", choice1);
		mMultiQuestionsGroup.add(temp);
		temp = new MultiQuestion(R.string.question2,"11 hours 5 minutes", choice2);
		mMultiQuestionsGroup.add(temp);
		temp = new MultiQuestion(R.string.question3,"Golden Set", choice3);
		mMultiQuestionsGroup.add(temp);
		temp = new MultiQuestion(R.string.question4,"163.4 mph", choice4);
		mMultiQuestionsGroup.add(temp);
		temp = new MultiQuestion(R.string.question5,"470", choice5);
		mMultiQuestionsGroup.add(temp);
		
	}
	
	public static QuestionData get(Context appContext) {
		//Method to access the singleton
		if (sQuestionData == null){
			sQuestionData = new QuestionData(appContext.getApplicationContext());	//makes sure we're using the long term context
		}
		return sQuestionData;
	}
	public ArrayList<MultiQuestion> getQuestions() {
		return mMultiQuestionsGroup;
	}
	
}

