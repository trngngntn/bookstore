package fa.training.entity;

import com.google.gson.annotations.JsonAdapter;
import fa.training.meta.EmployeeMeta;
import fa.training.utils.DateTimeUtils;
import fa.training.utils.typeAdapter.DateTypeAdapter;

import java.sql.Date;

public class Employee extends BaseEntity<Employee> {
    @Override
    public Class<EmployeeMeta> getMeta() {
        return EmployeeMeta.class;
    }

    private int id;
    private String name;
    private String phone;
    @JsonAdapter(value = DateTypeAdapter.class)
    private Date dob;
    private String address;
    private boolean sex;
    private int departmentId;
    private String email;
    private String account;
    private String password;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDob() {
        return dob;
    }

    public String getDobF() {
        return DateTimeUtils.formatDate(dob);
    }

    public String getAddress() {
        return address;
    }

    public boolean getSex() {
        return sex;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getEmail() {
        return email;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setEmail(String email) {
        if ("".equals(email)) {
            this.email = null;
        } else {
            this.email = email;
        }
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                ", departmentId=" + departmentId +
                ", email='" + email + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
