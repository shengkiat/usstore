package sg.edu.nus.iss.uss.exception;

import sg.edu.nus.iss.uss.exception.ErrorConstants.UssCode;

public class UssException extends Exception {

	private static final long serialVersionUID = -3360812732250813553L;

	public UssException(UssCode code, String messeage) {
		super(messeage);
		this.code = code;
	}

	private UssCode code;

	public UssCode getCode() {
		return code;
	}

	public void setCode(UssCode code) {
		this.code = code;
	}

}