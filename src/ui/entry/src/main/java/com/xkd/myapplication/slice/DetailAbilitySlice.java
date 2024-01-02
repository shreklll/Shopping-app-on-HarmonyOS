package com.xkd.myapplication.slice;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xkd.myapplication.ResourceTable;
import com.xkd.myapplication.beans.Product;
import com.xkd.myapplication.beans.ProductItem;
import com.xkd.myapplication.beans.User;
import com.xkd.myapplication.utils.ApiUtil;
import com.xkd.myapplication.utils.HttpRequestUtil;
import com.xkd.myapplication.utils.ImgContainUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.dispatcher.task.TaskPriority;

import java.sql.SQLOutput;
import java.util.List;

public class DetailAbilitySlice extends AbilitySlice {
    private Gson gson = new Gson();
    Product prod;
    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_detail);

        ImgContainUtil imgContainUtil = new ImgContainUtil();
        System.out.println("================"+imgContainUtil.listImg.get(1));
        Object productId = intent.getParams().getParam("productId");
        String s = String.valueOf(productId);
        Object userAccount = intent.getParams().getParam("userAccount");
        String uAccount = String.valueOf(userAccount);
        System.out.println(productId);
        DirectionalLayout detailLayout = findComponentById(ResourceTable.Layout_ability_detail);
        getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(() -> {
            System.out.println(ApiUtil.url + "/api/tradeInformation?id="+s);
            String s1 = HttpRequestUtil.sendGetRequest(this, ApiUtil.url + "/api/tradeInformation?id="+s);
            List<Product> list = gson.fromJson(s1, new TypeToken<List<Product>>() {
            }.getType());
            Product product = list.get(0);
            prod = product;
            System.out.println("================"+product);
            System.out.println(ApiUtil.url + "/api/insertById?id="+prod.getId());
            getUITaskDispatcher().asyncDispatch(()->{
                Text textPrice = findComponentById(ResourceTable.Id_product_detail_price_text);
                Text textAtt = findComponentById(ResourceTable.Id_product_detail_att_text);
                Text textName = findComponentById(ResourceTable.Id_product_detail_name_text);
                System.out.println(textPrice.getText());
                Image img = findComponentById(ResourceTable.Id_product_detail_image);
                textName.setText(product.getName());
                textPrice.setText("￥"+product.getPrice());
                textAtt.setText(product.getIntroduce());
                img.setPixelMap(imgContainUtil.listImg.get(Integer.parseInt(product.getId())));
            });

        });

        Button shopcartBtn = findComponentById(ResourceTable.Id_shopcart_add_button);
        shopcartBtn.setClickedListener(component -> {
            getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(()->{
                HttpRequestUtil.sendGetRequest(this, ApiUtil.url + "/api/insertById?id="+prod.getId()+"&account="+uAccount);
                System.out.println(ApiUtil.url + "/api/insertById?id="+prod.getId()+"========");
                System.out.println("添加成功");
            });

        });
    }
}
