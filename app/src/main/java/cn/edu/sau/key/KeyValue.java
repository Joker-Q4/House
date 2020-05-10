package cn.edu.sau.key;

public class KeyValue {

    // json关键字
    public static final String USER = "user";             // 用户名
    public static final String PWD = "pwd";               // 密码

    public static final String NAME = "username";         // 房主姓名
    public static final String PHONE = "phone";           // 房主联系方式
    public static final String RENT = "rent";             // 房屋租金
    public static final String APART = "apartment";       // 房屋户型
    public static final String AREA = "area";             // 房屋面积
    public static final String FLOOR = "flr";             // 楼层
    public static final String ORIEN = "orientation";     // 房屋朝向
    public static final String COND = "condi";            // 设施条件
    public static final String RES_Q = "residential_quarters";    // 小区
    public static final String RES_A = "respective_area"; // 所属区
    public static final String ADDR = "address";          // 具体地址
    public static final String PRO_R = "property_right";  // 房屋产权
    public static final String DESC = "description";      // 房屋描述
    public static final String IMAGE1 = "image1";         // 图片地址
    public static final String IMAGE2 = "image2";
    public static final String IMAGE3 = "image3";
    public static final String STATER = "stater";         // 出租状态

    // 上传图片个数,默认上传3张图片
    public static int num = 0;
    // 保存3张图片地址
    public static final String image[] = new String[40];

    public static final String QMinRent = "query_min_rent";              // 最小租金
    public static final String QMaxRent = "query_max_rent";              // 最大租金
    public static final String QApartment = "query_apartment";           // 户型
    public static final String QMinArea = "query_min_area";              // 最小面积
    public static final String QMaxArea = "query_max_area";              // 最大面积
    public static final String QOrientation = "query_orientation";       // 朝向
    public static final String QResidentialQuarters = "query_residential_quarters";      // 所在小区
    public static final String QRespectiveArea = "query_respective_area";        // 所属区
    public static final String QStater = "query_stater";                 // 是否已租

    public static final String ONAME = "owner_name";                        // 房主姓名
    public static final String OSEX = "owner_sex";                          // 房主性别
    public static final String OAGE = "owner_age";                          // 房主年龄
    public static final String OHID = "owner_id_house";                     // 房号id
    public static final String OPNUM = "owner_pnumber";                     // 房主联系方式
    public static final String OIDCard = "owner_id_number";                 // 房主身份证号
    public static final String OADDR = "owneraddress";                      // 房主地址
    public static final String OID = "owner_houseid";                       // 房屋id

    public static final String TNAME = "tenant_name";                        // 房客姓名
    public static final String TSEX = "tenant_sex";                          // 房客性别
    public static final String TAGE = "tenant_age";                          // 房客年龄
    public static final String TIDCard = "tenant_id_number";                 // 房客身份证号
    public static final String TMINRENT = "tenant_minrent";                  // 最小租金
    public static final String TMAXRENT = "tenant_maxrent";                  // 最大租金
    public static final String TAPART = "tenant_apart";                      // 户型
    public static final String TMINArea = "tenant_add_minarea";              // 最小面积
    public static final String TMAXArea = "tenant_add_maxarea";              // 最大面积
    public static final String TRQuarters = "tenant_add_rquarters";          // 所在小区
    public static final String TRArea = "tenant_add_rarea";                  // 所属区
    public static final String TPNUM = "tenant_pnumber";                     // 房客联系方式
    public static final String TADDR = "tenant_address";                     // 房客地址

    public static final String CN = "contract_number";       // 合同号
    public static final String HN = "house_number";          // 房号
    public static final String HA = "house_address";         // 房屋地址
    public static final String ON =	"owner_name";            // 房主姓名
    public static final String OP = "owner_pnumber";         // 房主电话
    public static final String OI = "owner_idnumber";        // 房主身份证号
    public static final String RNA = "renter_name";          // 租用人姓名
    public static final String RNU = "renter_number";        // 租用人电话
    public static final String RI = "renter_idnumber";       // 租用人身份证号
    public static final String R = "rent";                   // 租金
    public static final String LT = "lease_term";            // 租期
    public static final String A = "agent";                  // 中介人
    public static final String AF = "agency_fee";            // 中介费
    public static final String CD = "contract_date";         // 合同日期
    public static final String CP = "contract_photo";        // 合同照片

    public static final String QMIND = "query_min_date";     // 最早租期
    public static final String QMAXD = "query_max_date";     // 最晚租期

}
