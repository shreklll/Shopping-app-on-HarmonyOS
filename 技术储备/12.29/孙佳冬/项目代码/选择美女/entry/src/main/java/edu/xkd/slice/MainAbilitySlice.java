package edu.xkd.slice;

import edu.xkd.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainAbilitySlice extends AbilitySlice {
    Image image_01=null;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        // 获取布局里的对象
        Button button_01= (Button) this.findComponentById(ResourceTable.Id_button_01);
        image_01= (Image) this.findComponentById(ResourceTable.Id_image_01);

        // 为按钮组件添加单击事件
        button_01.setClickedListener(this::clickedListenerButton);
    }
    //  单击按钮执行的方法
    private void clickedListenerButton(Component component) {
        // 获取所有的图片对象然后保存到容器里
        List<Integer> intList = new ArrayList<>();
        intList.add(ResourceTable.Media_girl1);
        intList.add(ResourceTable.Media_girl2);
        intList.add(ResourceTable.Media_girl3);
        intList.add(ResourceTable.Media_girl4);
        intList.add(ResourceTable.Media_girl5);
        intList.add(ResourceTable.Media_girl6);
        intList.add(ResourceTable.Media_girl7);
        intList.add(ResourceTable.Media_girl8);
        intList.add(ResourceTable.Media_girl9);
        // 随机获取容器里的一个元素
        Random random = new Random();
        int indexImage = random.nextInt(intList.size());

        // 把获取到得元素显示image组件里
        image_01.setImageAndDecodeBounds(intList.get(indexImage));
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
