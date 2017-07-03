/**
 * 单词翻译器
 * 完成单词的翻译工作
 * 不分中英文
 * 可增删改查
 *
 */

//引入包
import java.util.*;

class translate {
	public static void main(String[] args) {
		//新建扫描器
		Scanner input = new Scanner(System.in);
		
		//新建初始变量数组
		String cns[] = new String[6]; //接收中文
		String ens[] = new String[6]; //接收英文
		int index = -1; //接收索引
		
		//初始值 方便操作删改；
		cns[0] = "任何";
		ens[0] = "any";
		cns[1] = "所有";
		ens[1] = "all";
		cns[3] = "你";
		ens[3] = "you";
		cns[4] = "我";
		ens[4] = "me";
		cns[5] = "错误";
		ens[5] = "bug";		
		//用循环打印主界面 这次用do
		do{
			System.out.println("欢迎使用中英翻译器");
			System.out.println("1.增加词库");
			System.out.println("2.删除单词");
			System.out.println("3.修改词库");
			System.out.println("4.查询词库");
			System.out.println("5.翻译单词");
			System.out.println("6.退出");
			System.out.println("请选择：");
			int choose = input.nextInt();
			switch(choose){
			case 1:
				/**
				 * 实现增加词库，增加中文或英文，自动对应中英文； 增加时不能直接覆盖原词，如果增加到已有的词进行提醒覆盖；
				 */
				for (;;) {
					System.out.println("请输入需要增加的英文单词：");
					String addEn = input.next();
					System.out.println("请输入它所对应的意思：");
					String addCn = input.next();

					boolean n = false;
					//新建一个tempEns数组，长度为ens数组的长度加1；
					String tempEns[] = new String[ens.length+1];
					//把原来的ens的数组遍历取出存到tempEns的对应数组里；
					for(int i=0; i<ens.length; i++){
						tempEns[i] = ens[i];
						n = true;
					}
					//把刚刚输入的addEn 赋值给tempEns数组的最后一位；
					tempEns[tempEns.length-1] = addEn;
					//把tempEns数组替换为ens数组；
					ens = tempEns;
					
					String tempCns[] = new String[cns.length+1];
					for(int i=0; i<cns.length; i++){
						tempCns[i] = cns[i];
					}
					tempCns[tempCns.length-1] = addCn;
					cns = tempCns;
					if(n){
						System.out.println("增加成功！");
					}else{
						System.out.println("请检查……");
					}
					System.out.println("是否继续增加？ y/n");
					String yn = input.next();
					if (yn.equalsIgnoreCase("y")) {
						continue;
					}
					break;
				}
				break;
			case 2:
				/**
				 * 删除单词，对相对应的单词进行删除操作
				 */
				for (;;) {
					
					System.out.println("请输入需要删除的单词(中英文不限)：");
					String del = input.next();
					// 遍历查找del在英文或中文中的位置;
					for(int i=0; i<ens.length; i++){
						if(del.equals(ens[i])){
							index = i;
						}else if(del.equals(cns[i])){
							index = i;
						}
					}
					// 判断是否找到，如果找到赋空值;
					if(index!=-1){
						cns[index] = null;
						ens[index] = null;
					}else{
						// 如果没找到，打印提示，要求再次输入;
						System.out.println("输入有误，请重新输入：");
						continue;
					}
					// 对需要删除的索引的值进行 由后向前覆盖；
					boolean isDel = false;
					for(int i=0; i<ens.length-1; i++){
						if(ens[i] == null){
							cns[i] = cns[i+1];
							ens[i] = ens[i+1];
							cns[i+1] = null;
							ens[i+1] = null;
							isDel = true;
						}
					}
					if (isDel) {
						System.out.println("删除成功！");
					}else
						System.out.println("请检查……");
					System.out.println("是否继续删除？ y/n");
					String yn = input.next();
					if (yn.equalsIgnoreCase("y")) {
						continue;
					}
					break;
				}
				break;
			case 3:
				// 修改词库
				while (true) {
					System.out.println("请输入需要修改的单词(中英文不限)：");
					String mod = input.next();
					// 遍历搜索mod在ens[i]或cns[i]里的位置，记录index
					for (int i = 0; i < ens.length; i++) {
						if (mod.equals(ens[i])) {
							index = i;
						} else if (mod.equals(cns[i])) {
							index = i;
						}
					}
					if (index != -1) {
						System.out.println("请输入新英文单词：");
						ens[index] = input.next();
						System.out.println("请输入新中文意思");
						cns[index] = input.next();
					} else {
						System.out.println("没找到需要修改的单词，请检查后重新输入：");
						continue;
					}
					System.out.println("修改成功，是否继续修改？ y/n");
					String yn = input.next();
					if ("y".equalsIgnoreCase(yn)) {
						continue;
					} else
						break;
				}
				break;
			case 4:
				for(int i=0; i<ens.length; i++){
					if(ens[i]==null){
						continue;
					}
					System.out.println(ens[i]+"\t"+cns[i]);
				}
				break;
			case 5:
				//翻译单词
				while(true){
					System.out.println("请输入要翻译的单词(中英文不限)：");
					String translate = input.next();
					int isEng = 1; 
					// 遍历查找需要翻译的词;
					for (int i = 0; i < ens.length; i++) {
						if (translate.equals(ens[i])) {
							index = i;
							
						} else if (translate.equals(cns[i])) {
							index = i;
							isEng = 0;
						}
					}
					if (index != -1 & isEng == 1) {
						System.out.println("您输入的英文对应中文意思是：" + cns[index]);
					} else if(index != -1 & isEng == 0){
						System.out.println("您输入的中文对应的英文意思是：" + ens[index]);
					
					}else{
						System.out.println("没找到该单词，请检查后重新输入：");
						continue;
					}
					System.out.println("是否继续翻译单词？ y/n");
					String yn = input.next();
					if("y".equalsIgnoreCase(yn)){
						continue;
					}else
						break;
				}
				break;
			case 6:
				System.out.println("感谢您的使用，再见！");
				index = -2;
				break;
			default:
				System.out.println("你不感觉输错了吗？ 是否退出？  y/n");
				String yn = input.next();
				if("y".equalsIgnoreCase(yn)){
					System.out.println("感谢您的使用，再见！");
					index = -2;
				}
				break;
			}
		}while(index != -2); //当index不为－2的时候，进行循环，执行为－2表示结束程序
	}
}
