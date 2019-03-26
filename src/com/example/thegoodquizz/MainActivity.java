package com.example.thegoodquizz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    //member variables
	Button	mTrueButton;
	Button mFalseButton;
	TextView mQuestionText;
	TextView mScoreTextView;
	ProgressBar mProgressBar;
	int mQuestion;
	int mScore=0;
	int mIndex=0;
	
	
	//question bank
	private TrueFalse [] QuestionBank=new TrueFalse[]{
		new TrueFalse(R.string.question_1, true),
		new TrueFalse(R.string.question_2,true),
		new TrueFalse(R.string.question_3,true),
		new TrueFalse(R.string.question_4,true),
		new TrueFalse(R.string.question_5,true),
		new TrueFalse(R.string.question_6,false),
		new TrueFalse(R.string.question_7,true),
		new TrueFalse(R.string.question_8,false),
		new TrueFalse(R.string.question_9,true),
		new TrueFalse(R.string.question_10,true),
		new TrueFalse(R.string.question_11,false),
		new TrueFalse(R.string.question_12,false),
		new TrueFalse(R.string.question_13,true)
	};
	
	final int PROGRESS_BAR_INCREMENT=(int)(Math.ceil(100.0/QuestionBank.length));
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if(savedInstanceState!=null){
        	mScore=savedInstanceState.getInt("ScoreKey");
        	mIndex=savedInstanceState.getInt("IndexKey");
        }else{
        	mScore=0;
        	mIndex=0;
        }
        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mQuestionText= (TextView)findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        
        mQuestion= QuestionBank[mIndex].getmQuestionID();
        mQuestionText.setText(mQuestion);
        mScoreTextView.setText("Eskò "+mScore+"/"+QuestionBank.length);
        
        mTrueButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checkAnswer(true);
			updateQuestion();
			}
		});
        
        mFalseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checkAnswer(false);
				updateQuestion();
			}
		});
        
        
    }
 
    private void updateQuestion(){
    	mIndex= (mIndex+1)%QuestionBank.length;
    	
    	if(mIndex==0){
    		AlertDialog.Builder alert=new Builder(this);
    		alert.setTitle("Game Over");
    		alert.setCancelable(false);
    		alert.setMessage("Eskò "+mScore+"/"+QuestionBank.length);
    		alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
    		alert.show();
    	}
    	mQuestion=QuestionBank[mIndex].getmQuestionID();
    	mQuestionText.setText(mQuestion);
    	mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
    	mScoreTextView.setText("Eskò "+mScore+"/"+QuestionBank.length);
    }
    
    private void checkAnswer(boolean userAnswer){
    	boolean correctAnswer=QuestionBank[mIndex].ismAnswer();
    	
    	if(userAnswer==correctAnswer){
    		Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
    	mScore++;
    	}else{
    		Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
    	}
    }
    
    public void onSaveInstanceState(Bundle outState){
    	outState.putInt("ScoreKey", mScore);
    	outState.putInt("IndexKey", mIndex);
    }
}
