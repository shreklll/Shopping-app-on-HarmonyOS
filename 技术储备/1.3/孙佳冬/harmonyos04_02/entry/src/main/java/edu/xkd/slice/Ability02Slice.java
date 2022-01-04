package edu.xkd.slice;

import edu.xkd.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class Ability02Slice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_02);
        Button button02_01= (Button) this.findComponentById(ResourceTable.Id_button02_01);
        button02_01.setClickedListener(this::ClickedListenerButton02_01);
    }

    private void ClickedListenerButton02_01(Component component) {
        this.terminateAbility();
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
