package com.desandroid.ormlite_ada;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.desandroid.ormlite_ada.model.entities.AdaFrameworkEntity;
import com.desandroid.ormlite_ada.model.entities.OrmLiteEntity;
import com.desandroid.ormlite_ada.model.helpers.AdaFrameworkHelper;
import com.desandroid.ormlite_ada.model.helpers.OrmLiteHelper;

public class MainActivity extends Activity {

	private final static int NUM_OF_ITERATION = 100;
	
	private TextView labelOrmTestResult;
	private TextView labelAdaTestResult;
	private AdaFrameworkHelper adaFramework;
	private OrmLiteHelper ormLite;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    protected void onStart() {
    	try {
    		
    		initializeHelpers();
    		
    	} catch (Exception e) {
    		Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			super.onStart();
		}
    }
    
    private void initializeHelpers() throws Exception {
    	labelOrmTestResult = (TextView)findViewById(R.id.labelOrmTestResult);
    	labelAdaTestResult = (TextView)findViewById(R.id.labelAdaTestResult);
    	adaFramework = new AdaFrameworkHelper(this);
    	ormLite = OrmLiteHelper.getInstance(this);
    }
    
    public void execute_ORM_Lite_Test(View pView) {
    	try {
    		
    		Date startTime = new Date();
    		
    		for (int index = 1; index <= NUM_OF_ITERATION; index++) {
    			OrmLiteEntity entity = new OrmLiteEntity();
    			entity.setName(String.format("Entity %d", index));
    			entity.setAge(index);
    			entity.setBirthDay(new Date());
    			
    			ormLite.add(entity);
    		}
    		
    		Date endTime = new Date();
    		calculateTimeDiference(startTime, endTime, 1);
    		
    	} catch (Exception e) {
    		Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
    }
    
    public void execute_ADA_Framework_Test_1(View pView) {
    	try {
    		
    		Date startTime = new Date();
    		
    		for (int index = 1; index <= NUM_OF_ITERATION; index++) {
    			AdaFrameworkEntity entity = new AdaFrameworkEntity();
    			entity.setName(String.format("Entity %d", index));
    			entity.setAge(index);
    			entity.setBirthDay(new Date());
    			adaFramework.getEntitiesSet().add(entity);
    		}
    		adaFramework.getEntitiesSet().save();
    		
    		Date endTime = new Date();
    		calculateTimeDiference(startTime, endTime, 2);
    		
    	} catch (Exception e) {
    		Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
    }
    
    public void execute_ADA_Framework_Test_2(View pView) {
    	try {
    		
    		Date startTime = new Date();
    		
    		for (int index = 1; index <= NUM_OF_ITERATION; index++) {
    			AdaFrameworkEntity entity = new AdaFrameworkEntity();
    			entity.setName(String.format("Entity %d", index));
    			entity.setAge(index);
    			entity.setBirthDay(new Date());
    			adaFramework.getEntitiesSet().save(entity);
    		}
    		
    		Date endTime = new Date();
    		calculateTimeDiference(startTime, endTime, 2);
    		
    	} catch (Exception e) {
    		Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
    }
    
    public void clear_Results(View pView) {
    	try {
    		
    		if (labelOrmTestResult != null) {
    			labelOrmTestResult.setText(getString(R.string.label_orm_execution_time));
    		}
    		
    		if (labelAdaTestResult != null) {
    			labelAdaTestResult.setText(getString(R.string.label_ada_execution_time));
    		}
    		
    	} catch (Exception e) {
    		Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
    }
    
    private void calculateTimeDiference(Date pInitialDate, Date pFinishDate, int pType) {
		Long initialTime = pInitialDate.getTime();
		Long finishTime = pFinishDate.getTime();
		
		long totalTime = finishTime - initialTime;
		
		DateFormat df = new SimpleDateFormat("mm:ss.S");
		String resultValue = df.format(new Date(totalTime));
		
		if (pType == 1) {
			if (labelOrmTestResult != null) {
				labelOrmTestResult.setText(String.format(getString(R.string.label_orm_execution_time_pattern), resultValue));
			} 
		} else {
			if (labelAdaTestResult != null) {
				labelAdaTestResult.setText(String.format(getString(R.string.label_ada_execution_time_pattern), resultValue));
			} 
		}
	}
}
