package com.xkd.myapplication.slice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xkd.myapplication.ResourceTable;
import com.xkd.myapplication.beans.Product;
import com.xkd.myapplication.beans.User;
import com.xkd.myapplication.utils.ApiUtil;
import com.xkd.myapplication.utils.HttpRequestUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.TextField;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

import java.util.List;

public class LoginAbilitySlice extends AbilitySlice {
    //    String s ="http://j9v94z.natappfree.cc/api/loadstate";
    private Gson gson = new Gson();
    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_login);
        TextField tf1 =  findComponentById(ResourceTable.Id_login_name_textfield);
        TextField tf2 =  findComponentById(ResourceTable.Id_login_pwd_textfield);
        Button btn = findComponentById(ResourceTable.Id_login_btn);
        btn.setClickedListener(component -> {
            TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
            globalTaskDispatcher.asyncDispatch(()->{
                String account = tf1.getText();
                String url =ApiUtil.url +"/api/loadstate?account="+account;
                System.out.println(url);
                String s = HttpRequestUtil.sendGetRequest(this,url);
                List<User> list = gson.fromJson(s, new TypeToken<List<User>>() {
                }.getType());
                System.out.println(list);
                User user = list.get(0);
                System.out.println(user);
                if (tf1.getText().equals(user.getAccout()) && tf2.getText().equals(user.getPassword())) {
                    Intent intent1 = new Intent();
                    intent1.setParam("userAccount",user.getAccout());
                    present(new MainAbilitySlice(),intent1);
                }
            });

        });
    }
}
