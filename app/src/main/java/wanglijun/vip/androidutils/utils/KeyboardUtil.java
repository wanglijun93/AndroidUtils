/*
 * Copyright 2015-2016 北京博瑞彤芸文化传播股份有限公司
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wanglijun.vip.androidutils.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * @author wlj
 * @date 2018/09/01
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils
 * @description 键盘工具类
 */
public class KeyboardUtil {

    /**
     * 隐藏键盘
     */
    public static void hideSoftInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示键盘
     */
    public static void showSoftInput(EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) et.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * 展示键盘并选中最后一个
     */
    public static void showSoftInputSelect(EditText et) {
        showSoftInputSelect(et, 300);
    }

    /**
     * 展示键盘并选中最后一个
     */
    public static void showSoftInputSelect(final EditText et, long delayMillis) {
        et.postDelayed(new Runnable() {

            @Override
            public void run() {
                showSoftInput(et);
                et.setSelection(et.getText().length());
            }
        }, delayMillis);
    }

}
