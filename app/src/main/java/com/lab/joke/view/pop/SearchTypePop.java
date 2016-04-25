package com.lab.joke.view.pop;

import android.content.Context;
import android.view.View;

/**
 * Created by luokaiwen on 15/6/26.
 * <p/>
 * 搜索类型Pop
 */
//@WLayout(layoutId = R.layout.pop_search_type)
public class SearchTypePop extends BasePop {
    public SearchTypePop(Context context) {
        super(context);
    }

    @Override
    protected void getPopView(View popupWindow) {

    }

//    private OnSearchTypeChangedListener mOnSearchTypeChangedListener;
//
//    public SearchTypePop(Context context) {
//
//        super(context);
//    }
//
//    public void setOnSearchTypeChangedListener(OnSearchTypeChangedListener onSearchTypeChangedListener) {
//        this.mOnSearchTypeChangedListener = onSearchTypeChangedListener;
//    }
//
//    @Override
//    protected void getPopView(View popupWindow) {
//
//        Button btnProduct = (Button) popupWindow.findViewById(R.id.btn_product);
//        Button btnCraftsman = (Button) popupWindow.findViewById(R.id.btn_craftsman);
//
//        btnCraftsman.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                mPopupWindow.dismiss();
//                if (null != mOnSearchTypeChangedListener) {
//                    mOnSearchTypeChangedListener.onTypeChange(WKey.TypeSearch.CRAFTSMAN);
//                }
//            }
//        });
//
//        btnProduct.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                mPopupWindow.dismiss();
//                if (null != mOnSearchTypeChangedListener) {
//                    mOnSearchTypeChangedListener.onTypeChange(WKey.TypeSearch.PRODUCT);
//                }
//            }
//        });
//    }
//
//    public interface OnSearchTypeChangedListener {
//        void onTypeChange(int searchType);
//    }
}
