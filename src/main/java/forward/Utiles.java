package forward;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2018/12/30.
 */
public class Utiles {

    //分隔处理：p14:4.0,4.0
    public static ForwardNode dealLine(String line){
        if (line == null || line.equals("")){
            return null;
        }
        ForwardNode node = new ForwardNode();
        Integer firstIndex = line.indexOf(":");
        String nodeName = line.substring(0, firstIndex);
        node.setNodeName(nodeName);

        Integer twoIndex =  line.indexOf(",");
        String forwardStr = line.substring(firstIndex+1,twoIndex);
        node.setForward(Double.parseDouble(forwardStr));

        String avgNumStr = line.substring(twoIndex+1);
        node.setAvgNum(Double.parseDouble(avgNumStr));
        return node;
    }

    //处理读取文件内容到list中
    public static Map<String, ForwardNode> readFile(String filePath){
        Map<String, ForwardNode> map = new HashMap<String, ForwardNode>(126);
        ForwardNode node = null;
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    node = dealLine(lineTxt);
                    if (node != null && node.getForward()>node.getAvgNum()){
                        map.put(node.getNodeName(), node);
                    }
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return map;
    }

}
