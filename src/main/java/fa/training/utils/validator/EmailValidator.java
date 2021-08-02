package fa.training.utils.validator;

public class EmailValidator extends GeneralStringValidator{
    @Override
    public boolean check(String o) throws Exception {
        return normalize(o).matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)+$");
    }
}
