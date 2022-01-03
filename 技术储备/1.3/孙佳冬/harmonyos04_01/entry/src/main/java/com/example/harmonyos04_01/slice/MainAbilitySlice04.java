package com.example.harmonyos04_01.slice;

import com.example.harmonyos04_01.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

public class MainAbilitySlice04 extends AbilitySlice {
    String contentBack="";
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 该slice所对应的界面配置文件
        super.setUIContent(ResourceTable.Layout_ability_main_04);

        Text text04_02 = (Text) this.findComponentById(ResourceTable.Id_text_04_02);
        contentBack = text04_02.getText();
        Button button04_01= (Button) this.findComponentById(ResourceTable.Id_button_04_01);
        button04_01.setClickedListener(this::ClickedListenerButton04_01);

    }

    private void ClickedListenerButton04_01(Component component) {
        // 返回
        Intent intent = new Intent();
        intent.setParam("backcontent", contentBack);
        this.setResult(intent);
        this.terminate();
    }

    private void ClickedListenerButton03_02(Component component) {
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
