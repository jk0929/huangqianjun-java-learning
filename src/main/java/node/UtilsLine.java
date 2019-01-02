package node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/12/27.
 */
public class UtilsLine {

    public static final  String fileName = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/data/node.txt";

    public static final  String fileNameOne = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/data/num.txt";

    //节点生成总数
    public static final  String fileNameTwo = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/data/newnum.txt";

    //节点成功传输个数
    public static final  String fileNameThree = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/data/newnode.txt";

    //节点的消息成功率
    public static final  String fileNameFour = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/data/deliveryPro.txt";

    //清除4个现有文件的所有内容
    public static void clearEnd(){
        List<String> fileNameList = new ArrayList<String>();
        fileNameList.add(UtilsLine.fileName);
        fileNameList.add(UtilsLine.fileNameOne);
        try {
            clearList(fileNameList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //整理数据时候，必须要收到先清理文件：
    // newnum.txt,newnode.txt,deliveryPro.txt
    public static void calBefore(){
        List<String> fileList = new ArrayList<String>(3);
        fileList.add(UtilsLine.fileNameTwo);
        fileList.add(UtilsLine.fileNameThree);
        fileList.add(UtilsLine.fileNameFour);
        try{
            clearList(fileList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //清理一批文件内容
    public static void clearList(List<String> fileNameList) throws IOException {
        if (fileNameList == null || fileNameList.size() == 0){
            return;
        }
        for (String fileName:fileNameList){
            clear(fileName);
        }

    }

    //清理指定文件的所有内容
    public static void clear(String fileName)throws IOException{
        FileWriter out = new FileWriter(fileName, true);
        java.io.RandomAccessFile file =   new  java.io.RandomAccessFile(fileName, "rw");
        file.setLength(0);
        //刷新IO内存流
        out.flush();
        //关闭
        out.close();
    }



    //将内容输入到指定的文件中
    public static  void writeOne(String conent,String fileName) throws IOException {
        //此处设置为true即可追加
        FileWriter out = new FileWriter(fileName, true);
        //换行
        out.write("\r\n");
        //往文件写入
        out.write(conent);
        //换行
        out.write("\r\n");
        //刷新IO内存流
        out.flush();
        //关闭
        out.close();
    }

    //从指定文件(newnum.txt,newnode.txt)读取每行的数据：
    //读取内容：p14:17
    //处理结果：p14,17(对象)
    public static List<Node> readLine(String filePath){
        List<Node> list = new ArrayList<Node>();
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    if (!lineTxt.equals(null) && !lineTxt.equals("")){
                        Node node = new Node();
                        Integer index = lineTxt.indexOf(":");
                        String nodeId = lineTxt.substring(0,index);
                        String num = lineTxt.substring(index+1);
                        Integer numInt = new Integer(num);
                        node.setTotalNum(numInt);
                        node.setId(nodeId);
                        list.add(node);
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
        return list;
    }

    //nodesOne表示为：消息生成总个数队列
    //nodeTwo表示为：消息成功传递个数队列
    public static List<NodePro> calDeliveryPro(List<Node> nodesOne, List<Node> nodesTwo){
        if (nodesOne==null ||nodesOne.size()==0){
            return null;
        }
        if (nodesTwo==null || nodesTwo.size()==0){
            return null;
        }
        Node first,two;
        List<NodePro> nodePros = new ArrayList<NodePro>(125);
        for (int i=0;i<nodesOne.size();i++){
            first = nodesOne.get(i);
            for (int j=0;j<nodesTwo.size();j++){
                two = nodesTwo.get(j);
                if (first.getId().equals(two.getId())){
                    NodePro nodePro = new NodePro();
                    nodePro.setId(first.getId());
                    Double deliveryPro = 1.0*two.getTotalNum()/first.getTotalNum();
                    deliveryPro = Double.parseDouble(String.format("%.2f", deliveryPro));
                    nodePro.setDeliveryPro(deliveryPro);
                    nodePros.add(nodePro);
                }
            }
        }
    return nodePros;
    }

        public static List<Message> dealLine(String filePath, Deal deal){
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
                    deal.dealDate(lineTxt, list);
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



}
