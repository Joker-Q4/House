package cn.edu.sau.pojo;

public class Tenant {

    private String name;                    // 房客姓名
    private String sex;                     // 房客性别
    private int age;                        // 房客年龄
    private String id_number;               // 房客身份证号
    private double min_rent;                // 最小租金
    private double max_rent;                // 最大租金
    private String apart;                   // 户型
    private double min_area;                // 最小面积
    private double max_area;                // 最大面积
    private String residential_quarters;    // 小区
    private String respective_area;         // 区域
    private int p_number;                   // 房客联系方式
    private String address;                 // 房客家庭住址

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getId_number() {
        return id_number;
    }
    public void setId_number(String id_number) {
        this.id_number = id_number;
    }
    public double getMin_rent() {
        return min_rent;
    }
    public void setMin_rent(double min_rent) {
        this.min_rent = min_rent;
    }
    public double getMax_rent() {
        return max_rent;
    }
    public void setMax_rent(double max_rent) {
        this.max_rent = max_rent;
    }
    public String getApart() {
        return apart;
    }
    public void setApart(String apart) {
        this.apart = apart;
    }
    public double getMin_area() {
        return min_area;
    }
    public void setMin_area(double min_area) {
        this.min_area = min_area;
    }
    public double getMax_area() {
        return max_area;
    }
    public void setMax_area(double max_area) {
        this.max_area = max_area;
    }
    public String getResidential_quarters() {
        return residential_quarters;
    }
    public void setResidential_quarters(String residential_quarters) {
        this.residential_quarters = residential_quarters;
    }
    public String getRespective_area() {
        return respective_area;
    }
    public void setRespective_area(String respective_area) {
        this.respective_area = respective_area;
    }
    public int getP_number() {
        return p_number;
    }
    public void setP_number(int p_number) {
        this.p_number = p_number;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
