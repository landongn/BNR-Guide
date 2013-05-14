package com.example.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private Button mPrevButton;
	private TextView mQuestionTextView;
	private TextView mCurrentScoreTextView;
	private int mCurrentIndex = 0;
	private int mCurrentScore = 0;
	
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	private static final String KEY_SCORE = "score";
	
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, true),
			new TrueFalse(R.string.question_africa, true),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
	};

	
	private void updateQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void updateScore() {
		mCurrentScoreTextView.setText(mCurrentScore);
	}
	
	private void checkAnswer(boolean userPressedTrue) {
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		 int messageResId =0;
		 if (userPressedTrue == answerIsTrue) {
			 messageResId = R.string.correct_toast;
			 mCurrentScore++;
			 updateScore();
		 } else {
			 messageResId = R.string.incorrect_toast;
			 if(mCurrentScore > 0) {
				 mCurrentScore--;
				 updateScore();
			 }
		 }
		 
		 Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        
        mCurrentScoreTextView = (TextView)findViewById(R.id.point_value);
       
        
        mQuestionTextView = (TextView)findViewById(R.id.question_label);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v){
        		updateQuestion();
        	}
        });
        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		//does nothing yet
        		checkAnswer(true);
        	}
        });
        
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// nothing yet!
				checkAnswer(false);
			}
		});
        
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
        
        mPrevButton = (Button)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		if(mCurrentIndex > 1) {
        			mCurrentIndex = (mCurrentIndex - 1);
        		} else {
        			mCurrentIndex = mQuestionBank.length-1;
        		}
        		updateQuestion();
        	}
        });
        
        
        if(savedInstanceState != null) {
        	mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        	mCurrentScore = savedInstanceState.getInt(KEY_SCORE, 0);
        	mCurrentScoreTextView.setText(mCurrentScore);
        } else {
        	mCurrentScoreTextView.setText("0");
        }
        updateQuestion();
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    	super.onSaveInstanceState(savedInstanceState);
    	Log.i(TAG, "OnSaveInstanceState");
    	savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    	savedInstanceState.putInt(KEY_SCORE, mCurrentScore);
    }
    
    
    
    @Override
    public void onStart() {
    	super.onStart();
    	Log.d(TAG, "onStart() called");
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
    	super.onStop();
    	Log.d(TAG, "onStop() called");
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG, "onDestroy() called");
    }
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }
    
}
