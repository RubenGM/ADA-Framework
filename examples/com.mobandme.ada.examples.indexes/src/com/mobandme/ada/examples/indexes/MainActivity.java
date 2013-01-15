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

package com.mobandme.ada.examples.indexes;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.app.Activity;

import com.mobandme.ada.examples.indexes.adapters.ProductAdapter;
import com.mobandme.ada.examples.indexes.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.indexes.model.context.DataContext;
import com.mobandme.ada.examples.indexes.model.entities.Product;
import com.desandroid.framework.ada.exceptions.AdaFrameworkException;


/**
 * Main activity of the Application.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Application Activity
 * @version  2.3
 */
public class MainActivity extends Activity {

	private ListView        productsListView;
	private ProductAdapter  productsListAdapter;
	private DataContext     dataContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			
			//Initialize our Application Activity.
			initializeActivity();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	private void initializeActivity() throws AdaFrameworkException {
		setContentView(R.layout.activity_main);
		
		//Create ObjectContext instance.
		dataContext = new DataContext(this);
		
		//Configure our Products ListView to be managed by the Products ObjectSet. 
		// When you add, change or remove an element from this ObjectSet, 
		// the ListView will be updated  automatically. 
		productsListView = (ListView)findViewById(R.id.ProductsListView);
		if (productsListView != null) {
			//Initialize Products DataAdapter
			productsListAdapter = new ProductAdapter(this);
			
			//Associate our Products DataAdapter to the Products ListView
			productsListView.setAdapter(productsListAdapter);

			//Link our Products DataAdapter to Products ObjectSet.
			dataContext.ProductsSet.setAdapter(productsListAdapter);
		}
	}
	
	/**
	 * This method capture all search actions shooted by the user.
	 * @param pView
	 */
	public void executeSearch(View pView) {
		try {
			
			if (pView.getId() == R.id.SearchByName) {
				//Search Products by Name.
				searchByName(String.format("Product %05d", 1));
			} else if (pView.getId() == R.id.SearchByPrice) {
				//Search Products by Price.
				searchByPrice(49.99f);
			} else if (pView.getId() == R.id.SearchAll) {
				//Search All Products without filters.
				searchAll();
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	/**
	 * Search All the Products.
	 * @throws AdaFrameworkException
	 */
	private void searchAll() throws AdaFrameworkException {
		//Don't need additional parameters.
		dataContext.ProductsSet.fill(Product.DEFAULT_SORT);
	}
	
	/**
	 * Search Products by Name.
	 * @param pName Product Name.
	 * @throws AdaFrameworkException
	 */
	private void searchByName(String pName) throws AdaFrameworkException {
		//We get the name of the database field, so we abstract model source code.
		String wherePattern = String.format("%s = ?", dataContext.ProductsSet.getDataTableFieldName("Name"));
		dataContext.ProductsSet.fill(wherePattern, new String[] { pName }, Product.DEFAULT_SORT);
	}
	
	/**
	 * Search Products by Price.
	 * @param pPrice Product Price.
	 * @throws AdaFrameworkException
	 */
	private void searchByPrice(Float pPrice) throws AdaFrameworkException {
		//We get the name of the database field, so we abstract model source code.
		String wherePattern = String.format("%s >= ?", dataContext.ProductsSet.getDataTableFieldName("Price"));
		dataContext.ProductsSet.fill(wherePattern, new String[] { Float.toString(pPrice) }, Product.DEFAULT_SORT);
	}
}
