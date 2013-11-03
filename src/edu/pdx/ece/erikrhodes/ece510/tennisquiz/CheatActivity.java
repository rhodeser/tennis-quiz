package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	public static final String EXTRA_ANSWER_IS_TRUE = "edu.pdx.ece.erikrhodes.ece510.tennisquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN="edu.pdx.ece.erikrhodes.ece510.tennisquiz.answer_shown";
	private boolean mAnswerIsTrue;
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
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
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
				setAnswerShownResult(true);
			}
		});	
	}
}
