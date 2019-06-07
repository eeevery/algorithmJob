import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.xml.internal.ws.server.sei.SEIInvokerTube;

import java.util.*;

/**
 * @author Milingyun
 * @date 2019-05-07 22:31
 */
public class Five {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("请输入集合X：");
        String[] x=sc.nextLine().split(" ");
        System.out.println("请输入X的一个子集簇F：");
        String[] y=sc.nextLine().split(",");
        Set X=new HashSet();
        for(String s:x){
            X.add(s);
        }
        List<Set> F=new ArrayList<>();
        for(String s:y){
            String[] temp=s.split(" ");
            Set set=new HashSet();
            for(String str:temp){
                set.add(str);
            }
            F.add(set);
        }

       List<Set> resultList=new ArrayList<>();
       Set result=new HashSet();
       BackTrack(X,F,resultList,result);
       int min=resultList.get(0).size();
       for(int i=0;i<resultList.size();i++){
           min=min>resultList.get(i).size()?resultList.get(i).size():min;

       }
        System.out.println("最小子集：");
        for(Set set:resultList){
            if(set.size()==min){
                System.out.println(set);
            }
        }


    }
    public static void BackTrack(Set X, List<Set> F,List<Set> resultList,Set result){
        //终止
        if(X.size()==0){
            //保留结果
            resultList.add(result);
            return;
        }
        if(F.size()==0){
            return;
        }
        //交集
        Set sn=new HashSet(X);
        sn.retainAll(F.get(0));
        if(sn.size()==0){//交集为空集
            F.remove(0);
            BackTrack(X,F,resultList,result);
        }else{
            //取该集合
            Set XTemp=new HashSet(X);
            XTemp.removeAll(F.get(0));
            List<Set> FTemp=new ArrayList<>();
            if(F.size()>1) {
                 FTemp = F.subList(1, F.size() );
            }
            Set resultTemp=new HashSet<>(result);
            resultTemp.add(F.get(0));
            BackTrack(XTemp,FTemp,resultList,resultTemp);
            //舍弃该集合

            BackTrack(X,F.subList(1,F.size()),resultList,result);

        }

    }
}
