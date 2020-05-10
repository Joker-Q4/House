package cn.edu.sau.joker;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyInterface  {

    /**
     * 登陆接口
     */
    public interface onLoginCallback {
        void Success();
        void Fail();
    }

    /**
     * 查询工作人员接口
     */
    public interface onQueryWorkersCallback {
        void Success(JSONArray JsonArray);
        void Fail();
    }

    /**
     * 查询房屋列表
     */
    public interface onQueryHousesCallback {
        void Success(JSONArray JsonArray);
        void Fail();
    }

    /**
     * 统计信息
     */
    public interface onStatisticsCallback {
        void Success(JSONObject jsonObject);
        void Fail();
    }

    /**
     * 查询房主列表
     */
    public interface onQueryOwnersCallback {
        void Success(JSONArray JsonArray);
        void Fail();
    }

    /**
     * 查询房客列表
     */
    public interface onQueryTenantsCallback {
        void Success(JSONArray JsonArray);
        void Fail();
    }

    /**
     * 查询合同列表
     */
    public interface onQueryContractsCallback {
        void Success(JSONArray JsonArray);
        void Fail();
    }

    /**
     * 查询单个房屋信息
     */
    public interface onQueryHouseOneCallback {
        void Success(JSONObject jsonObject);
        void Fail();
    }

    /**
     * 查询房主详细信息
     */
    public interface onQueryOwnerOneCallback {
        void Success(JSONObject jsonObject);
        void Fail();
    }

    /**
     * 查询租客的详细信息
     */
    public interface onQueryTenantOneCallback {
        void Success(JSONObject jsonObject);
        void Fail();
    }

    /**
     * 查询单个合同信息
     */
    public interface onQueryContractOneCallback {
        void Success(JSONObject jsonObject);
        void Fail();
    }

    /**
     * 修改房屋信息
     */
    public interface onUpdateHouseCallback {
        void Success();
        void Fail();
    }

    /**
     * 删除房屋信息
     */
    public interface onDeleteHouseCallback {
        void Success();
        void Fail();
    }

    /**
     * 修改合同信息
     */
    public interface onUpdateContractCallback {
        void Success();
        void Fail();
    }

    /**
     * 删除合同信息
     */
    public interface onDeleteContractCallback {
        void Success();
        void Fail();
    }

    /**
     * 修改房主信息
     */
    public interface onUpdateOwnerCallback {
        void Success();
        void Fail();
    }

    /**
     * 删除房主信息
     */
    public interface onDeleteOwnerCallback {
        void Success();
        void Fail();
    }

    /**
     * 修改房客信息
     */
    public interface onUpdateTenantCallback {
        void Success();
        void Fail();
    }

    /**
     * 删除房客信息
     */
    public interface onDeleteTenantCallback {
        void Success();
        void Fail();
    }
}
