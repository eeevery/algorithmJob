import java.util.*;

/**
 * @author Milingyun
 * @date 2019-04-30 18:07
 */
public class Four {
    public static void  greedy(List<Job> jobs){
        Collections.sort(jobs);
        int[] arrange=new int[jobs.size()];
        int gain=0;
        for(Job j:jobs){
            int index=j.getD()-1;
            while(arrange[index]!=0&&index>=0){
                index--;
            }
            if(index<0) continue;
            else{
                arrange[index]=j.getId();
                gain+=j.p;
            }
        }
        StringBuilder result=new StringBuilder();
        for(int i:arrange){
            result.append(i+",");
        }
        result.deleteCharAt(result.length()-1);
        System.out.println("最佳安排方案："+result);
        System.out.println("该方案收益为："+gain);

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入n个作业的最迟完成时间di：");
        String[] first=scan.nextLine().split(" ");
        System.out.print("请输入n个作业的收益pi：");
        String[] second=scan.nextLine().split(" ");
        List<Job> jobs=new ArrayList<>();
        for(int i=0;i<first.length;i++){
            Job j=new Job(i+1,Integer.parseInt(first[i]),Integer.parseInt(second[i]));
            jobs.add(j);
        }
        greedy(jobs);

    }
    static class Job implements Comparable<Job>{
        int id;
        int d;
        int p;

        Job(int id,int d,int p){
            this.id=id;
            this.d=d;
            this.p=p;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        @Override
        public int compareTo(Job job) {
            if(p < job.p){
                return 1;
            }
            if (p > job.p) {
                return -1;
            }
            return 0;
        }
    }
}
