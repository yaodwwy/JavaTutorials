import java.util.*;
public class 迷你DVD管理器 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);//扫描器
		//数据初始化 赋初始值  固定长度为6
		String names[] ={"罗马假日","风声鹤唳","浪漫满屋",null,null,null};		//名称
		boolean states[] ={true,false,false,false,false,false};				//状态
		int dates[] = {1,0,0,0,0,0};			//借出日期
		int counts[] ={15,12,30,0,0,0};			//借出次数
		//打印主界面：
		do{
		System.out.println("欢 迎 使 用 迷 你 DVD 管 理 器");
		System.out.println("--------------------------------");
		System.out.println("1. 新 增 DVD");
		System.out.println("2. 查 看 DVD");
		System.out.println("3. 删 除 DVD");
		System.out.println("4. 借 出 DVD");
		System.out.println("5. 归 还 DVD");
		System.out.println("6. 退 出 DVD");
		System.out.println("--------------------------------");
		System.out.println("请选择：");
		int choose = input.nextInt();
			switch (choose) {
			case 1:
				while (true) {
					// 打印提示
					System.out.println("新 增 DVD--------->");
					System.out.println("请输入光盘名称：");
					String name = input.next();
					// 遍历为null的值填充新增的值;
					boolean isAdd = false;
					for (int i = 0; i < names.length; i++) {
						if (names[i] == null) {
							names[i] = name;
							System.out.println("增加成功！");
							isAdd = true;
							break;
						}
					}
					if(isAdd != true){
						System.out.println("未能增加，可能光盘已满！");
						break;
					}
					// 询问是否继续？
					System.out.println("请问是否继续增加？ y/n");
					String yn = input.next();
					if ("y".equalsIgnoreCase(yn)) {
						continue;
					} else
						break;
				}
				break;
			case 2:
				while(true){
					// 打印提示
					System.out.println("查 看 DVD--------->");
					System.out.println("序号\t状态\t       名称\t\t借出日期\t借出次数");
					int num = 1;
					for (int i = 0; i < names.length; i++) {
						if(names[i] != null){
						System.out.println(num + "\t"+ (states[i] ? "已借出" : "可借")+ "\t《"+names[i]+"》\t"+ (dates[i] != 0 ? dates[i]+ "日":"   ")+ "\t"+ counts[i]);
						num++;
						}
					}
					System.out.println("**********************************************");
					System.out.println("输入o返回：");
					String yn = input.next();
					if ("o".equalsIgnoreCase(yn)) {
						break;
					} else
						continue;
				}
				break;
			case 3:
				while (true) {
					// 打印提示
					System.out.println("删 除 DVD--------->");
					System.out.println("请输入DVD名称：");
					String name = input.next();
					// 遍历把需要删除的值赋为空;
					boolean isDel = false;
					for (int i = 0; i < names.length; i++) {
						if (name.equals(names[i])) {
							names[i] = null;
							states[i] = false;
							dates[i] = 0;
							counts[i] = 0;
							isDel = true;
							System.out.println("删除成功！");
							break;
						}
					}
					if (isDel != true) {
						System.out.println("未找到您需要删除的光盘，请重新输入：");
						continue;
					}
					System.out.println("***************************************");
					System.out.println("输入o返回：");
					String yn = input.next();
					if ("o".equalsIgnoreCase(yn)) {
						break;
					}
					continue;
				}
				break;
		case 4:
			while(true){
				System.out.println("借 出 DVD--------->");
				System.out.println("请输入借出的DVD名称：");
				String name = input.next();
				// 遍历把需要借出的值进行修改;
				boolean isOut = false;
				for (int i = 0; i < names.length; i++) {
					if (name.equals(names[i]) && !states[i]) {
						System.out.println("请输入借出的日期：");
						dates[i] = input.nextInt();
						if(dates[i]>31 || dates[i]<=0){
							System.out.println("输入有误，不能>31日或小于1日！");
							continue;
						}
						states[i] = true;
						isOut = true;
						counts[i]++;
						System.out.println("《" + name + "》借出成功！");
						break;
					}
				}
				
				if (isOut != true) {
					System.out.println("借出失败，请重新输入？");
					continue;
				}
				System.out.println("***************************************");
				System.out.println("输入o返回：");
				String yn = input.next();
				if ("o".equalsIgnoreCase(yn)) {
					break;
				}
				continue;
			}
			break;
		case 5:
			while(true){
				System.out.println("归 还 DVD--------->");
				System.out.println("请输入归还的DVD名称：");
				String name = input.next();
				// 遍历修改;
				boolean isIn = false;
				for (int i = 0; i < names.length; i++) {
					if (name.equals(names[i]) & states[i]) {
						System.out.println("请输入归还的日期：");
						int date = input.nextInt();
						if(dates[i]>31 || dates[i]<=0){
							System.out.println("输入有误，不能>31日或小于1日！");
							continue;
						}
						if (!states[i]) {
							System.out.println("归还失败，该光盘从未借出？！");
							break;
						}
						System.out.println("《" + name + "》归还成功！");
						System.out.println("借出日期为：" + dates[i]);
						System.out.println("归还日期为：" + date);
						System.out.println("应付金额为：" +( (date-dates[i])>=0?(date-dates[i]):(date-dates[i]+31))+"元。");
						states[i] = false;
						isIn = true;
						dates[i] = 0;
						break;
					}
				}
				
				if (isIn != true) {
					System.out.println("未找到《"+ name + "》归还失败，请重新输入？");
					continue;
				}
				
				
				System.out.println("***************************************");
				System.out.println("输入o返回：");
				String yn = input.next();
				if ("o".equalsIgnoreCase(yn)) {
					break;
				}
				continue;
			}
			break;
			
		case 6:
			System.out.println("谢谢使用，再见！");
			System.exit(0);
		default:
			System.out.println("输入有误，请重新输入！");
				break;
		}
		
		
		}while(true);
	}

}
