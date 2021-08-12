package fa.training.utils.validator;

public class DoubleValidator implements Validator<Double>{
    @Override
    public Double normalize(Double o) {
        return o;
    }

    @Override
    public boolean check(Double o) throws Exception {
        return o > 0;
    }
}
