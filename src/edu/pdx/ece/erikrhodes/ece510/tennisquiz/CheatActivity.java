package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
		//insure it comes from the correct activity
	//public static final String EXTRA_ANSWER_IS_TRUE = "edu.pdx.ece.erikrhodes.ece510.tennisquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN="edu.pdx.ece.erikrhodes.ece510.tennisquiz.answer_shown";
	private boolean mAnswerIsTrue;
	public static String EXTRA_ANSWER_IS_TRUE;
	private String mAnswerString;
	private TextView mAnswerTextView;
	//create String for correct answer
	private Button mShowAnswer;
	
	private void setAnswerShownResult(boolean isAnswerShown){
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		setAnswerShownResult(false);
		//getStringExtra 
		//pass the string/int answer along with this intent, then use it
	//	mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mAnswerString = getIntent().getStringExtra(EXTRA_ANSWER_IS_TRUE);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener(){
			@Override
			//if they want to cheat, set answer to correct one
			public void onClick(View v) {
				//if (mAnswerIsTrue) {
				//	mAnswerTextView.setText(R.string.true_button);
			//	}
			//	else {
			//		mAnswerTextView.setText(R.string.false_button);
			//	}
				//set my answer here as text, not toast
				//just display the string, no logic
				mAnswerTextView.setText(mAnswerString);
				setAnswerShownResult(true);
			}
		});	
	}
}
