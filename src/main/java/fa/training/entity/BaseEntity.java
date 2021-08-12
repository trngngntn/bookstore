package fa.training.entity;

import fa.training.meta.Meta;
import fa.training.utils.validator.Validator;

import java.beans.Expression;


public abstract class BaseEntity<T> {
    private transient Class<T> base;

    public abstract Class<? extends Meta> getMeta();

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
            this.base = (Class<T>) getMeta().getDeclaredMethod("getEntityClass").invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validate(){
        return false;
    }

    public boolean check() throws Exception {
        Meta[] values = getMeta().getEnumConstants();

        for(Meta meta : values){

            if(meta.getValidator() == null) continue;

            Object o = new Object();

            Expression expression = new Expression(o, meta.getValidator(), "new", null);
            expression.execute();

            Validator v = (Validator) expression.getValue();
            if(v != null){
                if(v.check(get(meta)) == false) {
                    System.out.println(meta.getFieldName() + " is Invalid");
                    return false;
                }
            }
        }
        return true;
    }

    public void normalize() throws Exception {
        Meta[] values = getMeta().getEnumConstants();

        for(Meta meta : values){

            if(meta.getValidator() == null) continue;

            Object o = new Object();

            Expression expression = new Expression(o, meta.getValidator(), "new", null);
            expression.execute();

            Validator v = (Validator) expression.getValue();
            if(v != null){
                set(meta, v.normalize(get(meta)));
            }
        }
    }
}
