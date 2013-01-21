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

package com.mobandme.ada.examples.basics.ui.fragments;

import com.desandroid.framework.ada.DataBinder;
import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.exceptions.AdaFrameworkException;
import com.mobandme.ada.examples.basics.R;
import com.mobandme.ada.examples.basics.model.context.ApplicationDataContext;
import com.mobandme.ada.examples.basics.model.entites.Employee;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Detail form fragment.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Application Fragments
 * @version  2.3
 */
public class employeeDetailFragment extends Fragment {
	private View fragmentView;
	private Employee employee = new Employee();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.employee_detail_fragment_layout, container, true);
	}
	
	@Override
	public void onViewCreated(View pView, Bundle savedInstanceState) {
		try {
			
			fragmentView = pView;
			initializeFragment(pView);
			
		} catch (Exception e) {
			Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	
	private void initializeFragment(View pView) throws AdaFrameworkException {
		Bundle intentExtras = getActivity().getIntent().getExtras();
		if (intentExtras != null) {
			executeShowCommand(intentExtras.getInt("employeeID"));
		}
	}
	
	public void executeShowCommand(int pIndex) {
		try {
			
			employee = ApplicationDataContext.DataBase.EmployeesSet.get(pIndex);
			employee.setStatus(Entity.STATUS_UPDATED);
			employee.bind(fragmentView);
			
		} catch (Exception e) {
			Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	
 	public void executeDeleteCommand(View pView) {
		try {
			
			if (employee.getID() != null) {
			
				employee.setStatus(Entity.STATUS_DELETED);
				ApplicationDataContext.DataBase.EmployeesSet.save();
				
				getActivity().setResult(Activity.RESULT_OK);
				getActivity().finish();
			}
			
		} catch (Exception e) {
			Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	
	public void executeSaveCommand(View pView) {
		try {
			
			employee.bind(fragmentView, DataBinder.BINDING_UI_TO_ENTITY);
			if (employee.validate(getActivity())) {
				
				if (employee.getID() == null) {
					ApplicationDataContext.DataBase.EmployeesSet.add(employee);
				}
				ApplicationDataContext.DataBase.EmployeesSet.save();
				
				getActivity().setResult(Activity.RESULT_OK);
				getActivity().finish();
				
			} else {
				Toast.makeText(getActivity(), employee.getValidationResultString("-"), Toast.LENGTH_SHORT).show();
			}
			
		} catch (Exception e) {
			Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}