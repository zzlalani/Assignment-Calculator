package com.xaviya.assigncalcu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.xaviya.assigncalcu.*;

public class MainActivity extends Activity {
	
	EditText txtMain;
	boolean screenClear = false;
	
	float lastMemory = 0;
	
	int opr;
	
	Add add = new Add();
	Sub sub = new Sub();
	Mul mul = new Mul();
	Div div = new Div();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtMain = (EditText)findViewById(R.id.txtMain);
		
		Button btn0 = (Button)findViewById(R.id.btn0);
		Button btn1 = (Button)findViewById(R.id.btn1);
		Button btn2 = (Button)findViewById(R.id.btn2);
		Button btn3 = (Button)findViewById(R.id.btn3);
		Button btn4 = (Button)findViewById(R.id.btn4);
		Button btn5 = (Button)findViewById(R.id.btn5);
		Button btn6 = (Button)findViewById(R.id.btn6);
		Button btn7 = (Button)findViewById(R.id.btn7);
		Button btn8 = (Button)findViewById(R.id.btn8);
		Button btn9 = (Button)findViewById(R.id.btn9);
		Button btnDot = (Button)findViewById(R.id.btnDot);
		
		Button btnAdd = (Button)findViewById(R.id.btnAdd);
		Button btnSub = (Button)findViewById(R.id.btnSub);
		Button btnDiv = (Button)findViewById(R.id.btnDiv);
		Button btnMul = (Button)findViewById(R.id.btnMul);
		
		Button btnEq = (Button)findViewById(R.id.btnEq);
		
		Button btnC = (Button)findViewById(R.id.btnC);
		
		Button btnCredits = (Button)findViewById(R.id.btnCredits);
		
		btnCredits.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
				alertDialog.setTitle("Assignment Calculator.");
				alertDialog.setMessage("Created By Zeeshan Lalani" +
						"\nFirst attemp to create an Android application" +
						"\nGiven as assignment");
				alertDialog.setIcon(R.drawable.ic_action2);
				alertDialog.setButton("Close", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
			            dialog.cancel();
			        }
				});
				alertDialog.show();
			}
		});
		
		btn0.setOnClickListener(viewClickListener);
		btn1.setOnClickListener(viewClickListener);
		btn2.setOnClickListener(viewClickListener);
		btn3.setOnClickListener(viewClickListener);
		btn4.setOnClickListener(viewClickListener);
		btn5.setOnClickListener(viewClickListener);
		btn6.setOnClickListener(viewClickListener);
		btn7.setOnClickListener(viewClickListener);
		btn8.setOnClickListener(viewClickListener);
		btn9.setOnClickListener(viewClickListener);
		btnDot.setOnClickListener(viewClickListener);
		
		btnAdd.setOnClickListener(viewClickListener);
		btnSub.setOnClickListener(viewClickListener);
		btnMul.setOnClickListener(viewClickListener);
		btnDiv.setOnClickListener(viewClickListener);
		
		btnEq.setOnClickListener(viewClickListener);
		
		btnC.setOnClickListener(viewClickListener);
		
		txtMain.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int inType = txtMain.getInputType(); // backup the input type
				txtMain.setInputType(InputType.TYPE_NULL); // disable soft input
				txtMain.onTouchEvent(event); // call native handler
				txtMain.setInputType(inType); // restore input type
				return true; // consume touch even
			}
		});
		
	}
	
	public int getOpr() {
		return opr;
	}

	public void setOpr(int opr) {
		/*
		 * 1 = add
		 * 2 = sub
		 * 3 = mul
		 * 4 = div
		 */
		this.opr = opr;
	}

	public float getLastMemory() {
		return lastMemory;
	}

	public void setLastMemory(float lastMemory) {
		this.lastMemory = lastMemory;
	}

	private OnClickListener viewClickListener = new OnClickListener() {
		
		@Override
		public void onClick(final View v) {
			switch(v.getId()){
				case R.id.btn0:
					setScreenText("0");
				break;
				case R.id.btn1:
					setScreenText("1");
				break;
				case R.id.btn2:
					setScreenText("2");
				break;
				case R.id.btn3:
					setScreenText("3");
				break;
				case R.id.btn4:
					setScreenText("4");
				break;
				case R.id.btn5:
					setScreenText("5");
				break;
				case R.id.btn6:
					setScreenText("6");
				break;
				case R.id.btn7:
					setScreenText("7");
				break;
				case R.id.btn8:
					setScreenText("8");
				break;
				case R.id.btn9:
					setScreenText("9");
				break;
				case R.id.btnDot:
					setScreenText(".");
				break;

				case R.id.btnAdd:
					
					setOpr(1);
					calculate(add);
					
				break;
				case R.id.btnSub:
					
					setOpr(2);
					calculate(sub);
					
				break;
				case R.id.btnMul:
					
					setOpr(3);
					calculate(mul);
					
				break;
				case R.id.btnDiv:
					
					setOpr(4);
					calculate(div);
					
				break;

				case R.id.btnEq:
					
					equalClick(getOpr());
					
				break;

				case R.id.btnC:
					clearScreen();
				break;
			}

		}
	};
	
	private void equalClick(int opr) {
		switch (opr) {
			case 1:
				calculate(add);
			break;
			case 2:
				calculate(sub);
			break;
			case 3:
				calculate(mul);
			break;
			case 4:
				calculate(div);
			break;
		}
		
		setLastMemory(0);
	}
	
	private void calculate(Calculation obj) {
		
		float lastMem = getLastMemory();
		float currMem = getScreenText();
		
		if ( lastMem == 0 ) {
			setLastMemory(currMem);
		} else {
			
			float result = obj.action(lastMem, currMem);
			
			setLastMemory(result);
			String sResult = Float.toString(result);
			
			screenClear();
			setScreenText(sResult);
			
		}
		screenClear();
		
	}
	
	private void screenClear() {
		this.screenClear = true;
	}
	
	private void clearScreen() {
		txtMain.setText("");
		screenClear();
		this.lastMemory = 0;
	}
	
	private float getScreenText() {
		String val = txtMain.getText().toString();
		float fVal = Float.parseFloat(val);
		return fVal;
	}
	
	private void setScreenText(String txt) {
		if ( this.screenClear ) {
			txtMain.setText(txt);
			this.screenClear = false;
		} else {
			txtMain.append(txt);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Calculator **/
	
	public class Calculation {
		
		public float action(float a, float b) {
			return 0;
		}
	}
	
	public class Add extends Calculation {
		@Override
		public float action(float a, float b) {
			return a + b;
		}
	}
	
	public class Sub extends Calculation {
		@Override
		public float action(float a, float b) {
			return a - b;
		}
	}
	
	public class Mul extends Calculation {
		@Override
		public float action(float a, float b) {
			return a * b;
		}
	}
	
	public class Div extends Calculation {
		@Override
		public float action(float a, float b) {
			return a / b;
		}
	}

}
