package node;

import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 2018/12/27.
 * 处理数据接口
 */
public interface Deal {

    //处理文件方法
    void dealTxt() throws IOException;

    //统计数据过程方法
    List<Node> calDate(List<Message> messages);

    //将从文件读取每条数据，进行合理分隔
     void dealDate(String line,List<Message> messages);
}
