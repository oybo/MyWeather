/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.android.oyb.com.myapp.manager;

import android.content.Context;
import android.content.SharedPreferences;

import app.android.oyb.com.myapp.AppContext;

/**
 *  shaPreference 共享信息工具类
 *  @author: ouyangbo
 *  @date:   2014-12-25
 *  @email:  ouyangbo@kingnode.com
 *  @remark: 
 *  @modify by:
 */
public class PreferenceUtils {

	private static SharedPreferences mSharedPreferences;
	private static PreferenceUtils mPreferenceUtils;
	private static SharedPreferences.Editor editor;

	/**
	 * 单例模式，获取instance实例
	 * 
	 * @return
	 */
	public static PreferenceUtils getInstance() {
		if (mPreferenceUtils == null) {
			synchronized (PreferenceUtils.class) {
				mPreferenceUtils = new PreferenceUtils();
			}
		}
		return mPreferenceUtils;
	}

	private PreferenceUtils() {
		String PREFERENCE_NAME = AppContext.getInstance().getPackageName();
		mSharedPreferences = AppContext.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		editor = mSharedPreferences.edit();
	}

	public void putString(String key, String value){
		editor.putString(key, value);
		editor.commit();
	}
	
	public void putBoolen(String key, boolean value){
		editor.putBoolean(key, value);
		editor.commit();
	}

	public void putInt(String key, int value){
		editor.putInt(key, value);
		editor.commit();
	}
	
	
	public String getString(String key, String defValue){
		return mSharedPreferences.getString(key, defValue);
	}

	public boolean getBoolean(String key, boolean defValue){
		return mSharedPreferences.getBoolean(key, defValue);
	}

	public int getInt(String key, int defValue){
		return mSharedPreferences.getInt(key, defValue);
	}
	
	public void remove(String key) {
		if(mSharedPreferences.contains(key)) {
			editor.remove(key);
			editor.commit();
		}
	}
	
	public boolean isContains(String key) {
		return mSharedPreferences.contains(key);
	}
	
	public void clear() {
		editor.clear().commit();
	}

}
