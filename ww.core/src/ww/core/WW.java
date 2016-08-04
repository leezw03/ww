package ww.core;

import java.io.File;

public class WW {

	public static final String LOG_CORE = "ww.core";
	
	public static final String PROP_PATH_APP = "classpath:app.properties";
	public static final String PROP_ROOT_PATH_SYS = "classpath*:ww"+File.separator+"config"+File.separator+"sys"+File.separator;
	public static final String PROP_ROOT_PATH_ENV = "classpath*:ww"+File.separator+"config"+File.separator+"env"+File.separator;
	
}
