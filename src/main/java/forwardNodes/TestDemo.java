package forwardNodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计每个节点的转出个数、转入个数，
 *       例如：节点id（转出个数，转入个数）== w104(3,0)
 */
public class TestDemo {
    public static void main(String[] args){
        String fileName = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/datenew/frtold.txt";
        List<Info> list = readTxtFile(fileName);
        List<String> finalList = new ArrayList<String>();
        Map<Integer,Object> map = new HashMap<Integer,Object>();
        for (int i=0;i<list.size();i++){
            map.put(i,list.get(i));
        }
        for (int i=0;i<map.size();i++){
            Info firstInfo = (Info) map.get(i);
            Integer fromsum = firstInfo.getFromNum();
            Integer toSum = firstInfo.getToNum();
            String str = "";
            if(firstInfo.getTrue() == true){
                for (int j=i+1;j<map.size();j++){
                    Info twoInfo = list.get(j);
                    if(firstInfo.getNodeId().equals(twoInfo.getNodeId()) && twoInfo.getTrue() == true) {
                        fromsum = fromsum + twoInfo.getFromNum();
                        toSum = toSum + twoInfo.getToNum();
                        twoInfo.setTrue(false);
                        map.put(j,twoInfo);
                    }
                }
                firstInfo.setTrue(false);
                map.put(i,firstInfo);
                str = str+firstInfo.getNodeId()+"("+toSum+","+fromsum+")";
                finalList.add(str);
            }
        }
        for (String str : finalList){
            System.out.println(str);
            System.out.println();
        }
    }

    //统计所有节点的转入、转出个数
    public static List<Info> readTxtFile(String filePath){
        List<Info> list = new ArrayList<Info>();
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    subString(lineTxt, list);
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

    //统计出：节点转入、转出到另外一个节点的个数
    public static void subString(String lineTxt, List<Info> list){
        if(lineTxt.equals(null) || lineTxt.equals("")){
            return;
        }
        Integer firstIndex = lineTxt.indexOf("(");
        Integer twoIndex = lineTxt.indexOf(",");
        Integer threeIndex = lineTxt.indexOf(")");
        String nodeIdFirst = "";
        Integer toNumFirst = 0;
        Integer fromNumFirst = 0;
        String nodeIdTwo = "";
        Integer toNumTwo = 0;
        Integer fromNumTwo = 0;
        if(firstIndex < twoIndex){
            nodeIdFirst = lineTxt.substring(firstIndex+1,twoIndex);
//            tempValue = Integer.parseInt(lineTxt.substring(twoIndex+1,threeIndex));
        }
        firstIndex = lineTxt.indexOf("(",firstIndex+1);
        twoIndex = lineTxt.indexOf(",",twoIndex+1);
        threeIndex = lineTxt.indexOf(")",threeIndex+1);
        if(firstIndex < twoIndex){
            nodeIdTwo = lineTxt.substring(firstIndex+1,twoIndex);
            fromNumTwo = Integer.parseInt(lineTxt.substring(twoIndex+1,threeIndex));
        }
        toNumFirst =  fromNumTwo;
        Info firstInfo = new Info(nodeIdFirst, toNumFirst, fromNumFirst);
//        String socialStr = lineTxt.substring(lineTxt.indexOf("social:"));
        list.add(firstInfo);
        Info twoInfo = new Info(nodeIdTwo, toNumTwo, fromNumTwo);
        list.add(twoInfo);
    }
}


