package forwardNodes;

import node.UtilsLine;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2019/1/1.
 */
public class UtilesInfo {

    public static Map<String,List<Info>> infoMap = new HashMap<String, List<Info>>(5);

    public static final String fileNameSaw = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/datenew/SAW.txt";

    public static final String fileNameEpidemic = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/datenew/Epidemic.txt";

    public static final String fileNameFRnew = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/datenew/frnew.txt";

    public static final String fileNameFRold = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/datenew/frold.txt";

    public static final String fileNameFRoldNew = "D:\\EclipseWorkSpace/the-one-1.6.0/reports/datenew/frtold.txt";

    public static final String SAW = "SAW";

    public static final String Epidemic = "Epidemic";

    public static final String FRnew = "FRnew";

    public static final String FRold = "FRold";

    public static final String FRoldNew = "FRoldNew";


    /**
     * 新建5个采集数据文件
     * @return
     */
    public static List<String> buildFiles(){
        List<String> files = new ArrayList<String>(5);
        files.add(UtilesInfo.SAW);
//        files.add(UtilesInfo.Epidemic);
        files.add(UtilesInfo.FRold);
        files.add(UtilesInfo.FRoldNew);
        files.add(UtilesInfo.FRnew);
        return files;
    }

    /**
     *根据文件名对应文件path
     */
    public static String getFileName(String file){
        if (file == null || file.equals("")){
            return null;
        }
        if (UtilesInfo.Epidemic.equals(file)){
            return UtilesInfo.fileNameEpidemic;
        }else if (UtilesInfo.SAW.equals(file)){
            return UtilesInfo.fileNameSaw;
        }else if (UtilesInfo.FRnew.equals(file)){
            return UtilesInfo.fileNameFRnew;
        }else if (UtilesInfo.FRold.equals(file)){
            return UtilesInfo.fileNameFRold;
        }else if (UtilesInfo.FRoldNew.equals(file)){
            return UtilesInfo.fileNameFRoldNew;
        }
        return null;
    }

    public static void clearFiles(List<String> files) throws IOException {
        if (files == null){
            return;
        }
        for (String file:files){
            if (UtilesInfo.SAW.equals(file)){//SAW文件作为基础，所有不能删除掉
                continue;
            }
            String filePath = getFileName(file);
            UtilsLine.clear(filePath);
        }
    }

    public static List<Info> getListForFile(Map<String,List<Info>> map,String fileName){
        if (map == null){
            return null;
        }
        if (fileName.equals(UtilesInfo.Epidemic)){
            return map.get(UtilesInfo.Epidemic);
        }else if (fileName.equals(UtilesInfo.FRnew)){
            return map.get(UtilesInfo.FRnew);
        }else if (fileName.equals(UtilesInfo.FRold)){
            return map.get(UtilesInfo.FRold);
        }else if (fileName.equals(UtilesInfo.FRoldNew)){
            return map.get(UtilesInfo.FRoldNew);
        }else if (fileName.equals(UtilesInfo.SAW)){
            return map.get(UtilesInfo.SAW);
        }
        return null;
    }

    /**
     * 处理多个数据文件
     * 前提条件为：map中必须有SAW文件
     */
    public static void getValues(Map<String,List<Info>> map){
        if (map == null){
            return;
        }
        List<Info> infos = map.get(UtilesInfo.SAW);
        for (Map.Entry<String,List<Info>> entry:map.entrySet()){
            String key = entry.getKey();
            List<Info> value = entry.getValue();
            if (key.equals(UtilesInfo.SAW)){
                continue;
            }
            getValueFor(infos, value, key);
        }
    }

    /**
     * 以SAW.txt数据为基础，找出对应的节点的数据
     * @param list
     * @param inputList
     * @param key
     * @return
     */
    public static void getValueFor(List<Info> list,List<Info> inputList,String key){
        List<Info> list1 = new ArrayList<Info>(list.size()+1);
        List<Info> list2 = new ArrayList<Info>(inputList.size()+1);
        for (int i=0;i<list.size();i++){
            Info info = list.get(i);
            for (int j=0;j<inputList.size();j++){
                Info inputInfo = inputList.get(j);
                if (info.getNodeId().equals(inputInfo.getNodeId())){
                    list1.add(info);
                    list2.add(inputInfo);
                }
            }
        }
        if (infoMap.get(UtilesInfo.SAW) == null){
            infoMap.put(UtilesInfo.SAW, list1);
        }
        if (infoMap.get(key) == null){
            infoMap.put(key, list2);
        }
    }


    public static String getbzChas(){
        if (infoMap == null){
            return null;
        }
        StringBuilder builder = new StringBuilder("");
        for (Map.Entry<String,List<Info>> entry: infoMap.entrySet()){
            String key = entry.getKey();
            List<Info> value = entry.getValue();
            Double bzCha = getbzChaTo(value);
            DecimalFormat df = new DecimalFormat("###.###");
            builder.append(key).append(":").append(df.format(bzCha));
            builder.append("   ");
        }
        return builder+"";
    }

    /**
     * 节点的消息转出个数的标准差
     * @param list
     * @return
     */
    public static Double getbzChaTo(List<Info> list){
        Integer sum = 0;
        Double avg;
        for (int i=0;i<list.size();i++){
            sum += list.get(i).getToNum();
        }
        avg = 1.0*sum/list.size();
        Double pingFang = 0.0;
        for (int j=0;j<list.size();j++){
            Double chaZhi = Math.pow(list.get(j).getToNum()-avg,2);
            pingFang += chaZhi;
        }
        return Math.sqrt(1.0*pingFang/list.size());
    }

    /**
     * 节点的消息转入个数的标准差
     * @param list
     * @return
     */
    public static Double getbzChaFrom(List<Info> list){
        Integer sum = 0;
        Double avg;
        for (int i=0;i<list.size();i++){
            sum += list.get(i).getFromNum();
        }
        avg = 1.0*sum/list.size();
        Double pingFang = 0.0;
        for (int j=0;j<list.size();j++){
            Double chaZhi = Math.pow(list.get(j).getFromNum()-avg,2);
            pingFang += chaZhi;
        }
        return Math.sqrt(1.0*pingFang/list.size());
    }
}
