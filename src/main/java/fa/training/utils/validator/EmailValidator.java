package fa.training.utils.validator;

import fa.training.dao.EmployeeDAO;

public class EmailValidator extends NonSpaceStringValidator{
    @Override
    public boolean check(String o) throws Exception {
        o = normalize(o);
        if("".equals(o)){
            return true;
        }
        EmployeeDAO employeeDAO = new EmployeeDAO();
        return o.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)+$") && !employeeDAO.emailExists(o);
    }
}
