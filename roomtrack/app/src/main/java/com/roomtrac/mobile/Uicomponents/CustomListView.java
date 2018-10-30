package com.roomtrac.mobile.Uicomponents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Copyright 2012 American Express Company All right reserved
 * <p>
 * Custom ListView to apply the custom functionality of swipe up and down
 * </p>
 * 
 * @author krachama
 */
public class CustomListView extends ListView {

	private static final int SWIPE_DISTANCE = 30;
	private OnSwipeListener onSwipeListener = null;
	private int mSwipeDistance = SWIPE_DISTANCE;
	private boolean isHandledSwipe = true;
	private Context context;
	
	/**
	 * Parametric constructor to set context.
	 * 
	 * @param context
	 */
	public CustomListView(Context context) {
		super(context);
		this.context = context;
	}

	/**
	 * Parametric constructor to set context, font attributes.
	 * 
	 * @param context
	 * @param attrs
	 */
	public CustomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	/**
	 * Parametric constructor to set context, font attributes and font style.
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CustomListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	/**
	 * This method is to add touch listener.
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP) {
			isHandledSwipe = true;
		}
		swipeGestute.onTouchEvent(ev);
		return super.onTouchEvent(ev);
	}

	/**
	 * This method is to add swipe listener for receipts listview
	 * 
	 * @param onSwipeListener
	 *            the onSwipeListener to set
	 */
	public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
		this.onSwipeListener = onSwipeListener;
	}

	/**
	 * This method is to set swipe distance to make new request
	 * 
	 * @param swipeDistance
	 *            the swipeDistance to set
	 */
	public void setSwipeDistance(int swipeDistance) {
		this.mSwipeDistance = swipeDistance;
	}

	// Adding Gesture listener to listview
	private GestureDetector swipeGestute = new GestureDetector(context,new OnGestureListener() {

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {

			if (onSwipeListener != null) {

				if ((distanceX > 0) && (distanceX > mSwipeDistance)) {
					onSwipeListener.onSwipe(SWIPE.SWIPE_LEFT,
							Math.abs(distanceY));
				} else if ((distanceX < 0)
						&& (Math.abs(distanceX) > mSwipeDistance)) {
					onSwipeListener.onSwipe(SWIPE.SWIPE_RIGHT,
							Math.abs(distanceX));
				} else if ((distanceY > 0) && (distanceY > mSwipeDistance)) {
					onSwipeListener.onSwipe(SWIPE.SWIPE_DOWN,
							Math.abs(distanceY));
				} else if ((distanceY < 0)
						&& (Math.abs(distanceY) > mSwipeDistance) && isHandledSwipe) {
					onSwipeListener.onSwipe(SWIPE.SWIPE_UP, Math.abs(distanceY));
					isHandledSwipe = false;
				}
			}

			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {

		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			return false;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}
	});

	public enum SWIPE {
		DEFAULT, SWIPE_LEFT, SWIPE_RIGHT, SWIPE_UP, SWIPE_DOWN
	};

	/**
	 * Interface to detect swipe
	 * 
	 * @author krachama
	 * 
	 */
	public interface OnSwipeListener {
		void onSwipe(SWIPE eventName, float distance);

	}

}
