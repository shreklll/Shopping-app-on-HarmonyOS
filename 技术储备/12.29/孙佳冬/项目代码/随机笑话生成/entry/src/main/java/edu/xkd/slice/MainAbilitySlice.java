package edu.xkd.slice;

import edu.xkd.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class MainAbilitySlice extends AbilitySlice {
    Text text_01=null;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        // 获取布局里的文本框和按钮对象
        text_01= (Text) this.findComponentById(ResourceTable.Id_text_01);
        Button button_01= (Button) this.findComponentById(ResourceTable.Id_button_01);
        // 给按钮对象设置单击事件
        button_01.setClickedListener(this::clickedListenerButton);

    }
    // 按钮单击时，执行的方法
    private void clickedListenerButton(Component component) {
        // 获取本地文件里的内容（base/profile）
        BufferedReader br=null;
        StringBuffer  sb=null;     // 保存文件里的内容
        try {
            sb=new StringBuffer();
            Resource resource = this.getResourceManager().getResource(ResourceTable.Profile_joke);
            // 由于文件里的内容可以全部转换成字符   以行为单位效率高     inputstream——reader——bufferread
            br = new BufferedReader(new InputStreamReader(resource));
            String line_content=null;
            while((line_content=br.readLine())!=null){
                sb.append(line_content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotExistException e) {
            e.printStackTrace();
        }finally{
            try {
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 把获取到得切割成多个元素  ---
        String fileContent = sb.toString();
        String[] fileContentArray = fileContent.split("---");
        //  随机选择一个元素
        Random random = new Random();
        int index = random.nextInt(fileContentArray.length);
        String textInfo=fileContentArray[index];

        //  把选择好的元素显示在文件框
        text_01.setText(textInfo);

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
