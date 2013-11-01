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

import com.mobandme.ada.examples.devfest.R;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.devfest.model.entities.Employee;
import com.mobandme.ada.examples.devfest.model.services.DatabaseService;
import com.mobandme.ada.examples.devfest.ui.adapters.EmployeesListAdapter;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class EmployeesListFragment extends Fragment implements OnItemClickListener, OnQueryTextListener {
	public interface OnEmployeeClickListener {
		void onEmployeeClick(Employee employee);
	}
	
	private ListView             	mList;
	private EmployeesListAdapter 	mAdapter;
	private OnEmployeeClickListener mListener;
	
	public void setOnEmployeeClickListener(OnEmployeeClickListener pListener) {
		mListener = pListener;
	}
	
	@Override
	protected View onFragmentCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_employees_list, null);
	}
	
	@Override
	protected void onFragmentViewCreated(View view, Bundle savedInstanceState) {

		try {
			
			mList = (ListView)getView().findViewById(R.id.Employees_List);
			if (mList != null) {
				mAdapter = new EmployeesListAdapter(getActivity());
				mList.setEmptyView(getView().findViewById(R.id.empty));
				mList.setOnItemClickListener(this);
				mList.setAdapter(mAdapter);
				
				DatabaseService.getInstance(this).EmployeesSet.setAdapter(mAdapter);
				DatabaseService.getInstance(this).EmployeesSet.search();
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}

	@Override
	protected void onFragmentCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_employees_list, menu);
		
		SearchView searchView = (SearchView)MenuItemCompat.getActionView(menu.findItem(R.id.MenuCommand_Search));
		if (searchView != null) {
			searchView.setOnQueryTextListener(this);
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
		try {
			
			if (mListener != null) {
				mListener.onEmployeeClick(DatabaseService.getInstance(this).EmployeesSet.get(index));
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}

	@Override
	public boolean onQueryTextChange(String newText) { 
		try {
			
			DatabaseService.getInstance(this).EmployeesSet.search(newText);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
		
		return true; 
	}

	@Override
	public boolean onQueryTextSubmit(String query) { 
		return false; 
	}
}
