package com.roomtrac.mobile.Uicomponents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;


/**
 * <p>
 * Custom Edit Text to apply the custom type face
 * </p>
 * 
 */
public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    private Context mCntxt;
    private FontUtil mFontUtil;
    private IOnBackButtonListener mListener;

    /**
     * Listener for Back Key
     * 
     */
    public interface IOnBackButtonListener {
        boolean onEditTextBackButton();
    }

    /**
     * Parametric constructor to set context
     * 
     * @param context
     */
    public CustomEditText(Context context) {
        super(context);
        init(context);
    }

    /**
     * Initializing the Context and Font
     * 
     * @param context
     *            Activity Context
     */
    private void init(Context context) {
        mCntxt = context;
        mFontUtil = new FontUtil(mCntxt);
    }

    /**
     * Parametric constructor to set attributes
     * 
     * @param context
     * @param attrs
     */
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        mFontUtil.setFont(this, mFontUtil.getFontFileName(attrs));
    }

    /**
     * Method to set the custom font
     * 
     * @param fontFileName
     *            name of the font file
     */
    public void setFont(String fontFileName) {
        mFontUtil.setFont(this, fontFileName);
    }

    /**
     * Method to handle Done Button in keyboard
     */
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        InputConnection connection = super.onCreateInputConnection(outAttrs);
        int imeActions = outAttrs.imeOptions & EditorInfo.IME_MASK_ACTION;
        if ((imeActions & EditorInfo.IME_ACTION_DONE) != 0) {
            outAttrs.imeOptions ^= imeActions;
            outAttrs.imeOptions |= EditorInfo.IME_ACTION_DONE;
        }
        if ((outAttrs.imeOptions & EditorInfo.IME_FLAG_NO_ENTER_ACTION) != 0) {
            outAttrs.imeOptions &= ~EditorInfo.IME_FLAG_NO_ENTER_ACTION;
        }
        return connection;
    }

    public void setOnBackButtonListener(IOnBackButtonListener listener) {
        mListener = listener;
    }

    /**
     * Method for to catch back key event in "Add Note" editor
     */
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && mListener != null && mListener.onEditTextBackButton()) {
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }
}