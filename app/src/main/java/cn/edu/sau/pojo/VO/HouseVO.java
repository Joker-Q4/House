package cn.edu.sau.pojo.VO;

public class HouseVO {
    private int id;                 // 房屋的主键
    private double rent;            // 房屋租金
    private String apartment;       // 房屋户型
    private String image1;          // 房屋的第一张照片
    private String image2;
    private String image3;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getRent() {
        return rent;
    }
    public void setRent(double rent) {
        this.rent = rent;
    }
    public String getApartment() {
        return apartment;
    }
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
    public String getImage1() {
        return image1;
    }
    public void setImage1(String image1) {
        this.image1 = image1;
    }
    public String getImage2() {
        return image2;
    }
    public void setImage2(String image2) {
        this.image2 = image2;
    }
    public String getImage3() {
        return image3;
    }
    public void setImage3(String image3) {
        this.image3 = image3;
    }
}
