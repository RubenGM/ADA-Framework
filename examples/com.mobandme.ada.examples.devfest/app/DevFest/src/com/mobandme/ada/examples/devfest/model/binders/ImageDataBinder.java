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

package com.mobandme.ada.examples.devfest.model.binders;


import android.view.View;
import com.mobandme.ada.DataBinder;
import com.mobandme.ada.DataBinding;
import com.mobandme.ada.Entity;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.devfest.model.entities.Employee;
import com.mobandme.ada.examples.devfest.ui.views.RoundImageView;
import com.mobandme.ada.exceptions.AdaFrameworkException;

public class ImageDataBinder extends DataBinder {

	@Override
	public void bind(DataBinding pBinding, Entity pEntity, View pView, int pDirection) throws AdaFrameworkException {
		try {
			
			if (pDirection == DataBinder.BINDING_ENTITY_TO_UI) {
				if (((Employee)pEntity).getImageId() != -1) {
					int imageId = ((Employee)pEntity).getImageId();
				
					((RoundImageView)pView).setImageResource(imageId);
					((RoundImageView)pView).setTag(imageId);
				}
			} else {
				int currentImageId = -1;
				if (pView.getTag() != null) 
					currentImageId = (Integer)pView.getTag();
				
				((Employee)pEntity).setImageId(currentImageId);
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(pView.getContext(), e);
		}
	}
}
