package forward;

import node.UtilsLine;

import java.io.IOException;

/**
 * Created by Admin on 2018/12/30.
 * 清除forward.txt文件所有内容
 */
public class ClearFile {
    public static void main(String[] args) throws IOException {
        String fileName = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/forward.txt";
//        String fileNameTwo = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/forwardNew.txt";
        UtilsLine.clear(fileName);
    }
}
