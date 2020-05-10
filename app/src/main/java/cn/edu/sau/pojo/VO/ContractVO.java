package cn.edu.sau.pojo.VO;

public class ContractVO {

    private int id;                  // 合同的主键
    private String owner_name;       // 房主姓名
    private String renter_name;      // 房客姓名
    private String contract_photo;   // 合同照片

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOwner_name() {
        return owner_name;
    }
    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }
    public String getRenter_name() {
        return renter_name;
    }
    public void setRenter_name(String renter_name) {
        this.renter_name = renter_name;
    }
    public String getContract_photo() {
        return contract_photo;
    }
    public void setContract_photo(String contract_photo) {
        this.contract_photo = contract_photo;
    }
}
