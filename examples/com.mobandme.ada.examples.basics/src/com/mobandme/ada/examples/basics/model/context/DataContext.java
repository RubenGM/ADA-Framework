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

package com.mobandme.ada.examples.basics.model.context;

import android.content.Context;
import com.desandroid.framework.ada.ObjectContext;
import com.desandroid.framework.ada.ObjectSet;
import com.mobandme.ada.examples.basics.model.entites.Employee;

/**
 * Application Data Context.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Application Model Context
 * @version  2.3
 */

public class DataContext extends ObjectContext {

	public ObjectSet<Employee> EmployeesSet;
	
	public DataContext(Context pContext) { 
		super(pContext); 
		
		initialize();
	}

	private void initialize() {
		try {
			
			EmployeesSet = new ObjectSet<Employee>(Employee.class, this);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
