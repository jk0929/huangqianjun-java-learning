package test;

import node.UtilsLine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/12/25.
 * 处理node.txt文件中数据
 * node.txt表示：M19:w88->c51->w91,true
 *              消息M19由w88生成，最终成功传输到目的节点
 * 处理之后的结果：w88:11
 *               w88节点生成消息，并生成传输到目的节点，个数为：11
 */
public class NodeDemoTwo {

//    核心方法
    public static void dealNodeTxt() throws IOException {
        List<node.Message> messages = readLine(node.UtilsLine.fileName);
        List<node.Node> nodes = calNodeDelivery(messages);
        for (node.Node node:nodes){
            UtilsLine.writeOne(node.toString(), UtilsLine.fileNameThree);
        }
    }

    public static List<node.Node> calNodeDelivery(List<node.Message> messages){
        if (messages == null || messages.size() == 0){
            return null;
        }
        List<node.Node> nodes = new ArrayList<node.Node>(126);
        List<String> nodeIdList = new ArrayList<String>(messages.size()+1);
        for (int i=0;i<messages.size();i++){
            node.Message message = messages.get(i);
            String nodeId = message.getFromId();
            Integer totalNum = 1;
            node.Node node = new node.Node();
            if (!nodeIdList.contains(nodeId)){
                for (int j=i+1;j<messages.size();j++){
                    String nextNodeId = messages.get(j).getFromId();
                    if (nodeId.equals(nextNodeId)){
                        totalNum++;
                    }
                }
                nodeIdList.add(nodeId);
                node.setId(nodeId);
                node.setTotalNum(totalNum);
                node.setSuccessNum(0);
                if (node != null){
                    nodes.add(node);
                }
            }
        }
        return nodes;
    }

    public static List<node.Message> readLine(String filePath){
        List<node.Message> list = new ArrayList<node.Message>();
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    dealDate(lineTxt, list);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }

//    处理node.txt文件里面数据
    private static void dealDate(String line,List<node.Message> messages){
        if (line.equals(null) || line.equals("")){
            return;
        }
        node.Message message = new node.Message();

        Integer fistNum = line.indexOf(":");
        String messageId = line.substring(0,fistNum);
        message.setId(messageId);

        Integer twoNum = line.indexOf("-");
        String fromId = line.substring(fistNum+1,twoNum);
        message.setFromId(fromId);

        Integer threeNum = line.lastIndexOf(">");
        Integer fourNum = line.indexOf(",");
        String toId = line.substring(threeNum+1,fourNum);
        message.setToId(toId);
        messages.add(message);
    }
}
