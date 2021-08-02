package fa.training.utils.validator;

public class GeneralStringValidator implements Validator<String> {
    @Override
    public String normalize(String o) {
        return o.trim();
    }

    @Override
    public boolean check(String o) throws Exception {
        o = normalize(o);
        return o != null && !o.equals("");
    }
}
