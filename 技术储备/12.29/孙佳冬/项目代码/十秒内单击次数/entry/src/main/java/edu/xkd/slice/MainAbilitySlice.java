package edu.xkd.slice;

import edu.xkd.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;

public class MainAbilitySlice extends AbilitySlice {
    boolean flag = true;         //  表示是否为第一次单击按钮  true 表示是第一次  false 表示不是第一次
    int num = 0;
    Button button_01 = null;
    Text text_01 = null;
    long startTime = 0;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        // 获取布局中的组件对象
        button_01 = (Button) this.findComponentById(ResourceTable.Id_button_01);
        text_01 = (Text) this.findComponentById(ResourceTable.Id_text_01);


        // 为按钮对象添加单击事件
        button_01.setClickedListener(this::clickedListenerButton);


    }

    private void clickedListenerButton(Component component) {
        num++;
        if (flag) {
            // 非第一次点击按钮
            // 文本框：显示在10秒内的点击次数
            this.flag = false;
            text_01.setText(num + "");
            button_01.setText("疯狂点我");
            startTime = System.currentTimeMillis();
        } else {
            long endTime = System.currentTimeMillis();
            if (endTime - startTime <= 10000) {
                //  在10秒之内
                // 文本框显示当前点击次数
                text_01.setText(num + "");
            } else {
                //  不在10秒之内
                //  文本框显示最终点击次数
                //  按钮显示结束，并且按钮能被单击
                text_01.setText("恭喜您：最终点击" + num + "次。");
                button_01.setTextColor(Color.RED);
                button_01.setText("结束");
                button_01.setClickable(false);   // 按钮不能单击
            }
        }
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
