package com.example.harmonyos04_01;

import com.example.harmonyos04_01.slice.MainAbilitySlice;
import com.example.harmonyos04_01.slice.MainAbilitySlice02;
import com.example.harmonyos04_01.slice.MainAbilitySlice03;
import com.example.harmonyos04_01.slice.MainAbilitySlice04;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 设置类MainAbilitySlice为主slice
        super.setMainRoute(MainAbilitySlice.class.getName());

        // 添加普通slice到该ability
        this.addActionRoute("mainabilityslice02", MainAbilitySlice02.class.getName());
        this.addActionRoute("mainabilityslice03", MainAbilitySlice03.class.getName());
        this.addActionRoute("mainabilityslice04", MainAbilitySlice04.class.getName());
    }
}
