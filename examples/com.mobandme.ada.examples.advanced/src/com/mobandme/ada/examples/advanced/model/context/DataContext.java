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

package com.mobandme.ada.examples.advanced.model.context;

import java.io.File;
import android.content.Context;
import android.os.Environment;
import android.database.sqlite.SQLiteDatabase;
import com.desandroid.framework.ada.ObjectContext;
import com.desandroid.framework.ada.ObjectSet;
import com.mobandme.ada.examples.advanced.model.entities.Company;
import com.mobandme.ada.examples.advanced.model.entities.Country;
import com.mobandme.ada.examples.advanced.model.entities.Directive;
import com.mobandme.ada.examples.advanced.model.entities.Employee;
import com.mobandme.ada.examples.advanced.model.entities.Worker;
import com.mobandme.ada.examples.advanced.model.helpers.CountriesLoaderHelper;
import com.mobandme.ada.examples.advanced.helpers.ExceptionsHelper;
import com.desandroid.framework.ada.exceptions.AdaFrameworkException;
import com.mobandme.ada.examples.advanced.model.sets.CountriesObjectSet;

/**
 * Application Data Context.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Data Context
 * @version  2.3
 */

public class DataContext extends ObjectContext {

	final static String DATABASE_FOLDER  = "%s/MobAndMe/ADAFramework/Advance/";
	final static String DATABASE_NAME    = "adaframework.db";
	final static int    DATABASE_VERSION = 1;
	
	/**************************************************/
	/*      		OBJECTSETS DEFINITION 			  */
	/**************************************************/
	public CountriesObjectSet   CountriesSet;
	public ObjectSet<Company>   CompaniesSet;
	public ObjectSet<Directive> DirectivesSet;
	public ObjectSet<Worker>    WorkersSet;
	
	/**************************************************/
	/*      		CONSTRUCTORS 		 			  */
	/**************************************************/
	public DataContext(Context pContext) {
		//Set a custom DataBase path and version.
		super(pContext, String.format("%s%s", getDataBaseFolder(), DATABASE_NAME), DATABASE_VERSION);
		
		//Initialize the ObjectContext
		initializeContext();
	}


	/**************************************************/
	/*      			EVENTS				 		  */
	/**************************************************/
	
	@Override
	protected void onPopulate(SQLiteDatabase pDatabase) {
		try {
			
			CountriesSet.addAll(CountriesLoaderHelper.getList(getContext()));
			CountriesSet.save(pDatabase);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
	
	@Override
	protected void onError(Exception pException) {
		ExceptionsHelper.manage(getContext(), pException);
	}
	
	
	/**************************************************/
	/*      		PRIVATE METHOS   	 			  */
	/**************************************************/
	
	private void initializeContext() {
		try {
			
			//Enable DataBase Transactions to be used by the Save process.
			this.setUseTransactions(true);

			//Enable the creation of DataBase table indexes.
			this.setUseTableIndexes(true);

			//Enable LazyLoading capabilities.
			//this.useLazyLoading(true);
			
			//Set a custom encryption algorithm.
			this.setEncryptionAlgorithm("AES");
			
			//Set a custom encryption master pass phrase.
			this.setMasterEncryptionKey("com.mobandme.ada.examples.advanced");
			
			//Initialize ObjectSets instances.
			initializeObjectSets();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(e);
		}
	}
	
	private void initializeObjectSets() throws AdaFrameworkException {
		CountriesSet = new CountriesObjectSet(this);
		CompaniesSet = new ObjectSet<Company>(Company.class, this);
		DirectivesSet = new ObjectSet<Directive>(Directive.class, this);
		WorkersSet = new ObjectSet<Worker>(Worker.class, this);
		
		CountriesSet.fill(Country.DEFAULT_SORT);
		CompaniesSet.fill(Company.DEFAULT_SORT);
		DirectivesSet.fill(Employee.DEFAULT_SORT);
		WorkersSet.fill(Employee.DEFAULT_SORT);
	}
	
	private static String getDataBaseFolder() {
		String folderPath = "";
		try {
			
			folderPath = String.format(DATABASE_FOLDER, Environment.getExternalStorageDirectory().getAbsolutePath());
			
			File dbFolder = new File(folderPath);
			if (!dbFolder.exists()) {
				dbFolder.mkdirs();
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(e);
		}
		
		return folderPath;
	}
}
