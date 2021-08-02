package fa.training.utils.validator;

public interface Validator<T> {
    public T normalize(T o);
    public boolean check(T o) throws Exception;
}
