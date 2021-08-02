package fa.training.utils.validator;

import fa.training.dao.EmployeeDAO;

public class AccountValidator extends NonSpaceStringValidator{
    @Override
    public boolean check(String o) throws Exception {
        o = normalize(o);
        EmployeeDAO employeeDAO = new EmployeeDAO();
        return !employeeDAO.accountExists(o) && !o.equalsIgnoreCase("admin") && !o.equalsIgnoreCase("root") && !o.equalsIgnoreCase("guest");
    }
}
