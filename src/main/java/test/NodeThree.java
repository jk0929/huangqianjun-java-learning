package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/12/25.
 * num.txt文件表示为：M1:w85
 *                  节点w85生成M1的消息
 * 整理结果为：w85:17
 *            节点w85生成17个不同消息
 */
public class NodeThree {

//    //核心方法
//    public static void dealNumTxt() throws IOException {
//        List<node.Message> messages = readLine(node.UtilsLine.fileNameOne);
////        List<node.Node> nodes = calDate(messages);
//        for (node.Node node:nodes){
//            node.UtilsLine.writeOne(node.toString(), node.UtilsLine.fileNameTwo);
//        }
//    }



//    public static List<node.Node> calDate(List<node.Message> messages){
//        if (messages == null || messages.size() == 0){
//            return null;
//        }
//        List<String> nodeIdList = new ArrayList<String>(messages.size()+1);
//        List<node.Node> nodes = new ArrayList<node.Node>(126);
//        for (int i=0;i<messages.size();i++){
//            node.Message message = messages.get(i);
//            String nodeId = message.getFromId();
//            Integer totalNum = 1;
//            node.Node node = new node.Node();
//            if (!nodeIdList.contains(nodeId)){
//                for (int j=i+1;j<messages.size();j++){
//                    String nextNodeId = messages.get(j).getFromId();
//                    if (nodeId.equals(nextNodeId)){
//                        totalNum++;
//                    }
//                }
//                nodeIdList.add(nodeId);
//                node.setId(nodeId);
//                node.setTotalNum(totalNum);
//                if (node != null){
//                    nodes.add(node);
//                }
//            }
//        }
//        return nodes;
//    }

//    public static List<node.Message> readLine(String filePath){
//        List<node.Message> list = new ArrayList<node.Message>();
//        try {
//            String encoding="GBK";
//            File file=new File(filePath);
//            if(file.isFile() && file.exists()){ //判断文件是否存在
//                InputStreamReader read = new InputStreamReader(
//                        new FileInputStream(file),encoding);//考虑到编码格式
//                BufferedReader bufferedReader = new BufferedReader(read);
//                String lineTxt = null;
//                while((lineTxt = bufferedReader.readLine()) != null){
//                    dealDate(lineTxt, list);
//                }
//                read.close();
//            }else{
//                System.out.println("找不到指定的文件");
//            }
//        } catch (Exception e) {
//            System.out.println("读取文件内容出错");
//            e.printStackTrace();
//        }
//        return list;
//    }

    //处理num.txt文件里面数据
    //M1:w85
//    private static void dealDate(String line,List<node.Message> messages){
//        if (line.equals(null) || line.equals("")){
//            return;
//        }
//        node.Message message = new node.Message();
//
//        Integer fistNum = line.indexOf(":");
//        String messageId = line.substring(0,fistNum);
//        message.setId(messageId);
//
//        String fromId = line.substring(fistNum+1);
//        message.setFromId(fromId);
//        messages.add(message);
//    }
}
