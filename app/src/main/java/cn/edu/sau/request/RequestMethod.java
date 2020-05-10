package cn.edu.sau.request;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.url.UrlString;
import cn.edu.sau.response.ResponseMethod;

public class RequestMethod {

    // 工作人员登录请求方法
    public static void RequestLogin(final Activity activity, final JSONObject jsonObject, final MyInterface.onLoginCallback onLoginCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.Login);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onLoginCallback.Success();
                    else
                        onLoginCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

 //               Log.e("1231321321", "返回结果是:" + resultObject.toString());
                //解析登录结果
            }
        }).start();
    }

    /**
     * 管理员登陆请求方法
     */
    public static void adminRequestLogin(final Activity activity, final JSONObject jsonObject, final MyInterface.onLoginCallback onLoginCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.adminLogin);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onLoginCallback.Success();
                    else
                        onLoginCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 查询所有工作人员信息的方法
     */
    public static void queryWorkerList(final Activity activity, final MyInterface.onQueryWorkersCallback onQueryWorkersCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONArray JsonArray = ResponseMethod.sendQuery(UrlString.queryWorkerList);
                // 获取服务器的信息并打印
                Log.e("从服务器获取信息：", JsonArray.toString());
                // 不为空则查询成功
                if(!JsonArray.toString().equals(""))
                    onQueryWorkersCallback.Success(JsonArray);
                else
                    onQueryWorkersCallback.Fail();
            }
        }).start();
    }

    /**
     * 添加一个工作人员
     */
    public static void addWorkerToList(final Activity activity, final JSONObject jsonObject, final MyInterface.onLoginCallback onLoginCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.addWorkerToList);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onLoginCallback.Success();
                    else
                        onLoginCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 修改工作人员密码
     */
    public static void updateWokerList(final Activity activity, final JSONObject jsonObject, final MyInterface.onLoginCallback onLoginCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.updateWokerList);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onLoginCallback.Success();
                    else
                        onLoginCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 删除一个工作人员
     */
    public static void delWorkerFromList(final Activity activity, final JSONObject jsonObject, final MyInterface.onLoginCallback onLoginCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.delWorkerFromList);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onLoginCallback.Success();
                    else
                        onLoginCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 录入房屋信息
     */
    public static void addHouseToList(final Activity activity, final JSONObject jsonObject, final MyInterface.onLoginCallback onLoginCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.addHouseToList);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onLoginCallback.Success();
                    else
                        onLoginCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 查询房屋列表
     */
    public static void queryHouseList(final Activity activity, final JSONObject jsonObject, final MyInterface.onQueryHousesCallback onQueryHousesCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONArray JsonArray = ResponseMethod.sendQuery(jsonObject, UrlString.queryHouseList);
                // 获取服务器的信息并打印
                if(JsonArray != null) {
                    Log.e("从服务器获取信息：", JsonArray.toString());
                    // 不为空则查询成功
                    if(!JsonArray.toString().equals(""))
                        onQueryHousesCallback.Success(JsonArray);
                    else
                        onQueryHousesCallback.Fail();
                }
            }
        }).start();
    }

    /**
     * 统计房屋信息
     */
    public static void queryHouseStatistics(final Activity activity, final MyInterface.onStatisticsCallback onStatisticsCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject jsonObject = ResponseMethod.sendQueryStatistics(UrlString.queryHouseStatistics);
                // 获取服务器的信息并打印
                if(jsonObject != null) {
                    Log.e("从服务器获取信息：", jsonObject.toString());
                    // 不为空则查询成功
                    if(!jsonObject.toString().equals(""))
                        onStatisticsCallback.Success(jsonObject);
                    else
                        onStatisticsCallback.Fail();
                }
            }
        }).start();
    }

    /**
     * 添加房主信息
     */
    public static void addOwnerToList(final Activity activity, final JSONObject jsonObject, final MyInterface.onLoginCallback onLoginCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.addOwnerToList);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onLoginCallback.Success();
                    else
                        onLoginCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 添加房客需求
     */
    public static void addTenantToList(final Activity activity, final JSONObject jsonObject, final MyInterface.onLoginCallback onLoginCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.addTenantToList);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onLoginCallback.Success();
                    else
                        onLoginCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 添加合同信息
     */
    public static void addContractToList(final Activity activity, final JSONObject jsonObject, final MyInterface.onLoginCallback onLoginCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.addContractToList);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onLoginCallback.Success();
                    else
                        onLoginCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 查询合同列表
     */
    public static void queryContractList(final Activity activity, final JSONObject jsonObject, final MyInterface.onQueryHousesCallback onQueryHousesCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONArray JsonArray = ResponseMethod.sendQuery(jsonObject, UrlString.queryContractList);
                // 获取服务器的信息并打印
                if(JsonArray != null) {
                    Log.e("从服务器获取信息：", JsonArray.toString());
                    // 不为空则查询成功
                    if(!JsonArray.toString().equals(""))
                        onQueryHousesCallback.Success(JsonArray);
                    else
                        onQueryHousesCallback.Fail();
                }
            }
        }).start();
    }

    /**
     * 统计合同信息
     */
    public static void queryContractStatistics(final Activity activity, final MyInterface.onStatisticsCallback onStatisticsCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject jsonObject = ResponseMethod.sendQueryStatistics(UrlString.queryContractStatistics);
                // 获取服务器的信息并打印
                if(jsonObject != null) {
                    Log.e("从服务器获取信息：", jsonObject.toString());
                    // 不为空则查询成功
                    if(!jsonObject.toString().equals(""))
                        onStatisticsCallback.Success(jsonObject);
                    else
                        onStatisticsCallback.Fail();
                }
            }
        }).start();
    }

    /**
     * 查询房主列表
     */
    public static void queryOwnerList(final Activity activity, final JSONObject jsonObject, final MyInterface.onQueryOwnersCallback onQueryOwnersCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONArray JsonArray = ResponseMethod.sendQuery(jsonObject, UrlString.queryOwnerList);
                // 不为空则查询成功
                if(!JsonArray.toString().equals(""))
                    onQueryOwnersCallback.Success(JsonArray);
                else
                    onQueryOwnersCallback.Fail();
            }
        }).start();
    }

    /**
     * 查询房客列表
     * @param activity
     * @param onQueryTenantsCallback
     */
    public static void queryTenantList(final Activity activity, final JSONObject jsonObject, final MyInterface.onQueryTenantsCallback onQueryTenantsCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONArray JsonArray = ResponseMethod.sendQuery(jsonObject, UrlString.queryTenantList);
                // 获取服务器的信息并打印
                Log.e("从服务器获取信息：", JsonArray.toString());
                // 不为空则查询成功
                if(!JsonArray.toString().equals(""))
                    onQueryTenantsCallback.Success(JsonArray);
                else
                    onQueryTenantsCallback.Fail();
            }
        }).start();
    }

    /**
     * 查询房屋详细信息
     */
    public static void queryHouseOne(final Activity activity, final JSONObject jsonObject, final MyInterface.onQueryHouseOneCallback onQueryHouseOneCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
//                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.Login);
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.queryHouseOne);
                // 获取服务器的信息并打印
                if(resultObject!=null)
                    onQueryHouseOneCallback.Success(resultObject);
                else
                    onQueryHouseOneCallback.Fail();
            }
        }).start();
    }

    /**
     * 查询房主详细信息
     */
    public static void queryOwnerOne(final Activity activity, final JSONObject jsonObject, final MyInterface.onQueryOwnerOneCallback onQueryOwnerOneCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
//                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.Login);
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.queryOwnerOne);
                // 获取服务器的信息并打印
                if(resultObject!=null)
                    onQueryOwnerOneCallback.Success(resultObject);
                else
                    onQueryOwnerOneCallback.Fail();
            }
        }).start();
    }

    /**
     * 查询租客的详细信息
     * @param activity
     * @param jsonObject
     * @param onQueryTenantOneCallback
     */
    public static void queryTenantOne(final Activity activity, final JSONObject jsonObject, final MyInterface.onQueryTenantOneCallback onQueryTenantOneCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
//                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.Login);
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.queryTenantOne);
                // 获取服务器的信息并打印
                if(resultObject!=null)
                    onQueryTenantOneCallback.Success(resultObject);
                else
                    onQueryTenantOneCallback.Fail();
            }
        }).start();
    }

    /**
     * 查询合同的详细信息
     * @param activity
     * @param jsonObject
     * @param onQueryContractOneCallback
     */
    public static void queryContractOne(final Activity activity, final JSONObject jsonObject, final MyInterface.onQueryContractOneCallback onQueryContractOneCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
//                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.Login);
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.queryContractOne);
                // 获取服务器的信息并打印
                if(resultObject!=null)
                    onQueryContractOneCallback.Success(resultObject);
                else
                    onQueryContractOneCallback.Fail();
            }
        }).start();
    }

    /**
     * 修改房屋信息
     * @param activity
     * @param jsonObject
     * @param onUpdateHouseCallback
     */
    public static void updateHouse(final Activity activity, final JSONObject jsonObject, final MyInterface.onUpdateHouseCallback onUpdateHouseCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.updateHouseOne);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onUpdateHouseCallback.Success();
                    else
                        onUpdateHouseCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 删除房屋信息
     * @param activity
     * @param jsonObject
     * @param onDeleteHouseCallback
     */
    public static void deleteHouse(final Activity activity, final JSONObject jsonObject, final MyInterface.onDeleteHouseCallback onDeleteHouseCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.deleteHouseOne);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onDeleteHouseCallback.Success();
                    else
                        onDeleteHouseCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 更新合同信息
     * @param activity
     * @param jsonObject
     * @param onUpdateContractCallback
     */
    public static void updateContract(final Activity activity, final JSONObject jsonObject, final MyInterface.onUpdateContractCallback onUpdateContractCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.updateContract);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onUpdateContractCallback.Success();
                    else
                        onUpdateContractCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 删除合同信息
     * @param activity
     * @param jsonObject
     * @param onDeleteContractCallback
     */
    public static void deleteContract(final Activity activity, final JSONObject jsonObject, final MyInterface.onDeleteContractCallback onDeleteContractCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.deleteContract);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onDeleteContractCallback.Success();
                    else
                        onDeleteContractCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 修改房主信息
     * @param activity
     * @param jsonObject
     * @param onUpdateOwnerCallback
     */
    public static void updateOwner(final Activity activity, final JSONObject jsonObject, final MyInterface.onUpdateOwnerCallback onUpdateOwnerCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.updateOwner);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onUpdateOwnerCallback.Success();
                    else
                        onUpdateOwnerCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 删除房主信息
     * @param activity
     * @param jsonObject
     * @param onDeleteOwnerCallback
     */
    public static void deleteOwner(final Activity activity, final JSONObject jsonObject, final MyInterface.onDeleteOwnerCallback onDeleteOwnerCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.deleteOwner);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onDeleteOwnerCallback.Success();
                    else
                        onDeleteOwnerCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 更新房客信息
     * @param activity
     * @param jsonObject
     * @param onUpdateTenantCallback
     */
    public static void updateTenant(final Activity activity, final JSONObject jsonObject, final MyInterface.onUpdateTenantCallback onUpdateTenantCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.updateTenant);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onUpdateTenantCallback.Success();
                    else
                        onUpdateTenantCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 删除房客信息
     * @param activity
     * @param jsonObject
     * @param onDeleteTenantCallback
     */
    public static void deleteTenant(final Activity activity, final JSONObject jsonObject, final MyInterface.onDeleteTenantCallback onDeleteTenantCallback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendQueryState(jsonObject, UrlString.deleteTenant);
                try {
                    if(resultObject.getString("status").equals("success"))
                        onDeleteTenantCallback.Success();
                    else
                        onDeleteTenantCallback.Fail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}