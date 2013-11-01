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

package com.mobandme.ada.examples.devfest.model.sets;

import com.mobandme.ada.Entity;
import com.mobandme.ada.ObjectContext;
import com.mobandme.ada.ObjectSet;
import com.mobandme.ada.examples.devfest.model.entities.Employee;
import com.mobandme.ada.exceptions.AdaFrameworkException;
import com.mobandme.ada.q.Delete;
import com.mobandme.ada.q.Insert;
import com.mobandme.ada.q.Select;
import com.mobandme.ada.q.Update;

@SuppressWarnings("serial")
public class EmployeesObjectSet extends ObjectSet<Employee> {

	public EmployeesObjectSet(Class<Employee> pManagedType, ObjectContext pContext) throws AdaFrameworkException {
		super(pManagedType, pContext);
	}


	/***
	 * This method fill all Employees entries into the set list.
	 */
	public void search() throws Exception {
		new Select()
			.from(Employee.class)
			.orderBy(Employee.DEFAULT_SORT)
			.execute(this);
	}
	
	/**
	 * This method fill the set list with filtered entries.
	 * @param query Filter value.
	 */
	public void search(String query) throws Exception {
		String nameField = getDataTableFieldName("name");
		
		if (query != null && !query.trim().equals("")) {
			String queryPatter = "%" + query + "%";
			
			new Select()
				.from(Employee.class)
				.where(String.format("%s LIKE ?", nameField), new String[] { queryPatter })
				.orderBy(Employee.DEFAULT_SORT)
				.execute(this);
		} else {
			search();
		}
	}
	
	/**
	 * This method save new entities or update existing entities. 
	 * @param employee Employee object.
	 */
	public void saveEmployee(Employee employee) throws AdaFrameworkException {
		if (employee.getStatus() == Entity.STATUS_NEW) {
			new Insert()
				.into(Employee.class)
				.values(employee)
				.execute(this);
		} else {
			new Update(Employee.class)
					.set(employee)
					.execute(this);
		}
	}

	/**
	 * This method delete an Employee.
	 * @param employee Employee object.
	 */
	public void deleteEmployee(Employee employee) throws AdaFrameworkException {
		if (employee.getStatus() != Entity.STATUS_NEW) {
			new Delete()
				.from(Employee.class)
				.where(employee)
				.execute(this);
		}
	}
}
