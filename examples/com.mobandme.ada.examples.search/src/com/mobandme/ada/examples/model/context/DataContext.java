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

package com.mobandme.ada.examples.model.context;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.desandroid.framework.ada.ObjectContext;
import com.desandroid.framework.ada.ObjectSet;
import com.mobandme.ada.examples.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.model.entities.Author;
import com.mobandme.ada.examples.model.entities.Category;
import com.mobandme.ada.examples.model.entities.Phrase;

/**
 * This class represent our application data context.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Model Entities
 * @version  2.2.2
 */

public class DataContext extends ObjectContext {

	public ObjectSet<Author> AuthorsSet;
	public ObjectSet<Category> CategoriesSet;
	public ObjectSet<Phrase> PhrasesSet;
	
	public DataContext(Context pContext) { 
		super(pContext);
		
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
			
			if (AuthorsSet.size() == 0) {
				AuthorsSet.add(new Author("Groucho Marx"));
				AuthorsSet.add(new Author("Albert Einstein"));
				AuthorsSet.save(pDatabase);
			}
			
			if (CategoriesSet.size() == 0) {
				CategoriesSet.add(new Category("Category 001"));
				CategoriesSet.add(new Category("Category 002"));
				CategoriesSet.add(new Category("Category 003"));
				CategoriesSet.add(new Category("Category 004"));
				CategoriesSet.add(new Category("Category 005"));
				CategoriesSet.save(pDatabase);
			}
			
			if (PhrasesSet.size() == 0) {
				Phrase phrase1 = new Phrase(AuthorsSet.get(0), "The secret of life is honesty and fair dealing. If you can fake that, you've got it made.");
				phrase1.Categories.add(CategoriesSet.get(0));
				phrase1.Categories.add(CategoriesSet.get(1));
				PhrasesSet.add(phrase1); 
				
				Phrase phrase2 = new Phrase(AuthorsSet.get(1), "The important thing is never to stop questioning.");
				phrase2.Categories.add(CategoriesSet.get(2));
				phrase2.Categories.add(CategoriesSet.get(3));
				phrase2.Categories.add(CategoriesSet.get(4));
				PhrasesSet.add(phrase2);
				PhrasesSet.save(pDatabase);
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
			
			AuthorsSet    = new ObjectSet<Author>(Author.class, this);
			CategoriesSet = new ObjectSet<Category>(Category.class, this);
			PhrasesSet    = new ObjectSet<Phrase>(Phrase.class, this);
			
			AuthorsSet.fill();
			CategoriesSet.fill();
			PhrasesSet.fill();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(),e);
		}
	}
}
