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
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressWarnings("unused")
public class RoundImageView extends ImageView {

	private final static int MASK_SHAPE_GONE = 0;
	private final static int MASK_SHAPE_ROUND = 1;
	private final static int MASK_SHAPE_HEXAGON = 2;
	private final static int MASK_SHAPE_ROUND_RECT = 3;
	private final static int MASK_SHAPE_OCTOGON = 4;
	
	private int    strokeColor = Color.argb(255, 56, 56, 56);
	private int    strokeShadowColor = Color.argb(152, 214, 214, 214);
	private int    size = 0;
	private int    maskShape = 1;
	private float  strokeWidth = 4;
	private float  strokeShadowWidth = (strokeWidth + 2);
	private float  cornerRadious = 0.0f;
	private Bitmap resultBitmap;
	private Bitmap scaledBitmap;
	private Path   maskPath;
	private Paint  borderPaint;
	private Paint  shadowPaint;
	private Canvas maskedCanvas;
	private Paint  maskPaint;
		
	public int  getStrokeColor() { return strokeColor; }
	public void setStrokeColor(int pColor) { strokeColor = pColor; }
	
	public float getStrokeWidth() { return strokeWidth; }
	public void  setStrokeWidth(float pWidth) { strokeWidth = pWidth; }
	
	public int  getStrokeShadowColor() { return strokeShadowColor; }
	public void setStrokeShadowColor(int pColor) { strokeShadowColor = pColor; }
	
	public float getStrokeShadowWidth() { return strokeShadowWidth; }
	public void  setStrokeShadowWidth(float pWidth) { strokeShadowWidth = pWidth; }
	
	public int  getMaskShape() { return maskShape; }
	public void setMaskShape(int pMaskShape) { maskShape = pMaskShape; }
	
