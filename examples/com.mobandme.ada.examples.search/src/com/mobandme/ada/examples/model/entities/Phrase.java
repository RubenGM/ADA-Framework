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

package com.mobandme.ada.examples.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;


/**
 * This class represent our Phrases entity.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Model Entities
 * @version  2.2.2
 */

@Table(name = "Phrases")
public class Phrase extends Entity {

	public static final String DEFAULT_SORT = "Content ASC";
	public static final String TABLE_PHRASES_JOIN_AUTHORS = "Phrases INNER JOIN LINK_Phrases_Author_Authors ON Phrases.ID = LINK_Phrases_Author_Authors.Phrases_ID";
	public static final String TABLE_PHRASES_JOIN_CATEGORIES = "Phrases INNER JOIN LINK_Phrases_Categories_Categories ON Phrases.ID = LINK_Phrases_Categories_Categories.Phrases_ID";
	
	
	@TableField(name = "Content", datatype = DATATYPE_STRING, maxLength = 250, required = true)
	public String Content;
	
	@TableField(name = "Author", datatype = DATATYPE_ENTITY_LINK)
	public Author Author;
	
	@TableField(name = "Categories", datatype = DATATYPE_ENTITY_LINK)
	public List<Category> Categories;
	
	
	public Phrase() { Categories = new ArrayList<Category>(); }
	public Phrase(String pContent) { 
		this();
		Content = pContent;
	} 
	public Phrase(Author pAuthor, String pContent) {
		this(pContent);
		Author = pAuthor;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s)", Content, Author.Name);
	}
}
