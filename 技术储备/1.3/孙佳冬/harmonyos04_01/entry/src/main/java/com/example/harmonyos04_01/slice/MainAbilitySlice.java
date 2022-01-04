package com.example.harmonyos04_01.slice;

import com.example.harmonyos04_01.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 该slice所对应的界面配置文件
        super.setUIContent(ResourceTable.Layout_ability_main);
        
        //  获取按钮01
        Button button_01 = (Button) this.findComponentById(ResourceTable.Id_button_01);

        // 设置按钮的单击事件
        button_01.setClickedListener(this::ClickedListenerButton01);


        //  获取按钮01
        Button button_02 = (Button) this.findComponentById(ResourceTable.Id_button_02);

        // 设置按钮的单击事件
        button_02.setClickedListener(this::ClickedListenerButton02);

        //  获取按钮01
        Button button_03 = (Button) this.findComponentById(ResourceTable.Id_button_03);

        // 设置按钮的单击事件
        button_03.setClickedListener(this::ClickedListenerButton03);
        
        
    }

    @Override
    protected void onResult(int requestCode, Intent resultIntent) {
        if (requestCode == 2000) {
            String backcontent = resultIntent.getStringParam("backcontent");
            ToastDialog toastDialog = new ToastDialog(this);
            toastDialog.setText(backcontent);
            toastDialog.setAlignment(LayoutAlignment.CENTER);
            toastDialog.show();
        }
    }


    private void ClickedListenerButton03(Component component) {
        Intent intent = new Intent();
        this.presentForResult(new MainAbilitySlice04(),intent,2000);
    }

    private void ClickedListenerButton02(Component component) {
        // 同一个Ability里  页面之间的跳转
        Intent intent = new Intent();
        intent.setParam("name", "cjgong");
        intent.setParam("age", "20");
        this.present(new MainAbilitySlice03(),intent);
    }

    private void ClickedListenerButton01(Component component) {
        // 同一个Ability里  页面之间的跳转
        Intent intent = new Intent();
        this.present(new MainAbilitySlice02(),intent);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
