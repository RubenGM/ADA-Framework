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

package com.mobandme.ada.examples;

import java.util.List;

import com.desandroid.framework.ada.exceptions.AdaFrameworkException;
import com.mobandme.ada.examples.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.model.context.DataContext;
import com.mobandme.ada.examples.model.entities.Author;
import com.mobandme.ada.examples.model.entities.Category;
import com.mobandme.ada.examples.model.entities.Phrase;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;

/**
 * Main application activity.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Model Entities
 * @version  2.2.2
 */

public class MainActivity extends Activity {

	private DataContext dataContext;
	private TextView    twSearchResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			
			initializeActivity();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this,e);
		}
	}
	
	public void executeSearch(View pView) {
		try {
		
			if (pView.getId() == R.id.SearchByAuthor) {
				//Search Phrases by Author
				searchByAuthor(dataContext.AuthorsSet.get(0));
			} else if (pView.getId() == R.id.SearchByCategory) {
				//Search Phrases by Category
				searchByCategory(dataContext.CategoriesSet.get(3));
			} else {
				searchAll();
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this,e);
		}
	}
	
	private void initializeActivity() throws AdaFrameworkException {
		setContentView(R.layout.activity_main);
		twSearchResult = (TextView)findViewById(R.id.twSearchResult);
		
		//Create ObjectContext instance.
		dataContext = new DataContext(this);
		
		//Search all phrases.
		searchAll();
	}
	
	private void searchAll() throws AdaFrameworkException {
		List<Phrase> phrasesList = dataContext.PhrasesSet.search(false, null, null, null, null, null, null, null);
		displayPhrases(phrasesList);
	}
	
	private void searchByAuthor(Author pAuthor) throws AdaFrameworkException {
		String wherePattern = "Authors_ID = ?";
		List<Phrase> phrasesList = dataContext.PhrasesSet.search(Phrase.TABLE_PHRASES_JOIN_AUTHORS, false, null, wherePattern, new String[] { pAuthor.getID().toString() }, Phrase.DEFAULT_SORT, null, null, null, null);
		displayPhrases(phrasesList);
	}
	
	private void searchByCategory(Category pCategory) throws AdaFrameworkException {
		String wherePattern = "Categories_ID = ?";
		List<Phrase> phrasesList = dataContext.PhrasesSet.search(Phrase.TABLE_PHRASES_JOIN_CATEGORIES, false, null, wherePattern, new String[] { pCategory.getID().toString() }, Phrase.DEFAULT_SORT, null, null, null, null);
		displayPhrases(phrasesList);
	}

	private void displayPhrases(List<Phrase> pPhrasesList) {
		if (pPhrasesList != null && pPhrasesList.size() > 0) {
			String searchResult = "";
			for(Phrase phrase : pPhrasesList) {
				if (!searchResult.equals(""))
					searchResult += "\r\n";
				
				searchResult += phrase.toString();
			}
			
			if (twSearchResult != null)
				twSearchResult.setText(searchResult);
		}
	}
}
