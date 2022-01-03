package edu.xkd.slice;

import edu.xkd.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        // 获取按钮对象
        Button button01_01 = (Button) this.findComponentById(ResourceTable.Id_button01_01);

        // 给按钮对象添加单击事件
        button01_01.setClickedListener(this::ClickedListenerButton01_01);


        // 获取按钮对象
        Button button01_02 = (Button) this.findComponentById(ResourceTable.Id_button01_02);

        // 给按钮对象添加单击事件
        button01_02.setClickedListener(this::ClickedListenerButton01_02);
    }

    private void ClickedListenerButton01_02(Component component) {
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withAction("ability02slice02")
                .build();
        intent.setOperation(operation);
        this.startAbility(intent);
    }

    private void ClickedListenerButton01_01(Component component) {
        Intent intent = new Intent();

        // 通过Intent中的OperationBuilder类构造operation对象，指定设备标识（空串表示当前设备）、应用包名、Ability名称
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName("edu.xkd")
                .withAbilityName("edu.xkd.Ability02")
                .build();

        // 把operation设置到intent中
        intent.setOperation(operation);
        startAbility(intent);
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
