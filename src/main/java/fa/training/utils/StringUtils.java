package fa.training.utils;

public class StringUtils {
    public static String getDisplayName(String fieldName){
        StringBuilder result = new StringBuilder();
        result.append(fieldName.substring(0, 1).toUpperCase());
        for(int i = 1 ; i < fieldName.length(); i++){
            if(fieldName.charAt(i) >= 'A' && fieldName.charAt(i) <= 'Z'){
                result.append(" ");
            }
            result.append(fieldName.charAt(i));
        }
        return result.toString();
    }
}
