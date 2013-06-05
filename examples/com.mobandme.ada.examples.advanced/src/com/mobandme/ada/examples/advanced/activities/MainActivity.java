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

package com.mobandme.ada.examples.advanced.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;

import com.mobandme.ada.exceptions.AdaFrameworkException;
import com.mobandme.ada.examples.advanced.R;
import com.mobandme.ada.examples.advanced.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.advanced.model.DataBase;
import com.mobandme.ada.examples.advanced.model.entities.Address;
import com.mobandme.ada.examples.advanced.model.entities.Company;
import com.mobandme.ada.examples.advanced.model.entities.Directive;
import com.mobandme.ada.examples.advanced.model.entities.Worker;

/**
 * Application Main Activity.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Activity
 * @version  2.3
 */

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeActivity();
	}
	
	private void initializeActivity() {
		try {
		
			setContentView(R.layout.activity_main);
			
			DataBase.initialize(this);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(e);
		}
	}
	
	public void executeCommand(View pView) {
		try {
			
			if (pView != null) {
				switch (pView.getId()) {
					case R.id.Command_NewCompany:
						createNewCompany();
						break;
					case R.id.Command_NewWorker:
						createNewWorker();
						break;
					case R.id.Command_NewDirective:
						createNewDirective();
						break;
				}
			}
			
			Toast.makeText(this, getString(R.string.message_save_ok), Toast.LENGTH_SHORT).show();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	private Address createNewAddress(String pStreet) {
		Address address = new Address();
		address.Street = pStreet;
		address.Number = 25;
		address.Floor = 7;
		address.Door = "A";
		address.City = "Bilbao";
		address.Province = "Bizkaia";
		//Find the Country into DataBase if it does not exist, create.
		address.Country = DataBase.Context.CountriesSet.getByName("España", true);
		
		return address;
	}
	
	private void createNewCompany() throws AdaFrameworkException {
		Company company = new Company();
		company.Name    = "Mob&Me";
		company.Address = createNewAddress("C/ Mob&Me Street");
		
		//METHOD 1
		//This method save all instances with changes contained into the ObjectSet.
		DataBase.Context.CompaniesSet.add(company);
		DataBase.Context.CompaniesSet.save();
		
		//METHOD 2
		//This is an alternative method to save only one entity.
		//DataBase.Context.CompaniesSet.save(company);
	}
	
	private void createNewWorker() throws AdaFrameworkException {
		Worker worker = new Worker();
		worker.Name = "Txus";
		worker.Surname = "Ballesteros";
		worker.UserName = "user1234";
		worker.Password = "pass1234";
		worker.SalaryRate = 45.5f;
		worker.Active = true;
		//If the CompaniesSet is empty, create a new Company.
		if (DataBase.Context.CompaniesSet.size() == 0) {
			createNewCompany();
		}
		//Get the first company from CompaniesSet. 
		worker.Company = DataBase.Context.CompaniesSet.get(0);
		worker.Address.add(createNewAddress("C/ Worker Address 1"));
		worker.Address.add(createNewAddress("C/ Worker Address 2"));
		
		//METHOD 1
		//This method save all instances with changes contained into the ObjectSet.
		DataBase.Context.WorkersSet.add(worker);
		DataBase.Context.WorkersSet.save();
		
		//METHOD 2
		//This is an alternative method to save only one entity.
		//DataBase.Context.WorkersSet.save(worker);
	}
	
	private void createNewDirective() throws AdaFrameworkException {
		Directive directive = new Directive();
		directive.Name = "Eric";
		directive.Surname = "Schmidt";
		directive.UserName = "user4321";
		directive.Password = "pass4321";
		directive.Active = true;
		//If the CompaniesSet is empty, create a new Company.
		if (DataBase.Context.CompaniesSet.size() == 0) {
			createNewCompany();
		}
		//Get the first company from CompaniesSet. 
		directive.Company = DataBase.Context.CompaniesSet.get(0);
		directive.Address.add(createNewAddress("C/ Directive Address 1"));
		directive.Address.add(createNewAddress("C/ Directive Address 2"));
		//If the WorkersSet is empty, create a new Worker.
		if (DataBase.Context.WorkersSet.size() == 0) {
			createNewWorker();
		}
		directive.Workers.add(DataBase.Context.WorkersSet.get(0));
		
		//METHOD 1
		//This method save all instances with changes contained into the ObjectSet.
		DataBase.Context.DirectivesSet.add(directive);
		DataBase.Context.DirectivesSet.save();
		
		//METHOD 2
		//This is an alternative method to save only one entity.
		//DataBase.Context.DirectivesSet.save(directive);
	}
}
