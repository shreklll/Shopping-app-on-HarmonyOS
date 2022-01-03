package com.example.harmonyos04_01.slice;

import com.example.harmonyos04_01.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class MainAbilitySlice02 extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 该slice所对应的界面配置文件
        super.setUIContent(ResourceTable.Layout_ability_main_02);

        // 获取返回按钮
        Button button02_01= (Button) this.findComponentById(ResourceTable.Id_button_02_01);
        // 添加单击事件
        button02_01.setClickedListener(this::ClickedListenerButton02_01);

    }

    private void ClickedListenerButton02_01(Component component) {
        // 关闭当前的页面（当前页面是由主页面跳转过来的，所以关闭当前页面）
        this.terminate();
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
