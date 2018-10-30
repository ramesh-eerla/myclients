package com.roomtrac.mobile.Uicomponents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/**
 * Copyright 2012 American Express Company All right reserved
 * <p>
 * This is a Custom Image View used for Zooming the captured Image.
 * </p>
 * 
 * @author kmaroj
 * 
 */
public class CustomImageView extends android.support.v7.widget.AppCompatImageView {

    private Matrix matrix = new Matrix();

    private ScaleBeginListener scaleBeginListener;
    // We can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;

    // Remember some things for zooming
    private PointF last = new PointF();
    private PointF start = new PointF();
    private float minScale = 1f;
    private float maxScale = 3f;
    private float[] m;

    private float redundantXSpace, redundantYSpace;

    private float width, height;
    private static final int CLICK = 3;
    private float saveScale = 1f;
    private float right, bottom, origWidth, origHeight, bmWidth, bmHeight;

    private ScaleGestureDetector mScaleDetector;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setClickable(true);

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        matrix.setTranslate(1f, 1f);
        m = new float[12];
        setImageMatrix(matrix);
        setScaleType(ScaleType.MATRIX);

        setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                mScaleDetector.onTouchEvent(event);
                if (v.getParent() != null) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                matrix.getValues(m);
                float x = m[Matrix.MTRANS_X];
                float y = m[Matrix.MTRANS_Y];
                PointF curr = new PointF(event.getX(), event.getY());

                switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    last.set(event.getX(), event.getY());
                    start.set(last);
                    mode = DRAG;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {
                        float deltaX = curr.x - last.x;
                        float deltaY = curr.y - last.y;
                        float scaleWidth = Math.round(origWidth * saveScale);
                        float scaleHeight = Math.round(origHeight * saveScale);
                        if (scaleWidth < width) {
                            deltaX = 0;
                            if (y + deltaY > 0) {
                                deltaY = -y;
                            } else if (y + deltaY < -bottom) {
                                deltaY = -(y + bottom);
                            }
                        } else if (scaleHeight < height) {
                            deltaY = 0;
                            if (x + deltaX > 0) {
                                deltaX = -x;
                            } else if (x + deltaX < -right) {
                                deltaX = -(x + right);
                            }
                        } else {
                            if (x + deltaX > 0) {
                                deltaX = -x;
                            } else if (x + deltaX < -right) {
                                deltaX = -(x + right);
                            }

                            if (y + deltaY > 0) {
                                deltaY = -y;
                            } else if (y + deltaY < -bottom) {
                                deltaY = -(y + bottom);
                            }
                        }
                        matrix.postTranslate(deltaX, deltaY);
                        last.set(curr.x, curr.y);
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    mode = NONE;
                    int xDiff = (int) Math.abs(curr.x - start.x);
                    int yDiff = (int) Math.abs(curr.y - start.y);
                    if (xDiff < CLICK && yDiff < CLICK) {
                        performClick();
                    }
                    if (v.getParent() != null) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    break;

                default:
                    break;
                }
                setImageMatrix(matrix);
                invalidate();
                return true; // indicate event was handled
            }

        });
    }

    /**
     * Parametric constructor to set context.
     * 
     * @param context
     */
    public CustomImageView(Context context) {
        super(context);
        // init();
    }

    /**
     * Parametric constructor to set context, font attributes and font style.
     * 
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // init();
    }

    /**
     * This method is to set a Bitmap as the content of this ImageView
     */

    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        bmWidth = bm.getWidth();
        bmHeight = bm.getHeight();
    }

    /**
     * This method is to Set a max zoom
     */
    public void setMaxZoom(float x) {
        maxScale = x;
    }

    public void setScaleBeginListener(ScaleBeginListener sbl) {

        scaleBeginListener = sbl;

    }

    /**
     * This class calculates the coordinates after zoom
     * 
     * @author kmaroj
     * 
     */
    private class ScaleListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {

        /**
         * Responds to the beginning of a scaling gesture
         * 
         * @return boolean
         */

        public boolean onScaleBegin(ScaleGestureDetector detector) {
            mode = ZOOM;
            if (scaleBeginListener != null) {
                scaleBeginListener.scaleBegin();
            }
            return true;
        }

        /**
         * Responds to the beginning of a scaling gesture
         * 
         * @return boolean
         */

        public boolean onScale(ScaleGestureDetector detector) {
            float mScaleFactor = (float) Math.min(
                    Math.max(.95f, detector.getScaleFactor()), 1.05);
            float origScale = saveScale;
            saveScale *= mScaleFactor;
            if (saveScale > maxScale) {
                saveScale = maxScale;
                mScaleFactor = maxScale / origScale;
            } else if (saveScale < minScale) {
                saveScale = minScale;
                mScaleFactor = minScale / origScale;
            }
            right = width * saveScale - width
                    - (2 * redundantXSpace * saveScale);
            bottom = height * saveScale - height
                    - (2 * redundantYSpace * saveScale);
            if (origWidth * saveScale <= width
                    || origHeight * saveScale <= height) {
                matrix.postScale(mScaleFactor, mScaleFactor, width / 2,
                        height / 2);
                if (mScaleFactor < 1) {
                    matrix.getValues(m);
                    float x = m[Matrix.MTRANS_X];
                    float y = m[Matrix.MTRANS_Y];
                    if (mScaleFactor < 1) {
                        if (Math.round(origWidth * saveScale) < width) {
                            if (y < -bottom) {
                                matrix.postTranslate(0, -(y + bottom));
                            } else if (y > 0) {
                                matrix.postTranslate(0, -y);
                            }
                        } else {
                            if (x < -right) {
                                matrix.postTranslate(-(x + right), 0);
                            } else if (x > 0) {
                                matrix.postTranslate(-x, 0);
                            }
                        }
                    }
                }
            } else {
                matrix.postScale(mScaleFactor, mScaleFactor,
                        detector.getFocusX(), detector.getFocusY());
                matrix.getValues(m);
                float x = m[Matrix.MTRANS_X];
                float y = m[Matrix.MTRANS_Y];
                if (mScaleFactor < 1) {
                    if (x < -right) {
                        matrix.postTranslate(-(x + right), 0);
                    } else if (x > 0) {
                        matrix.postTranslate(-x, 0);
                    }
                    if (y < -bottom) {
                        matrix.postTranslate(0, -(y + bottom));
                    } else if (y > 0) {
                        matrix.postTranslate(0, -y);
                    }
                }
            }
            return true;

        }
    }

    /**
     * Measure the view and its content to determine the measured width and the
     * measured height
     */

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        float scaleX = (float) width / (float) bmWidth;
        float scaleY = (float) height / (float) bmHeight;

        float scale = Math.min(scaleX, scaleY);
        matrix.setScale(scale, scale);

        // Setting scale parameters to align center in screen
        redundantYSpace = (float) height - (scale * (float) bmHeight);
        redundantXSpace = (float) width - (scale * (float) bmWidth);

        setImageMatrix(matrix);
        saveScale = 1f;
        redundantYSpace /= (float) 2;
        redundantXSpace /= (float) 2;

        matrix.postTranslate(redundantXSpace, redundantYSpace);

        origWidth = width - 2 * redundantXSpace;
        origHeight = height - 2 * redundantYSpace;
        right = width * saveScale - width - (2 * redundantXSpace * saveScale);
        bottom = height * saveScale - height
                - (2 * redundantYSpace * saveScale);
        setImageMatrix(matrix);
    }

    public interface ScaleBeginListener {

        void scaleBegin();

    }
}
