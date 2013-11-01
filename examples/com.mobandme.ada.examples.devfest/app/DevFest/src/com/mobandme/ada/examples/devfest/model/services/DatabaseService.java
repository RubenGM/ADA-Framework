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

package com.mobandme.ada.examples.devfest.model.services;

import android.content.Context;

import com.mobandme.ada.ObjectContext;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.devfest.model.entities.Employee;
import com.mobandme.ada.examples.devfest.model.sets.EmployeesObjectSet;
import com.mobandme.ada.examples.devfest.ui.fragments.Fragment;

public final class DatabaseService extends ObjectContext {

	private static DatabaseService mServiceIntence;
	
	public static DatabaseService getInstance(Fragment pFragment) { return getInstance(pFragment.getActivity()); }
	
	public static DatabaseService getInstance(Context pContext) {
		if (mServiceIntence == null)
			mServiceIntence = new DatabaseService(pContext);
		
		return mServiceIntence;
	}
	
	public EmployeesObjectSet EmployeesSet;

	public DatabaseService(Context pContext) { 
		super(pContext);
		
		try {
			
			EmployeesSet = new EmployeesObjectSet(Employee.class, this);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(pContext, e);
		}
	}
	
	@Override
	protected void onError(Exception e) { ExceptionsHelper.manage(getContext(), e); }
}
