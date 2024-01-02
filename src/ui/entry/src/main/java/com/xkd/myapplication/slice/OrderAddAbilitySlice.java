package com.xkd.myapplication.slice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xkd.myapplication.ResourceTable;
import com.xkd.myapplication.beans.Product;
import com.xkd.myapplication.utils.ApiUtil;
import com.xkd.myapplication.utils.HttpRequestUtil;
import com.xkd.myapplication.utils.ImgContainUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

import java.util.List;

public class OrderAddAbilitySlice extends AbilitySlice {
    private Gson gson = new Gson();
    //当前订单包含的购物车记录的ID，多个ID以,隔开
    private String account;
    private String userName;
    ImgContainUtil imgContainUtil ;


    protected void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_order_add);
        Object userAccount = intent.getParams().getParam("userAccount");
        Object user = intent.getParams().getParam("userName");
        account = String.valueOf(userAccount);
        userName = String.valueOf(user);
        imgContainUtil = new ImgContainUtil();

        getUITaskDispatcher().asyncDispatch(()->{
            Text textName = findComponentById(ResourceTable.Id_addr_name_text);
            textName.setText(userName);

        });

        getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(()->{
            String s = HttpRequestUtil.sendGetRequest(this, ApiUtil.url + "/api/seacherShopCart?account=" + account);
            List<Product> list = gson.fromJson(s, new TypeToken<List<Product>>() {
            }.getType());
            System.out.println(list);
            getUITaskDispatcher().asyncDispatch(()->{
                TableLayout table = (TableLayout) findComponentById(ResourceTable.Id_order_add_list_table);
                table.removeAllComponents();
                Text priceText = (Text) findComponentById(ResourceTable.Id_order_total_price_text);
                for (Product product : list) {
                    Component template = LayoutScatter.getInstance(this).parse(ResourceTable.Layout_order_item_template,null,false);
                    Image image = (Image) template.findComponentById(ResourceTable.Id_order_item_image); //图片
                    Text text1 = (Text) template.findComponentById(ResourceTable.Id_order_item_product_name_text);
                    Text text2 = (Text) template.findComponentById(ResourceTable.Id_order_item_product_price_text);

                    image.setPixelMap(imgContainUtil.listImg.get(Integer.parseInt(product.getId())));
                    text1.setText(product.getName());
                    text2.setText(product.getPrice()+"");
                    table.addComponent(template);
                }
            });
        });

    }
}
