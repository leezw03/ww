package ww.core.utils.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ReflectUtil {
	public static void setFieldValue(Object target, String fname,
			Class ftype, Object fvalue) throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		if (target == null
				|| fname == null
				|| "".equals(fname)
				|| (fvalue != null && !ftype.isAssignableFrom(fvalue
						.getClass()))) {
			return;
		}

		Class clazz = target.getClass();
		boolean success = false;
		for(;clazz!=Object.class;clazz = clazz.getSuperclass()){
			try {
				Method method = clazz.getDeclaredMethod("set"
						+ Character.toUpperCase(fname.charAt(0))
						+ fname.substring(1), ftype);
				if (!Modifier.isPublic(method.getModifiers())) {
					method.setAccessible(true);
				}
				method.invoke(target, fvalue);
				success= true;
			} catch (Exception me) {
				try {
					Field field = clazz.getDeclaredField(fname);
					if (!Modifier.isPublic(field.getModifiers())) {
						field.setAccessible(true);
					}
					field.set(target, fvalue);
					success = true;
				} catch (Exception e) {
				}
			}
		}
		if(!success){
			throw new NoSuchFieldException("can't find filed:"+fname +" from "+clazz);
		}
	}
}
