package fa.training.meta;

import fa.training.utils.validator.Validator;

public interface Meta {
    public String getFieldName();
    public String getDBName();
    public Class getType();
    public Class<? extends Validator> getValidator();
    public boolean isExclusive();
    /*public Class getEntityClass();
    public Class getDAOClass();
    public String getDBTableName();*/
}
