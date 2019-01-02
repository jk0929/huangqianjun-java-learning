package node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/12/27.
 */
public class DealNode implements Deal {

    public void dealTxt() throws IOException {
        List<Message> messages = UtilsLine.dealLine(UtilsLine.fileNameOne, this);
        List<Node> nodes = calDate(messages);
        if (nodes==null || nodes.size()==0){
            return;
        }
        for (Node node:nodes){
            UtilsLine.writeOne(node.toString(), UtilsLine.fileNameTwo);
        }
    }

    public List<Node> calDate(List<Message> messages) {
        if (messages == null || messages.size() == 0){
            return null;
        }
        List<String> nodeIdList = new ArrayList<String>(messages.size()+1);
        List<Node> nodes = new ArrayList<Node>(126);
        for (int i=0;i<messages.size();i++){
            Message message = messages.get(i);
            String nodeId = message.getFromId();
            Integer totalNum = 1;
            Node node = new Node();
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
                if (node != null){
                    nodes.add(node);
                }
            }
        }
        return nodes;
    }

    public void dealDate(String line, List<Message> messages) {
        if (line.equals(null) || line.equals("")){
            return;
        }
        Message message = new Message();

        Integer fistNum = line.indexOf(":");
        String messageId = line.substring(0,fistNum);
        message.setId(messageId);

        String fromId = line.substring(fistNum+1);
        message.setFromId(fromId);
        messages.add(message);
    }
}
