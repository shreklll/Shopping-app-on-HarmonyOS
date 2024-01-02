package com.xkd.myapplication.slice;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xkd.myapplication.ResourceTable;
import com.xkd.myapplication.beans.Product;
import com.xkd.myapplication.beans.User;
import com.xkd.myapplication.provider.TabPageSliderProvider;
import com.xkd.myapplication.utils.ApiUtil;
import com.xkd.myapplication.utils.HttpRequestUtil;
import com.xkd.myapplication.utils.ImgContainUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice implements Component.FocusChangedListener {
    String s = null;
    private Gson gson = new Gson();
    ImgContainUtil imgContainUtil ;
    User user;
    String account;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        account = (String) intent.getParams().getParam("userAccount");

        //        初始化tablist
        TabList tabList =  findComponentById(ResourceTable.Id_tab_list);
        String[] tablistTags = {"首页", "购物车", "我的"};
        for (int i = 0; i < tablistTags.length; i++) {
            TabList.Tab tab =  tabList.new Tab(this);
            tab.setText(tablistTags[i]);
            tabList.addTab(tab);
        }

        //初始化PageSlider
        List<Integer> layoutFileIds = new ArrayList<>();
        layoutFileIds.add(ResourceTable.Layout_ability_main_index);
//        layoutFileIds.add(ResourceTable.Layout_ability_main_category);
        layoutFileIds.add(ResourceTable.Layout_ability_main_shopcart);
        layoutFileIds.add(ResourceTable.Layout_ability_main_user_center);

        PageSlider pageSlider = findComponentById(ResourceTable.Id_page_slider);
        pageSlider.setProvider(new TabPageSliderProvider(layoutFileIds,this));

        //Tablist与PageSlider联动
        tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                //获取点击的菜单索引
                int index = tab.getPosition();
                //设置pageslider的索引与菜单一致
                pageSlider.setCurrentPage(index);
                if (index == 0) {
                    initIndex(pageSlider);
                } else if (index == 1) {
//                    initCategory(pageSlider);
                    initShopcart(pageSlider);
                } else if (index == 2) {
                    initUserCenter(pageSlider);
                }
            }

            @Override
            public void onUnselected(TabList.Tab tab) {

            }

            @Override
            public void onReselected(TabList.Tab tab) {

            }
        });
        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int i, float v, int i1) {

            }

            @Override
            public void onPageSlideStateChanged(int i) {

            }

            @Override
            public void onPageChosen(int i) {
                if (tabList.getSelectedTabIndex() !=  i) {
                    tabList.selectTabAt(i);
                }
            }
        });

        tabList.selectTabAt(0);


    }
    // 分类
    private void initCategory(PageSlider pageSlider) {

        TextField searchTextField = findComponentById(ResourceTable.Id_category_search_textfield);
        searchTextField.setFocusChangedListener(this);

    }
    //购物车初始化
    private void initShopcart(PageSlider pageSlider) {
        imgContainUtil = new ImgContainUtil();


        getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(()->{
            String s = HttpRequestUtil.sendGetRequest(this, ApiUtil.url + "/api/seacherShopCart?account="+account);
            List<Product> list = gson.fromJson(s, new TypeToken<List<Product>>() {
            }.getType());
            System.out.println(list);
            TableLayout container =  findComponentById(ResourceTable.Id_shopcart_list_table);
            getUITaskDispatcher().asyncDispatch(()->{
                for (Product product : list) {

                    //每个购物车信息渲染到购物车列表的模版中
                    Component template = LayoutScatter.getInstance(this).parse(ResourceTable.Layout_shopcart_item_template, null, false);
                    Image image =  template.findComponentById(ResourceTable.Id_shopcart_item_image); //商品图片
                    Text text1 =  template.findComponentById(ResourceTable.Id_shopcart_item_product_name_text); //商品名
                    Text text2 =  template.findComponentById(ResourceTable.Id_shopcart_item_product_price_text);//商品价格
                    image.setPixelMap(imgContainUtil.listImg.get(Integer.parseInt(product.getId())));
                    text1.setText(product.getName());
                    text2.setText(product.getPrice()+"");
                    container.addComponent(template);
                }
            });
        });
        Button combtn = findComponentById(ResourceTable.Id_account_button);
        combtn.setClickedListener(component -> {
            Intent intent = new Intent();
            intent.setParam("userAccount", account);
            intent.setParam("userName", user.getName());
            present(new OrderAddAbilitySlice(),intent);
        });

    }
    //首页
    private void initIndex(PageSlider pageSlider) {

        List<Integer> listImg = new ArrayList<>();
        listImg.add(ResourceTable.Media_img1);
        listImg.add(ResourceTable.Media_img01);
        listImg.add(ResourceTable.Media_img02);
        listImg.add(ResourceTable.Media_img03);

        TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
        globalTaskDispatcher.asyncDispatch(()->{
            String url =ApiUtil.url +"/api/loadstate?account="+account;
            String s = HttpRequestUtil.sendGetRequest(this,url);
            List<User> list = gson.fromJson(s, new TypeToken<List<User>>() {
            }.getType());
            System.out.println(list);
            user = list.get(0);
        });
        // 获取推荐商品数据
        TableLayout productListTable = (TableLayout) findComponentById(ResourceTable.Id_index_product_list_table);
        getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(()->{
            String s = HttpRequestUtil.sendGetRequest(this, ApiUtil.url + "/api/searchproduct");
            List<Product> list = gson.fromJson(s, new TypeToken<List<Product>>() {
            }.getType());
            System.out.println(list);

            getUITaskDispatcher().asyncDispatch(()->{
                for (Product product : list) {
                    DirectionalLayout template =
                            (DirectionalLayout) LayoutScatter.getInstance(this).parse(ResourceTable.Layout_product_list_item_template, null, false);
                    Image image = template.findComponentById(ResourceTable.Id_product_image);
                    Text text1 = template.findComponentById(ResourceTable.Id_product_price_text);
                    Text text2 = template.findComponentById(ResourceTable.Id_product_name_text);
                    text1.setText(product.getPrice());
                    text2.setText(product.getName());
                    image.setPixelMap(listImg.get(Integer.parseInt(product.getId())));
                    productListTable.addComponent(template);
                    template.setClickedListener(component -> {
                        Intent intent = new Intent();
                        intent.setParam("productId", product.getId());
                        intent.setParam("userAccount", user.getAccout());
                        System.out.println(product.getId());
                        present(new DetailAbilitySlice(),intent);
                    });

                }
            });
        });


        //监听首页的搜素输入框，点击输入框则跳转到SearchAbilitySlice
        TextField searchTextField = findComponentById(ResourceTable.Id_index_search_input);
        searchTextField.setFocusChangedListener(this);


//        Component productLayout = pageSlider.findComponentById(ResourceTable.Id_layout_product01);
//        productLayout.setClickedListener(component -> {
//            Intent intent = new Intent();
//            intent.setParam("productId", "10001");
//            this.present(new DetailAbilitySlice(),intent);
//        });
    }

    private void initUserCenter(PageSlider pageSlider) {
//          TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
//            globalTaskDispatcher.asyncDispatch(()->{
//                String url =ApiUtil.url +"/api/loadstate";
//                String s = HttpRequestUtil.sendGetRequest(this,url);
//                List<User> list = gson.fromJson(s, new TypeToken<List<User>>() {
//                }.getType());
//                System.out.println(list);
//                User user = list.get(0);
                System.out.println(user);
                getUITaskDispatcher().asyncDispatch(()->{
                    Text userName = findComponentById(ResourceTable.Id_user_name_text);
                    userName.setText(user.getName());
                });

//            });

    }


    @Override
    public void onFocusChange(Component component, boolean b) {
        if (b) {
            present(new SearchAbilitySlice(),new Intent());
        }
    }
}
