package org.gozhe.android.cgt.activity.upload;

import java.util.List;

import org.gozhe.android.R;
import org.gozhe.android.cgt.activity.MainActivity;
import org.gozhe.android.cgt.activity.map.MapActivity;
import org.gozhe.android.cgt.entity.QuestionType;
import org.gozhe.android.cgt.service.QuestionTypeService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AddQuestionAActivity extends Activity {

	private Spinner spin_dl, spin_xl;
	private EditText et_address, et_grid, et_compcode, et_describe;
	private RadioButton rb_event, rb_component;
	private GridView gv_photos;
	
	private Button btn_next,btn_openmap,btn_save;

	QuestionTypeService qtService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_question_a);

		initViews();

		qtService= new QuestionTypeService(this);
		
		try {
			loadEvent_DL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initViews() {
		spin_dl = (Spinner) findViewById(R.id.type_sdl);
		spin_xl = (Spinner) findViewById(R.id.type_sxl);
		rb_event = (RadioButton) findViewById(R.id.type_sj);
		rb_component = (RadioButton) findViewById(R.id.type_bj);
		et_address = (EditText) findViewById(R.id.et_address);
		et_grid = (EditText) findViewById(R.id.et_grid);
		et_compcode = (EditText) findViewById(R.id.et_compcode);
		et_describe = (EditText) findViewById(R.id.et_describe);

		btn_openmap=(Button)findViewById(R.id.btn_openmap);
		btn_save =(Button)findViewById(R.id.question_a_btn_save);
		
		rb_event.setSelected(true);
		et_compcode.setEnabled(false);//事件时，部件标识码不可用
	
		rb_event.setOnCheckedChangeListener(this.spinEvemtCheckedListener);
		rb_component.setOnCheckedChangeListener(this.spinCompCheckedListener);
		
		
		btn_openmap.setOnClickListener(this.openMapListener);
		
		btn_save.setOnClickListener(this.saveClickListener);
		
	}
	

	//------------ 地图操作--------------//
	
	private OnClickListener openMapListener=new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), MapActivity.class);
			startActivity(intent);
		}
		
	};
	
	
	//------------ 保存并继续 --------------//
	
	
	private OnClickListener saveClickListener= new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//----------- 下拉框 事件--------------//
	
	private OnCheckedChangeListener spinEvemtCheckedListener = new OnCheckedChangeListener(){
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			try {
				if (isChecked){
					loadEvent_DL();
					et_compcode.setEnabled(false);//事件时，部件标识码不可用
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
	
	private OnCheckedChangeListener spinCompCheckedListener = new OnCheckedChangeListener(){
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				try {
					if (isChecked){
						loadComponent_DL();
						et_compcode.setEnabled(true);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	};
	
	private void loadEvent_DL() throws Exception {
		List<QuestionType> list_event_dl = qtService.getEventDL();
		ArrayAdapter<QuestionType> adapter_dl = new ArrayAdapter<QuestionType>(
				this, R.layout.custom_spinner, list_event_dl);//android.R.layout.simple_spinner_item
		
		adapter_dl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spin_dl.setAdapter(adapter_dl);
		spin_dl.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String code = ((QuestionType) spin_dl.getSelectedItem())
						.getCode();
				try {
					loadEvent_XL(code);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void loadEvent_XL(String dlcode) throws Exception {
		List<QuestionType> list_event_xl = qtService.getEventXL(dlcode);
		ArrayAdapter<QuestionType> adapter_xl = new ArrayAdapter<QuestionType>(
				this, R.layout.custom_spinner, list_event_xl);
		adapter_xl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin_xl.setAdapter(adapter_xl);
		spin_xl.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String code = ((QuestionType) spin_xl.getSelectedItem())
						.getCode();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void loadComponent_DL() throws Exception {
		List<QuestionType> list_event_dl = qtService.getComponentDL();
		ArrayAdapter<QuestionType> adapter_dl = new ArrayAdapter<QuestionType>(
				this, R.layout.custom_spinner, list_event_dl);
		adapter_dl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spin_dl.setAdapter(adapter_dl);
		spin_dl.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String code = ((QuestionType) spin_dl.getSelectedItem())
						.getCode();
				try {
					loadComponent_XL(code);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void loadComponent_XL(String dlcode) throws Exception {
		List<QuestionType> list_event_xl = qtService.getComponentXL(dlcode);
		ArrayAdapter<QuestionType> adapter_xl = new ArrayAdapter<QuestionType>(
				this, R.layout.custom_spinner, list_event_xl);
		adapter_xl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin_xl.setAdapter(adapter_xl);
		spin_xl.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String code = ((QuestionType) spin_xl.getSelectedItem())
						.getCode();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}
