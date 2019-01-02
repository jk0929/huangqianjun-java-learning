package forwardNodes;

import node.UtilsLine;

import java.io.IOException;

/**
 * Created by Admin on 2019/1/1.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        //清除SAW.txt文件内容
        UtilsLine.clear(UtilesInfo.fileNameSaw);
    }
}
