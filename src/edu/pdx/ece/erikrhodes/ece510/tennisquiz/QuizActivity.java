
package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*This class controls all the data and provides the logic for the sequence of events
 */

public class QuizActivity extends Activity {

	private Button mOKButton;
	private Button mCheatButton;
	private ImageButton mNextButton;
	private MultiQuestion multiquestion;
	private TextView mQuestionTextView;
	private RadioButton mQuestionRadioButton1;
	private RadioButton mQuestionRadioButton2;
	private RadioButton mQuestionRadioButton3;
	private RadioButton mQuestionRadioButton4;
	private RadioGroup mQuestionRadioGroup;
	private QuestionData mQuestionBank;
	private static final String KEY_INDEX = "index";			//Strings used for saving data when Activity is destroyed
	private static final String KEY_CHEATER = "cheater";
	private static final String KEY_ANSWERED = "answered";
	private ArrayList<MultiQuestion> mQuestionList;
	private int mCurrentIndex = 0;
	private boolean mIsCheater;
	private boolean mAnswered = false;
	private static final String TAG = "QuizActivity";
	
	
	private void updateQuestion() {
		/* This method is called to change to the data to the appropriate question.
		 * it is used when the next button has been hit or on the initial inflation of the view
		 */
	
		//get the current index and use it to pull the new question data into the textview
		multiquestion = mQuestionList.get(mCurrentIndex);
		mQuestionTextView.setText(multiquestion.getQuestion());			//set current question
		mQuestionRadioButton1.setText(multiquestion.getChoice()[0]);	//set current choices
		mQuestionRadioButton2.setText(multiquestion.getChoice()[1]);
		mQuestionRadioButton3.setText(multiquestion.getChoice()[2]);
		mQuestionRadioButton4.setText(multiquestion.getChoice()[3]);
		mQuestionRadioGroup.clearCheck();
		mQuestionRadioButton1.setEnabled(true);					//enable the Radio, OK, and Cheat buttons
		mQuestionRadioButton2.setEnabled(true);
		mQuestionRadioButton3.setEnabled(true);
		mQuestionRadioButton4.setEnabled(true);
		mOKButton.setEnabled(true);
		mCheatButton.setEnabled(true);
		mNextButton.setEnabled(false);		
		}
			
	private void checkAnswer(int userChoice) {
		//Uses the user's selection Resource ID to check which choice has been selected.  
		//Checks if the answer is correct, and sets the toast value accordingly
		
		String correctAnswer = mQuestionList.get(mCurrentIndex).getAnswer();
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

	Toast.makeText(this, messageToDisplay, Toast.LENGTH_SHORT)
		.show();
	}
	
	//Default method overridden to keep track of whether the user has cheated or not
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_quiz);
		
		//Create Singleton
		mQuestionList = QuestionData.get(this).getQuestions();
		mQuestionRadioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		mQuestionRadioButton1 = (RadioButton)findViewById(R.id.radio0);
		mQuestionRadioButton2 = (RadioButton)findViewById(R.id.radio1);
		mQuestionRadioButton3 = (RadioButton)findViewById(R.id.radio2);
		mQuestionRadioButton4 = (RadioButton)findViewById(R.id.radio3);

	//grab the object TextView by ID, casting it. 
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
	//retrieve button by R.id and set Button to inflated widget of type view (cast to type Button). 
		mOKButton = (Button)findViewById(R.id.ok_button);
	//listen for event, argument takes object that implements OnClickListener
		mOKButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int userChoice;
				mAnswered = true;			//When the OK button has been clicked, remember this state for layout switch
				userChoice = mQuestionRadioGroup.getCheckedRadioButtonId();
				if (mQuestionRadioGroup.getCheckedRadioButtonId() != -1){		//if there is a selection, disable the radio buttons
					mQuestionRadioButton1.setEnabled(false);
					mQuestionRadioButton2.setEnabled(false);
					mQuestionRadioButton3.setEnabled(false);
					mQuestionRadioButton4.setEnabled(false);
					mOKButton.setEnabled(false);							
					mCheatButton.setEnabled(false);
					mNextButton.setEnabled(true);			//enable the next button, and record that the user answered
					mAnswered = true;
					checkAnswer(userChoice);
				}
			}
		});
	
	mNextButton = (ImageButton)findViewById(R.id.next_button);	
	mNextButton.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
						//When the next button is clicked, increment the current index to be used for the new textview
			mCurrentIndex = (mCurrentIndex + 1) % 6;
			mIsCheater = false;					//clear the user's cheat record
			updateQuestion();					//advance view to next question
			}
			});
	mNextButton.setEnabled(false);
	mCheatButton = (Button)findViewById(R.id.cheat_button);
	mCheatButton.setOnClickListener(new View.OnClickListener() {
				//Check if Cheat button has been clicked
		@Override
		public void onClick(View v) {
			Intent i = new Intent(QuizActivity.this, CheatActivity.class);
			String answerString = multiquestion.getAnswer();		//get the correct answer string and pass it to the Cheat activity
			i.putExtra(CheatActivity.USER_ANSWER_STRING, answerString);
			startActivityForResult(i, 0);
			}
		});
	if (savedInstanceState != null){
				//if we have saved the state, get the values saved
        mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        mIsCheater = savedInstanceState.getBoolean(KEY_CHEATER, false);
        mAnswered = savedInstanceState.getBoolean(KEY_ANSWERED,false);
		if (mAnswered){								//disable the radio buttons if we see that the question has been answered already
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
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);		//save the question index, if they cheated, and if they've answered the current question
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
