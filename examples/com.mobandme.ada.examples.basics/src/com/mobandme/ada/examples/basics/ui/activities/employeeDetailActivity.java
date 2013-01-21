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

package com.mobandme.ada.examples.basics.ui.activities;

import com.mobandme.ada.examples.basics.R;
import com.mobandme.ada.examples.basics.ui.fragments.employeeDetailFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Detail Activity.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Application Activity
 * @version  2.3
 */
public class employeeDetailActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
        	
        	setContentView(R.layout.employee_detail_activity_layout);
        	
        } catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	
	public void executeDeleteCommand(View pView) {
		try {
			
			Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.EmployeeDetailFragment);
	    	if (fragment != null) {
	    		((employeeDetailFragment)fragment).executeDeleteCommand(pView);
	    	}
	    	
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	
	public void executeSaveCommand(View pView) {
		try {
			
			Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.EmployeeDetailFragment);
	    	if (fragment != null) {
	    		((employeeDetailFragment)fragment).executeSaveCommand(pView);
	    	}
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
