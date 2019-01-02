package node;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by Admin on 2018/12/27.
 * 节点消息成功率处理
 * */
public class NodeDeliveryProb {

    //统计节点消息，并保存到输入指定文件内：deliveryPro.txt
    public  static void calDeliveryProb() throws IOException {
        List<Node> nodesOne = UtilsLine.readLine(UtilsLine.fileNameTwo);
        List<Node> nodesTwo = UtilsLine.readLine(UtilsLine.fileNameThree);
        List<NodePro> list = UtilsLine.calDeliveryPro(nodesOne, nodesTwo);
        if (list==null || list.size()==0){
            return;
        }
        String value = getbzCha(list);
        System.out.println(value);
    }

    public static String  getbzCha(List<NodePro> pros){
        if (pros == null){
            return "0.0";
        }
        Double sum = 0.0;
        for (int i=0;i<pros.size();i++){
            sum = sum + pros.get(i).getDeliveryPro();
        }
        Double avg = 1.0*sum/pros.size();
        Double chazhi = 0.0;
        for (int j=0;j<pros.size();j++){
            chazhi = chazhi + Math.pow(pros.get(j).getDeliveryPro()-avg, 2);
        }
        Double bzcha = Math.sqrt(1.0*chazhi/pros.size());
        DecimalFormat df = new DecimalFormat("###.###");
        String value = df.format(bzcha);
        return value;
    }
}
