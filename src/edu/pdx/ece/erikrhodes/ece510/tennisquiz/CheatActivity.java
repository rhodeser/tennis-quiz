package edu.pdx.ece.erikrhodes.ece510.tennisquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/* This activity receives the intent of quizActivity along with the 
 * 
 */

public class CheatActivity extends Activity {
	public static final String EXTRA_ANSWER_SHOWN="edu.pdx.ece.erikrhodes.ece510.tennisquiz.answer_shown";
	public static String USER_ANSWER_STRING;
	private String mAnswerString;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	
	private void setAnswerShownResult(boolean isAnswerShown){
		//Creates new intent object and whether the user cheated
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// create activity and inflate view
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		setAnswerShownResult(false);
		mAnswerString = getIntent().getStringExtra(USER_ANSWER_STRING);		//store the answer (sent as an extra from QuizActivity)
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener(){		
			//when showAnswer button is clicked, display the string and record that the user cheated
			@Override
			public void onClick(View v) {
				mAnswerTextView.setText(mAnswerString);
				setAnswerShownResult(true);
			}
		});	
	}
}
