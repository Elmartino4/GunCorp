package github.elmartino4.guncorp;

import java.lang.reflect.Field;

/**
 * @author: jtsnake
 * @date: "Jun"
 */
public class ReflectionUtil {

    /**
     * Gets the value of the field on the given object
     * 
     * @param obj  the object with the field being examined
     * @param name the name of the field in the object
     * @param <    T > will return the field type w/o static casting
     * @return the field value
     * @throws NoSuchFieldException   thrown if can't find field
     * @throws IllegalAccessException thrown if the field isn't accessible
     *                                (shouldn't be a problem w/
     *                                setAccessible(true))
     */
    public static <T> T getFieldValue(Object obj, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = findField(obj.getClass(), name);
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    /**
     * Set the value of the field on a given object
     * 
     * @param obj   the object with the field to set value on
     * @param name  name of the field in the object
     * @param value the value to set on the field
     * @throws NoSuchFieldException   thrown if can't find the field
     * @throws IllegalAccessException thrown if the field isn't accessible
     *                                (shouldn't be a problem w/
     *                                setAccessible(true))
     */
    public static void setFieldValue(Object obj, String name, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = findField(obj.getClass(), name);
        field.setAccessible(true);
        field.set(obj, value);
    }

    /**
     * Helper method to find the field in the class
     * 
     * @param clzz class type of the object
     * @param name name of the field
     * @return the java.lang.reflect.Field
     * @throws NoSuchFieldException thrown if method recurses to java.lang.Object
     *                              w/o finding the field by the given name
     */
    static Field findField(Class clzz, String name) throws NoSuchFieldException {
        Field field = null;
        try {
            field = clzz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            if (clzz.getSuperclass() != null) {
                field = ReflectionUtil.findField(clzz.getSuperclass(), name);
            } else {
                throw e;
            }
        }
        return field;
    }
}