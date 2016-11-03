package com.keyi.ouyangboss;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Application类是整个应用程序初始化的上下文
 * 
 * @author Administrator
 * 
 */
public class MyApplicaion extends Application {

	/**
	 * 本方法会在应用程序启动的时候被调用，这个方法中，可以去做一些对整个应用进行初始化的工作
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		System.out.println("MyApplicaion onCreate");
	}
}
