/**
   Copyright Mob&Me 2013 (@MobAndMe)

   Licensed under the GPL General Public License, Version 3.0 (the "License"),  
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.gnu.org/licenses/gpl.html

   Unless required by applicable law or agreed to in writing, software 
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   Website: http://adaframework.com
   Contact: Txus Ballesteros <txus.ballesteros@mobandme.com>
*/


package com.mobandme.ada.examples.devfest.ui.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.mobandme.ada.DataBinder;
import com.mobandme.ada.examples.devfest.R;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.devfest.model.entities.Employee;
import com.mobandme.ada.examples.devfest.model.services.DatabaseService;
import com.mobandme.ada.examples.devfest.ui.views.RoundImageView;

import android.view.View;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class EmployeesEditFragment extends Fragment implements OnClickListener, OnDateSetListener {
	public interface OnEmployeesEditFinishListener {
		void onEmployeesEditFinish();
	}
	
	private EditText        mEmployeeBirth;
	private RoundImageView  mEmployeImage;
	private int      		mCurrentImage = 0;
	private Employee 		mEmployee;
	private OnEmployeesEditFinishListener mListener;
	
	
	public void setEmployee(Employee employee) { mEmployee = employee; }
	public void setOnEmployeesEditFinish(OnEmployeesEditFinishListener listener) { mListener = listener; } 
	
	public void delete() {
		try {
			
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        switch (which){
			        case DialogInterface.BUTTON_POSITIVE:
			        	deleteEmployee();
			            break;
			        }
			    }
			};
			
			new AlertDialog.Builder(getActivity())
				.setMessage(R.string.message_delete_confirm)
				.setPositiveButton(R.string.button_yes, dialogClickListener)
				.setNegativeButton(R.string.button_no, dialogClickListener)
				.show();

		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	private void deleteEmployee() {
		try {
			
			DatabaseService.getInstance(getActivity()).EmployeesSet.deleteEmployee(mEmployee);
			if (mListener != null)
				mListener.onEmployeesEditFinish();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	public void save() {
		try {
			
			mEmployee.bind(getView(), DataBinder.BINDING_UI_TO_ENTITY);
			if (mEmployee.validate(getActivity())) {
				DatabaseService.getInstance(getActivity()).EmployeesSet.saveEmployee(mEmployee);
				
				if (mListener != null)
					mListener.onEmployeesEditFinish();
			} else {
				showErrors();
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	@Override
	protected View onFragmentCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_employees_edit, null);
	}
	
	@Override
	protected void onFragmentViewCreated(View view, Bundle savedInstanceState) {
		getView().findViewById(R.id.Employee_DatePicker).setOnClickListener(this);
		mEmployeeBirth = (EditText)getView().findViewById(R.id.Employee_Birthday);
		mEmployeImage  = (RoundImageView)getView().findViewById(R.id.Employee_Image);
		mEmployeImage.setOnClickListener(this);
	}
	
	@Override
	protected void onFragmentResume() {
		showEmployee();
	}
	
	@Override
	protected void onFragmentCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_employees_edit, menu);
	}
	
	private void showErrors() {
		Toast.makeText(getActivity(), mEmployee.getValidationResultString("\n* "), Toast.LENGTH_SHORT).show();
	}
	
	private void showEmployee() {
		try {
			
			if (mEmployee != null) {
				mEmployee.bind(getView(), DataBinder.BINDING_ENTITY_TO_UI);
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	@Override
	public void onClick(View view) {
		try {
			
			switch (view.getId()) {
				case R.id.Employee_DatePicker:
					showCalendar();
					break;
				case R.id.Employee_Image:
					changePicture();
					break;
			}
			
			
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		try {
			
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.set(year, monthOfYear, dayOfMonth);
			
			Date currentDate = calendar.getTime();
			if (mEmployeeBirth != null) {
				SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy");
				mEmployeeBirth.setText(formater.format(currentDate));
				mEmployeeBirth.setTag(currentDate);
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	private void showCalendar() {
		Calendar calendar = GregorianCalendar.getInstance();
		Date currentDate = (Date)mEmployeeBirth.getTag();
		if (currentDate != null) {
			calendar.setTime(currentDate);
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		DatePickerDialog datePicker = new DatePickerDialog(getActivity(), this, year, month, day);
		datePicker.show();
	}
	
	private void changePicture() {
		mCurrentImage++;
		int currentImageID = R.drawable.face00;
		switch (mCurrentImage) {
			case 1:
				currentImageID = R.drawable.face01;
				break;
			case 2:
				currentImageID = R.drawable.face02;
				break;
			case 3:
				currentImageID = R.drawable.face03;
				break;
			case 4:
				currentImageID = R.drawable.face04;
				break;
			case 5:
				currentImageID = R.drawable.face05;
				break;
			case 6:
				currentImageID = R.drawable.face06;
				break;
			case 7:
				mCurrentImage = 0;
				break;
		}
		
		if (mEmployeImage != null) {
			mEmployeImage.setImageResource(currentImageID);
			mEmployeImage.setTag(currentImageID);
		}
	}
}
