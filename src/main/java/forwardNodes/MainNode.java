package forwardNodes;


import node.UtilsLine;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2018/6/18.
 * 计算指定算路的标准差
 */
public class MainNode {

    public static void main(String[] args) throws IOException {

        //构建5个采集数据文件
        List<String> files =  UtilesInfo.buildFiles();

        //采集5个对应数据文件内容
        Map<String, List<Info>> map = UtilesNodes.readFiles(files);

        //处理5个文件数据内容,并对应数据放入infoMap中
        UtilesInfo.getValues(map);

        //计算标准差，输出5种算法的结果
        String string = UtilesInfo.getbzChas();
        System.out.println(string);

        //清除5种算法的文件，除了SAW.txt文件
//        UtilesInfo.clearFiles(files);
    }
}
