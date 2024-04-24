package iloveyouboss.utils;

import java.util.List;
import java.util.stream.Stream;

public class StringUtils {
   public static List<String> fromCSV(String csv) {
      return Stream.of(csv.split(",")).toList();
   }

   public static String toCSV(List<String> answerOptions) {
      return String.join(",", answerOptions);
   }
}
