package node;

import node.Deal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/12/27.
 */
public class DealNum implements Deal {

    public void dealTxt() throws IOException {
        List<Message> messages = UtilsLine.dealLine(UtilsLine.fileName, this);
        List<Node> nodes = calDate(messages);
        if (nodes==null || nodes.size()==0){
            return;
        }
        for (Node node:nodes){
            UtilsLine.writeOne(node.toString(), UtilsLine.fileNameThree);
        }
    }

    public List<Node> calDate(List<Message> messages) {
        if (messages == null || messages.size() == 0){
            return null;
        }
        List<Node> nodes = new ArrayList<Node>(126);
        List<String> nodeIdList = new ArrayList<String>(messages.size()+1);
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
                node.setSuccessNum(0);
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
