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

import com.mobandme.ada.DataBinder;
import com.mobandme.ada.examples.devfest.R;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.devfest.model.entities.Employee;

import android.view.View;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.view.LayoutInflater;

public class EmployeesDetailFragment extends Fragment {

	private Employee mEmployee;
	public void setEmployee(Employee employee) { 
		mEmployee = employee;
	}
	
	@Override
	protected View onFragmentCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_employees_detail, null);
	}
	
	@Override
	protected void onFragmentResume() {
		showEmployee();
	}
	
	@Override
	protected void onFragmentCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_employees_detail, menu);
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
}
