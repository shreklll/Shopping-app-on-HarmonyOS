package edu.xkd.slice;

import edu.xkd.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;

public class MainAbilitySlice extends AbilitySlice {
    Image image_01=null;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        //获取界面布局里的组件
        DirectionalLayout layout_01= (DirectionalLayout) this.findComponentById(ResourceTable.Id_layout_01);
        image_01= (Image) this.findComponentById(ResourceTable.Id_image_01);
        // 给布局对象添加双击事件
        layout_01.setDoubleClickedListener(this::doubleClickedListenerLayout);
        image_01.setDoubleClickedListener(this::doubleClickedListenerImage);


    }
    // 取消点赞
    private void doubleClickedListenerImage(Component component) {
        image_01.setImageAndDecodeBounds(ResourceTable.Media_white);
    }

    // 点赞
    private void doubleClickedListenerLayout(Component component) {
        image_01.setImageAndDecodeBounds(ResourceTable.Media_red);
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
