package node;


import java.io.IOException;

/**
 * Created by Admin on 2018/12/27.
 * 程序入口
 */
public class MainDemo {
    public static void main(String[] args) throws IOException {
        //清除3个文件内容
        UtilsLine.calBefore();

        //处理num.txt文件
        Deal dealNum = new DealNum();
        dealNum.dealTxt();
//        test.NodeThree.dealNumTxt();

        //处理node.txt文件
        dealNum = new DealNode();
        dealNum.dealTxt();
//       test.NodeDemoTwo.dealNodeTxt();

        //统计节点消息成功率
        NodeDeliveryProb.calDeliveryProb();

        //最后将node.txt,num.txt文件内容清理
        UtilsLine.clearEnd();
    }
}
