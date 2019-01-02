package forward;

import node.UtilsLine;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 2018/12/30.
 */
public class DealForward {
    public static void main(String[] args) throws IOException {
        String fileName = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/forward.txt";
        String disFileName = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/forwardNew.txt";

        //处理下读取的文件内容
        Collection<ForwardNode> nodes = Utiles.readFile(fileName).values();

        //清理下文件
        UtilsLine.clear(disFileName);
        //防止抛出空指针异常
        if (nodes == null || nodes.size() == 0){
            return;
        }
        for (ForwardNode node:nodes){
            //将内容写入指定文件
            UtilsLine.writeOne(node.toString(), disFileName);
        }
        System.out.println(nodes.size());
    }

}
