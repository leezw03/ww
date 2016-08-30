package ww;

import java.io.File;

public class Ww {

	public interface Log {
		/**
		 * 日志前缀
		 */
		public static final String PREFIX = "ww.";
		/**
		 * 核心框架日志
		 */
		public static final String CORE = PREFIX+"core";
	}
	
	public interface Env {
		
		public static final String APP = "classpath:app.properties";
		public static final String SYS = "classpath*:ww"+File.separator+"config"+File.separator+"sys"+File.separator;
		public static final String ENV = "classpath*:ww"+File.separator+"config"+File.separator+"env"+File.separator;
		
	}
	
}
