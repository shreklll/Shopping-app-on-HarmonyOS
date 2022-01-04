package com.example.harmonyos04_01.slice;

import com.example.harmonyos04_01.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

public class MainAbilitySlice03 extends AbilitySlice {
    Text text03_01=null;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 该slice所对应的界面配置文件
        super.setUIContent(ResourceTable.Layout_ability_main_03);

        text03_01= (Text) this.findComponentById(ResourceTable.Id_text_03_01);
        Button button03_01= (Button) this.findComponentById(ResourceTable.Id_button_03_01);
        Button button03_02= (Button) this.findComponentById(ResourceTable.Id_button_03_02);
        button03_01.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                String name = intent.getStringParam("name");
                String age = intent.getStringParam("age");
                text03_01.setText("传递内容：name="+name+";age="+age+"。");
            }
        });
        button03_02.setClickedListener(this::ClickedListenerButton03_02);

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
