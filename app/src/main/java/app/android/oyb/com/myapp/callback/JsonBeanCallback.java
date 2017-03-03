package app.android.oyb.com.myapp.callback;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.BaseRequest;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import app.android.oyb.com.myapp.data.Convert;
import app.android.oyb.com.myapp.data.RspBase;
import okhttp3.Call;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * ================================================
 */
public abstract class JsonBeanCallback<T> extends AbsCallback<T> {

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
    /*    // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        request.headers("header1", "HeaderValue1")//
                .params("params1", "ParamsValue1")//
                .params("token", "3215sdf13ad1f65asd4f3ads1f");*/
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     * <pre>
     * OkGo.get(Urls.URL_METHOD)//
     *     .tag(this)//
     *     .execute(new DialogCallback<RspBase<ServerModel>>(this) {
     *          @Override
     *          public void onSuccess(RspBase<ServerModel> responseData, Call call, Response response) {
     *              handleResponse(responseData.data, call, response);
     *          }
     *     });
     * </pre>
     */
    @Override
    public T convertSuccess(Response response) {
        //com.lzy.demo.callback.DialogCallback<com.lzy.demo.model.RspBase<com.lzy.demo.model.ServerModel>> 得到类的泛型，包括了泛型参数
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //我们的示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        //com.lzy.demo.model.RspBase<com.lzy.demo.model.ServerModel>
        Type type = params[0];

        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
        RspBase rspBase = null;
        try {
            JsonReader jsonReader = new JsonReader(response.body().charStream());
            rspBase = Convert.fromJson(jsonReader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.close();

        return (T) rspBase;
    }

    @Override
    public void onSuccess(T t, Call call, Response response) {
        if(t != null) {
            RspBase rspBase = (RspBase) t;
            int status = rspBase.status;
            if(status == 1) {
                success((T) rspBase);
            } else {
                error(rspBase.info);
            }
        } else {
            error("网络请求失败");
        }
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
        error(e.getMessage());
    }

    public abstract void success(T t);

    public abstract void error(String message);

}