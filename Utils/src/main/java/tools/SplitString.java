package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class SplitString 
{
	//定义各类常量
	private String SPLIT_INDELIM = "";

	private String SPLIT_OUTDELIM = "";

	private String SPLIT_SOURCESTRING = "";

	/**
	 * @param inDelim
	 * @param outDelim
	 * @param sourceString
	 */
	public SplitString(String inDelim, String outDelim, String sourceString)
	{
		super();
		this.SPLIT_INDELIM = inDelim;
		this.SPLIT_OUTDELIM = outDelim;
		this.SPLIT_SOURCESTRING = sourceString;
	}

	/**
	 * 分割字符串

	 * @param sourceString
	 * @param inDelim
	 * @return
	 */
	public String[] split(String sourceString, String inDelim)
	{
		try
		{
			this.SPLIT_SOURCESTRING = sourceString;
			this.SPLIT_INDELIM = inDelim;
			List list = new ArrayList();
			StringTokenizer st = new StringTokenizer(sourceString, inDelim);
			while (st.hasMoreTokens())
			{
				list.add(st.nextToken());
			}
			int index2 = list.size();
			String[] string = new String[index2];
			for (int i = 0; i < index2; i++)
			{
				string[i] = (String) list.get(i);
			}
			return string;
		}
		catch(Exception e)
		{
			String[] string = new String[1];
			string[0] = sourceString;
			return string;
		}
	}

	/**
	 * 分割字符串

	 * @return
	 */
	public String split()
	{
		StringTokenizer st = new StringTokenizer(SPLIT_SOURCESTRING, SPLIT_INDELIM);
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens())
		{
			sb.append(st.nextToken());
			sb.append(SPLIT_OUTDELIM);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 构造函数

	 */
	public SplitString() 
	{
		super();
	}

	/**
	 * 构造函数

	 * @param sourceString
	 */
	public SplitString(String sourceString)
	{
		super();
		this.SPLIT_SOURCESTRING = sourceString;
	}
}
