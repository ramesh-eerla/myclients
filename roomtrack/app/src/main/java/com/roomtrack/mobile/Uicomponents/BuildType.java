package com.roomtrack.mobile.Uicomponents;

/**
 * Copyright 2012 American Express Company All right reserved
 * <p>
 * BuildType type is a PROD build
 * </p>
 * The boolean indicates whether the build is of type PROD or debug In a debug
 * build debug/BuildType.java.DEBUG with PROD=false is copied over prior to
 * compilation and the conditional code such as proxy & TrustingHttpClient,
 * EasySSLSocketFactory etc. will get included in the build.
 * 
 * @author krachama
 */
public final class BuildType {

	public static final boolean PROD = false;
	
	/*
	 * This flag shall be used to enable / disable crashylitics, we shall disable it for
	 * day to day works and enable in Production / SIT /UAT.
	 */
	public static final boolean ENABLE_CRASHYLITICS = false;

}
