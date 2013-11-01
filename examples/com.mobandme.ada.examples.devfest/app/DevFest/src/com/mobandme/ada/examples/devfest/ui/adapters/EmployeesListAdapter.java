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

package com.mobandme.ada.examples.devfest.ui.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.mobandme.ada.DataBinder;
import com.mobandme.ada.examples.devfest.R;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.devfest.model.entities.Employee;
import com.mobandme.ada.examples.devfest.ui.views.RoundImageView;

@SuppressWarnings("unused")
public class EmployeesListAdapter extends ArrayAdapter<Employee> {
	
	public EmployeesListAdapter(Context context) { super(context, R.layout.employee_list_item); }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View returnedValue = convertView;
		
		try {
	
			if (returnedValue == null) {
				LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				returnedValue = inflater.inflate(R.layout.employee_list_item, null);
			}
			
			Employee employee = getItem(position);
			if (employee != null) {
				employee.bind(returnedValue);
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
		
		return returnedValue;
	}
}
