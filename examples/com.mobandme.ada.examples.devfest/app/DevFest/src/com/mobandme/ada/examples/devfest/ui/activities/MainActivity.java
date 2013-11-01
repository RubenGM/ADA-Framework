package com.mobandme.ada.examples.devfest.ui.activities;

import com.mobandme.ada.examples.devfest.R;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.devfest.model.entities.Employee;
import com.mobandme.ada.examples.devfest.ui.fragments.EmployeesDetailFragment;
import com.mobandme.ada.examples.devfest.ui.fragments.EmployeesEditFragment;
import com.mobandme.ada.examples.devfest.ui.fragments.EmployeesEditFragment.OnEmployeesEditFinishListener;
import com.mobandme.ada.examples.devfest.ui.fragments.EmployeesListFragment;
import com.mobandme.ada.examples.devfest.ui.fragments.EmployeesListFragment.OnEmployeeClickListener;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity implements OnEmployeeClickListener, OnEmployeesEditFinishListener {

	private View 		   	        mMainLayout;
	private Employee                mCurrentEmployee;
	private EmployeesListFragment   mEmployeesListFragment;
	private EmployeesDetailFragment mEmployeesDetailFragment;
	private EmployeesEditFragment   mEmployeesEditFragment;
	
	@Override
	protected void onActivityCreate(Bundle savedInstance) {
		setContentView(R.layout.activity_main);

		initializeLayout();
	}
	
	@Override
	protected boolean onActivityOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				getSupportFragmentManager().popBackStack();
				break;
			case R.id.MenuCommand_Add:
				mCurrentEmployee = new Employee();
				mEmployeesEditFragment.setEmployee(mCurrentEmployee);
				showFragment(mEmployeesListFragment, mEmployeesEditFragment);
				break;
			case R.id.MenuCommand_Edit:
				mEmployeesEditFragment.setEmployee(mCurrentEmployee);
				showFragment(mEmployeesDetailFragment, mEmployeesEditFragment);
				break;
			case R.id.MenuCommand_Accept:
				mEmployeesEditFragment.save();
				break;
			case R.id.MenuCommand_Discard:
				mEmployeesEditFragment.delete();
				break;
				
		}
		
		return true;
	}
	
	private void initializeLayout() {
		mMainLayout = findViewById(R.id.MainLayout);
		if (mMainLayout != null) {
			mEmployeesEditFragment = new EmployeesEditFragment();
			mEmployeesEditFragment.setOnEmployeesEditFinish(this);
			
			mEmployeesDetailFragment = new EmployeesDetailFragment();
			
			mEmployeesListFragment = new EmployeesListFragment();
			mEmployeesListFragment.setOnEmployeeClickListener(this);
			
			showFragment(null,  mEmployeesListFragment);
		}
	}
	
	@Override
	public void onEmployeeClick(Employee employee) {
		try {
			
			mCurrentEmployee = employee;
			mEmployeesDetailFragment.setEmployee(mCurrentEmployee);
			showFragment(mEmployeesListFragment, mEmployeesDetailFragment);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}

	@Override
	public void onEmployeesEditFinish() {
		try {
			
			mCurrentEmployee = null;
			showFragment(mEmployeesEditFragment, mEmployeesListFragment);
			getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
}