	public RoundImageView(Context context) { super(context); }
	public RoundImageView(Context context, AttributeSet attrs) { super(context, attrs); configureView(attrs); }
	public RoundImageView(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); configureView(attrs); }

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		try {
		
			//Calculate the control size.
			int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
		    int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
		    size = Math.min(parentWidth, parentHeight);
		    
		    //Destroy the View to will be regenetared with the new dimensions.
		    destroyView();
		    
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		} finally {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
	
	@Override
	protected void onDraw(Canvas pCanvas) {
		try {

			if (scaledBitmap == null) {
				scaledBitmap = Bitmap.createScaledBitmap(((BitmapDrawable)getDrawable()).getBitmap(), size, size, true);
			}
			
			if (resultBitmap == null) {
				resultBitmap = Bitmap.createBitmap(pCanvas.getWidth(), pCanvas.getHeight(), scaledBitmap.getConfig());
			}
			
			if (borderPaint == null && strokeWidth > 0) {
				borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
				borderPaint.setColor(strokeColor);
				borderPaint.setStyle(Paint.Style.STROKE);
				borderPaint.setStrokeWidth(strokeWidth);
			}
			
			if (shadowPaint == null && strokeShadowWidth > 0) {
				shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
				shadowPaint.setColor(strokeShadowColor);
				shadowPaint.setStyle(Style.STROKE);
				shadowPaint.setStrokeWidth(strokeShadowWidth);
			}
			
			//Generate the mask and shape paths.
			createMask();
			
			//Create the transparency paint.
			if (maskPaint == null) {
				maskPaint = new Paint();
				maskPaint.setAntiAlias(true);
				maskPaint.setColor(Color.BLACK);
				maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
			}
			
			if (maskedCanvas == null) {
				//Create a Canvas object to will be paint on it.
				maskedCanvas = new Canvas(resultBitmap);
				//Draw background original image.
				maskedCanvas.drawBitmap(scaledBitmap, 0, 0, null);
				
				//Draw transparent area masking the original bitmap image.
				if (maskPath != null) {
					maskedCanvas.drawPath(maskPath, maskPaint);
				}
			}
			
			//Draw the processed Bitmap into the original view canvas instance.
			pCanvas.drawBitmap(resultBitmap, 0, 0, null);
			
			//Draw the image shadow border.
			if (maskShape == MASK_SHAPE_ROUND && shadowPaint != null && strokeShadowWidth > 0) {
				pCanvas.drawCircle((size / 2), (size / 2), ((size / 2) - (strokeShadowWidth / 2)), shadowPaint);
			}
			
			//Draw the image border.
			if (maskShape == MASK_SHAPE_ROUND && borderPaint != null && strokeWidth > 0) {
				pCanvas.drawCircle((size / 2), (size / 2), ((size / 2) - (strokeWidth / 2)), borderPaint);
			}
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
	
	@Override
	protected void onDetachedFromWindow() {
		try {
			
			destroyView();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		} finally {
			super.onDetachedFromWindow();
		}
	}
	
	private void createMask() {
		
		if (maskPath == null && maskShape != MASK_SHAPE_GONE) {
			switch (maskShape) {
				case MASK_SHAPE_HEXAGON:
					maskPath = new Path();
					maskPath.moveTo((size * 0.25f), 0);
					maskPath.lineTo((size * 0.75f), 0);
					maskPath.lineTo(size, (size * 0.5f));
					maskPath.lineTo((size * 0.75f), size);
					maskPath.lineTo((size * 0.25f), size);
					maskPath.lineTo(0, (size * 0.5f));
					maskPath.lineTo((size * 0.25f), 0);
					break;
				case MASK_SHAPE_OCTOGON:
					maskPath = new Path();
					maskPath.moveTo((size * 0.25f), 0);
					maskPath.lineTo((size * 0.75f), 0);
					maskPath.lineTo(size, (size * 0.25f));
					maskPath.lineTo(size, (size * 0.75f));
					maskPath.lineTo((size * 0.75f), size);
					maskPath.lineTo((size * 0.25f), size);
					maskPath.lineTo(0, (size * 0.75f));
					maskPath.lineTo(0, (size * 0.25f));
					maskPath.lineTo((size * 0.25f), 0);
					break;
				case MASK_SHAPE_ROUND_RECT:
					if (cornerRadious > 0.0f) {
						maskPath = new Path();
						maskPath.addRoundRect(new RectF(0, 0, size, size), cornerRadious, cornerRadious, Direction.CW);
					}
					break;
				default:
					maskPath = new Path();
					maskPath.addCircle((size / 2), (size / 2), (size / 2), Direction.CCW);
					break;
			}
		}
		
		if (maskPath != null) {
			maskPath.toggleInverseFillType();
			maskPath.close();
		}
	}
	
	@SuppressLint("Recycle")
	private void configureView(AttributeSet pAttrs) {
		try {
		
			if (pAttrs != null) {
				TypedArray customChartParameters = getContext().obtainStyledAttributes(pAttrs, R.styleable.RoundImageView);
				if (customChartParameters != null) {
					strokeWidth       = customChartParameters.getDimension(R.styleable.RoundImageView_strokeWidth, strokeWidth);
					strokeShadowWidth = customChartParameters.getDimension(R.styleable.RoundImageView_strokeShadowWidth, strokeShadowWidth);
					strokeColor       = customChartParameters.getColor(R.styleable.RoundImageView_strokeColor, strokeColor);
					strokeShadowColor = customChartParameters.getColor(R.styleable.RoundImageView_strokeShadowColor, strokeShadowColor);
					cornerRadious     = customChartParameters.getDimension(R.styleable.RoundImageView_cornerRadious, cornerRadious);
					maskShape         = customChartParameters.getInt(R.styleable.RoundImageView_maskShape, maskShape);
				}
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
	
	private void destroyView() {
		borderPaint  = null;
		shadowPaint  = null;
		resultBitmap = null;
		scaledBitmap = null;
		maskedCanvas = null;
		maskPath     = null;
		//shapePath    = null;
		maskPaint    = null;
	}
}
