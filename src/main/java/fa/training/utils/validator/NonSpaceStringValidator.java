package fa.training.utils.validator;

public class NonSpaceStringValidator extends GeneralStringValidator{
    @Override
    public String normalize(String o) {
        return super.normalize(o).replaceAll(" ", "");
    }
}
