import java.lang.reflect.Field;
/**
 * Get value for a given public field or null if the field does not exist or is not accessible.
 */
class FieldGetter {

    public Object getFieldValue(Object object, String fieldName) throws IllegalAccessException {
        Class classOfObject = object.getClass();
        Field[] fields = classOfObject.getFields();
        for (Field field :
                fields) {
            field.setAccessible(true);
            if (field.getName().equals(fieldName)) {
                return field.get(object);
            }
        }
        return null;
    }

}