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

package com.mobandme.ada.examples.indexes.model.context;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.desandroid.framework.ada.ObjectContext;
import com.desandroid.framework.ada.ObjectSet;
import com.mobandme.ada.examples.indexes.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.indexes.model.entities.Product;


/**
 * This class represent our application data context.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Data Context
 * @version  2.3
 */

public class DataContext extends ObjectContext {

	public ObjectSet<Product> ProductsSet;
	
	public DataContext(Context pContext) {
		super(pContext);
		
		//Initialize Application ObjectContext instance.
		initializeContext();
	}
	
	/**
	 * This method capture all exceptions into the creation database data model process. 
	 */
	@Override
	protected void onError(Exception pException) {
		ExceptionsHelper.manage(getContext(), pException);
	}
	
	/**
	 * This method will populated our database with demonstration data.
	 */
	@Override
	protected void onPopulate(SQLiteDatabase pDatabase) {		
		try {
			
			if (ProductsSet.size() == 0) {
				for(int counter = 1; counter <= 100; counter++) {
					if ((counter % 2) == 0) {
						ProductsSet.add(new Product(String.format("Product %05d", counter), "Smartphone", (0.99f + counter)));
					} else {
						ProductsSet.add(new Product(String.format("Product %05d", counter), "Tablet", (0.99f + counter)));
					}
				}
				ProductsSet.save(pDatabase);
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		} finally {
			super.onPopulate(pDatabase);
		}
	}
	
	/**
	 * This method initialize our Application data context, create instances of ObjectSets and fill it with data.
	 */
	private void initializeContext() {
		try {
			//Configure the ObjectContext to use Database Table Indexes.
			setUseTableIndexes(true);
			//Configure the ObjectContext to use Lazy Loading techniques.
			//useLazyLoading(true);
			
			//Initialize our ObjectSet instances.
			ProductsSet = new ObjectSet<Product>(Product.class, this);
			
			//Fill the Products ObjectSet with all elements saved into the Database.
			ProductsSet.fill();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(),e);
		}
	}
}
