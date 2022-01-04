package edu.xkd;

import edu.xkd.slice.Ability02Slice;
import edu.xkd.slice.Ability02Slice02;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class Ability02 extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(Ability02Slice.class.getName());

        this.addActionRoute("ability02slice02", Ability02Slice02.class.getName());
    }
}
