/**
 * 实现翻译器的翻译工作；
 * 实现对单词的增、删、改、查；
 * 键入中文翻译英文，键入英文翻译中文；
 * 键入新单词时，提示词库中无当前单词，随便录入该单词。
 */

import java.util.*;

class translate1 {
	public static void main(String[] args) {
		
		// 导入扫描器包和创建input对象
		Scanner input = new Scanner(System.in);
		
		// 创建数组变量
		
		String enWords[] = new String[50]; // 英文数组
		String cnWords[] = new String[50]; // 中文数组

		
		/*
		 * 测试块，对数据自动copy放大，未完成；
		 * 
		 * arrays.copyOf String enWords[] =
		 * String cnWords[] = Arrays.copyOf(enWord,enWord.length+1); 
		 * String cnWords[] = Arrays.copyOf(cnWord,cnWord.length+1);
		 */
		
		// 设置默认值，方便直接修改删除
		enWords[0] = "begin";
		cnWords[0] = "开始";
		enWords[1] = "end";
		cnWords[1] = "结束";
		enWords[2] = "pause";
		cnWords[2] = "暂停";
		enWords[3] = "happy";
		cnWords[3] = "高兴";
		enWords[4] = "sad";
		cnWords[4] = "伤心";
		enWords[5] = "good";
		cnWords[5] = "好";

		// 输出主界面
		while (true) {
			System.out.println("--------------->");
			System.out.println("英汉翻译器");
			System.out.println("1.增加单词");
			System.out.println("2.删除单词");
			System.out.println("3.修改词库");
			System.out.println("4.翻译单词");
			System.out.println("5.查看所有词汇");
			System.out.println("6.退出");
			System.out.println("请选择：");
			int choose = input.nextInt();
			switch (choose) {
			case 1:
				// 增加单词；
				String newEnWord = null; // 接收新英文词；
				String newCnWord = null; // 接收新中文词；

				while (true) { 
					
					// 增加单词到数组
					System.out.println("请输入英文单词：");
					newEnWord = input.next();
					System.out.println("请输入对应的中文意思");
					newCnWord = input.next();
			//		int index = -1;
					
					// 遍历插入新词；
					for (int i = 0; i < enWords.length; i++) {
						// 判断不为空值
						if (enWords[i] == null) {
							enWords[i] = newEnWord;
							cnWords[i] = newCnWord;
							break;
						} else
							continue;
					}
					System.out.println("是否继续增加？ y/n");
					String ys = input.next();
					if (ys.equalsIgnoreCase("y")) {
						continue;
					} else
						break;
				}
				break;
			case 2:
				while (true) {
					// 删除单词，并把单词之后的索引向前移动一位；
					System.out.println("请输入要删除的单词(中英文不限)：");
					String reWord = input.next();
					int index = -1;

					// 遍历取出需要删除的单词；
					for (int i = 0; i < enWords.length; i++) {
						if (reWord.equalsIgnoreCase(enWords[i])) {
							index = i;
						} else if (reWord.equalsIgnoreCase(cnWords[i])) {
							index = i;
						}
					}

					// 对找到的索引位置数组进行删除
					if (index != -1) {
						enWords[index] = null;
						cnWords[index] = null;

						// 遍历对null值进行覆盖。
						for (int i = 0; i < enWords.length - 1; i++) {
							if (enWords[i] == null) {
								enWords[i] = enWords[i + 1]; // 运行前检查是否越界；
					//			enWords[enWords.length - 1] = null;
							} else
								continue;
						}
					} else {
						System.out.println("未找到该词，请重新输入：");
					}

					System.out.println("操作成功，是否继续删除？ y/n");
					String ys = input.next();
					if (ys.equalsIgnoreCase("y")) {
						continue;
					} else
						break;
				}
				break;
			case 3:
				while (true) {
					// 修改单词，对已有的单词已经修改
					System.out.println("请输入需要修改的单词(中英文不限)：");
					String modWord = input.next();
					String modEnWord = null; // 变量储存 修改英文的单词
					String modCnWord = null; // 变量储存 修改中文的单词
					int index = -1;

					// 遍历取出需要修改的单词；
					for (int i = 0; i < enWords.length; i++) {
						if (modWord.equalsIgnoreCase(enWords[i])) { // 如果输入的为英文字母
							modEnWord = enWords[i];
							index = i;
						} else if (modWord.equalsIgnoreCase(cnWords[i])) { // 如果输入的为中文字母
							modCnWord = cnWords[i];
							index = i;
						}
					}

					// 对取出的单词进行重新修改；
					if (modEnWord != null) {
						System.out.println("请输入需要修改的新英文单词：");
						enWords[index] = input.next();
						System.out.println("请输入对应的中文翻译");
						cnWords[index] = input.next();

					} else if (modCnWord != null) {
						System.out.println("请输入需要修改的新中文单词：");
						cnWords[index] = input.next();
						System.out.println("请输入对应的英文翻译");
						enWords[index] = input.next();

					} else {
						System.out.println("未找到该词，请重新输入：");
						continue;
					}

					System.out.println("修改成功，是否继续修改？ y/n");
					String yn = input.next();
					if (yn.equalsIgnoreCase("y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case 4:
				// 翻译单词；
				while (true) {
					System.out.println("请输入要翻译的单词(中英文不限)：");
					String traWord = input.next();
					String traEnWord = null; // 变量储存 翻译为英文的单词
					String traCnWord = null; // 变量储存 翻译为中文的单词

					// 遍历取出需要翻译的单词
					for (int i = 0; i < enWords.length; i++) {
						if (traWord.equalsIgnoreCase(enWords[i])) {
							traCnWord = cnWords[i];
						} else if (traWord.equalsIgnoreCase(cnWords[i])) {
							traEnWord = enWords[i];
						}
					}

					// 对翻译的单词进行输出显示
					if (traEnWord != null) {
						System.out.println("它的英文翻译为：" + traEnWord);
					} else if (traCnWord != null) {
						System.out.println("它的中文翻译为：" + traCnWord);
					} else {
						System.out.println("未找到该词，请重新输入：");
					}

					System.out.println("翻译完成，是否继续翻译？ y/n");
					String yn = input.next();
					if (yn.equalsIgnoreCase("y")) {
						continue;
					}
					break;
				}
				break;
			case 5:
				// 显示所有词库
				for (int i = 0; i < enWords.length; i++) {
					if (enWords[i] != null && cnWords[i] != null) {
						System.out.println(enWords[i] + "\t" + cnWords[i]);
					}
				}

				break;
			case 6:
				// 退出程序
				System.out.println("感谢使用，再见！");
				return;
			default:
				System.out.println("输入有误，请重新选择！");
				break;
			}

		}
	}

}
