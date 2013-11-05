package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private Button mOKButton;
	private Button mCheatButton;
	private Button mNextButton;
	private MultiQuestion multiquestion;
	private TextView mQuestionTextView;
	private RadioButton mQuestionRadioButton1;
	private RadioButton mQuestionRadioButton2;
	private RadioButton mQuestionRadioButton3;
	private RadioButton mQuestionRadioButton4;
	private RadioGroup mQuestionRadioGroup;
	private QuestionData mQuestionBank;
	private static final String KEY_INDEX = "index";
	private static final String KEY_CHEATER = "cheater";
	private static final String KEY_ANSWERED = "answered";
	private ArrayList<MultiQuestion> mQuestionList;
	private int mCurrentIndex = 0;
	private boolean mIsCheater;
	private boolean mAnswered;
	private static final String TAG = "QuizActivity";
	
	
	private void updateQuestion() {
		//Log.d(TAG, "Updating question text for question #" +mCurrentIndex, new Exception());
		//int question = R.string.question0;
	//	multiquestion = mQuestionBank.getQuestions().get(mCurrentIndex);
	//	mQuestionTextView.setText(multiquestion.getQuestion());
		//mQuestionRadioButton1.setText(multiquestion.getChoice());
		
		multiquestion = mQuestionList.get(mCurrentIndex);
		Log.d(multiquestion.getAnswer(), "the answer");
		mQuestionTextView.setText(multiquestion.getQuestion());
		mQuestionRadioButton1.setText(multiquestion.getChoice()[0]);
		mQuestionRadioButton2.setText(multiquestion.getChoice()[1]);
		mQuestionRadioButton3.setText(multiquestion.getChoice()[2]);
		mQuestionRadioButton4.setText(multiquestion.getChoice()[3]);
		mQuestionRadioGroup.clearCheck();
		mQuestionRadioButton1.setEnabled(true);
		mQuestionRadioButton2.setEnabled(true);
		mQuestionRadioButton3.setEnabled(true);
		mQuestionRadioButton4.setEnabled(true);
		mOKButton.setEnabled(true);
		mCheatButton.setEnabled(true);
//TODO: Index through questions to display new content

			}
	private void checkAnswer(int userChoice) {
		String correctAnswer = mQuestionList.get(mCurrentIndex).getAnswer();
		String userString = getString(multiquestion.getChoice()[2]);
		int messageToDisplay = 0;
		

		
		if (userChoice == R.id.radio0){
			if (getString(multiquestion.getChoice()[0]).equals(correctAnswer)) messageToDisplay = R.string.correct_toast;
			else messageToDisplay = R.string.incorrect_toast;
		}
		if (userChoice == R.id.radio1){
			if (getString(multiquestion.getChoice()[1]).equals(correctAnswer)) messageToDisplay = R.string.correct_toast;
			else messageToDisplay = R.string.incorrect_toast;
		}	
		if (userChoice == R.id.radio2){	
			if (getString(multiquestion.getChoice()[2]).equals(correctAnswer)) messageToDisplay = R.string.correct_toast;
			else messageToDisplay = R.string.incorrect_toast;
		}
	    if (userChoice == R.id.radio3){		
	    	if (getString(multiquestion.getChoice()[3]).equals(correctAnswer)) messageToDisplay = R.string.correct_toast;
			else messageToDisplay = R.string.incorrect_toast;
	    }
	    
		if (mIsCheater) {
			messageToDisplay = R.string.judgment_toast;
			}
	    
		//get R.id of all 4 buttons.  if one equal to selected, get that string
//		R.string.choice01
	//	getString(resId)
//		getText(resId)
	//	userString = getString(userChoice);
		Log.d(userString, "is de userString");
		Log.d(correctAnswer, "is de correctAnswer");
		Log.d("Message to display is", Integer.toString(messageToDisplay));
	//Context = instance of Activity (subclass of Context). needed to find string's R.id
	//this refers to anonymous class View.OnClickListener.
	Toast.makeText(this, messageToDisplay, Toast.LENGTH_SHORT)
		.show();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
	}
	
	
	//@override checks that class actually has method that we're overriding
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//create static singleton, then array from there
	
		//Log.d(TAG, "onCreate(Bundle) called");
		setContentView(R.layout.activity_quiz);
		mQuestionList = QuestionData.get(this).getQuestions();
		

		mQuestionRadioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		mQuestionRadioButton1 = (RadioButton)findViewById(R.id.radio0);
		mQuestionRadioButton2 = (RadioButton)findViewById(R.id.radio1);
		mQuestionRadioButton3 = (RadioButton)findViewById(R.id.radio2);
		mQuestionRadioButton4 = (RadioButton)findViewById(R.id.radio3);
	
		//grab the object TextView by ID, casting it. 
		//create the simpleton here
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		//multiquestion.setAnswer(answer)
	//retrieve button by R.id and set Button to inflated widget of type view, cast to type Button. 
		mOKButton = (Button)findViewById(R.id.ok_button);
	//listen for event, argument takes object that implements OnClickListener)
	//This is a new nameless class, usually listeners are
		mOKButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			int userChoice;
			mAnswered = true;
			userChoice = mQuestionRadioGroup.getCheckedRadioButtonId();
			mQuestionRadioButton1.setEnabled(false);
			mQuestionRadioButton2.setEnabled(false);
			mQuestionRadioButton3.setEnabled(false);
			mQuestionRadioButton4.setEnabled(false);
			mOKButton.setEnabled(false);
			mCheatButton.setEnabled(false);
			
		//	Log.d(TAG, "Got HEEEEEEEM", new Exception());
			checkAnswer(userChoice);
		//	Log.d(TAG, Integer.toString(userChoice));
			}
			});
	
	
	mNextButton = (Button)findViewById(R.id.next_button);	
	mNextButton.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
			mCurrentIndex = (mCurrentIndex + 1) % 5;
			//mQuestionList.size()
			mIsCheater = false;
			updateQuestion();
			}
			});
	mCheatButton = (Button)findViewById(R.id.cheat_button);
	mCheatButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(QuizActivity.this, CheatActivity.class);
		//	boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
			String answerString = multiquestion.getAnswer();
			i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerString);
			startActivityForResult(i, 0);
			}
		});
		if (savedInstanceState != null){
        mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        mIsCheater = savedInstanceState.getBoolean(KEY_CHEATER, false);
        //mAnswered = savedInstanceState.getBoolean(KEY_ANSWERED, false);
        mAnswered = savedInstanceState.getBoolean(KEY_ANSWERED);
        if (mAnswered){
			mQuestionRadioButton1.setEnabled(false);
			mQuestionRadioButton2.setEnabled(false);
			mQuestionRadioButton3.setEnabled(false);
			mQuestionRadioButton4.setEnabled(false);
			mOKButton.setEnabled(false);
			mCheatButton.setEnabled(false);	
 
        }
        }
		
		updateQuestion();
	}
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
		savedInstanceState.putBoolean(KEY_CHEATER,  mIsCheater);
		savedInstanceState.putBoolean(KEY_ANSWERED,  mAnswered);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

}
