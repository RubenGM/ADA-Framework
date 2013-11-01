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

package com.mobandme.ada.examples.devfest.ui.views;

import com.mobandme.ada.examples.devfest.R;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class Label extends TextView {

	private Typeface typeface = null;
	private String   fontPath = null;
	private String   fontName = null;
	private String   textStyle = null;
	private boolean  textVertical = false;
	
	public void setVertical(boolean pValue) { textVertical = pValue;}
	public boolean setVertical() { return textVertical;}
	
	public String getFontName() { return fontName; }
	
	public void setFontName(String fontName) { 
		this.fontName = fontName; 
		
		configuteTypeFace();
	}
	
	public String getTextStyle() { return textStyle;}
	
	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
		
		configuteTypeFace();
	}
	
	public Label(Context context) {
		super(context);
	}
	
	public Label(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		try {
			configureControl(attrs);
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
	
	public Label(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		try {
			configureControl(attrs);
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		try {
			
			if (textVertical) {
				setMeasuredDimension( getMeasuredHeight(), getMeasuredWidth());
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
	
	@Override
	protected void onDraw(Canvas pCanvas) {
		try {
			
			if (textVertical) {
				pCanvas.save();
				
				pCanvas.translate(getWidth(), 0);
				pCanvas.rotate(90);
				
				pCanvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
				getLayout().draw(pCanvas);
				
				pCanvas.restore();
			}
			
			super.onDraw(pCanvas);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
	
	private void configuteTypeFace() {
		if (!this.isInEditMode()) {
			 if (getFontName() != null) {
			 	String completeFontName = getFontName();
				if (getTextStyle() != null) {
					if (getTextStyle().equals("0x0")) {
						completeFontName = String.format("%s_normal", completeFontName);
					} else if (getTextStyle().equals("0x1")) {
						completeFontName = String.format("%s_bold", completeFontName);
					} else if (getTextStyle().equals("0x2")) {
						completeFontName = String.format("%s_italic", completeFontName);
					} else if (getTextStyle().equals("0x3")) {
						completeFontName = String.format("%s_bold_italic", completeFontName);
					}
				}
				
				try {
					//Search the .ttf file into folder /assets/fonts/
					fontPath = String.format("fonts/%s.ttf", completeFontName);
					typeface = Typeface.createFromAsset(getContext().getAssets(), fontPath);
				} catch (Exception e1) {
					try  {
						//Search the .ttf file into folder /assets/
						fontPath = String.format("%s.ttf", completeFontName);
						typeface = Typeface.createFromAsset(getContext().getAssets(), fontPath);
					} catch (Exception e2) {
						try  {
							//Search the .ttf file into folder /assets/fonts/
							fontPath = String.format("fonts/%s.ttf", getFontName());
							typeface = Typeface.createFromAsset(getContext().getAssets(), fontPath);
						} catch (Exception e3) {
							//Search the .ttf file into folder /assets/
							fontPath = String.format("%s.ttf", getFontName());
							typeface = Typeface.createFromAsset(getContext().getAssets(), fontPath);
						}
					}
				}
	
				if (typeface != null) {
					setTypeface(typeface);
				}						
			}
		}
	}
	
	private void configureControl(AttributeSet attrs) {
		if (attrs != null) {
			
			 TypedArray customParameters = getContext().obtainStyledAttributes(attrs, R.styleable.Label);
			 if (customParameters != null) {
				 fontName     = customParameters.getString(R.styleable.Label_fontName);
				 textStyle    = customParameters.getString(R.styleable.Label_android_textStyle);
				 textVertical = customParameters.getBoolean(R.styleable.Label_vertical, textVertical);
				 customParameters.recycle(); 
			 }
		}	 
		
		configuteTypeFace();
	}
}