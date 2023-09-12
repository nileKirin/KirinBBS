package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParameterChecker {
    public String createAccountFormatCheck(String userId, String pass, String rePass, String age, String email, String remail) {
        String errorMsg = "";

        errorMsg += isUserId(userId) ? "" : "ログインIDは4文字以上にしてください"+StaticTools.LINE_SEPARATOR;
        errorMsg += isPass(pass)     ? "" : "パスワードは6文字以上にしてください"+StaticTools.LINE_SEPARATOR;
        errorMsg += isAge(age)       ? "" : "年齢は2桁の整数で入力してください"+StaticTools.LINE_SEPARATOR;
        errorMsg += isMail(email)    ? "" :"メールアドレスの値が正しくありません"+StaticTools.LINE_SEPARATOR;

        if (isNull(userId, pass, rePass, age, email, remail)) {
            errorMsg = "入力されていないデータがあります!";
        } else if (!(pass.equals(rePass) && email.equals(remail)) && errorMsg.isEmpty()) {
            errorMsg = "再入力のデータが間違っています！";
        }
        return errorMsg;
    }


    public String contactFormCheck(String name, String email, String remail, String contents, String textArea) {
    	String errorMsg = "";

        if (!isMail(email)) errorMsg += "メールアドレスの値が正しくありません"+StaticTools.LINE_SEPARATOR;
    	if(isNull(name,email,remail,contents,textArea)){
    		errorMsg ="入力されていないデータがあります!";
    	}else if(!email.equals(remail) && errorMsg.isEmpty()) {
    		errorMsg ="再入力のデータが間違えています！";
    	}
    	return  errorMsg;
    }

    public static boolean isNull(String... params) {
        for (String param : params) {
            if (param.isEmpty() && param.length() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean matches(String input, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    // ログインIDチェック メソッド(4<=x)
    public boolean isUserId(String userId) {
        String regexPattern = "[^.]{4,}";
        return matches(userId, regexPattern);
    }

    // PASSWORDチェック メソッド((6<= x < 30 )&半角英数);
    public boolean isPass(String pass) {
        String regexPattern = "[A-Za-z0-9]{6,30}";
        return matches(pass, regexPattern);
    }

    // 年齢のチェック メソッド(2桁の整数)
    public boolean isAge(String ageStr) {
        String regexPattern = "[0-9]{1,2}";
        return matches(ageStr, regexPattern);
    }

    // メールチェック メソッド(2文字以上 & [@][.] )
    public boolean isMail(String email) {
        String regexPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return matches(email, regexPattern);
    }
}


/*

import java.util.HashMap;
import java.util.Map;

public class ParameterChecker {
    private static final Map<String, String> errorMessages = new HashMap<>();

    static {
        errorMessages.put("userId", "ログインIDは５文字以上にしてください");
        errorMessages.put("pass", "パスワードは６文字以上にしてください");
        errorMessages.put("age", "年齢は2桁の整数で入力してください");
        errorMessages.put("email", "メールアドレスの値が正しくありません");
    }

    public String formatCheck(String userId, String pass, String rePass, String age, String email, String remail) {
        StringBuilder errorMsg = new StringBuilder();

        for (Map.Entry<String, String> entry : errorMessages.entrySet()) {
            String field = entry.getKey();
            String message = entry.getValue();

            if (!isValid(field, userId, pass, age, email) && !isNull(userId, pass, rePass, age, email, remail)) {
                errorMsg.append(message).append(StaticTools.LINE_SEPARATOR);
            }
        }

        if (!(pass.equals(rePass) && email.equals(remail)) && errorMsg.length() == 0) {
            errorMsg.append("再入力のデータが間違っています！");
        }

        return errorMsg.toString();
    }

    private boolean isValid(String field, String userId, String pass, String age, String email) {
        switch (field) {
            case "userId":
                return isUserId(userId);
            case "pass":
                return isPass(pass);
            case "age":
                return isAge(age);
            case "email":
                return isMail(email);
            default:
                return false;
        }
    }

    private boolean isNull(String... params) {
        for (String param : params) {
            if (param.length() == 0) {
                return true;
            }
        }
        return false;
    }

}



 */
