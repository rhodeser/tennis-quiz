package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

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
	private int mCurrentIndex = 0;
	private boolean mIsCheater;
	private static final String TAG = "QuizActivity";
	private void updateQuestion() {
		//Log.d(TAG, "Updating question text for question #" +mCurrentIndex, new Exception());
		//int question = R.string.question0;
	//	multiquestion = mQuestionBank.getQuestions().get(mCurrentIndex);
	//	mQuestionTextView.setText(multiquestion.getQuestion());
		//mQuestionRadioButton1.setText(multiquestion.getChoice());
		
		
		mQuestionTextView.setText(R.string.question0);
		mQuestionRadioButton1.setText(R.string.choice01);
		mQuestionRadioButton2.setText(R.string.choice02);
		mQuestionRadioButton3.setText(R.string.choice03);
		mQuestionRadioButton4.setText(R.string.choice04);
//TODO: Index through questions to display new content

		
			}
	private void checkAnswer(String userChoice) {
		String correctAnswer = multiquestion.getAnswer();	//Get correct answer
		int messageToDisplay = 0;
	
	if (mIsCheater) {
		messageToDisplay = R.string.judgment_toast;
		}
	else {
		if  (userChoice == correctAnswer) {
			messageToDisplay = R.string.correct_toast;
		}else {
			messageToDisplay = R.string.incorrect_toast;
		}
	}
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
		//
		super.onCreate(savedInstanceState);
		
		//Log.d(TAG, "onCreate(Bundle) called");
		setContentView(R.layout.activity_quiz);
		//grab the object TextView by ID, casting it. 
		//create the simpleton here
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
	
		mQuestionRadioButton1 = (RadioButton)findViewById(R.id.radio0);
		mQuestionRadioButton2 = (RadioButton)findViewById(R.id.radio1);
		mQuestionRadioButton3 = (RadioButton)findViewById(R.id.radio2);
		mQuestionRadioButton4 = (RadioButton)findViewById(R.id.radio3);
		
	//retrieve button by R.id and set Button to inflated widget of type view, cast to type Button. 
		mOKButton = (Button)findViewById(R.id.ok_button);
	//listen for event, argument takes object that implements OnClickListener)
	//This is a new nameless class, usually listeners are
		mOKButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			//getchecked or selected on radiogroup, 
			//can check resource ids, 
			// call checkAnswer(userChoice);
			}
			});
	
	
	mNextButton = (Button)findViewById(R.id.next_button);	
	mNextButton.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
		//	mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.getQuestions().size();
		//	index by 5
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
			boolean answerIsTrue = true;
			i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
			startActivityForResult(i, 0);
			}
		});
		updateQuestion();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

}
