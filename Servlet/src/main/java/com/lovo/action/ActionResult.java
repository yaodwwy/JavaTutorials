package com.lovo.action;

/**
 * Actionִ�н��
 * @author ���
 *
 */
public class ActionResult {
	private ResultContent resultContent;
	private ResultType resultType;

	public ActionResult(ResultContent resultContent) {
		this(resultContent, ResultType.Forward);
	}

	public ActionResult(ResultContent resultContent, ResultType type) {
		this.resultContent = resultContent;
		this.resultType = type;
	}

	/**
	 * ���ִ�н��������
	 */
	public ResultContent getResultContent() {
		return resultContent;
	}
	
	/**
	 * ���ִ�н��������
	 */
	public ResultType getResultType() {
		return resultType;
	}

}
