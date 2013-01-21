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

import com.desandroid.framework.ada.exceptions.AdaFrameworkException;
import com.mobandme.ada.examples.basics.R;
import com.mobandme.ada.examples.basics.model.context.ApplicationDataContext;
import com.mobandme.ada.examples.basics.model.context.DataContext;
import com.mobandme.ada.examples.basics.model.entites.Employee;
import com.mobandme.ada.examples.basics.ui.activities.employeeDetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * List form fragment.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Application Fragments
 * @version  2.3
 */
public class employeeListFragment extends Fragment {
    private ListView employeesListView;
    private ArrayAdapter<Employee> employeesListViewAdapter; 
    
    private OnItemClickListener itemClickLitener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> pParent, View pView, int pPosition, long id) {
			try {
	        	
				Intent deatailIntent = new Intent(getActivity(), employeeDetailActivity.class);
				deatailIntent.putExtra("employeeID", pPosition);
				startActivityForResult(deatailIntent, 1);
	        	
	        } catch (Exception e) {
				Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
			}
		}
    };
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.employee_list_fragment_layout, container);
	}
	
	@Override
	public void onViewCreated(View pView, Bundle pSavedInstanceState) {
		try {
			
			initializeFragment(pView);
			
		} catch (Exception e) {
			Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	
	
	private void initializeFragment(View pView) throws AdaFrameworkException {
		
		ApplicationDataContext.DataBase = new DataContext(getActivity());
		
		
    	this.employeesListView = (ListView)pView.findViewById(R.id.EmployeesListView);
    	
    	if (this.employeesListView != null) {
    		this.employeesListView.setOnItemClickListener(itemClickLitener);
    		this.employeesListViewAdapter = new ArrayAdapter<Employee>(getActivity(), android.R.layout.simple_list_item_1);
    		
    		ApplicationDataContext.DataBase.EmployeesSet.fill();
    		ApplicationDataContext.DataBase.EmployeesSet.setAdapter(this.employeesListViewAdapter);
    		this.employeesListView.setAdapter(this.employeesListViewAdapter);
    	}
    }
    
    public void executeAddNewCommand(View pView) {
    	try {
    		
    		Intent deatailIntent = new Intent(getActivity(), employeeDetailActivity.class);
    		startActivityForResult(deatailIntent, 1);
    		
    	} catch (Exception e) {
 			Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
 		}
    }
}
