package fa.training.entity;

import fa.training.meta.Meta;

@SuppressWarnings("ALL")
public abstract class BaseEntity {
    private Class base;

    public abstract Class getMeta();

    public Object get(Meta meta) {
        String methodName = "get" + meta.getFieldName().substring(0, 1).toUpperCase() + meta.getFieldName().substring(1);
        try {
            return base.getDeclaredMethod(methodName).invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(Meta meta, Object  value) {
        String methodName = "set" + meta.getFieldName().substring(0, 1).toUpperCase() + meta.getFieldName().substring(1);
        try {
            base.getDeclaredMethod(methodName, meta.getType()).invoke(this, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseEntity() {
        try {
            this.base = (Class) getMeta().getDeclaredMethod("getEntityClass").invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}