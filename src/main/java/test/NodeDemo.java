package test;

import node.Message;
import node.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/12/20.
 * 统计每个节点的消息成功率
 */
public class NodeDemo {
    public static void main(String[] args){
        String fileName = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/node.txt";
        List<Message> messages = readLine(fileName);
        List<Node> nodes = calNodeDelivery(messages);
        for (Node node:nodes){
            System.out.println(node);
        }
        System.out.println(nodes.size());
    }
    public static List<Node> calNodeDelivery(List<Message> messages){
        if (messages == null || messages.size() == 0){
            return null;
        }
        List<Node> nodes = new ArrayList<Node>(126);
        List<String> nodeIdList = new ArrayList<String>(messages.size()+1);
        for (int i=0;i<messages.size();i++){
            Message message = messages.get(i);
            String nodeId = message.getFromId();
            Integer successNum = 0;
            Integer totalNum = 0;
            Node node = new Node();
            if (!nodeIdList.contains(nodeId)){
                for (int j=i+1;j<messages.size();j++){
                    String nextNodeId = messages.get(j).getFromId();
                    if (nodeId.equals(nextNodeId)){
                        if (messages.get(j).getSucc() == true){
                            successNum++;
                        }
                        totalNum++;
                    }
                }
                nodeIdList.add(nodeId);
                node.setId(nodeId);
                node.setSuccessNum(successNum);
                node.setTotalNum(totalNum);
                if (node != null){
                    nodes.add(node);
                }
            }
        }
        return nodes;
    }


    public static List<Message> readLine(String filePath){
        List<Message> list = new ArrayList<Message>();
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

    private static void dealDate(String line, List<Message> messages){
        if (line.equals(null) || line.equals("")){
            return;
        }
        Message message = new Message();

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

        String isSucc = line.substring(fourNum+1);
        Boolean isSuccess = false;
        if (!isSucc.equals(null)){
            if (isSucc.equals("false")){
                 isSuccess = false;
            }else if (isSucc.equals("true")){
                 isSuccess = true;
            }
        }
        message.setSucc(isSuccess);
        messages.add(message);
    }


}
