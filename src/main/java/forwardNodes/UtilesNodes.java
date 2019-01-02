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
 * Created by Admin on 2019/1/1.
 */
public class UtilesNodes {

    /**
     * M3:(w104,3)-->(w114,1),time:127,social:1.0
     * 整理结果：（从节点转出1个消息副本到另外一个节点）
     * w104(1,0)———>w114(0,1)
     * @param lineTxt
     * @return
     */
    public static List<Info> subStringNew(String lineTxt){
        List<Info> list = new ArrayList<Info>(2);
        if(lineTxt.equals(null) || lineTxt.equals("")){
            return list;
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
        list.add(firstInfo);
        Info twoInfo = new Info(nodeIdTwo, toNumTwo, fromNumTwo);
        list.add(twoInfo);

        return list;
    }

    /**
     * M3:(w104,6)-->(w114,3)
     * w104 6
     * w114 3
     * @param lineTxt
     * @return
     */
    public static Map<String, String> subString(String lineTxt){
        Map<String,String > map = new HashMap<String, String>();
        Integer firstIndex = lineTxt.indexOf("(");
        Integer twoIndex = lineTxt.indexOf(",");
        Integer threeIndex = lineTxt.indexOf(")");
        String str = "";
        String value = "";
        if(firstIndex < twoIndex){
            str = lineTxt.substring(firstIndex+1,twoIndex);
            value = lineTxt.substring(twoIndex+1,threeIndex);
        }
        map.put(str,value);
        firstIndex = lineTxt.indexOf("(",firstIndex+1);
        twoIndex = lineTxt.indexOf(",",twoIndex+1);
        threeIndex = lineTxt.indexOf(")",threeIndex+1);
        if(firstIndex < twoIndex){
            str = lineTxt.substring(firstIndex+1,twoIndex);
            value = lineTxt.substring(twoIndex+1,threeIndex);
        }
        map.put(str,value);
        return map;
    }

    /**
     *M1:(p14,8)-->(p28,4),time:118
     * 分隔的结果为：p14  4
     */
    public static List<String>  subStringTwo(String lineTxt){
        List<String> list = new ArrayList<String>(3);
        if(!lineTxt.equals(null) && !lineTxt.equals("")){
            Integer firstIndex = lineTxt.indexOf("(");
            Integer twoIndex = lineTxt.indexOf(",");
            String  strOne = lineTxt.substring(firstIndex+1,twoIndex);
            list.add(strOne);

            Integer threeIndex = lineTxt.lastIndexOf(")");
            Integer fourIndex = lineTxt.indexOf(",",twoIndex+1);
            String  strTwo = lineTxt.substring(fourIndex+1,threeIndex);
            list.add(strTwo);
            return list;
        }
        return null;
    }

    public static Map<String, List<Info>> readFiles(List<String> files){
        Map<String,List<Info>> map = new HashMap<String, List<Info>>(5);
        if (files == null || files.size() == 0){
            return map;
        }
        List<Info> infos = null;
        for (String fileName:files){
           if (fileName.equals(UtilesInfo.Epidemic)){
              infos = readTxtFile(UtilesInfo.fileNameEpidemic);
               map.put(UtilesInfo.Epidemic, infos);
           }else if (fileName.equals(UtilesInfo.FRnew)){
               infos = readTxtFile(UtilesInfo.fileNameFRnew);
               map.put(UtilesInfo.FRnew, infos);
           }else if (fileName.equals(UtilesInfo.FRold)){
               infos = readTxtFile(UtilesInfo.fileNameFRold);
               map.put(UtilesInfo.FRold, infos);
           }else if (fileName.equals(UtilesInfo.FRoldNew)){
               infos = readTxtFile(UtilesInfo.fileNameFRoldNew);
               map.put(UtilesInfo.FRoldNew, infos);
           }else if (fileName.equals(UtilesInfo.SAW)){
               infos = readTxtFile(UtilesInfo.fileNameSaw);
               map.put(UtilesInfo.SAW, infos);
           }
        }
        return map;
    }

    public static List<Info> readTxtFile(String filePath){
        List<String> list;
        List<Info> reList = new ArrayList<Info>();
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    list = UtilesNodes.subStringTwo(lineTxt);
                    if(list != null){
                        Info info = new Info(list.get(0),
                                Integer.parseInt(list.get(1)));
                        reList.add(info);
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
        return reList;
    }
}
