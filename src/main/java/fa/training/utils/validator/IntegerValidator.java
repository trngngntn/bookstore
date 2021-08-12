package fa.training.utils.validator;

public class IntegerValidator implements Validator<Integer>{
    @Override
    public Integer normalize(Integer o) {
        return o;
    }

    @Override
    public boolean check(Integer o) throws Exception {
        return o > 0;
    }
}
