import org.apache.commons.lang3.ArrayUtils;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Three {
   public static  Set<String> findLongStr(String str) {

        int len=str.length();
        String[][] dp=new String[len][len];
        int flag=0;
        Set<String> maxStr=new HashSet<>();
        //边界,长度为1
        for(int i=0;i<len;i++){
            char c=str.charAt(i);
            StringBuilder sb=new StringBuilder();
            for(int j=i+1;j<len;j++){
                if(c==str.charAt(j)){
                    flag=1;
                    if(sb.length()!=0){
                        sb.append(",");
                    }
                    sb.append(j);
                    maxStr.add(String.valueOf(c));
                }
            }
            dp[i][i]=sb.toString();
        }

        for(int L=2;flag==1;L++){
            flag=0;
            for(int i=0;i+L-1<len;i++){
                int j=i+L-1;
                if(dp[i][j-1]!=null && !dp[i][j-1].equals("") && dp[j][j]!=null && !dp[j][j].equals("")){
                    String[] temp=dp[i][j-1].split(",");
                    String[] temp2=dp[j][j].split(",");
                    StringBuilder tempStr=new StringBuilder();
                    for(String s:temp2){
                        if(ArrayUtils.contains(temp,Integer.toString(Integer.parseInt(s)+1)) && j<Integer.parseInt(s)){
                            if(flag==0){
                                maxStr.clear();
                                maxStr.add(str.substring(i,j+1));
                            }else{
                                maxStr.add(str.substring(i,j+1));
                            }
                            flag=1;
                            if(tempStr.length()!=0){
                                tempStr.append(",");
                            }
                            tempStr.append(s);
                        }
                    }
                    dp[i][j]=tempStr.toString();
                }
            }
        }
        return maxStr;
    }
    public static void printResult(Set<String> result){
       StringBuilder sb=new StringBuilder();
       for(String s:result){
           if(sb.length()!=0){
               sb.append(",");
           }
           sb.append(s);
       }
        System.out.println("result:"+sb);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入字符串： ");
        while (scan.hasNext())
        {
            String str = scan.next();
            Set<String> result=findLongStr(str);
            printResult(result);
            System.out.print("请输入字符串： ");
        }

    }
}
