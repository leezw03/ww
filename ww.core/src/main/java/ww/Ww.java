package ww;

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
		public static final String WW_SYS = "classpath*:ww/config/sys/";
		public static final String SYS = "classpath*:config/sys/";
		public static final String WW_ENV = "classpath*:ww/config/env/";
		public static final String ENV = "classpath*:config/env/";
		
	}
	
}
